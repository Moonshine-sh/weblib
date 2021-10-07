package com.WebLib.domain;

import javax.persistence.*;


@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="name")
    private String name;

    @Column(name="author")
    private String author;

    @Column(name="year_of_publ")
    private int yearOfPubl;

    @Column(name="genreID")
    private int genreId;

    @Column(name="stock")
    private int stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPubl() {
        return yearOfPubl;
    }

    public void setYearOfPubl(int yearOfPubl) {
        this.yearOfPubl = yearOfPubl;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Book(){

    }

    public Book(String name, String author, int yearOfPubl, int genreId, int stock){
        this.name=name;
        this.author=author;
        this.yearOfPubl=yearOfPubl;
        this.genreId=genreId;
        this.stock=stock;
    }
}
