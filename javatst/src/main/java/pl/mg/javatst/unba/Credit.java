package pl.mg.javatst.unba;
/**
 * This class prezents Credit Object
 * @author GEO Pawel Biedronski
 */
public class Credit {
	private String ID     = null;
	private String name   = null;
	private String amount = null;
	private String unit   = "";
	/**
	 * Gets amount of Credit
	 * @return abount of Credit
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @return Credit Amount
	 */	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return Credit Name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets Credit Name
	 * @param name of credit
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Credit ID
	 */
	public String getId() {
		return ID;
	}
	/**
	 * Sets Credit ID
	 * @param id od Credit
	 */
	public void setID(String id) {
		this.ID = id;
	}
	
	/**
	 * Gets information about Credit object completity
	 * @return true/false
	 */
	public boolean isComplete(){
		return (ID!=null) && (name!=null) && (amount!=null);
	}
	/**
	 * @return unit od Credit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * Sets unit of Credit
	 * @param unit of Credit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}	
	/**
	 * Gets Credit object description for loging purpouse
	 * @return description of Credit object 
	 */
	public String toString(){
		return "Credit [ID="+ID+ ", Name="+name +",amount= "+amount+",unit="+unit+"]";		
	}	
}
