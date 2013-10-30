package edu.upc.eetac.dsa.raul.better.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource ds = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		ds = DataSourceSPA.getInstance().getDataSource();
	}
	
    public RegisterServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		if (action.equals("REGISTRO")) {	//VERIFICAR CAJAS
			String username,pass,name,email;
			username = req.getParameter("username");
			pass = req.getParameter("userpass");
			name = req.getParameter("name");
			email = req.getParameter("email");
			try {
				Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				String update = "INSERT INTO users VALUES('"+username+"',MD5('"+pass+")','"+name+"','"+email+"');";
				int row = stmt.executeUpdate(update);
				//if (row == 0)
				update = "INSERT INTO users_roles VALUES('"+username+"','registered');";
				row = stmt.executeUpdate(update);
				//if (row == 0)
			} catch (Exception e) {}
			String url = "/validar.jsp";
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(req, res);
		} 
	}
}
