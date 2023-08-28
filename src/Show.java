import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Show")
public class Show extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
        out.println("a:hover { text-decoration: underline; }");
        out.println(".back-button {");
        out.println("  background-color: #007bff;");
        out.println("  color: #fff;");
        out.println("  padding: 10px 15px;");
        out.println("  text-align: center;");
        out.println("  display: inline-block;");
        out.println("  margin-top: 10px;");
        out.println("  border-radius: 5px;");
        out.println("  text-decoration: none;");
        out.println("}");
        out.println("</style>");
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

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "abc123");

            ResultSet rs = null;
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM medicine.med");
            rs = stmt.executeQuery();

            out.println("<a href='index.html' class='back-button'>Back</a>");

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Medicine Name</th>");
            out.println("<th>Brand</th>");
            out.println("<th>Quantity</th>");
            out.println("<th>Rate</th>");
            out.println("<th>Total</th>");
            out.println("<th>Add Medicine</th>");
            out.println("<th>Sell Medicine</th>");
            out.println("<th>Edit</th>");
            out.println("<th>Delete</th>");
            out.println("</tr>");

            while (rs.next()) {
                int id = rs.getInt(1);

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getString(3) + "</td>");
                out.println("<td>" + rs.getString(4) + "</td>");
                out.println("<td>" + rs.getString(5) + "</td>");
                out.println("<td>" + rs.getString(6) + "</td>");
                out.println("<td><a href='ShowAddMedicine?id=" + rs.getString("id") + "'>Add Medicine</a></td>");
                out.println("<td><a href='ShowSellMedicine?id=" + rs.getString("id") + "'>Sell medicine</a></td>");
                out.println("<td><a href='ShowEdit?id=" + rs.getString("id") + "'>Edit</a></td>");
                out.println("<td><a href='DeleteRecord?id=" + rs.getString("id") + "'>Delete</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</body>");
        out.println("</html>");

        
//        RequestDispatcher rd = request.getRequestDispatcher("/index.html");
//        rd.include(request, response);
    }
}
