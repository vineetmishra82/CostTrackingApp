package DataBase;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Date;

import Exceptions.CostManagerException;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Objects.MyCost;
import Objects.MyCurrency;

public class MyDatabaseConnection implements DBConnection {

	public Connection con;
	public Statement stmt;
	public PreparedStatement pstmt;
	public String dataBase = "costExpense";

	public MyDatabaseConnection() {
		try {
			con = DriverManager.getConnection("jdbc:derby:" + dataBase + ";create = true");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			System.out.println("Connected to Embedded Derby Database -> " + dataBase);
			} catch (SQLException e) {
				
			System.out.println("Unable to connect to Database...");
		e.printStackTrace();
		}
	}
	
	@Override
	public void initialSetup() 
	{
		/*
		 * Initial set up of Database, UDT & Settings
		 */
		System.out.println("Creating initial cost table if not already created...");
		boolean costTableCreated = createInitialTable("cost");

		if (costTableCreated) {
			System.out.println("Cost table already exists");
		} else {
			System.out.println("Cost table created...");
		}

		System.out.println("Creating initial category table if not already created...");

		boolean categoryTableCreated = createInitialTable("category");

		if (categoryTableCreated) {
			System.out.println("Category table already exists");
		} else {
			System.out.println("Category table created...");
		}

		System.out.println("Creating initial currency table if not already created...");

		boolean currencyTableCreated = createInitialTable("currency");

		if (currencyTableCreated) {
			System.out.println("Currency table already exists");
		} else {
			System.out.println("Currency table created...");
		}
		
		System.out.println("Setting up intitial 4 categories");
		
		boolean result = setUpIntitialData("category");
		
		if(result)
		{
			System.out.println("4 intitial categories insertion completed..");
		}
		else {
			System.out.println("No data inserted into table..");
		}
		

		result = setUpIntitialData("currency");
		
		if(result)
		{
			System.out.println("Basic currencies insertion completed..");
		}
		else {
			System.out.println("No data inserted into table..");
		}		
	}

	@Override
	public  boolean setUpIntitialData(String setUp) {
		
		String table="",query="SELECT * from ";
		
		if(setUp.equals("currency"))
		{
			table = "currencyTable";
			
		}
		else {
			table = "categoryTable";
			
		}
		query += table; 
		
		ResultSet result = exeQuery(query);
		
		try {
			if(result.next())
			{
				System.out.println("Basic "+setUp+" data exists in table "+table);
				return false;
			}
			
			else {
				System.out.println("Inserting data .....");
				
				
				
				if(setUp.equals("currency"))
				{
					query = "Insert into "+table+" (currency) values(?)";
					String[][] values = {
							{"INR","₹"},
							{"USD","$"},
							{"NIS","₪"}};
					
				
					for(int i=0;i<3;i++)
					{
						int j = 0;
						try {
							
							MyCurrency currency = new MyCurrency(values[i][j], values[i][j+1]);
							pstmt = con.prepareStatement(query);
							pstmt.setObject(1, currency);
							pstmt.executeUpdate();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				
				}
				else {
					query = "Insert into "+table+" values(";
					String[] values = {"Fuel","Rent","Electricity","Grocery"};
								
					for(int i=0;i<4;i++)
					{
						try {
							stmt.execute(query+"'"+values[i]+"')");
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}
				
				System.out.println("Checking by fetching data...");
							
				result = exeQuery("Select * from "+table);			
				if(result.next())
				{
					pstmt = con.prepareStatement("SELECT * from "+table);
					if(setUp.equals("currency"))
					{
						System.out.println("Getting currencies from table :");
						
						try {
							
							ResultSet rs = pstmt.executeQuery();
							
							while(rs.next())
							{
								MyCurrency currency = (MyCurrency) rs.getObject(2);
								System.out.println(currency.toString());
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					else {
						ResultSet rs = pstmt.executeQuery();
						
						while(rs.next())
						{
							System.out.println(rs.getString(1));							
						}						
						
						}
						
					return true;	
					
				}
				else {
					System.out.println("No data found...");
				}
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	private  boolean createInitialTable(String tableType) {

		String table = "", value = "";

		if (tableType.equals("cost")) {
			table = "costTable";
			value = "(cost MyCost,Date date)";
		} else if (tableType.equals("category")) {
			table = "categoryTable";
			value = " (categoryName VARCHAR(50), PRIMARY KEY(categoryName))";
		} else {
			table = "currencyTable";
			value = " (ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),currency MyCurrency)";
		}

		try {
			DatabaseMetaData dbm = con.getMetaData();

			ResultSet rs = dbm.getTables(null, null, table.toUpperCase(), null);

			if (!rs.next()) {
				if(table.equals("costTable"))
				{
					String query = "CREATE TYPE MyCurrency EXTERNAL NAME 'Objects.MyCurrency' LANGUAGE JAVA";
					stmt.execute(query);
					System.out.println("MyCurrency data type created !");
					
					query = "CREATE TYPE MyCost EXTERNAL NAME 'Objects.MyCost' LANGUAGE JAVA";
					stmt.execute(query);
					System.out.println("MyCost data type created !");
				}
				String query = "CREATE Table " + table + value;
				
				boolean r = updateQuery(query);				

				return !r;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public  ResultSet exeQuery(String query) {
		// Will return the data sets as ResultSet
		ResultSet rs = null;
	
		try {
			
			System.out.println(query);
			rs = stmt.executeQuery(query);

		} catch (SQLException e) {
			System.out.println("Error encountered in Selecting table");
			e.printStackTrace();
		}

		return rs;

	}

	public  boolean updateQuery(String query) {
		
		try {
			
			if(!query.contains("UPDATE") && (!query.contains("DELETE")&& (!query.contains("CREATE"))))
			{
				if(query.contains(" | "))
				{
					String[] currency = query.split(" | ");
										
					MyCurrency newCurr = new MyCurrency(currency[0], currency[2]);
					MyCurrency oldCurr = new MyCurrency(currency[4], currency[6]);
					
					System.out.println(oldCurr.toString());
					System.out.println(newCurr.toString());
					query = "SELECT * from CURRENCYTABLE";
					pstmt = con.prepareStatement(query);
					
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next())
					{
						MyCurrency currencyObj = (MyCurrency) rs.getObject(2);
						if(currencyObj.equals(oldCurr))
						{
							query = "DELETE from CURRENCYTABLE where ID = "+rs.getInt(1);
							pstmt = con.prepareStatement(query);
							pstmt.execute();
							
							query = "INSERT into CURRENCYTABLE (currency) VALUES(?)";
							pstmt = con.prepareStatement(query);
							pstmt.setObject(1, newCurr);
							pstmt.execute();
							pstmt.close();
							rs.close();
							break;
						}
					}					
					

				}
				
				else {
					
				String[] currency = query.split(" - ");
					MyCurrency curr = new MyCurrency(currency[0], currency[1]);
					
					query = "SELECT * from CURRENCYTABLE";
					pstmt = con.prepareStatement(query);
					
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next())
					{
						MyCurrency currencyObj = (MyCurrency) rs.getObject(2);
						if(currencyObj.equals(curr))
						{
							query = "DELETE from CURRENCYTABLE where ID = "+rs.getInt(1);
							pstmt = con.prepareStatement(query);
							pstmt.execute();
							pstmt.close();
							rs.close();
							break;
							
						}
						
					}
										
				}
				
			}
			
			else {		
				
				stmt.execute(query);	
			}
			
			return true;
		} catch (SQLException e) {
			System.out.println("Error encountered in Updating table");
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public  boolean closeConnection() {
		try {
			stmt.close();
			con.close();
			System.out.println("Connection closed....");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public  boolean exeObjQuery(String query, Object obj) {

		try {
			if(query.contains("COSTTABLE"))
			{
				MyCost cost = (MyCost) obj;
				Date date = (Date) cost.getDate();
				
				query += "?,?)";
				
				System.out.println("Final query ->"+query);
				
				pstmt = con.prepareStatement(query);
				pstmt.setObject(1, cost);
				pstmt.setObject(2, date);
			}
			else if(query.contains("CURRENCY"))
			{
				MyCurrency currency = (MyCurrency) obj;
				String finalQuery = query;				
				System.out.println("Final query ->"+query);
				
				query = "SELECT * from CURRENCYTABLE";
				pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					MyCurrency currencyObj = (MyCurrency) rs.getObject(2);
					
					if(currency.equals(currencyObj))
					{
						return false;
					}
				}
				
				rs.close();
				
				pstmt = con.prepareStatement(finalQuery);
				pstmt.setObject(1, currency);
			}
			else {
				System.out.println(query);
				pstmt = con.prepareStatement(query);
			}
			
			try {
				pstmt.executeUpdate();
			}catch(SQLException e)
			{
				return false;
			}
			
			
			return true;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

}
