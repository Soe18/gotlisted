

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gotlisted.ToDoBean;

/**
 * Servlet implementation class ToDoList
 */
@WebServlet("/ToDoList")
public class ToDoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private String db_url;
	private String db_user;
	private String db_pswd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDoList() {
        super();
        // TODO Auto-generated constructor stub
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
		response.setContentType("text/html");
		
		// if attribute empty, put 0
		if (request.getAttribute("messageType")==null) {
			request.setAttribute("messageType", 0);
		}
		HttpSession session = request.getSession(false);
		
		try {
			if (session.getAttribute("user_id") != null) {
				// get todolist
				String query = "SELECT id, title, descr, done, user FROM todoitem WHERE todoitem.user="+session.getAttribute("user_id");
				ResultSet res = conn.createStatement().executeQuery(query);
				
				ArrayList<ToDoBean> todolist = new ArrayList<ToDoBean>(); // creo il bean
				while(res.next()) { // ad ogni riga nel database, aggiungo un item nella todolist
					todolist.add(new ToDoBean(res.getInt("id"), res.getString("title"), res.getString("descr"), res.getBoolean("done"), res.getInt("user")));
				}
				request.setAttribute("todolist", todolist); // imposto come attributo della richiesta la todolist
				
				// redirect a todolist.jsp con tutte le informazioni
				request.getRequestDispatcher("/WEB-INF/todolist.jsp").forward(request, response);
				//response.getWriter().append("ok");
			} else { // not logged, redirect to login
				response.getWriter().append("no");
				response.sendRedirect("./login");
			}
		}
		catch (Exception e) {
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
