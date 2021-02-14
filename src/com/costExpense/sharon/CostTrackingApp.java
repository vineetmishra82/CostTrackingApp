package com.costExpense.sharon;

import java.sql.SQLException;

import Model.MyModel;
import View.MyView;
import ViewModel.MyViewModel;

public class CostTrackingApp {

	public static void main(String[] args) throws SQLException {
		
		
		//Creating instances
		MyViewModel vm = new MyViewModel();
		MyModel mod = new MyModel();
		MyView view = new MyView();
		
		//Binding Components
		vm.setView(view);
		vm.setModel(mod);
		view.setVmodel(vm);
		
		
		
	}

}