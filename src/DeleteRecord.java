

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteRecord")
public class DeleteRecord extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	
		 String id = request.getParameter("id");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "abc123");

	            // Delete the record from the database
	            PreparedStatement deleteStmt = con.prepareStatement("DELETE FROM medicine.med WHERE id = ?");
	            deleteStmt.setString(1, id);

	            int rowsDeleted = deleteStmt.executeUpdate();

	            if (rowsDeleted > 0) {
	                response.sendRedirect("Show"); // Redirect to the "Show" page after successful deletion
	            } else {
	                // Handle the case where the record was not found or deletion failed
	                response.sendRedirect("Show"); // Redirect back to the "Show" page
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		
		
	
	
	
	}

}
