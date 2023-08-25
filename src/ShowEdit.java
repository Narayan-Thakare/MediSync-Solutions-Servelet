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

@WebServlet("/ShowEdit")
public class ShowEdit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String idParam = request.getParameter("id");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Edit Record</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; text-align: center; margin: 0; padding: 0; }");
        out.println("h1 { background-color: #007bff; color: #fff; padding: 10px 0; }");
        out.println("form { max-width: 400px; margin: 0 auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); }");
        out.println("label { display: block; margin-bottom: 10px; font-weight: bold; }");
        out.println("input[type='text'] { width: 100%; padding: 10px; margin-bottom: 20px; border: 1px solid #ccc; border-radius: 4px; }");
        out.println("input[type='submit'] { background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; }");
        out.println("input[type='submit']:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
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

                out.println("<form method='get' action='ShowEditUpadte'>");
                out.println("<input type='hidden' name='eid' value='" + idParam + "'>");
                out.println("<label>Name:</label>");
                out.println("<input type='text' name='name' value='" + name + "'><br>");
                out.println("<label>Brand:</label>");
                out.println("<input type='text' name='brand' value='" + brand + "'><br>");
                out.println("<label>Quantity:</label>");
                out.println("<input type='text' name='quantity' value='" + quantity + "'><br>");
                out.println("<label>Rate:</label>");
                out.println("<input type='text' name='rate' value='" + rate + "'><br>");
                out.println("<label></label>");
                out.println("<input type='hidden' name='total' value='" + total + "'><br>");
                out.println("<input type='submit' value='Edit'>");
                out.println("</form>");
            } else {
                out.println("Record not found.");
            }
        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
        }

        out.println("</body>");
        out.println("</html>");
    }
}
