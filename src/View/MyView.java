/*
 * This class implements View model and handles the frontline interface with User.
 * 
 * It sends and receives data using ViewModel Class and updates itself.
 */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Objects.MyCost;
import Objects.MyCurrency;
import ViewModel.MyViewModel;
import ViewModel.ViewModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class MyView implements View {

	private JFrame f;
	private JTabbedPane tabbedPane;
	private JPanel cost;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JComboBox<String> categoryList;
	private JComboBox<String> currencyList;
	private JLabel lblNewLabel_3;
	private JButton saveCostEntry;
	private JDateChooser dateChooser;
	private JLabel lblNewLabel_2_1;
	private JPanel category;
	private JLabel lblNewLabel_4;
	private JButton saveNewCategory;
	private JComboBox<String> chooseCategory;
	private JLabel lblNewLabel_4_1;
	private JLabel lblNewLabel_4_1_1_1;
	private JButton saveNewCurrency;
	private JLabel lblNewLabel_4_1_1;
	private JButton editCategory;
	private JButton deleteCategory;
	private JButton saveCategory;
	private JLabel lblNewLabel_4_1_1_1_1;
	private JComboBox<String> chooseCurrency;
	private JButton editCurrency;
	private JButton deleteCurrency;
	private JButton saveCurrency;
	private JPanel report;
	private JPanel pie;
	private JLabel lblNewLabel;
	private JTextField editCurrencyNameField;
	private JTextField costValue;
	private JTextField newCategory;
	private JTextField newCurrency;
	private JTextField newSymbol;
	private JTextField editCategoryField;
	private ViewModel vmodel;
	private JTextField editCurrencySymbolField;
	private JLabel lblNewLabel_2_1_1;
	private JDateChooser fromDate;
	private JDateChooser toDate;
	private JTable dataTable;
	private JScrollPane scrollPane;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private JFreeChart pieChart;
	private String[][] data;
	private ChartPanel chart;
	private DefaultTableModel model;
	private PieDataset dataset;

	public MyView() {

		this.setF(new JFrame());
		this.setTabbedPane(new JTabbedPane(JTabbedPane.TOP));
		this.setCost(new JPanel());
		this.setLblNewLabel_1(new JLabel("Enter Cost"));
		this.setLblNewLabel_2(new JLabel("Select Category"));
		this.setCategoryList(new JComboBox<String>());
		this.setCurrencyList(new JComboBox<String>());
		this.setLblNewLabel_3(new JLabel("Default Currency"));
		this.setSaveCostEntry(new JButton("Save"));
		this.setDateChooser(new JDateChooser());
		this.setLblNewLabel_2_1(new JLabel("Select Date"));
		this.setCategory(new JPanel());
		this.setLblNewLabel_4(new JLabel("Enter New Category"));
		this.setSaveNewCategory(new JButton("Save"));
		this.setChooseCategory(new JComboBox<String>());
		this.setLblNewLabel_4_1(new JLabel("Enter New Currency"));
		this.setLblNewLabel_4_1_1_1(new JLabel("Choose Category"));
		this.setSaveNewCurrency(new JButton("Save "));
		this.setLblNewLabel_4_1_1(new JLabel("New Currency Symbol"));
		this.setEditCategory(new JButton("Edit"));
		this.setDeleteCategory(new JButton("Delete"));
		this.setSaveCategory(new JButton("Save"));
		this.setLblNewLabel_4_1_1_1_1(new JLabel("Choose Currency"));
		this.setChooseCurrency(new JComboBox<String>());
		this.setEditCurrency(new JButton("Edit"));
		this.setDeleteCurrency(new JButton("Delete"));
		this.setSaveCurrency(new JButton("Save"));
		this.setSaveCurrency(new JButton("Save"));
		this.setReport(new JPanel());
		this.setVmodel(new MyViewModel());
		this.setPie(new JPanel());
		this.setLblNewLabel(new JLabel("Welcome to Cost management App"));
		this.setEditCurrencyNameField(new JTextField());
		this.setCostValue(new JTextField());
		this.setNewCategory(new JTextField());
		this.setNewSymbol(new JTextField());
		this.setEditCategoryField(new JTextField());
		this.setLblNewLabel_2_1_1(new JLabel("From "));
		this.setDateChooser_1(new JDateChooser());
		this.setScrollPane(new JScrollPane());
		this.setNewCurrency(new JTextField());
		this.setFromDate(new JDateChooser());
		this.setToDate(new JDateChooser());
		this.setDataset(new DefaultPieDataset());

		model = new DefaultTableModel();

		vmodel.startInitialData();
		fillDropDowns(true);
		fillReportsTable();
		System.out.println("initial setup done");

		f.setTitle("Cost Management App");

		f.setSize(417, 400);// 400 width and 500 height
		f.getContentPane().setLayout(null);// using no layout managers

		tabbedPane.setBounds(0, 48, 401, 313);
		tabbedPane.setBackground(Color.decode("#ffc478"));
		f.getContentPane().add(tabbedPane);
		tabbedPane.addTab("Cost Entry", null, cost, null);
		cost.setLayout(null);
		// cost.setBackground(Color.decode("#f0e5d8"));

		lblNewLabel_1.setBounds(57, 59, 83, 22);
		cost.add(lblNewLabel_1);

		lblNewLabel_2.setBounds(41, 92, 99, 22);
		cost.add(lblNewLabel_2);

		categoryList.setBounds(150, 92, 118, 22);
		categoryList.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				checkEnability();

			}
		});
		cost.add(categoryList);

		costValue.setBounds(150, 59, 118, 22);
		costValue.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				checkEnability();

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		cost.add(costValue);
		costValue.setColumns(10);

		currencyList.setBounds(270, 11, 116, 16);
		currencyList.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				checkEnability();

			}
		});
		cost.add(currencyList);

		lblNewLabel_3.setBounds(150, 12, 118, 14);
		cost.add(lblNewLabel_3);

		saveCostEntry.setEnabled(false);
		saveCostEntry.setBounds(175, 156, 89, 23);
		saveCostEntry.setBackground(Color.decode("#bbdfc8"));
		saveCostEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startButtonAction(e);
				dataset = getDataSet(null, null);
				pieChart = ChartFactory.createPieChart("Cost Expenses", dataset, true, true, false);
				pie.remove(chart);
				chart = new ChartPanel(pieChart);
				Dimension d = new Dimension(400, 280);
				chart.setPreferredSize(d);
				chart.setVisible(true);
				pie.add(chart);
			}
		});

		cost.add(saveCostEntry);

//		dateChooser.setEnabled(false);
		dateChooser.getCalendarButton().setEnabled(true);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(150, 125, 118, 20);
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				checkEnability();

			}
		});
		cost.add(dateChooser);

		lblNewLabel_2_1.setBounds(41, 123, 99, 22);
		cost.add(lblNewLabel_2_1);

		tabbedPane.addTab("Category Management", null, category, null);
		// category.setBackground(Color.decode("#f0e5d8"));
		tabbedPane.setEnabledAt(1, true);
		category.setLayout(null);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblNewLabel_4.setBounds(10, 8, 133, 14);
		category.add(lblNewLabel_4);
		newCategory.setFont(new Font("Tahoma", Font.PLAIN, 11));
		newCategory.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				checkEnability();

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		newCategory.setBounds(153, 5, 96, 20);
		category.add(newCategory);
		newCategory.setColumns(10);
		saveNewCategory.setFont(new Font("Tahoma", Font.BOLD, 11));

		saveNewCategory.setEnabled(false);
		saveNewCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButtonAction(e);
			}
		});
		saveNewCategory.setBounds(259, 4, 110, 23);
		category.add(saveNewCategory);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblNewLabel_4_1.setBounds(10, 40, 133, 14);
		category.add(lblNewLabel_4_1);
		newCurrency.setFont(new Font("Tahoma", Font.PLAIN, 11));

		newCurrency.setToolTipText("Currency Name");
		newCurrency.setColumns(10);
		newCurrency.setBounds(153, 36, 96, 20);
		newCurrency.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				checkEnability();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				checkEnability();

			}

			@Override
			public void keyPressed(KeyEvent e) {
				checkEnability();

			}
		});

		category.add(newCurrency);
		saveNewCurrency.setFont(new Font("Tahoma", Font.BOLD, 11));

		saveNewCurrency.setEnabled(false);
		saveNewCurrency.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startButtonAction(e);

			}
		});
		saveNewCurrency.setBounds(259, 46, 110, 23);
		category.add(saveNewCurrency);
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblNewLabel_4_1_1.setBounds(10, 63, 146, 17);
		category.add(lblNewLabel_4_1_1);
		newSymbol.setFont(new Font("Tahoma", Font.PLAIN, 11));

		newSymbol.setToolTipText("Currency Name");
		newSymbol.setColumns(10);
		newSymbol.setBounds(153, 61, 96, 20);
		newSymbol.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				checkEnability();

			}

			@Override
			public void keyReleased(KeyEvent e) {
				checkEnability();

			}

			@Override
			public void keyPressed(KeyEvent e) {
				checkEnability();

			}
		});

		category.add(newSymbol);
		chooseCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chooseCategory.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				checkEnability();

			}
		});

		chooseCategory.setBounds(118, 123, 102, 22);
		category.add(chooseCategory);
		lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblNewLabel_4_1_1_1.setBounds(10, 123, 146, 17);
		category.add(lblNewLabel_4_1_1_1);
		editCategory.setFont(new Font("Tahoma", Font.BOLD, 11));
		editCategory.setBackground(Color.decode("#bbdfc8"));

		editCategory.setBounds(230, 123, 60, 23);
		editCategory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startButtonAction(e);

			}
		});

		category.add(editCategory);
		deleteCategory.setFont(new Font("Tahoma", Font.BOLD, 11));

		deleteCategory.setEnabled(false);

		deleteCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButtonAction(e);
			}
		});
		deleteCategory.setBounds(300, 123, 80, 23);
		category.add(deleteCategory);
		editCategoryField.setFont(new Font("Tahoma", Font.PLAIN, 13));

		editCategoryField.setEnabled(false);
		editCategoryField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				checkEnability();

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
		editCategoryField.setBounds(118, 156, 102, 20);
		category.add(editCategoryField);
		editCategoryField.setColumns(10);
		saveCategory.setFont(new Font("Tahoma", Font.BOLD, 11));

		saveCategory.setEnabled(false);
		saveCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButtonAction(e);
			}
		});
		saveCategory.setBounds(230, 155, 75, 23);
		category.add(saveCategory);
		lblNewLabel_4_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblNewLabel_4_1_1_1_1.setBounds(10, 196, 146, 17);
		category.add(lblNewLabel_4_1_1_1_1);
		chooseCurrency.setFont(new Font("Tahoma", Font.PLAIN, 13));

		chooseCurrency.setBounds(118, 193, 102, 22);
		chooseCurrency.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				checkEnability();

			}
		});

		category.add(chooseCurrency);
		editCurrency.setFont(new Font("Tahoma", Font.BOLD, 11));
		editCurrency.setBackground(Color.decode("#bbdfc8"));
		editCurrency.setBounds(230, 193, 60, 23);
		editCurrency.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startButtonAction(e);

			}
		});

		category.add(editCurrency);
		deleteCurrency.setFont(new Font("Tahoma", Font.BOLD, 11));
		deleteCurrency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButtonAction(e);
			}
		});

		deleteCurrency.setEnabled(false);
		deleteCurrency.setBounds(300, 193, 80, 23);
		category.add(deleteCurrency);
		editCurrencyNameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		editCurrencyNameField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				checkEnability();

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		editCurrencyNameField.setEnabled(false);
		editCurrencyNameField.setColumns(10);
		editCurrencyNameField.setBounds(118, 226, 102, 20);
		editCurrencyNameField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				checkEnability();

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		category.add(editCurrencyNameField);
		saveCurrency.setFont(new Font("Tahoma", Font.BOLD, 11));
		saveCurrency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				startButtonAction(e);
			}
		});

		saveCurrency.setEnabled(false);
		saveCurrency.setBounds(230, 240, 75, 23);
		category.add(saveCurrency);

		editCurrencySymbolField = new JTextField();
		editCurrencySymbolField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		editCurrencySymbolField.setEnabled(false);
		editCurrencySymbolField.setColumns(10);
		editCurrencySymbolField.setBounds(118, 254, 102, 20);
		editCurrencySymbolField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				checkEnability();

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		category.add(editCurrencySymbolField);

		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Currency");
		lblNewLabel_4_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_1_1_1_1.setBounds(10, 226, 96, 17);
		lblNewLabel_4_1_1_1_1_1.setForeground(Color.decode("#09015f"));
		category.add(lblNewLabel_4_1_1_1_1_1);

		JLabel lblNewLabel_4_1_1_1_1_1_1 = new JLabel("Symbol");
		lblNewLabel_4_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_1_1_1_1_1.setBounds(10, 257, 96, 17);
		lblNewLabel_4_1_1_1_1_1_1.setForeground(Color.decode("#09015f"));
		category.add(lblNewLabel_4_1_1_1_1_1_1);

		tabbedPane.addTab("Report", null, report, null);
		// report.setBackground(Color.decode("#f0e5d8"));
		report.setLayout(null);
		lblNewLabel_2_1_1.setBounds(10, 14, 56, 14);

		report.add(lblNewLabel_2_1_1);
		fromDate.getCalendarButton().setEnabled(true);
		fromDate.setBounds(51, 11, 107, 20);

		report.add(fromDate);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("To ");
		lblNewLabel_2_1_1_1.setBounds(174, 14, 56, 14);
		lblNewLabel_2_1_1_1.setForeground(Color.decode("#09015f"));
		report.add(lblNewLabel_2_1_1_1);

		toDate = new JDateChooser();
		toDate.getCalendarButton().setEnabled(true);
		toDate.setBounds(200, 11, 107, 20);
		report.add(toDate);

//		JDateChooser fromDate = new JDateChooser();
//		fromDate.getCalendarButton().setEnabled(true);
//		fromDate.setBounds(235, 11, 77, 20);
//		report.add(fromDate);

		JButton searchBtn = new JButton("Go");
		searchBtn.setBounds(326, 10, 60, 23);
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String message;

				if (!(fromDate.getDate() == null) && !(toDate.getDate() == null)) {
					if (fromDate.getDate().before(toDate.getDate()))

					{
						model.setRowCount(0);
						for (int i = 0; i < model.getRowCount(); i++) {
							model.removeRow(i);
						}

						model.fireTableDataChanged();
						model.setRowCount(0);
						String[][] data = getTableData(fromDate, toDate);
						for (int i = 0; i < data.length; i++) {
							model.addRow(data[i]);
						}
						model.fireTableDataChanged();
						dataTable = new JTable(model);

						dataset = getDataSet(fromDate, toDate);
						pieChart = ChartFactory.createPieChart("Cost Expenses", dataset, true, true, false);
						pie.remove(chart);
						chart = new ChartPanel(pieChart);
						Dimension d = new Dimension(400, 280);
						chart.setPreferredSize(d);
						chart.setVisible(true);
						pie.add(chart);

					} else {
						message = "Fill dates correctly !";
						JOptionPane.showMessageDialog(f, message, "Warning", JOptionPane.WARNING_MESSAGE);

					}

				}

				else {

					message = "Fill dates correctly !";
					JOptionPane.showMessageDialog(f, message, "Warning", JOptionPane.WARNING_MESSAGE);

				}

			}

		});

		report.add(searchBtn);
		scrollPane.setBounds(10, 47, 376, 227);

		report.add(scrollPane);
		scrollPane.setForeground(Color.decode("#f0e5d8"));
		// dataTable.setBackground(Color.decode("#f0e5d8"));

		scrollPane.setViewportView(dataTable);

		this.setPieChart(pieChart);
		tabbedPane.addTab("Pie Chart", null, pie, null);
		pie.setBackground(Color.decode("#f0e5d8"));
		chart = new ChartPanel(pieChart);
		Dimension d = new Dimension(400, 280);
		chart.setPreferredSize(d);
		chart.setVisible(true);
		pie.add(chart);

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 364, 26);

		f.getContentPane().add(lblNewLabel);
		f.setVisible(true);
	}

	public void setDataset(PieDataset dataset) {
		this.dataset = dataset;
	}

	public void setFromDate(JDateChooser fromDate) {
		this.fromDate = fromDate;
		this.fromDate.setForeground(Color.decode("#bbdfc8"));
	}

	public void setToDate(JDateChooser toDate) {
		this.toDate = toDate;
		this.toDate.setForeground(Color.decode("#bbdfc8"));
	}

	public void setPieChart(JFreeChart pieChart) {
		dataset = getDataSet(null, null);
		this.pieChart = ChartFactory.createPieChart("Cost Expenses", dataset, true, true, false);
	}

	private PieDataset getDataSet(JDateChooser fromDate, JDateChooser toDate) {
		DefaultPieDataset dataset = new DefaultPieDataset();

		data = getTableData(fromDate, toDate);

		if (data == null || data.length == 0) {
			dataset.setValue("No Cost Entry", 100);
		} else if (data.length > 0) {

			Map<String, Double> dataCount = new HashMap<>();
			for (int i = 0; i < data.length; i++) {
				if (!dataCount.containsKey(data[i][1]) || dataCount.isEmpty()) {
					if (!(data[i][1] == null)) {
						dataCount.put(data[i][1], Double.parseDouble(data[i][0]));

					}
				}

				else {

					dataCount.replace(data[i][1], (dataCount.get(data[i][1]) + Double.parseDouble(data[i][0])));
				}

			}

			for (Entry<String, Double> d : dataCount.entrySet()) {
				dataset.setValue(d.getKey(), d.getValue());
			}

		}

		return dataset;
	}

	public void setScrollPane(JScrollPane jScrollPane) {
		if (!jScrollPane.equals(null)) {
			this.scrollPane = jScrollPane;
		}

	}

	public void setDateChooser_1(JDateChooser jDateChooser) {

		this.fromDate = jDateChooser;
		this.fromDate.setForeground(Color.decode("#bbdfc8"));

	}

	public JTextField getEditCurrencyNameField() {
		return editCurrencyNameField;
	}

	public void setEditCurrencyNameField(JTextField editCurrencyNameField) {
		this.editCurrencyNameField = editCurrencyNameField;
	}

	public ViewModel getVmodel() {
		return vmodel;
	}

	@Override
	public void setVmodel(ViewModel vmodel) {

		this.vmodel = vmodel;

	}

	public JTextField getEditCurrencySymbolField() {
		return editCurrencySymbolField;
	}

	public void setEditCurrencySymbolField(JTextField editCurrencySymbolField) {
		if (!editCurrencySymbolField.equals(null)) {
			this.editCurrencySymbolField = editCurrencySymbolField;
		}
	}

	public JTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(JTable dataTable) {
		if (!dataTable.equals(null)) {
			this.dataTable = dataTable;
			this.dataTable.setForeground(Color.decode("#f0e5d8"));
		}
	}

	public JLabel getLblNewLabel_2_1_1() {
		return lblNewLabel_2_1_1;
	}

	public void setLblNewLabel_2_1_1(JLabel lblNewLabel_2_1_1) {
		if (!lblNewLabel_2_1_1.equals(null)) {
			this.lblNewLabel_2_1_1 = lblNewLabel_2_1_1;
			this.lblNewLabel_2_1_1.setForeground(Color.decode("#09015f"));
		}
	}

	public JDateChooser getDateChooser_1() {
		return fromDate;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	@Override
	public void fillReportsTable() {
		String[] cols = { "Cost", "Category", "Date", "Currency" };
		for (int i = 0; i < cols.length; i++) {
			model.addColumn(cols[i]);
		}

		data = getTableData(null, null);

		if (!(data == null)) {
			for (int i = 0; i < data.length; i++) {
				model.addRow(data[i]);
			}
		}

		dataTable = new JTable(model);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		dataTable.setDefaultRenderer(String.class, centerRenderer);

	}

	@Override
	public String[][] getTableData(JDateChooser fromDate, JDateChooser toDate) {

		String query = "SELECT * from COSTTABLE";

		String[][] data = null;

		try (ResultSet result = vmodel.getTableData(query);) {

			result.last();
			int size = result.getRow();
			result.beforeFirst();

			if (size == 0) {

				return null;
			}
			data = new String[size][4];
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			int row = 0;
			while (result.next()) {
				MyCost cost = (MyCost) result.getObject(1);

				if (!(fromDate == null) && !(toDate == null)) {
					if ((cost.getDate().after(fromDate.getDate()) || cost.getDate().equals(fromDate.getDate()))
							&& (cost.getDate().before(toDate.getDate()) || cost.getDate().equals(toDate.getDate()))) {
						data[row][0] = String.valueOf(cost.getValue());
						data[row][1] = cost.getCategory();
						data[row][2] = simpleDateFormat.format(cost.getDate());
						data[row][3] = ((MyCurrency) cost.getCurrency()).toString();
						row++;
					}
				}

				else {
					data[row][0] = String.valueOf(cost.getValue());
					data[row][1] = cost.getCategory();
					data[row][2] = simpleDateFormat.format(cost.getDate());
					data[row][3] = ((MyCurrency) cost.getCurrency()).toString();
					row++;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	public void fillDropDowns(boolean isFirstSetup) {

		if (isFirstSetup) {

			currencyList.addItem("Select Currency");
			currencyList.setSelectedIndex(0);

			chooseCurrency.addItem("Select Currency");
			chooseCurrency.setSelectedIndex(0);

			chooseCategory.addItem("Select Category");
			chooseCategory.setSelectedIndex(0);

			categoryList.addItem("Select Category");
			categoryList.setSelectedIndex(0);

		}

		Vector<Object> items = vmodel.getComboList("currency");

		for (Object s : items) {
				currencyList.addItem(((MyCurrency) s).toString());
				chooseCurrency.addItem(((MyCurrency) s).toString());

			
		}

		items = vmodel.getComboList("category");

		for (Object s : items) {
				categoryList.addItem((String) s);
				chooseCategory.addItem((String) s);

			
		}

	}

	public JFrame getF() {
		return f;
	}

	public void setF(JFrame f) {

		if (f != null) {
			this.f = f;
		}

	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		if (tabbedPane != null) {
			this.tabbedPane = tabbedPane;
		}

	}

	public JPanel getCost() {
		return cost;
	}

	public void setCost(JPanel cost) {
		if (cost != null) {
			this.cost = cost;
		}

	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public void setLblNewLabel_1(JLabel lblNewLabel_1) {
		if (lblNewLabel_1 != null)
			this.lblNewLabel_1 = lblNewLabel_1;
		this.lblNewLabel_1.setForeground(Color.decode("#09015f"));
	}

	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}

	public void setLblNewLabel_2(JLabel lblNewLabel_2) {
		this.lblNewLabel_2 = lblNewLabel_2;
		this.lblNewLabel_2.setForeground(Color.decode("#09015f"));
	}

	public JComboBox<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(JComboBox<String> categoryList) {
		if (categoryList != null)
			this.categoryList = categoryList;

	}

	public JComboBox<String> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(JComboBox<String> currencyList) {

		this.currencyList = currencyList;
	}

	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}

	public void setLblNewLabel_3(JLabel lblNewLabel_3) {

		if (lblNewLabel_3 != null) {
			this.lblNewLabel_3 = lblNewLabel_3;
			this.lblNewLabel_3.setForeground(Color.decode("#09015f"));
		}

	}

	public JButton getSaveCostEntry() {
		return saveCostEntry;
	}

	public void setSaveCostEntry(JButton saveCostEntry) {
		if (!saveCostEntry.equals(null)) {
			this.saveCostEntry = saveCostEntry;
		}

	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public void setDateChooser(JDateChooser dateChooser) {
		if (!dateChooser.equals(null)) {
			this.dateChooser = dateChooser;
		}
	}

	public JLabel getLblNewLabel_2_1() {
		return lblNewLabel_2_1;
	}

	public void setLblNewLabel_2_1(JLabel lblNewLabel_2_1) {

		if (!lblNewLabel_2_1.equals(null)) {
			this.lblNewLabel_2_1 = lblNewLabel_2_1;
			this.lblNewLabel_2_1.setForeground(Color.decode("#09015f"));
		}
	}

	public JPanel getCategory() {
		return category;
	}

	public void setCategory(JPanel category) {

		if (!category.equals(null)) {
			this.category = category;
		}
	}

	public JLabel getLblNewLabel_4() {
		return lblNewLabel_4;
	}

	public void setLblNewLabel_4(JLabel lblNewLabel_4) {
		if (!lblNewLabel_4.equals(null)) {
			this.lblNewLabel_4 = lblNewLabel_4;
			this.lblNewLabel_4.setForeground(Color.decode("#09015f"));
		}
	}

	public JButton getSaveNewCategory() {
		return saveNewCategory;
	}

	public void setSaveNewCategory(JButton saveNewCategory) {
		if (!saveNewCategory.equals(null)) {
			this.saveNewCategory = saveNewCategory;
		}
	}

	public JComboBox<String> getChooseCategory() {
		return chooseCategory;
	}

	public void setChooseCategory(JComboBox<String> chooseCategory) {
		if (!chooseCategory.equals(null)) {
			this.chooseCategory = chooseCategory;
		}
	}

	public JLabel getLblNewLabel_4_1() {
		return lblNewLabel_4_1;
	}

	public void setLblNewLabel_4_1(JLabel lblNewLabel_4_1) {
		if (!lblNewLabel_4_1.equals(null)) {
			this.lblNewLabel_4_1 = lblNewLabel_4_1;
			this.lblNewLabel_4_1.setForeground(Color.decode("#09015f"));
		}
	}

	public JLabel getLblNewLabel_4_1_1_1() {
		return lblNewLabel_4_1_1_1;
	}

	public void setLblNewLabel_4_1_1_1(JLabel lblNewLabel_4_1_1_1) {
		if (!lblNewLabel_4_1_1_1.equals(null)) {
			this.lblNewLabel_4_1_1_1 = lblNewLabel_4_1_1_1;
			this.lblNewLabel_4_1_1_1.setForeground(Color.decode("#09015f"));
		}
	}

	public JButton getSaveNewCurrency() {
		return saveNewCurrency;
	}

	public void setSaveNewCurrency(JButton saveNewCurrency) {
		if (!saveNewCurrency.equals(null)) {
			this.saveNewCurrency = saveNewCurrency;
		}
	}

	public JLabel getLblNewLabel_4_1_1() {
		return lblNewLabel_4_1_1;
	}

	public void setLblNewLabel_4_1_1(JLabel lblNewLabel_4_1_1) {
		if (!lblNewLabel_4_1_1.equals(null)) {
			this.lblNewLabel_4_1_1 = lblNewLabel_4_1_1;
			this.lblNewLabel_4_1_1.setForeground(Color.decode("#09015f"));
		}
	}

	public JButton getEditCategory() {
		return editCategory;
	}

	public void setEditCategory(JButton editCategory) {
		if (!editCategory.equals(null)) {
			this.editCategory = editCategory;
		}
	}

	public JButton getDeleteCategory() {
		return deleteCategory;
	}

	public void setDeleteCategory(JButton deleteCategory) {
		if (!deleteCategory.equals(null)) {
			this.deleteCategory = deleteCategory;
		}
	}

	public JButton getSaveCategory() {
		return saveCategory;
	}

	public void setSaveCategory(JButton saveCategory) {
		if (!saveCategory.equals(null)) {
			this.saveCategory = saveCategory;
		}
	}

	public JLabel getLblNewLabel_4_1_1_1_1() {
		return lblNewLabel_4_1_1_1_1;
	}

	public void setLblNewLabel_4_1_1_1_1(JLabel lblNewLabel_4_1_1_1_1) {
		if (!lblNewLabel_4_1_1_1_1.equals(null)) {
			this.lblNewLabel_4_1_1_1_1 = lblNewLabel_4_1_1_1_1;
			this.lblNewLabel_4_1_1_1_1.setForeground(Color.decode("#09015f"));
		}
	}

	public JComboBox<String> getChooseCurrency() {
		return chooseCurrency;
	}

	public void setChooseCurrency(JComboBox<String> chooseCurrency) {
		if (!chooseCurrency.equals(null)) {
			this.chooseCurrency = chooseCurrency;
		}
	}

	public JButton getEditCurrency() {
		return editCurrency;
	}

	public void setEditCurrency(JButton editCurrency) {
		if (!editCurrency.equals(null)) {
			this.editCurrency = editCurrency;
		}
	}

	public JButton getDeleteCurrency() {
		return deleteCurrency;
	}

	public void setDeleteCurrency(JButton deleteCurrency) {
		if (!deleteCurrency.equals(null)) {
			this.deleteCurrency = deleteCurrency;
		}
	}

	public JButton getSaveCurrency() {
		return saveCurrency;
	}

	public void setSaveCurrency(JButton saveCurrency) {
		if (!saveCurrency.equals(null)) {
			this.saveCurrency = saveCurrency;
		}
	}

	public JPanel getReport() {
		return report;
	}

	public void setReport(JPanel report) {
		if (!report.equals(null)) {
			this.report = report;
		}
	}

	public JPanel getPie() {
		return pie;
	}

	public void setPie(JPanel pie) {
		if (!pie.equals(null)) {
			this.pie = pie;
		}
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		if (!lblNewLabel.equals(null)) {
			this.lblNewLabel = lblNewLabel;
		}
	}

	public JTextField getEditCurrencyField() {
		return editCurrencyNameField;
	}

	public void setEditCurrencyField(JTextField editCurrencyField) {
		if (!editCurrencyField.equals(null)) {
			this.editCurrencyNameField = editCurrencyField;
		}
	}

	public JTextField getCostValue() {
		return costValue;
	}

	public void setCostValue(JTextField costValue) {
		if (!costValue.equals(null)) {
			this.costValue = costValue;
		}
	}

	public JTextField getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(JTextField newCategory) {
		if (!newCategory.equals(null)) {
			this.newCategory = newCategory;
		}
	}

	public JTextField getNewCurrency() {
		return newCurrency;
	}

	public void setNewCurrency(JTextField newCurrency) {

		this.newCurrency = newCurrency;

	}

	public JTextField getNewSymbol() {
		return newSymbol;
	}

	public void setNewSymbol(JTextField newSymbol) {
		if (!newSymbol.equals(null)) {
			this.newSymbol = newSymbol;
		}
	}

	public JTextField getEditCategoryField() {
		return editCategoryField;
	}

	public void setEditCategoryField(JTextField editCategoryField) {
		if (!editCategoryField.equals(null)) {
			this.editCategoryField = editCategoryField;
		}
	}

	public void checkEnability() {

		// Cost entry page items

		// Save button enabling

		try {

			Double.parseDouble(this.costValue.getText());

			if (!this.categoryList.getSelectedItem().equals("Select Category")
					&& this.getDateChooser().getDate() != null
					&& !this.currencyList.getSelectedItem().equals("Select Currency")) {
				this.saveCostEntry.setEnabled(true);
				this.saveCostEntry.setBackground(Color.decode("#bbdfc8"));
			} else {
				throw new IllegalArgumentException();
			}

		} catch (IllegalArgumentException ex) {
			this.saveCostEntry.setEnabled(false);
		}

		// Category Management

		// Save buttons

		if (!this.newCategory.getText().isEmpty()) {

			this.saveNewCategory.setEnabled(true);
			this.saveNewCategory.setBackground(Color.decode("#bbdfc8"));
		}

		if (!this.newCurrency.getText().isEmpty() && !this.newSymbol.getText().isEmpty()) {
			this.saveNewCurrency.setEnabled(true);
			this.saveNewCurrency.setBackground(Color.decode("#bbdfc8"));
		} else {
			this.saveNewCurrency.setEnabled(false);
		}

		// Delete & Edit buttons

		if (!this.chooseCategory.getSelectedItem().equals("Select Category")) {
			this.deleteCategory.setEnabled(true);
			this.deleteCategory.setBackground(Color.decode("#ec4646"));
		} else {
			this.deleteCategory.setEnabled(false);
		}

		if (!this.editCategoryField.getText().isBlank()) {
			this.saveCategory.setEnabled(true);
			this.saveCategory.setBackground(Color.decode("#bbdfc8"));
		} else {
			this.saveCategory.setEnabled(false);
		}

		if (!this.chooseCurrency.getSelectedItem().equals("Select Currency")) {
			this.deleteCurrency.setEnabled(true);
			this.deleteCurrency.setBackground(Color.decode("#ec4646"));
		} else {
			this.deleteCurrency.setEnabled(false);
		}

		if (!this.editCurrencyNameField.getText().isBlank()) {
			this.saveCurrency.setEnabled(true);
			this.saveCurrency.setBackground(Color.decode("#bbdfc8"));
		} else {
			this.saveCurrency.setEnabled(false);
		}

	}

	@Override
	public void startButtonAction(ActionEvent e) {

		/*
		 * For 3 types of functions, insert, delete and update, we keep type identifier
		 * as follows: Insert - type1 Delete - type2 Update - type 3
		 */

		int type = 0;

		if (e.getSource() == this.saveCostEntry || e.getSource() == this.saveNewCategory
				|| e.getSource() == this.saveNewCurrency) {
			type = 1;
		} else if (e.getSource() == this.deleteCategory || e.getSource() == this.deleteCurrency) {
			type = 2;
		}

		else if (e.getSource() == this.saveCategory || e.getSource() == this.saveCurrency) {
			type = 3;
		}

		else {

			// Edit button is clicked so popping data in edit field

			if (e.getSource() == this.editCategory
					&& !this.chooseCategory.getSelectedItem().equals("Select Category")) {
				this.editCategoryField.setEnabled(true);
				this.editCategoryField.setText(this.chooseCategory.getSelectedItem().toString());
				this.saveCategory.setEnabled(true);
			}

			else if (e.getSource() == this.editCurrency
					&& !this.chooseCurrency.getSelectedItem().equals("Select Currency")) {
				this.editCurrencyNameField.setEnabled(true);
				this.editCurrencySymbolField.setEnabled(true);

				String item = (String) this.chooseCurrency.getSelectedItem();
				String[] currency = item.split("-");

				this.editCurrencyNameField.setText(currency[0].trim());
				this.editCurrencySymbolField.setText(currency[1].trim());
				this.saveCurrency.setEnabled(true);
			}

		}

		if (type != 0) {
			String query = "", table = "", comboBox = null, value = null, ops = null, message = "";
			Object obj = null;
			// Selecting table

			if (e.getSource() == this.getSaveCostEntry()) {
				table = "COSTTABLE";
			} else if (e.getSource() == this.getDeleteCategory() || e.getSource() == this.getSaveCategory()
					|| e.getSource() == this.getSaveNewCategory()) {
				table = "CATEGORYTABLE";
			} else {
				table = "CURRENCYTABLE";
			}

			if (type == 1) // Insert function
			{
				message = "Data Saved Successfully !";
				query = "Insert into " + table + " values(";
				if (e.getSource() == this.getSaveCostEntry()) {

					MyCost cost = new MyCost(Double.parseDouble(this.getCostValue().getText()),
							this.getCategoryList().getSelectedItem().toString(), this.getDateChooser().getDate(),
							getCurrencyObject(this.getCurrencyList().getSelectedItem().toString()));

					// query += "," + this.getDateChooser().getDate()+")";
					obj = cost;

					String[] newRow = cost.toString().split(" - ");

					DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
					model.addRow(new Object[] { newRow[0], newRow[1], newRow[2], newRow[3] });
					this.setPieChart(pieChart);
				} else if (e.getSource() == this.getSaveNewCategory()) {
					query += "'" + this.getNewCategory().getText() + "')";

					comboBox = "category";
					value = this.getNewCategory().getText();
					ops = "add";

				}

				else if (e.getSource() == this.getSaveNewCurrency()) {
					query = "INSERT into CURRENCYTABLE (currency) VALUES(?)";
					comboBox = "currency";
					value = this.getNewCurrency().getText() + "-" + this.getNewSymbol().getText();
					ops = "add";
					MyCurrency currency = new MyCurrency(this.getNewCurrency().getText(),
							this.getNewSymbol().getText());
					obj = currency;
				}

			}

			if (type == 2) {
				message = "Data deleted Successfully !";
				query = "DELETE from " + table + " where ";
				if (e.getSource() == this.deleteCategory) {
					query += "categoryname = '" + this.chooseCategory.getSelectedItem() + "'";
					comboBox = "category";
					value = (String) this.chooseCategory.getSelectedItem();
					ops = "del";
				} else {

					String item = (String) this.chooseCurrency.getSelectedItem();
					for (int i = 0; i < item.length(); i++) {
						if (item.charAt(i) == '-') {
							item = item.substring(0, i) + " - " + item.substring(i + 1, item.length());
							break;
						}
					}

					System.out.println(item);
					query = item;
					comboBox = "currency";
					value = (String) this.chooseCurrency.getSelectedItem();
					ops = "del";
				}
			}

			if (type == 3) {
				query = "UPDATE " + table + " SET ";
				if (e.getSource() == this.saveCategory) {
					if (!this.chooseCategory.getSelectedItem().equals(this.editCategoryField.getText())
							&& !this.editCategoryField.getText().isBlank()) {
						query += "categoryName = '" + this.editCategoryField.getText() + "' where categoryName = '"
								+ this.chooseCategory.getSelectedItem() + "'";
						message = "Data updated successfully !";
						comboBox = "category";
						value = (String) this.chooseCategory.getSelectedItem();
						ops = "edit";
					} else {
						message = "no";
					}

				} else if (e.getSource() == this.saveCurrency) {
					String item = (String) this.chooseCurrency.getSelectedItem();

					for (int i = 0; i < item.length(); i++) {
						if (item.charAt(i) == '-') {
							item = item.substring(0, i) + " - " + item.substring(i + 1, item.length());
							break;
						}
					}

					System.out.println(item);
					String[] currency = item.split(" - ");

					if ((!this.editCurrencyNameField.getText().trim().equals(currency[0].trim())
							&& !this.editCurrencyNameField.getText().isBlank())
							|| (!this.editCurrencySymbolField.getText().trim().equals(currency[1].trim())
									&& !this.editCurrencySymbolField.getText().isBlank())) {
						query = this.editCurrencyNameField.getText().trim() + " - "
								+ this.editCurrencySymbolField.getText().trim() + " | " + currency[0].trim() + " - "
								+ currency[1].trim();
						comboBox = "currency";
						message = "Data updated successfully !";
						value = (String) this.chooseCurrency.getSelectedItem();
						ops = "edit";
					} else {
						message = "no";
					}

				}

			}

			boolean result = false;
			if (message != "no") {
				result = vmodel.getButtonAction(type, query, obj);
			}

			if (result) {

				JOptionPane.showMessageDialog(f, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);

				if (comboBox != null) {
					updateComboBox(comboBox, value, ops);
				}

			}

			else {

				if (type == 1) {
					JOptionPane.showMessageDialog(f, "Duplicate entry - Data Already Exists !", "Warning",
							JOptionPane.WARNING_MESSAGE);

				}

				else if (type == 2) {
					JOptionPane.showMessageDialog(f, "Nothing to Delete..", "No changes made",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(f, "Nothing to save..", "No changes made",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}

		}

	}

	private MyCurrency getCurrencyObject(String selectedItem) {

		String[] set = selectedItem.split("-");

		return new MyCurrency(set[0], set[1]);
	}

	@Override
	public void updateComboBox(String comboBox, String value, String ops) {

		if (ops.equals("add")) {
			if (comboBox.equals("category")) {
				this.categoryList.addItem(value);
				this.categoryList.setSelectedIndex(0);

				this.chooseCategory.addItem(value);
				this.chooseCategory.setSelectedIndex(0);

				this.newCategory.setText("");
			} else if (comboBox.equals("currency")) {
				this.currencyList.addItem(value);
				this.currencyList.setSelectedIndex(0);

				this.chooseCurrency.addItem(value);
				this.chooseCurrency.setSelectedIndex(0);

				this.newCurrency.setText("");
				this.newSymbol.setText("");
			}

		} else if (ops.equals("del")) {
			if (comboBox.equals("category")) {
				this.categoryList.removeItem(value);
				this.categoryList.setSelectedIndex(0);

				this.chooseCategory.removeItem(value);
				this.chooseCategory.setSelectedIndex(0);

				this.newCategory.setText("");
			} else if (comboBox.equals("currency")) {
				this.currencyList.removeItem(value);
				this.currencyList.setSelectedIndex(0);

				this.chooseCurrency.removeItem(value);
				this.chooseCurrency.setSelectedIndex(0);

				this.newCurrency.setText("");
				this.newSymbol.setText("");
			}

		} else if (ops.equals("edit")) {

			if (comboBox.equals("category")) {
				this.categoryList.addItem(this.editCategoryField.getText());
				this.categoryList.removeItem(value);
				this.categoryList.setSelectedIndex(0);

				this.chooseCategory.removeItem(value);
				this.chooseCategory.addItem(this.editCategoryField.getText());
				this.chooseCategory.setSelectedIndex(0);

				this.editCategoryField.setText("");
			} else if (comboBox.equals("currency")) {
				this.currencyList.removeItem(value);
				this.currencyList
						.addItem(this.editCurrencyNameField.getText() + "-" + this.editCurrencySymbolField.getText());
				this.currencyList.setSelectedIndex(0);

				this.chooseCurrency.removeItem(value);
				this.chooseCurrency
						.addItem(this.editCurrencyNameField.getText() + "-" + this.editCurrencySymbolField.getText());
				this.chooseCurrency.setSelectedIndex(0);

				this.editCurrencyNameField.setText("");
				this.editCurrencySymbolField.setText("");
			}

		}
		
		this.saveCategory.setEnabled(false);
		this.saveCurrency.setEnabled(false);
		this.saveNewCategory.setEnabled(false);
		this.saveNewCurrency.setEnabled(false);
	}
}
