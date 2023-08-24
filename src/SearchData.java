

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

@WebServlet("/SearchData")
public class SearchData extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String sn = request.getParameter("searchname");

		
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<style>");
	        out.println("table { border-collapse: collapse; width: 70%; margin: auto; }");
	        out.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
	        out.println("th { background-color: #f2f2f2; }");
	        out.println("</style>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<h1>Welcome Narayan</h1>");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "abc123");

	            ResultSet rs = null;
	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM medicine.med WHERE name LIKE ?");
	            stmt.setString(1, "%" + sn + "%"); // Use '%' before and after the search term
	            rs = stmt.executeQuery();

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
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
