

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CartForm")
public class CartForm extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
		
	        out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Cart Form</title>");
	        out.println("</head>");
	        out.println("<body>");

	        out.println("<form action='CartInsert' method='GET'>");
	        out.println("<label for='name'>Name:</label>");
	        out.println("<input type='text' id='name' name='name' required><br><br>");

	        out.println("<label for='brand'>Brand:</label>");
	        out.println("<input type='text' id='brand' name='brand' required><br><br>");

	        out.println("<label for='quantity'>Quantity:</label>");
	        out.println("<input type='number' id='quantity' name='quantity' required><br><br>");

	        out.println("<label for='rate'>Rate:</label>");
	        out.println("<input type='number' id='rate' name='rate' required><br><br>");

	        out.println("<input type='submit' value='Submit'>");
	        out.println("</form>");

	        out.println("</body>");
	        out.println("</html>");
		
		
		
		
		
		
	}

}
