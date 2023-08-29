

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

@WebServlet("/sellmedicine")
public class sellmedicine extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
	//	out.print("narayan");
		  String eid = request.getParameter("eid");
		  String name = request.getParameter("name");
	        String brand = request.getParameter("brand");
	        String stquantity = request.getParameter("quantity");
	        String billquantity = request.getParameter("billquantity");

	        
	        ////////////////////////////////////////////////////////
	        
	        String stsellmedicine = request.getParameter("sellmedicine");
	   
	        int newquantity = Integer.parseInt(stsellmedicine);

	        ////////////////////////////////////////////////////////
	        
	        String strate = request.getParameter("rate");

	        int quantity = Integer.parseInt(stquantity);
	        double rate = Double.parseDouble(strate);
	     
	        int addnewQuantity=quantity-newquantity;
	        int intbillquantity = Integer.parseInt(billquantity);

	        double billtotal = intbillquantity * rate;

	        
	        double total = addnewQuantity * rate;
	       // String totalAsString = String.valueOf(total);
	        
	        
		//////////////////////////////////////////////////////////////
	        
	        
	        
	        
	        
	        
	        /////////////////////////////////////////////////////////


	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "abc123");

	            // Update the record in the database
	            PreparedStatement updateStmt = con.prepareStatement(
	                    "UPDATE medicine.med SET name = ?, brand = ?, quantity = ?, rate = ?, total = ? WHERE id = ?");
	            updateStmt.setString(1, name);
	            updateStmt.setString(2, brand);
	            updateStmt.setInt(3, addnewQuantity);
	            updateStmt.setDouble(4, rate);
	            updateStmt.setDouble(5, total);
	            updateStmt.setString(6, eid);


	            int rowsUpdated = updateStmt.executeUpdate();
	            
	            
	            PreparedStatement stmt = con.prepareStatement("INSERT INTO bill (name, brand, quantity, rate, total) VALUES (?, ?, ?, ?, ?)");
	            stmt.setString(1, name);
	            stmt.setString(2, brand);
	            stmt.setInt(3, intbillquantity);
	            stmt.setDouble(4, rate);
	            stmt.setDouble(5, billtotal);

	            int a = stmt.executeUpdate();
	            if (a > 0) {
	             //  out.println("Data inserted successfully.");
	              // response.sendRedirect("index.html");
	            } else {
	                out.println("Data insertion failed.");
	            }
	            
	            
	            
	            
	            
	            
	            
	            
	            

	            if (rowsUpdated > 0) {
	                out.println("<h2>Record updated successfully!</h2>");
	                
	                // Redirect the user to the "Show" page after a successful update
	                response.sendRedirect("index.html");
	            } else {
	                out.println("<h2>Record not found or update failed.</h2>");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		
		} catch (NumberFormatException e) {
		    // Handle the NumberFormatException here, e.g., by displaying an error message to the user.
		    out.println("<h2>Error: Invalid number format.</h2>");
		}
	        
	        
		
		
		
	}

}
