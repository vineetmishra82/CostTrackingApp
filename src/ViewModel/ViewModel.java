package ViewModel;
import java.sql.ResultSet;
import java.util.Vector;

import Model.Model;
import View.View;

public interface ViewModel {
	
	public Vector<Object> getComboList(String type);

	public boolean getButtonAction(int type, String query, Object obj);

	public ResultSet getTableData(String query);

	public void startInitialData();

	boolean getButtonAction(int type, String query);

	public void setView(View view);

	public void setModel(Model mod);

}
