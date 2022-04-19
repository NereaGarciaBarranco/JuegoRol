import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLConnection {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	// Datos que necesitamos para conectar con la BD
	final private String host = "localhost:3306/juegorol";
	final private String user = "root";
	final private String passwd = "1234";
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de realizar correctamente la
	 * conexion con la BD. Se llama cada vez que se lee de la BD
	 * o se guardan registros nuevos.
	 */
	public void conectarBD() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://" + host 
					+ "?user=" + user + "&password=" + passwd);
			statement = connect.createStatement();
		} catch (Exception e) {throw e;};
	}
	
	/**
	 * Pre: ---
	 * Post: Este metodo se encarga de cerrar la conexion con la BD.
	 * Se llama cada vez que se termina de leer o de guardar datos
	 * en la misma.
	 */
	public void cerrarBD() {
		try {
			if (resultSet != null) {
				resultSet.close();
			} if (statement != null) {
				statement.close();
			} if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {}
	}
	
	// Metodos get y set
	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

}
