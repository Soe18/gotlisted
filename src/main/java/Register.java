

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gotlisted.UtilFuncs;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private String db_url;
	private String db_user;
	private String db_pswd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
    }

    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.db_url = config.getInitParameter("db_url");
		this.db_user = config.getInitParameter("db_user");
		this.db_pswd = config.getInitParameter("db_pswd");
		
		try {
			String complete_db_url = "jdbc:mysql://"+db_url;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(complete_db_url, db_user, db_pswd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * errorFaults:
	 * 0 --> errorFault doesn't exist
	 * 1 --> Password aren't the same
	 * 2 --> Cannot create user
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = request.getParameter("username");
		
		try {
			// check if user still needs to register
			if (session.getAttribute("user_id") == null) { // not logged, let's register
				String password = request.getParameter("password");
				String confirm_password = request.getParameter("confirm_password");
				
				if (username != null) { // check if user wanted to register
					// check if password is same
					if (password.equals(confirm_password)) {
						password = UtilFuncs.sha256(password);
						
						String query = "INSERT INTO users (username, password) VALUES ('"+username+"','"+password+"')";
						int resInt = conn.createStatement().executeUpdate(query);
						
						if(resInt==1) { // user created
							session = request.getSession(); // create new session
							// get id of newborn session:
							query = "SELECT id, username, password FROM users WHERE username='"+username+"'";
							ResultSet res = conn.createStatement().executeQuery(query); // get the result 
							res.next(); // get the row
							session.setAttribute("user_id", res.getString("id")); // get id
							session.setAttribute("user_name", res.getString("username")); // get id
							
							// TODO: MIGHT CAUSE ERRORS, DIDN'T TRY IT YET
							
							response.sendRedirect("/gotlisted/");
						}
						else { // cannot create user			
							request.setAttribute("registerError", true);
							request.setAttribute("errorFault", 2);
							request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
						}
					}
					else { // password aren't the same
						request.setAttribute("registerError", true);
						request.setAttribute("errorFault", 1);
						request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
					}
				}
				else { // user didn't want to register, not an error
					request.setAttribute("registerError", false);
					request.setAttribute("errorFault", 0);
					request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
				}
				
			} else { // already logged in, get out
				response.sendRedirect("./");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
