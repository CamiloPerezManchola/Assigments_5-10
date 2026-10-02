
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet2
 */
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    response.setContentType("text/html; charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();

	    out.print("<h2>Data Received from Cookies in Servlet 2:</h2>");

	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            String decodedValue = java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");
	            out.print("<p><strong>" + cookie.getName() + "</strong>: " + decodedValue + "</p>");
	        }
	    } else {
	        out.print("<p>No cookies found!</p>");
	    }

	    out.close();
	}

}
