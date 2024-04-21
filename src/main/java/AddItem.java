

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddItem
 */
@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private String db_url;
	private String db_user;
	private String db_pswd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItem() {
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
	 * messageType:
	 * 0 --> null
	 * 1 --> successfully added new todo entry
	 * 2 --> successfully checked
	 * 3 --> successfully unchecked
	 * 4 --> generic error
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String title = request.getParameter("title");
		String descr = request.getParameter("descr");
		HttpSession session = request.getSession(false);
		
		try { // check if okay response, needs to be both logged in and to have typed a title (aka, just have done an input)
			if (session.getAttribute("user_id") != null && title != null) { // per vedere se utente ha inserito url o ha fatto input comnpleto
				// add item
				String query = "INSERT INTO todoitem (title, descr, done, user) VALUES ('"+title+"','"+descr+"', 0, "+session.getAttribute("user_id")+")";
				int resInt = conn.createStatement().executeUpdate(query);
				if (resInt==1) { // se va tutto bene, metto messageType a quello giusto e invio richiesta
					request.setAttribute("messageType", 1);
					request.getRequestDispatcher("todolist").forward(request, response);
				} else { // error msg
					request.setAttribute("messageType", 4);
					request.getRequestDispatcher("todolist").forward(request, response);
				}
				
			} else {
				response.sendRedirect("./todolist");
			}
		} catch (Exception e) {
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
