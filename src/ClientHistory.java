

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


@WebServlet("/ClientHistory")
public class ClientHistory extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			
			
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
		        out.println("</style>");
		        out.println("</head>");
		        out.println("<body>");
		        out.println("<header>");
		        out.println("<h1>Clent History</h1>");
		        out.println("</header>");

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }

		        try {
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "abc123");

		            ResultSet rs = null;
		            PreparedStatement stmt = con.prepareStatement("SELECT * FROM medicine.history ORDER BY id DESC");
		            rs = stmt.executeQuery();

		            out.println("<table>");
		            out.println("<tr>");
		          //  out.println("<th>ID</th>");
		            out.println("<th>Client Name</th>");
		            out.println("<th>Dr Name</th>");
		            out.println("<th>Bill Generated</th>");
		            out.println("<th>Time</th>");
		          
		            out.println("</tr>");

		            while (rs.next()) {
		                int id = rs.getInt(1);

		                out.println("<tr>");
		           //     out.println("<td>" + id + "</td>");
		                out.println("<td>" + rs.getString(2) + " </td>");
		                out.println("<td>" + rs.getString(3) + "</td>");
		                out.println("<td> Rs "  + rs.getString(4) + "</td>");
		                out.println("<td>" + rs.getString(5) + "</td>");
		                
		                out.println("</tr>");
		            }

		            out.println("</table>");

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

		        out.println("</body>");
		        out.println("</html>");
		        
//		        RequestDispatcher rd = request.getRequestDispatcher("/index.html");
//		        rd.include(request, response);
			
			
			
			
			
			
			
			
			
			
	}

}
