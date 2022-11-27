package com.bookstore;

import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public Author(){}

    public Author(long id, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public long getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public void setId(long id) {this.id = id;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    @Override
    public String toString(){ return this.getLastName() + " " + this.getFirstName() + " ID: " + this.getId();}

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof Author) {
            return this.getId() == ((Author)o).getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    
    
}