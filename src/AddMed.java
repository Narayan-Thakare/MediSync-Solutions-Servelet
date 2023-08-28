import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddMed")
public class AddMed extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set the content type to HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // HTML document start
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Add Medicine</title>");
        out.println("<style>");
        out.println("/* Your CSS styles here */");
        
        out.println("<style>");
        out.println("body {");
        out.println("  font-family: Arial, sans-serif;");
        out.println("  background-color: #f5f5f5;");
        out.println("  display: flex;");
        out.println("  justify-content: center;");
        out.println("  align-items: center;");
        out.println("  height: 100vh;");
        out.println("  margin: 0;");
        out.println("  padding: 0;");
        out.println("}");

        // Style for the form container
        out.println(".form-container {");
        out.println("  background-color: #ffffff;");
        out.println("  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
        out.println("  border-radius: 5px;");
        out.println("  padding: 20px;");
        out.println("  width: 1500px;"); // Adjusted width for better visibility
        out.println("}");

        out.println("h1 { text-align: center; }");
        out.println("label { font-weight: bold; }");
        out.println("input[type=\"text\"],");
        out.println("input[type=\"number\"] {");
        out.println("  width: 100%;");
        out.println("  padding: 10px;");
        out.println("  margin-bottom: 10px;");
        out.println("  border: 1px solid #ccc;");
        out.println("  border-radius: 3px;");
        out.println("}");

        // Style for the submit button with center alignment
        out.println(".center-align {");
        out.println("  display: flex;");
        out.println("  justify-content: center;");
        out.println("}");

        out.println("input[type=\"submit\"] {");
        out.println("  background-color: #007bff;");
        out.println("  color: #fff;");
        out.println("  padding: 10px 20px;");
        out.println("  border: none;");
        out.println("  border-radius: 3px;");
        out.println("  cursor: pointer;");
        out.println("}");

        out.println("input[type=\"submit\"]:hover { background-color: #0056b3; }");
        
        
        out.println(".header {");
        out.println("  font-size: 20px;");
        out.println("  font-weight: bold;");
        out.println("  text-align: center;");
        
        
        
        
        
        out.println("}");
        
        out.println("</style>");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Header with a back button
        out.println("<header class=\"header\">");
        out.println("<div class=\"container\">");
        out.println("<h1>Medicine Dashboard</h1>");
        
        // Back button
        out.println("<a href=\"javascript:history.back()\" class=\"back-button\">Back</a>");

        out.println("</div>");
        out.println("</header>");

        // Form for adding medication (your existing form)
        out.println("<div class=\"form-container\">");
        out.println("<form action=\"Insert\" method=\"GET\">");
        out.println("<h1>Add Medication</h1>");

        out.println("<label for=\"name\">Name:</label>");
        out.println("<input type=\"text\" id=\"name\" name=\"name\" required><br>");

        out.println("<label for=\"brand\">Brand:</label>");
        out.println("<input type=\"text\" id=\"brand\" name=\"brand\" required><br>");

        out.println("<label for=\"quantity\">Quantity:</label>");
        out.println("<input type=\"number\" id=\"quantity\" name=\"quantity\" required><br>");

        out.println("<label for=\"rate\">Rate:</label>");
        out.println("<input type=\"number\" id=\"rate\" name=\"rate\" required><br>");

        // Submit button
        out.println("<div class=\"center-align\">");
        out.println("<input type=\"submit\" value=\"Add Medicine\">");
        out.println("</div>");

        out.println("</form>");
        out.println("</div>");

        // HTML document end
        out.println("</body>");
        out.println("</html>");
    }
}
