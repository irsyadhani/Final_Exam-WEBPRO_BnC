package music.crud.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import music.crud.dao.UserDAO;
import music.crud.model.User;
import music.crud.model.Instrument;

/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        this.userDAO = new UserDAO();
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String action = request.getServletPath();

		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertUser(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteUser(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateUser(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		
		// section instrument
		case "/new_instrument":
				showNewFormInstrument(request, response);
			break;
		case "/insert_instrument":
			try {
				insertInstrument(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/delete_instrument":
			try {
				deleteInstrument(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/edit_instrument":
			try {
				showEditFormInstrument(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/update_instrument":
			try {
				updateInstrument(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/list_instrument":
			try {
				listInstrument(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		default:
			try {
				listUser(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	// listUser
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	//listInstrument
	private void listInstrument(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Instrument> listInstrument = userDAO.selectAllInstruments();
		request.setAttribute("listInstrument", listInstrument);
		RequestDispatcher dispatcher = request.getRequestDispatcher("instrument-list.jsp");
		dispatcher.forward(request, response);
	}
	
	// updateUser
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");

		User user = new User(id, name, email, country);
		userDAO.updateUser(user);
		response.sendRedirect("list");
	}
	
	// updateInstrument
	private void updateInstrument(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String status = request.getParameter("status");

		Instrument instrument = new Instrument(id, name, price, status);
		userDAO.updateInstrument(instrument);
		response.sendRedirect("list-instrument");
	}
	
	// deleteUser
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");

	}
	
	// deleteInstrument
	private void deleteInstrument(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteInstrument(id);
		response.sendRedirect("list-instrument");

	}
	
	// showEditForm
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}
	
	// showEditFormInstrument
	private void showEditFormInstrument(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Instrument existingInstrument= userDAO.selectInstrument(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("instrument-form.jsp");
		request.setAttribute("instrument", existingInstrument);
		dispatcher.forward(request, response);
	}
	
	// showNewForm
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}
	
	// showNewFormInstrument
	private void showNewFormInstrument(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("instrument-form.jsp");
		dispatcher.forward(request, response);
	}
	
	// insertUser
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(name, email, country);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}
	
	// insertInstrument
	private void insertInstrument(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String status = request.getParameter("status");
		Instrument newInstrument = new Instrument(name, price, status);
		userDAO.insertInstrument(newInstrument);
		response.sendRedirect("list_instrument");
	}
}
