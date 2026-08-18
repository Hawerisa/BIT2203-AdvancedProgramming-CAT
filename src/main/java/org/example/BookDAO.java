package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * BookDAO.java
 * Data Access Object handling data retrieval for books.
 */
public class BookDAO {

    /**
     * Retrieves all books from the database (mock data used for demonstration).
     */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Java: The Complete Reference", "Herbert Schildt", "978-1260440232"));
        books.add(new Book(2, "Clean Code", "Robert C. Martin", "978-0132350884"));
        books.add(new Book(3, "Design Patterns", "Erich Gamma et al.", "978-0201633610"));
        books.add(new Book(4, "Spring in Action", "Craig Walls", "978-1617294945"));
        return books;
    }
}