package com.WebLib.domain;

import javax.persistence.*;

@Entity
@Table(name="intertables")
public class Intertable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="bookID")
    private int bookID;

    @Column(name="readerID")
    private int readerID;

    public Intertable() {
    }

    public Intertable(int bookID, int readerID) {
        this.bookID = bookID;
        this.readerID = readerID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }
}
