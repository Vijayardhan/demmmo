package com.lms;

import java.util.List;

public class MainUtil {

    public static void main(String[] args) throws Exception {

        // Create books
        Book obj1 = new Book("A-z1", "Java", "james", 100, 4, "prog");
        Book obj2 = new Book("abp", "Python", "kames", 32, 5, "it");

        // Create library
        Library lb = new Library();
        lb.setLname("Techademy");
        lb.setAddress("Bangalore");

        lb.addBook(obj1);
        lb.addBook(obj2);

        // Serialization
        SerializationHandler sh = new SerializationHandler();
        sh.serializeLibrary(lb, "library.txt");

        Library dt1 = sh.deserializeLibrary("library.txt");

        // Display books
        System.out.println("-------------------------------------------------");
        System.out.println("ISBN\tTitle\t\tAuthor\tPrice\tAvailable");
        System.out.println("-------------------------------------------------");

        for (int i = 0; i < dt1.displayAvailableBooks().size(); i++) {
            Book dt = dt1.displayAvailableBooks().get(i);
            System.out.println(
                    dt.getISBN() + "\t" +
                    dt.getTitle() + "\t\t" +
                    dt.getAuthor() + "\t" +
                    dt.getPrice() + "\t" +
                    dt.getAvailability()
            );
        }

        // Search by author
        String author = "james";
        List<Book> sa = lb.searchByAuthor(author);

        if (sa.size() <= 0) {
            throw new BookNotFoundException("The book is not available");
        } else {
            System.out.println("\nBooks by author " + author + ": " + sa);
        }

        // Search by genre
        String genre = "it";
        List<Book> lg = lb.searchByGenre(genre);

        if (lg.size() <= 0) {
            throw new BookNotFoundException("The book is not available");
        } else {
            System.out.println("\nBooks in genre " + genre + ": " + lg);
        }

        // Remove book by ISBN
        String isbn = "hhh";
        lb.removeBook(isbn);

        // Borrow book
        Member mb = new Member();
        mb.setID(11);
        mb.setName("Hero");
        mb.setQnty(7);

        String bisbn = "abp";

        for (int i = 0; i < lb.displayAvailableBooks().size(); i++) {
            Book bk = lb.displayAvailableBooks().get(i);

            if (bisbn.equals(bk.getISBN())) {
                if (mb.getQnty() <= bk.getAvailability()) {
                    mb.borrowBook(bisbn);
                } else {
                    throw new NotEnoughBooksException(bisbn + " not enough books");
                }
            }
        }

        // Display borrowed books
        System.out.println("\nBorrowed Books:");
        System.out.println(mb.displayBorrowedBooks());

        // Return book
        mb.returnBook(bisbn);
    }
}