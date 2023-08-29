import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CartForm")
public class CartForm extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Cart Form</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; text-align: center; margin: 0; padding: 0; }");
        out.println("form { max-width: 400px; margin: 0 auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); }");
        out.println("label { display: block; margin-bottom: 10px; font-weight: bold; }");
        out.println("input[type='text'], input[type='number'] { width: 100%; padding: 10px; margin-bottom: 20px; border: 1px solid #ccc; border-radius: 4px; }");
        out.println("input[type='submit'] { background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; }");
        out.println("input[type='submit']:hover { background-color: #0056b3; }");
        out.println(".back-button {");
        out.println("  display: block;");
        out.println("  width: 100px;");
        out.println("  margin: 20px auto;");
        out.println("  padding: 10px 15px;");
        out.println("  background-color: #007bff;");
        out.println("  color: #fff;");
        out.println("  border-radius: 4px;");
        out.println("  text-decoration: none;");
        out.println("  float: left;"); // Add this CSS property to move the button to the left
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<a href='index.html' class='back-button'>Back</a>");

        out.println("<h1>Cart Form</h1>");

        out.println("<form action='CartInsert' method='GET'>");
        out.println("<label for='name'>Name:</label>");
        out.println("<input type='text' id='name' name='name' required><br><br>");

        out.println("<label for='brand'>Brand:</label>");
        out.println("<input type='text' id='brand' name='brand' required><br><br>");

        out.println("<label for='quantity'>Quantity:</label>");
        out.println("<input type='number' id='quantity' name='quantity' required><br><br>");

        out.println("<label for='rate'>Rate:</label>");
        out.println("<input type='number' id='rate' name='rate' required><br><br>");

        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");


    }
}
