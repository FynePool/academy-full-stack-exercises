package com.riccardorocco.SakilaDB;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class SakilaDB {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    private static Scanner stdinScanner = new Scanner(System.in);

    public static void main(String[] args) {
        printRentedFilmsByStoreAndYear();
        insertFantozzi();
    }

    private static void insertFantozzi(){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","citterio")){
            String query = "INSERT INTO film(title, language_id, length) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,"Fantozzi");
            stmt.setInt(2, 2);
            stmt.setInt(3,120);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                int id = rs.getInt(1);
                System.out.println("\nGenerated id %d".formatted(id));
            }
        } catch(SQLException e) { e.printStackTrace(); }
    }

    /**
     * Prints rented films from sakilaDB based on store_id and release_year
     */
    private static void printRentedFilmsByStoreAndYear(){
        Connection conn = null;
        Statement stmt = null, stmt2 = null;
        ResultSet rs = null, rs2 = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "citterio");
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            rs = stmt.executeQuery(storeAndYearSelector());
            String statementTypeSelection = readFromInput("Do you want to use [0: Statement] or [1: Prepared Statement]?", 0, 1);
            int count = 0;
            while (rs.next()) {
                int id = rs.getInt("film_id");
                String title = rs.getString("title");
                int length = rs.getInt("length");
                int year = rs.getInt("release_year");
                if (statementTypeSelection==null || Integer.parseInt(statementTypeSelection)==0){ 
                    rs2 = stmt2.executeQuery(actorsFromFilmID(id)); 
                } else { 
                    rs2 = actorsFromFilmIDPrepared(id).executeQuery(); 
                }
                List<String> actors = new ArrayList<>();
                while (rs2.next()) {
                    actors.add(String.format("%s %s", rs2.getString(1), rs2.getString(2)));
                }
                System.out.printf("[ " + ANSI_PURPLE + "TITLE:" + ANSI_RESET + " %s | " + 
                                         ANSI_PURPLE + "LENGTH:"+ ANSI_RESET + " %d | " + 
                                         ANSI_PURPLE + "YEAR:" + ANSI_RESET + " %d | " + 
                                         ANSI_PURPLE + "ACTORS:" + ANSI_RESET + " (%s) ]%n", title, length, year, actors.stream().collect(Collectors.joining(", ")));
                count++;
            }
            System.out.printf(ANSI_GREEN + "ROWS RETURNED: %d" + ANSI_RESET, count);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} }
            if (stmt != null) { try { stmt.close(); } catch (SQLException e) {} }
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} }
        }
    }

    /**
     * Build a query to retrieve all movies released in a certain year and rented in a certain store
     * @return The query string
     */
    private static String storeAndYearSelector(){
        int store_id = Integer.parseInt(readFromInput("Insert store id: ", -1, -1));
        int year = Integer.parseInt(readFromInput("Insert release year: ", -1, -1));

        return "SELECT DISTINCT f.film_id, f.title, f.release_year, f.length " + 
                "FROM film as f JOIN inventory as i ON i.film_id = f.film_id " +
                                "JOIN rental as r ON r.inventory_id = i.inventory_id " +
                                "JOIN payment as p ON p.rental_id = r.rental_id " +
                                "JOIN staff as st on st.staff_id = p.staff_id " +
                                "JOIN store as sto on sto.store_id = st.store_id " +
                "WHERE sto.store_id = " + store_id + " AND f.release_year = " + "'" + year + "' ;";
    }

    /**
     * Build a query that select the actors who did the movie identified by parameter
     * @param id Film ID
     * @return A string query
     */
    private static String actorsFromFilmID(int id){
        return "SELECT a.first_name, a.last_name " +  
               "FROM actor a JOIN film_actor fa ON (fa.actor_id = a.actor_id) " + 
               "WHERE fa.film_id = %d;".formatted(id);
    }

    /**
     * Similar method to actorsFromFilmID but used with prepared statements
     * @return 
     * @throws SQLException
     */
    private static PreparedStatement actorsFromFilmIDPrepared(int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "citterio");
        PreparedStatement pstmt = connection.prepareStatement("SELECT a.first_name, a.last_name " +  
                                                              "FROM actor a JOIN film_actor fa ON (fa.actor_id = a.actor_id) " + 
                                                              "WHERE fa.film_id = ?;");
        pstmt.setInt(1, id);
        return pstmt;
    }

    /**
     * Read from stdin
     * @param message String to print (the request)
     * @param leftBound Left bound of selection (-1 if you need to scan a String and not an integer)
     * @param rightBound Right bound of selection (-1 if you need to scan a String and not an integer)
     * @return The string of next line
     */
    private static String readFromInput(String message, int leftBound, int rightBound){
        String string = null;
        stdinScanner.useLocale(Locale.US);
        if (rightBound==-1 && leftBound==-1){
            System.out.println(message);
            string = stdinScanner.nextLine();
            return string;
        }
        if (leftBound!=-1 && rightBound!=-1) {
            do {
                System.out.println(message);
                try {
                string = stdinScanner.nextLine();
                } catch(Exception e ){
                    string = null;
                    System.out.println("Invalid selection");
                }
            } while (string!=null && (Integer.parseInt(string) < leftBound || Integer.parseInt(string) > rightBound));
        }
        return string;
    }
}
