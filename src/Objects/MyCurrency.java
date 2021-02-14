package Objects;

public class MyCurrency implements Currency,Comparable<MyCurrency> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String currName;
	public String currSymbol;
	
	public MyCurrency(String currName, String currSymbol) {
		this.setCurrName(currName);
		this.setCurrSymbol(currSymbol);
	}
	
	@Override
	public String getCurrName() {
		return currName;
	}

	@Override
	public void setCurrName(String currName) {
		if(!currName.equals(null))
		{
			this.currName = currName;	
		}
		
	}

	@Override
	public String getCurrSymbol() {
		return currSymbol;
	}

	@Override
	public void setCurrSymbol(String currSymbol2) {
		
		if(currSymbol2!=null)
		{
		this.currSymbol = currSymbol2;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (this.getCurrName()+"-"+this.getCurrSymbol());
	}

	@Override
	public boolean equals(Object obj) {

		MyCurrency currency = (MyCurrency) obj;		
		
		return (currency.getCurrName().equals(this.getCurrName()) &&
				currency.getCurrSymbol().equals(this.getCurrSymbol()));
	}

	@Override
	public int compareTo(MyCurrency o) {

		if(this.equals(o))
		{
			return 0;
		}
		return -1;
	}
	
	
	
	
	
	
	
	

}
