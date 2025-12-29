package com.lms;

import java.util.ArrayList;
import java.util.List;

public class Member extends Library {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private List<Book> borrowedBooks;
    private int qnty;

    private Library lb;

    // Default Constructor
    public Member() {
        lb = new Library();
        borrowedBooks = new ArrayList<>();

        Book obj1 = new Book("A-z1", "Java", "James", 100, 4, "prog");
        Book obj2 = new Book("abp", "Python", "Kames", 32, 5, "it");

        lb.addBook(obj1);
        lb.addBook(obj2);
    }

    // Parameterized Constructor
    public Member(int id, String name, int qnty) {
        this.id = id;
        this.name = name;
        this.qnty = qnty;
        this.borrowedBooks = new ArrayList<>();
        this.lb = new Library();
    }

    // Getter & Setter for Quantity
    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    // Getter & Setter for ID
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    // Getter & Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Borrow Book
    public void borrowBook(String ISBN) {
        for (Book b : lb.displayAvailableBooks()) {
            if (ISBN.equals(b.getISBN())) {
                borrowedBooks.add(b);
                System.out.println("Book borrowed successfully: " + ISBN);
                break;
            }
        }
    }

    // Return Book
    public void returnBook(String ISBN) {
        for (Book b : borrowedBooks) {
            if (b.getISBN().equals(ISBN)) {
                borrowedBooks.remove(b);
                System.out.println("Successfully returned book " + ISBN);
                break;
            }
        }
    }

    // Display Borrowed Books
    public List<Book> displayBorrowedBooks() {
        return borrowedBooks;
    }
}