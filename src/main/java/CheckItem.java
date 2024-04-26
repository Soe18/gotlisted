
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckItem
 */
@WebServlet("/CheckItem")
public class CheckItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private String db_url;
	private String db_user;
	private String db_pswd;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckItem() {
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
		String item_checked = request.getParameter("item_check");
		HttpSession session = request.getSession(false);
		int integerReturn = 0;
		
		try {
			if (session.getAttribute("user_id") != null && item_checked != null) { // per vedere se utente ha inserito url o ha fatto input comnpleto
				// control item
				String select_query = "SELECT done FROM todoitem WHERE id = '"+item_checked+"'";
				String query = "";
				ResultSet res = conn.createStatement().executeQuery(select_query);
				
				if(res.next()) {
					if(res.getString("done").equals("0")) {
						integerReturn = 2;
						query = "UPDATE todoitem SET done = '1' WHERE id = '"+item_checked+"'"; // 1 quindi checked
					}
					else {
						integerReturn = 3;
						query = "UPDATE todoitem SET done = '0' WHERE id = '"+item_checked+"'"; // 0 quindi unchecked
					}
				}
				
				int resInt = conn.createStatement().executeUpdate(query);
					
				if (resInt==1) { // se va tutto bene, metto messageType a quello giusto e invio richiesta
					request.setAttribute("messageType", integerReturn);
					request.getRequestDispatcher("todolist").forward(request, response);
				} else { // qualcosa è andato storto... qualcuno si sara' divertito con '...
					throw new ServletException("Impossibile marcare come l'elemento della todolist...");
				}
				
			} else {
				response.sendRedirect("./todolist");
			}
		} catch (Exception e) {
			throw new ServletException("Impossibile marcare come l'elemento della todolist...");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
