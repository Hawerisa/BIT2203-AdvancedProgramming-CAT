import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ShoppingCartServlet.java
 * Demonstrates proper session management using HttpSession for an e-commerce cart.
 *
 * @author Nyevu Chea (Reg: SCT221-0595/2024)
 */
@WebServlet("/cart")
public class ShoppingCartServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Handles POST requests to add items to the shopping cart.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Obtain active session or create new one
        HttpSession session = request.getSession(true);

        // Fetch existing cart from session or create new list
        @SuppressWarnings("unchecked")
        List<String> cart = (List<String>) session.getAttribute("shoppingCart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Add requested item to cart
        String item = request.getParameter("item");
        if (item != null && !item.trim().isEmpty()) {
            cart.add(item.trim());
        }

        // Save updated cart back to HTTP Session
        session.setAttribute("shoppingCart", cart);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Shopping Cart</title></head><body>");
            out.println("<h2>Item Added to Cart Successfully!</h2>");
            out.println("<p>Added: <strong>" + (item != null ? item : "None") + "</strong></p>");
            out.println("<p><a href='cart'>View Shopping Cart</a></p>");
            out.println("</body></html>");
        }
    }

    /**
     * Handles GET requests to retrieve and render shopping cart contents.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Retrieve existing session without creating a new one
        HttpSession session = request.getSession(false);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Your Shopping Cart</title></head><body>");
            out.println("<h2>Your Shopping Cart Contents</h2>");

            if (session != null && session.getAttribute("shoppingCart") != null) {
                @SuppressWarnings("unchecked")
                List<String> cart = (List<String>) session.getAttribute("shoppingCart");

                if (cart.isEmpty()) {
                    out.println("<p>Your shopping cart is currently empty.</p>");
                } else {
                    out.println("<ul>");
                    for (String cartItem : cart) {
                        out.println("<li>" + cartItem + "</li>");
                    }
                    out.println("</ul>");
                    out.println("<p><strong>Total Items:</strong> " + cart.size() + "</p>");
                }
                out.println("<br><p><strong>Session ID:</strong> " + session.getId() + "</p>");
            } else {
                out.println("<p>Your shopping cart is empty (No active session found).</p>");
            }

            out.println("<br><a href='" + response.encodeURL("cart") + "'>Refresh Cart</a>");
            out.println("</body></html>");
        }
    }
}