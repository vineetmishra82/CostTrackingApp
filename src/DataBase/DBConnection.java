package DataBase;

import java.sql.SQLException;

public interface DBConnection {

	boolean setUpIntitialData(String setUp) throws SQLException;
	public void initialSetup();
	public boolean closeConnection();
	
	
	
}
	
	
