package Objects;
import java.io.Serializable;

public interface Currency extends Serializable {
	
	public String getCurrName();
	public void setCurrName(String currName);
	public String getCurrSymbol();
	public void setCurrSymbol(String currSymbol);
	
	}
