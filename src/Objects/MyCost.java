package Objects;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCost implements Cost {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double value;
	private String category;
	private Date date;
	private MyCurrency currency;
	private  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public MyCost(double value, String category, Date date, MyCurrency currency) {
		
		this.setValue(value);
		this.setCategory(category);
		this.setDate(date);
		this.setCurrency(currency);
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		
		this.value = value;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MyCurrency getCurrency() {
		return currency;
	}
	public void setCurrency(MyCurrency currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return value + " - " + category + " - " + formatter.format(date) + " - " + currency.toString();
	}
	

	
	
	
	
	
	
	

}
