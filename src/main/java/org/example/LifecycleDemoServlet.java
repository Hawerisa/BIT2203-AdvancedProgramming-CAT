package org.example;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * LifecycleDemoServlet.java
 * Demonstrates all stages of the Java Servlet Lifecycle with log messages.
 */
@WebServlet("/lifecycle-demo")
public class LifecycleDemoServlet extends HttpServlet {

    // 1. Instantiation (Constructor)
    public LifecycleDemoServlet() {
        super();
        System.out.println("--------------------------------------------------");
        System.out.println("[STAGE 1: INSTANTIATION] - Servlet Constructor Called.");
        System.out.println("--------------------------------------------------");
    }

    // 2. Initialization Phase
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("[STAGE 2: INITIALIZATION] - init() method executed once.");
    }

    // 3. Service Phase (Handling HTTP Requests)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[STAGE 3: REQUEST HANDLING] - doGet() handling incoming client request.");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Servlet Lifecycle Demo Successful!</h2>");
        out.println("<p>Check the server console output to see the lifecycle logs.</p>");
        out.println("</body></html>");
    }

    // 4. Destruction Phase
    @Override
    public void destroy() {
        System.out.println("--------------------------------------------------");
        System.out.println("[STAGE 4: DESTRUCTION] - destroy() called for cleanup.");
        System.out.println("--------------------------------------------------");
        super.destroy();
    }
}