package com.lms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Book> books;
    private String lname;
    private String address;

    // Constructor (optional but recommended)
    public Library() {
        books = new ArrayList<>();
    }

    // Getter and Setter for lname
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    // Getter and Setter for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Add Book
    public void addBook(Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
    }

    // Remove Book by ISBN
    public void removeBook(String ISBN) {
        for (Book bk : books) {
            if (bk.getISBN().equals(ISBN)) {
                books.remove(bk);
                System.out.println("Successfully deleted one book.");
                break;
            }
        }
    }

    // Search books by Author
    public List<Book> searchByAuthor(String author) {
        List<Book> ba = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getAuthor().equals(author)) {
                ba.add(b);
            }
        }
        return ba;
    }

    // Search books by Genre
    public List<Book> searchByGenre(String genre) {
        List<Book> ba = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getGenre().equals(genre)) {
                ba.add(b);
            }
        }
        return ba;
    }

    // Display all available books
    public List<Book> displayAvailableBooks() {
        return books;
    }
}