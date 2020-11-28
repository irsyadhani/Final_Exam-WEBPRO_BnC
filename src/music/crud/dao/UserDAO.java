package music.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import music.crud.model.User;
import music.crud.model.Instrument;

public class UserDAO {
	private String url = "jdbc:mysql://localhost:3306/music_tc";
	private String user = "root";
	private String pass = "";

	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES "
			+ " (?, ?, ?);";
	private static final String INSERT_INSTRUMENTS_SQL = "INSERT INTO instruments" + "  (name, price, status) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
	
	private static final String SELECT_INSTRUMENT_BY_ID = "select id,name,price,status from instruments where id =?";
	private static final String SELECT_ALL_INSTRUMENTS = "select * from instruments";
	private static final String DELETE_INSTRUMENTS_SQL = "delete from instruments where id = ?;";
	private static final String UPDATE_INSTRUMENTS_SQL = "update instruments set name = ?,price= ?, country =? status id = ?;";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	// insert user
	public void insertUser(User user) throws SQLException {
//		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
//			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// insert instrument
		public void insertInstrument(Instrument instrument) throws SQLException {
//			System.out.println(INSERT_USERS_SQL);
			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INSTRUMENTS_SQL)) {
				preparedStatement.setString(1, instrument.getName());
				preparedStatement.setString(2, instrument.getPrice());
				preparedStatement.setString(3, instrument.getStatus());
//				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	// update user
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCountry());
			statement.setInt(4, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// update instrument
		public boolean updateInstrument(Instrument instrument) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_INSTRUMENTS_SQL);) {
				statement.setString(1, instrument.getName());
				statement.setString(2, instrument.getPrice());
				statement.setString(3, instrument.getStatus());
				statement.setInt(4, instrument.getId());

				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}
	
	// select user by id
	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, name, email, country);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	// select instrument by id
		public Instrument selectInstrument(int id) {
			Instrument instrument = null;
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INSTRUMENT_BY_ID);) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					String name = rs.getString("name");
					String price = rs.getString("price");
					String status = rs.getString("status");
					instrument = new Instrument(id, name, price, status);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return instrument;
		}
	
	// select users
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				users.add(new User(id, name, email, country));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	// select instrument
		public List<Instrument> selectAllInstruments() {
			List<Instrument> instruments = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INSTRUMENTS);) {
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String price = rs.getString("price");
					String status = rs.getString("status");
					instruments.add(new Instrument(id, name, price, status));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return instruments;
		}
	
	// delete user
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	// delete instrument
		public boolean deleteInstrument(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_INSTRUMENTS_SQL);) {
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}
}
