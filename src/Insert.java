

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Insert")
public class Insert extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String stquantity = request.getParameter("quantity");
        String strate = request.getParameter("rate");

        int quantity = Integer.parseInt(stquantity);
        double rate = Double.parseDouble(strate);
        double total = quantity * rate;
        String totalAsString = String.valueOf(total);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "abc123");

            PreparedStatement stmt = con.prepareStatement("INSERT INTO med (name, brand, quantity, rate, total) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, brand);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, rate);
            stmt.setString(5, totalAsString);

            int a = stmt.executeUpdate();
            if (a > 0) {
                out.println("Data inserted successfully.");
                response.sendRedirect("index.html");
            } else {
                out.println("Data insertion failed.");
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            // Properly handle exceptions, e.g., log errors
            e.printStackTrace();
            out.println("An error occurred: " + e.getMessage());
        }

		
		
	}

}
