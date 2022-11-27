package com.bookstore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private long isbn;
    private String title;
    private BigDecimal price;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> author;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;
    @Transient
    private ArrayList<BookCategory> categories;
    @Temporal(TemporalType.DATE)
    private Date year;
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public Book(long isbn, String title, BigDecimal price, List<Author> author, Publisher publisher, ArrayList<BookCategory> categories, Date year) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        for (Author a : author) this.author.add(a);
        this.publisher = publisher;
        this.categories=categories;
        this.year = year;
    }

    public Book(long isbn, String title, Author author, BigDecimal price, Date year){
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        if (author!=null) { this.author.add(author);}
        this.year = year;
    }

    private Book(Builder builder) {
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.price = builder.price;
        this.publisher = builder.publisher;
        this.author = builder.author;
        this.categories = builder.categories;
    }

    public long getIsbn(){return this.isbn;}
    public void setIsbn(long isbn){this.isbn = isbn;}
    
    public String getTitle(){return this.title;}
    public void setTitle(String title){this.title = title;}
    
    public BigDecimal getPrice(){if(this.price != null) return this.price; return new BigDecimal(0);}
    public void setPrice(BigDecimal price){this.price = price;}
    
    public List<Author> getAuthors(){
        if(this.author.size()!=0) return this.author; 
        ArrayList<Author> noauthors = new ArrayList<Author>() {{ add(new Author(0, "AUTHOR", "NO")); }};
        return noauthors;}
    public void setAuthor(Author author){this.author.add(author);}
    
    public Publisher getPublisher(){ 
        if(this.publisher != null) return this.publisher; 
        return new Publisher(0, "NO PUBLISHER");}
    public void setPublisher(Publisher publisher) {this.publisher=publisher;}

    public ArrayList<BookCategory> getCategories(){ return this.categories;}
    public void addCategory(String category){
        switch(category){
            case "OTHER":
                if (!this.categories.contains(BookCategory.OTHER))
                this.categories.add(BookCategory.OTHER);
                break;
            case "ACTION":
                if (!this.categories.contains(BookCategory.ACTION))
                this.categories.add(BookCategory.ACTION);
                break;
            case "SCIFI":
                if (!this.categories.contains(BookCategory.SCIFI))
                this.categories.add(BookCategory.SCIFI);
                break;
            case "ADVENTURE":
                if (!this.categories.contains(BookCategory.ADVENTURE))
                this.categories.add(BookCategory.ADVENTURE);
                break;
            case "FANTASY":
                if (!this.categories.contains(BookCategory.FANTASY))
                this.categories.add(BookCategory.FANTASY);
                break;
            case "POETRY":
                if (!this.categories.contains(BookCategory.POETRY))
                this.categories.add(BookCategory.POETRY);
                break;
            case "HISTORICAL":
                if (!this.categories.contains(BookCategory.HISTORICAL))
                this.categories.add(BookCategory.HISTORICAL);
                break;
        }
    }

    public void addAuthor(Author add){
        this.author.add(add);
    }


    public boolean hasThisAuthor(Long id){
        for (Author a : this.getAuthors()){
            return a.getId() == id;
        }
        return false;
    }
    @Override
    public String toString(){
        return ANSI_PURPLE + "\nBOOK DETAILS:\n" + "Title: " + ANSI_RESET + this.getTitle() + 
            "\n" + ANSI_PURPLE + "Author: " + ANSI_RESET + this.getAuthors().toString() + 
            "\n" + ANSI_PURPLE + "Publisher: " + ANSI_RESET + this.getPublisher().getName() + 
            "\n" + ANSI_PURPLE + "Category: " + ANSI_RESET  + this.getCategories() + 
            "\n" + ANSI_PURPLE + "Price: " + ANSI_RESET + this.getPrice().setScale(2, RoundingMode.FLOOR) + 
            "\n" + ANSI_PURPLE + "ISBN: " + ANSI_RESET + this.getIsbn() + "\n";
    }

    public static class Builder {
        private long isbn;
        private String title;
        private ArrayList<Author> author = new ArrayList<>();
        private Publisher publisher;
        private BigDecimal price;
        private ArrayList<BookCategory> categories = new ArrayList<>();

        public Builder(long isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public Builder author(Author author) {
            this.author.add(author);
            return this;
        }

        public Builder authors(Author[] authors) {
            for (Author a : authors){
                this.author.add(a);
            }
            
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder publisher(Publisher publisher){
            this.publisher = publisher;
            return this;
        }

        public Builder category(BookCategory category){
            if (category==null) {this.categories.add(BookCategory.OTHER); return this;}
            this.categories.add(category);
            return this;
        }

        public Builder categories(BookCategory[] categories){
            for (BookCategory category : categories){
                if (category==null) {this.categories.add(BookCategory.OTHER);}
                else { this.categories.add(category); }
            }
            return this;
        }

        public Book build(){
            return new Book(this);
        }
    }
}
