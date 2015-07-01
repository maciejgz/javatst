package pl.mg.javatst.unba;

public class AccessFee {
	private String ID         = "";
	private String des        = "";
	private String advance    = "";
	private String past       = "";
	private String correction = "";
	public String getAdvance() {
		return advance;
	}
	public void setAdvance(String advance) {
		this.advance = advance;
	}
	public String getCorrection() {
		return correction;
	}
	public void setCorrection(String correction) {
		this.correction = correction;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getPast() {
		return past;
	}
	public void setPast(String past) {
		this.past = past;
	}
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	
}
