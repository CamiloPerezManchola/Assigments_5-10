
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			// Output complete HTML with the bfcache reload fix in the head
	        out.print("<!DOCTYPE html>");
	        out.print("<html>");
	        out.print("<head>");
	        out.print("<title>Protected Page</title>");
	        out.print("<script>");
	        // If the page is loaded from the browser's back/forward cache, force a reload
	        out.print("window.addEventListener('pageshow', function(event) {");
	        out.print("    if (event.persisted) {");
	        out.print("        window.location.reload();");
	        out.print("    }");
	        out.print("});");
	        out.print("</script>");
	        out.print("</head>");
	        out.print("<body>");

			String n = request.getParameter("userName");
			out.print("Welcome " + n);

			HttpSession session = request.getSession();
			session.setAttribute("uname", n);

			out.print("<br><a href='Servlet2'>visit</a>");

			out.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
