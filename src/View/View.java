package View;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;

import ViewModel.MyViewModel;
import ViewModel.ViewModel;



public interface View {

	
	public void fillReportsTable();
	
	public String[][] getTableData(JDateChooser fromDate, JDateChooser toDate);
	
	
	public void fillDropDowns(boolean isFirstSetup);

	public void checkEnability();
	public void startButtonAction(ActionEvent e);
	public void updateComboBox(String comboBox, String value, String ops);
	public void setVmodel(ViewModel vm);
	
}