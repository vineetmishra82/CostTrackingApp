/*
 * This MyModel class implements Model interface and acts as an intermediately
 * between Database Connection & ViewModel
 * 
 * It also executes the database operations in Concurrent manner
 * 
 */

package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DataBase.MyDatabaseConnection;

public class MyModel implements Model {

	private MyDatabaseConnection db;
	private ExecutorService pool;
	private static boolean booleanValue;
	private ResultSet result;
	public MyModel() {
		pool = Executors.newFixedThreadPool(10);
	}
	

	@Override
	public ResultSet getItemsQuery(String setUp) {
	
		pool.submit(new Runnable() {
			
			@Override
			public void run() {
				db = new MyDatabaseConnection();
				
			}
		});
		
		
			ResultSet result;				
			
				
				String table = "", query = "SELECT * from ";

				if (setUp.equals("currency")) {
					table = "currencyTable";

				} else {
					table = "categoryTable";

				}
				query += table;

				result = db.exeQuery(query);
				
				
		return result;

	}

	@Override
	public boolean exeQuery(String query) {
		
	pool.submit(new Runnable() {
			
			@Override
			public void run() {
				db = new MyDatabaseConnection();
				
			}
		});
		
	try {
		
		booleanValue = db.updateQuery(query);
	}catch(NullPointerException e)
	{
		db = new MyDatabaseConnection();
		booleanValue = db.updateQuery(query);
	}
		
		return booleanValue;
	}

	@Override
	public boolean updateQuery(String query) {
		
	pool.submit(new Runnable() {
			
			@Override
			public void run() {
				db = new MyDatabaseConnection();
				
			}
		});
	
	try {
		
		booleanValue = db.updateQuery(query);
	}catch(NullPointerException e)
	{
		db = new MyDatabaseConnection();
		booleanValue = db.updateQuery(query);
	}
		
		return booleanValue;
	}

	@Override
	public ResultSet tableData(String query) {
		
		pool.submit(new Runnable() {
			
			@Override
			public void run() {
				db = new MyDatabaseConnection();
				
			}
		});
		
		try {
			
			result = db.exeQuery(query);
		}catch(NullPointerException e)
		{
			db = new MyDatabaseConnection();
			result = db.exeQuery(query);
		}
						
			return result; 
	}

	@Override
	public void startInitial() {
		
		pool.submit(new Runnable() {
		
		@Override
		public void run() {
			db = new MyDatabaseConnection();
			
		}
	});
		try {
			
			db.initialSetup();
		}catch(NullPointerException e)
		{
			db = new MyDatabaseConnection();
			db.initialSetup();
		}
				
		
	}

	public boolean exeObjectQuery(String query, Object obj) {
				
	pool.submit(new Runnable() {
			
			@Override
			public void run() {
				db = new MyDatabaseConnection();
				//booleanValue = db.exeObjQuery(query, obj);
			}
		});
	
	try {
		
		booleanValue = db.exeObjQuery(query, obj);
	}catch(NullPointerException e)
	{
		db = new MyDatabaseConnection();
		booleanValue = db.exeObjQuery(query, obj);
	}
	
		
		return booleanValue;

}

}