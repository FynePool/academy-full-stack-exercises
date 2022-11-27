package com.bookstore;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookStore {

    private List<Book> books;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public BookStore(Boolean test){
        this.books = new ArrayList<>();
    }

    public BookStore() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.books = new ArrayList<>();
        try (InputStream is = new BufferedInputStream(
                                    new FileInputStream(
                                    new File("C:/Users/mdro/Downloads/github/bookstore/bookstore/src/main/resources/books.csv"))))
        {
            readBooks(is);
            is.close();
        
        }
    }

    /**
     *           Reads CSV file containing books info to be stored. Books are in the format:
     *           ISBN,TITLE,AUTH1.ID;AUTH1.FN;AUTH1.LN[|AUTH2|AUTH3..],PUB.ID;PUB.NAME,PRICE,CATG1[|CATG2|CATG3]\n
     * @param is InputStream to read file books.csv
     * @throws IOException
     */
    private void readBooks(InputStream is) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int c; String fullStr="";
        while( (c=br.read())!=-1 ){
            if ((char) c == '\n'){
                String[] parts = fullStr.split(",");
                String[] authors = parts[2].split("\\|");
                String[] publisher = parts[3].split(";");
                String[] categories = parts[5].split("\\|");
                Publisher p;
                if (publisher.length<2) { 
                    p = null; 
                } else { 
                    p = new Publisher(Long.parseLong(publisher[0]), publisher[1]);
                }
                BigDecimal pr;
                if ( parts[4] == "" ) { pr = null; } else { pr = new BigDecimal(parts[4]); }
                
                Book b = new Book.Builder(Integer.parseInt(parts[0]), parts[1])
                                .publisher(p)
                                .price(pr)
                                .build();
                for (int i=0; i<categories.length; i++){
                    b.addCategory(categories[i]);
                }
                for (String a : authors){
                    String[] author = a.split(";");
                    b.addAuthor(new Author(Long.parseLong(author[0]), author[1], author[2]));
                }
                addBook(b);
                System.out.println(ANSI_GREEN + "[STARTUP] BOOK LOADED: " + ANSI_RESET + b.getTitle());
                fullStr = "";
            } else { fullStr+= (char) c; }
        }

    }

    /**
     *           Saves this.book on CSV file using format: 
     *           ISBN,TITLE,AUTH1.ID;AUTH1.FN;AUTH1.LN[|AUTH2|AUTH3..],PUB.ID;PUB.NAME,PRICE,CATG1[|CATG2|CATG3]\n
     * @param os OutputStream for PrintWriter
     */
    public void writeBooks(OutputStream os){
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
        for (Book b: this.books){
            pw.print(b.getIsbn()+","+b.getTitle()+",");
            for (Author a : b.getAuthors()){
                pw.print(a.getId()+";"+a.getFirstName()+";"+a.getLastName());
                if (b.getAuthors().size()>1 && b.getAuthors().indexOf(a)!=b.getAuthors().size()-1){
                    pw.print("|");
                } else { pw.print(",");}
            }
            pw.print(b.getPublisher().getId()+";"+b.getPublisher().getName()+",");
            pw.print(b.getPrice()+",");
            for (BookCategory bc : b.getCategories()){
                pw.print(bc);
                if (b.getCategories().size()>1 && b.getCategories().indexOf(bc)!=b.getCategories().size()-1){
                    pw.print("|");
                } else { pw.print("\n"); }
            } 
            System.out.println(ANSI_GREEN + "[SHUTDOWN] BOOK SAVED: " + ANSI_RESET + b.getTitle());
        }
        pw.flush();
    }

    /**
     * 
     * @return A list of all the books currently in stock.
     */
    public List<Book> getBooks() {
        return this.books;
    }

    /**
     * 
     * @return A list of all the authors from all the books currently in stock.
     */
    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        for (Book b: this.books){
            authors.addAll(b.getAuthors());
        }
        return authors;
    }

    public void addBook(Book book){
        if (!this.books.contains(book)) this.books.add(book);
    }

    public void removeBookByPrompt(int selection) {
        removeBook(this.books.get(selection-1));
    }

    public void removeBook(Book book) {
        this.books.removeAll(this.books.stream()
                                        .filter(ele -> ele.getIsbn()==book.getIsbn())
                                        .collect(Collectors.toList()));
    }

    public void printBooks(){
        for (Book b : this.books) System.out.println(b);
    }

    public List<Book> searchBooksByTitle(String title){
        return (List<Book>) this.books.stream()
                                            .filter(ele -> ele.getTitle().toLowerCase().contains(title.toLowerCase()))
                                            .collect(Collectors.toList());
    }

    public void searchBooksByAuthor(Long id){
        System.out.println(ANSI_GREEN + "BOOKS FROM AUTHOR:" + ANSI_RESET + 
            (List<Book>) this.books.stream()
                                        .filter(ele -> ele.hasThisAuthor(id))
                                        .collect(Collectors.toList()));  
    }
    
}