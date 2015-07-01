package pl.mg.javatst.unba;
/**
 * Class contains Credit Types constans
 * @author Geomant Inc. Pawel Biedronski
 */
public class CreditType {
	public static final short MINUTES  = 1;
	public static final short SMS      = 2;
	public static final short MMS      = 3;
	public static final short BYTES    = 4;
	public static final short CURRENCY = 5;
	/**
	 * Gets description of choosen Credit Type
	 * @param type of credit
	 * @return text description of Credit Type
	 */
	public final static String getNameXML(short type){
		String result = null;
		switch (type) {
		case MINUTES:
			result = "credits_minutes";
			break;
		case SMS:
			result = "credits_sms";
			break;
		case MMS:
			result = "credits_mms";
			break;
		case BYTES:
			result = "credits_bytes";
			break;
		case CURRENCY:
			result = "credits_currency";
			break;
		default:
			break;
		}
		return result;
	}
	
}
