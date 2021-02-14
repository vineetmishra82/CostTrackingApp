package Objects;
import java.io.Serializable;
import java.util.Date;

public interface Cost extends Serializable {

	public double getValue();
	public void setValue(double value);
	public String getCategory();
	public void setCategory(String category);
	public Date getDate();
	public void setDate(Date date);
	public MyCurrency getCurrency();
	public void setCurrency(MyCurrency currency);
}
