package Model;
import java.sql.ResultSet;

public interface Model {

	public ResultSet getItemsQuery(String setUp);

	public boolean exeQuery(String query);

	public boolean updateQuery(String query);

	public ResultSet tableData(String query);

	public void startInitial();
	
}
