package com.bookstore;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    private static BookStore mybookstore;
    
    public static void main( String[] args ) throws FileNotFoundException, ClassNotFoundException, IOException {
        mybookstore = new BookStore();
        bookStoreMenu();
    }

    public static void bookStoreMenu() throws FileNotFoundException, IOException {
        Scanner stdin = new Scanner(System.in);
        stdin.useLocale(Locale.US);
        int selection = -1;
        boolean quit = false;
        do {
            System.out.println("\nWelcome to the bookstore!");
            System.out.println("What do you want to do now?");
            System.out.println("0: Check how many books are in stock");
            System.out.println("1: Show book in stock");
            System.out.println("2: Add a new book");
            System.out.println("3: Remove a book");
            System.out.println("4: Search a book by author");
            System.out.println("5: Search book by title");
            System.out.println("6: Quit");
            System.out.println();
            try {
                selection = Integer.parseInt(stdin.nextLine());
            } catch(Exception e){
                selection = -1;
                System.out.println("Invalid selection");
            }
        } while (selection < 0 || selection > 13);

        switch(selection) {
            case 0:
                System.out.println(ANSI_GREEN + "Books in stock: " + mybookstore.getBooks().size() + ANSI_RESET);
                break;
            case 1:
                System.out.println(ANSI_GREEN + "BOOKS IN STOCK:" + ANSI_RESET);
                mybookstore.printBooks();
                break;
            case 2:
                addBookByPrompt(stdin);
                System.out.println(ANSI_GREEN + "BOOKS NOW IN STOCK:" + ANSI_RESET);
                mybookstore.printBooks();
                break;
            case 3:
                mybookstore.printBooks();
                System.out.println("Which book would you like to remove?");
                int bookselection = -1;
                do {
                System.out.println("Insert book isbn:");
                System.out.println();
                try {
                    bookselection = Integer.parseInt(stdin.nextLine());
                } catch(Exception e){
                    bookselection = -1;
                    System.out.println("Invalid selection");
                }
                }while( bookselection<0  || bookselection> mybookstore.getBooks().size() );
                mybookstore.removeBookByPrompt(bookselection);
                System.out.println(ANSI_GREEN + "REMOVED BOOK" + ANSI_RESET);
                System.out.println(ANSI_GREEN + "BOOK AFTER REMOVE OF BOOK:" + ANSI_RESET);
                mybookstore.printBooks();
                break;
            case 4:
                System.out.println("This are the authors of books in stock:");
                System.out.println(mybookstore.getAuthors());
                System.out.println("Insert the author ID to search for:");
                Long id = stdin.nextLong();
                mybookstore.searchBooksByAuthor(id);
                break; 
            case 5:
                System.out.print("Title to search for: ");
                System.out.println(mybookstore.searchBooksByTitle(stdin.nextLine()));
                break;
            case 6:
                System.out.println("Are you sure you want to quit?");
                System.out.println("All data will be lost.");
                System.out.print("Quit? (Y/n) ");
                if (stdin.nextLine().equalsIgnoreCase("y")) {
                    quit = true;
                    quitAndSave();
                }
                break;
        }
        if(!quit){
            bookStoreMenu();
        } else {
            System.out.println("Bye bye!");
            stdin.close();
        }
    }

    public static void quitAndSave() throws FileNotFoundException, IOException{
        try (OutputStream os = new BufferedOutputStream(
                                    new FileOutputStream(
                                    new File("C:/Users/mdro/Downloads/github/bookstore/bookstore/src/main/resources/books.csv"))))
        {
            mybookstore.writeBooks(os);
            os.close();
        }
    }

    public static void addBookByPrompt(Scanner scan){
        System.out.print("Do you want to add a book? (y/n) ");
        String again = scan.nextLine();
        while ("y".equals(again)) {
            System.out.print(ANSI_PURPLE + "ID: " + ANSI_RESET);
            long id = scan.nextLong();
            scan.nextLine();
            System.out.print(ANSI_PURPLE + "TITLE: " + ANSI_RESET);
            String title = scan.nextLine();
            Book.Builder builder = new Book.Builder(id, title);
            System.out.print("Add an author? (y/n) ");
            String again2 = scan.nextLine();
            while ("y".equals(again2)) {
                builder.author(askAuthor(scan));
                System.out.print("Add another author? (y/n) ");
                again2 = scan.nextLine();
            }
            System.out.print(ANSI_PURPLE + "PRICE: " + ANSI_RESET);
            builder.price(scan.nextBigDecimal());
            scan.nextLine();
            System.out.print("Add a Publisher? (y/n) ");
            again2 = scan.nextLine();
            if ("y".equals(again2)) { builder.publisher(askPublisher(scan)); }
            System.out.print("Add a category? (y/n) ");
            again2 = scan.nextLine();
            while ("y".equals(again2)) {
                builder.category(askCategory(scan));
                System.out.print("Add another category? (y/n) ");
                again2 = scan.nextLine();
            }
            Book b = builder.build();
            mybookstore.addBook(b);
            System.out.println(b);
            System.out.print("Add another book? (y/n) ");
            again = scan.nextLine();
        }
        System.out.println("Bye bye!");
    }

    private static Author askAuthor(Scanner scan) {
        System.out.println(ANSI_PURPLE + "AUTHOR: " + ANSI_RESET);
        System.out.print("\tID: ");
        long id = scan.nextLong();
        scan.nextLine();
        System.out.print("\tFIRST NAME: ");
        String firstName = scan.nextLine();
        System.out.print("\tLAST NAME: ");
        String lastName = scan.nextLine();
        return new Author(id, firstName, lastName);
    }

    private static Publisher askPublisher(Scanner scan) {
        System.out.println(ANSI_PURPLE+ "PUBLISHER: " + ANSI_RESET);
        System.out.print("\tID: ");
        long id = scan.nextLong();
        scan.nextLine();
        System.out.print("\tNAME: ");
        String name = scan.nextLine();
        return new Publisher(id, name);
    }

    private static BookCategory askCategory(Scanner scan){
        int selection = -1;
        boolean quit = false;
        do {
            System.out.println("\nThese are the avaiable categories");
            System.out.println("Choose one to add" + ANSI_PURPLE + "\nCATEGORIES:" + ANSI_RESET);
            System.out.println("0: Fantasy");
            System.out.println("1: Sci-Fi");
            System.out.println("2: Action");
            System.out.println("3: Adventure");
            System.out.println("4: Poetry");
            System.out.println("5: Historical");
            System.out.println("6: Quit");
            System.out.println();
            try {
                selection = Integer.parseInt(scan.nextLine());
            } catch(Exception e){
                selection = -1;
                System.out.println("Invalid selection");
            }
        } while (selection < 0 || selection > 6);
        
        BookCategory category = null;
        switch(selection) {
            case 0:
                category = BookCategory.FANTASY;
                break;
            case 1:
                category = BookCategory.SCIFI;
                break;
            case 2:
                category = BookCategory.ACTION;
                break;
            case 3:
                category = BookCategory.ADVENTURE;
                break;
            case 4:
                category = BookCategory.POETRY;
                break; 
            case 5:
                category = BookCategory.HISTORICAL;
                break;
            case 6:
                quit=true;
                break;
        }
        if(!quit){
            return category;
        } else {
            return BookCategory.OTHER;
        }
    }
}
