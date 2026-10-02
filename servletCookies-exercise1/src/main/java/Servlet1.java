import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import jakarta.servlet.ServletException; // Use javax.servlet if using older Java EE
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=ISO-8859-1");
        PrintWriter out = response.getWriter();

        // 1. Retrieve parameters safely
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");

        // Guard against direct URL access (missing form data)
        if (firstName == null) {
            out.print("<h3>Error: Please submit the form first.</h3>");
            out.close();
            return;
        }

        // 2. Handle multiple checkbox/select values
        String[] likesArray = request.getParameterValues("likes");
        String likesCombined = (likesArray != null) ? String.join(", ", likesArray) : "None";

        // 3. Add cookies safely
        response.addCookie(new Cookie("firstName", URLEncoder.encode(firstName, "UTF-8")));
        response.addCookie(new Cookie("lastName", URLEncoder.encode(lastName, "UTF-8")));
        response.addCookie(new Cookie("age", URLEncoder.encode(age, "UTF-8")));
        response.addCookie(new Cookie("gender", URLEncoder.encode(gender, "UTF-8")));
        response.addCookie(new Cookie("likes", URLEncoder.encode(likesCombined, "UTF-8")));

        // 4. Output success and button to Servlet2
        out.print("<h3>Welcome " + firstName + " " + lastName + "!</h3>");
        out.print("<form action='Servlet2' method='post'>");
        out.print("<input type='submit' value='Go to Servlet 2'>");
        out.print("</form>");

        out.close();
    }
}