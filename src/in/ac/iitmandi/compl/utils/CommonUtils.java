/**
 * 
 */
package in.ac.iitmandi.compl.utils;


/**
 * @author arjun
 *
 */
public class CommonUtils {

	public static final String JSON_PATH = "./dataset/transactions_formatted.json";
	public static int ITER_SIZE = 0;
	private static final String PREPENDERRORVAL = "Error : ";
	private static final String PREPENDLOGVAL = "Log : ";
	public static final CommonUtils INSTANCE = new CommonUtils();
	
	public static final Integer NONVALUEBENCH = 1;
	public static final Integer VALUEBENCH = 2;
	public static final Integer INTERMEDIATEVALUEBENCH = 3;
	public static final Integer VALUEBENCHLARGE = 4;
	public static final Integer VALUEBENCHMEDUIM = 5;
	public static final Integer VALUEBENCHSMALL = 6;
	public static final Integer VALUEBENCHXLARGE = 7;
	public static final Integer VALUEBENCHBOXED = 8;
	public static final Integer NONVALUEBENCHBOXED = 9;
	
	public static String generateErrorMsg(String msg) {
		return PREPENDERRORVAL + msg;
	}
	
	public static String generateLogMsg(String msg) {
		return PREPENDLOGVAL + msg;
	}
	
	public static int formatDateString(String transactionDate) {
		if(null != transactionDate && "" != transactionDate) {
			String formattedString = transactionDate.replaceAll("/", "");
			return Integer.parseInt(formattedString);
		}
		return 0;
	}

}
