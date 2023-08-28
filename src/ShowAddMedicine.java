import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowAddMedicine")
public class ShowAddMedicine extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String idParam = request.getParameter("id");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Edit Record</title>");
        
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;}");
        out.println(".container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);}");
        out.println("h1 { text-align: center;}");
        out.println("form { text-align: left;}");
        out.println("label { display: block; margin-top: 10px;}");
        out.println("input[type='text'] { width: 100%; padding: 10px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 3px;}");
        out.println("input[type='submit'] { background-color: #007BFF; color: #fff; border: none; padding: 10px 20px; border-radius: 3px; cursor: pointer;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Edit Record</h1>");

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "abc123");

            // Use an SQL query to select a record by ID
            stmt = con.prepareStatement("SELECT * FROM medicine.med WHERE id = ?");
            stmt.setInt(1, Integer.parseInt(idParam));
            rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                int quantity = rs.getInt("quantity");
                double rate = rs.getDouble("rate");
                double total = quantity * rate;

                out.println("<form method='get' action='AddMedicine'>");
                out.println("<input type='hidden' name='eid' value='" + idParam + "'>");
                out.println("<label for='name'>Name:</label>");
                out.println("<input type='text' id='name' name='name' value='" + name + "'>");
                out.println("<label for='brand'>Brand:</label>");
                out.println("<input type='text' id='brand' name='brand' value='" + brand + "'>");
                out.println("<label for='quantity'>Quantity:</label>");
                out.println("<input type='text' id='quantity' name='quantity' value='" + quantity + "'>");
                out.println("<label for='newmedicine'>New Medicine:</label>");
                out.println("<input type='text' id='newmedicine' name='newmedicine'><br>");
                out.println("<input type='hidden' name='rate' value='" + rate + "'>");
                out.println("<input type='hidden' name='total' value='" + total + "'>");
                out.println("<input type='submit' value='Update'>");
                out.println("</form>");
            } else {
                out.println("<p>Record not found.</p>");
            }
        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
