

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

@WebServlet("/CostumerHistory")
public class CostumerHistory extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        
        
        
        
        

        String name = request.getParameter("name");
        String drname = request.getParameter("drname");
        String sttotal = request.getParameter("total");
        String sttime = request.getParameter("time");

      //  int inttotal = Integer.parseInt(sttotal);
        double inttotal = Double.parseDouble(sttotal);

       // int inttime = Integer.parseInt(sttime);
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "abc123");

            PreparedStatement deleteStmt = con.prepareStatement("DELETE FROM medicine.bill ");
            //  deleteStmt.setString(1, id);

              int rowsDeleted = deleteStmt.executeUpdate();

              if (rowsDeleted > 0) {
                //  response.sendRedirect("Show"); // Redirect to the "Show" page after successful deletion
              } else {
                  // Handle the case where the record was not found or deletion failed
              //    response.sendRedirect("Show"); // Redirect back to the "Show" page
              }
              
            
            
            
            
            
            
            PreparedStatement stmt = con.prepareStatement("INSERT INTO history (name, drname, total, time) VALUES (?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, drname);
            stmt.setDouble(3, inttotal);
            stmt.setString(4, sttime);
        //    stmt.setString(5, totalAsString);

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
