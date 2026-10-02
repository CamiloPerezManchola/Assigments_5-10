import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public Servlet2() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Prevent browser from caching this page normally
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // Don't create a new session here
        HttpSession session = request.getSession(false);

        // No valid session = go back to login
        if (session == null || session.getAttribute("uname") == null) {
            response.sendRedirect("index.html");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n = (String) session.getAttribute("uname");

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

        out.print("Hello " + n);
        out.print("<br><br>");

        out.print("<form action='" + request.getContextPath() + "/LogoutServlet' method='get'>");
        out.print("<input type='submit' value='Logout'>");
        out.print("</form>");

        out.print("</body>");
        out.print("</html>");

        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}