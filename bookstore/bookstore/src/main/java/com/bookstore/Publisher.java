package com.bookstore;
import javax.persistence.*;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "publisher_id")
    private long id;
    @Column(name = "name")
    private String name;

    public Publisher(){}

    public Publisher(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
