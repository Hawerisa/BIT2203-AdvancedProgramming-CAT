package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * BookController.java
 * Servlet acting as the Controller to handle requests for viewing all books.
 */
@WebServlet("/books")
public class BookController extends HttpServlet {

    private BookDAO bookDAO;

    @Override
    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch data from Model
        List<Book> bookList = bookDAO.getAllBooks();

        // Attach data to request scope
        request.setAttribute("bookList", bookList);

        // Forward request to View (bookList.jsp)
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookList.jsp");
        dispatcher.forward(request, response);
    }
}