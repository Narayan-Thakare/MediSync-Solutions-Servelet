import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowBill")
public class ShowBill extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Medicine Inventory</title>");
        out.println("<style>");
        out.println("body {");
        out.println("  font-family: 'Arial', sans-serif;");
        out.println("  background-image: url('your-background-image.jpg');"); // Replace with your image path
        out.println("  background-size: cover;");
        out.println("  background-repeat: no-repeat;");
        out.println("  margin: 0;");
        out.println("}");
        out.println("header { background-color: #007bff; color: #fff; padding: 10px 0; text-align: center; }");
        out.println("h1 { margin: 20px 0; text-align: center; }");
        out.println("table { border-collapse: collapse; width: 90%; margin: auto; background-color: white; }");
        out.println("th, td { border: 1px solid #ddd; padding: 12px 15px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("a { text-decoration: none; color: #007bff; }");
        out.println("a:hover { text-decoration: underline; }");        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>Medicine Inventory</h1>");
        out.println("</header>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        double total = 0.0; // Initialize the total variable

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "abc123");

            
           
            
            
            
            
            
            
            
            
            
            
            
            ResultSet rs = null;
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM medicine.bill");
            rs = stmt.executeQuery();

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Medicine Name</th>");
            out.println("<th>Brand</th>");
            out.println("<th>Quantity</th>");
            out.println("<th>Rate</th>");
            out.println("<th>Total</th>");
            out.println("</tr>");

            while (rs.next()) {
                int id = rs.getInt(1);
                double itemTotal = rs.getDouble(6); // Get the "Total" value from the ResultSet

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getString(3) + "</td>");
                out.println("<td>" + rs.getString(4) + "</td>");
                out.println("<td>" + rs.getString(5) + "</td>");
                out.println("<td>" + itemTotal + "</td>");
                out.println("</tr>");

                total += itemTotal; // Update the total
            }

            out.println("<tr>");
            out.println("<td colspan=\"5\">Total</td>");
            out.println("<td>" + total + "</td>");
            out.println("</tr>");

            out.println("</table>");
            
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

        LocalDateTime currentTime = LocalDateTime.now();
        
        // Format the current time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        // Display the current time on the page
     //   out.println("<p>Current Time: " + formattedTime + "</p>");

        
        
        
        out.println("<form action=\"CostumerHistory\" method=\"GET\">");
        out.println("<h1>Billing</h1>");

        out.println("<label for=\"name\">Costmer Name</label>");
        out.println("<input type=\"text\" id=\"name\" name=\"name\" required><br>");

        out.println("<label for=\"brand\">Dr Name</label>");
        out.println("<input type=\"text\" id=\"brand\" name=\"drname\" required><br>");

        out.println("<label>Total</label>");
        out.println("<input type='text' name='total' value='" + total + "'><br>");
        
        out.println("<label></label>");
        out.println("<input type='hidden' name='time' value='" + formattedTime + "'><br>");
        
        out.println("<input type=\"submit\" value=\"Pay\">");
        out.println("</form>");
        
        
        
        out.println("</body>");
        out.println("</html>");

//        RequestDispatcher rd = request.getRequestDispatcher("/index.html");
//        rd.include(request, response);
    }
}
