

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gotlisted.ToDoBean;
import gotlisted.UtilFuncs;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private String db_url;
	private String db_user;
	private String db_pswd;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.setContentType("text/html");
		
		HttpSession session = request.getSession(false);
		
		try {
			String username = request.getParameter("username");
			
			// check if already logged
			if (session.getAttribute("user_id") == null && username != null) { // not logged, let's login	
				String password = UtilFuncs.sha256(request.getParameter("password"));
				
				String query = "SELECT id, username, password FROM users WHERE username='"+username+"' AND password='"+password+"'";
				ResultSet res = conn.createStatement().executeQuery(query);
				
				if(res.next()) { // user found
					session = request.getSession(); // create new session
					session.setAttribute("user_id", res.getString("id")); // get id
					session.setAttribute("user_name", res.getString("username")); // get id
					
					response.sendRedirect("todolist");
				}
				else { // user not found
					throw new ServletException("Errore di accesso, utente non trovato. <a href='login'>Riprovare</a>");
				}
			} else if (username==null) { // sta accedendo al login
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			} else {
				response.sendRedirect("./");
			}
	
		} catch (SQLException e) {
			throw new ServletException("Errore fatale! Qualcosa è andato veramente storto.");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
