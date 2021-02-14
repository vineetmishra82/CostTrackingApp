/*
 * This class handles the bridge between View & Model.
 * 
 * It accepts and returns data to and fro for both View & Model
 * 
 * This implements interface ViewModel
 */


package ViewModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Model.Model;
import Model.MyModel;
import View.MyView;
import View.View;

public class MyViewModel implements ViewModel {
	
	public Model model;
	public View view;
	private static Vector<Object> list;
	
	public MyViewModel()  {
	this.setModel(new MyModel());
	}

	public Vector<Object> getComboList(String type) {
		
		if(type.equals(null))
		{
			return null;
		}		
		
		list = new Vector<>();
		
	ResultSet result = model.getItemsQuery(type);
								
				try {
						while(result.next())
						{
							if(type.equals("currency"))
							{
								list.add(result.getObject(2));
							}
							else {
								list.add(result.getObject(1));
							}
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		return list;
	}

	public boolean getButtonAction(int type, String query, Object obj) {
		//Acts when a button is pressed
		
		System.out.println("Type is "+type);
		System.out.println("Query -> "+query);
		
		if(type==1)
		{
			return ((MyModel) model).exeObjectQuery(query,obj);
		}
		if (type==2 || type ==3)
		{
			return model.updateQuery(query);
		}
		
		return false;
	}

	public ResultSet getTableData(String query) {
		// TODO Auto-generated method stub
		return model.tableData(query);
	}

	@Override
	public void startInitialData() {
		model.startInitial();
		
	}

	@Override
	public boolean getButtonAction(int type, String query) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setView(View view) {

		this.view = view;
		
	}

	@Override
	public void setModel(Model mod) {

		this.model = mod;
		
	}

	
}
