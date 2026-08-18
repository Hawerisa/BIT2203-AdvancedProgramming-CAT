<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Book" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System - Book List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        h2 { color: #2c3e50; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
    </style>
</head>
<body>

    <h2>Library Book Catalog</h2>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>ISBN</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Book> bookList = (List<Book>) request.getAttribute("bookList");
                if (bookList != null && !bookList.isEmpty()) {
                    for (Book book : bookList) {
            %>
            <tr>
                <td><%= book.getId() %></td>
                <td><%= book.getTitle() %></td>
                <td><%= book.getAuthor() %></td>
                <td><%= book.getIsbn() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="4">No books available in the catalog.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

</body>
</html>