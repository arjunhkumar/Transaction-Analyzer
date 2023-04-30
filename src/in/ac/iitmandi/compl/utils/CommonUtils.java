/**
 * 
 */
package in.ac.iitmandi.compl.utils;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import in.ac.iitmandi.compl.ds.Dataset;

/**
 * @author arjun
 *
 */
public class CommonUtils {

	public static final String JSON_PATH = "./dataset/transactions_formatted.json";
	public static int ITER_SIZE = 0;
	public static long averageTime = 0;
	private static final String PREPENDERRORVAL = "Error : ";
	private static final String PREPENDLOGVAL = "Log : ";
	public static final CommonUtils INSTANCE = new CommonUtils();
	
	public static boolean debugMode = true;
	
	public static final Integer NONVALUEBENCH = 1;
	public static final Integer VALUEBENCH = 2;
	public static final Integer INTERMEDIATEVALUEBENCH = 3;
	public static final Integer VALUEBENCHLARGE = 4;
	public static final Integer VALUEBENCHMEDUIM = 5;
	public static final Integer VALUEBENCHSMALL = 6;
	public static final Integer VALUEBENCHXLARGE = 7;
	
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
	
	public static void printLog(String msg) {
		if(debugMode) {
			System.out.println(CommonUtils.generateLogMsg(msg));
		}
	}
	
	public static boolean validateArgs(String[] args) {
		if(! (args.length == 1 || args.length == 2)) {
			System.out.println(CommonUtils.generateErrorMsg("No. of arguments is incorrect."));
			System.out.println(CommonUtils.generateErrorMsg("Exiting without executing."));
			return false;
		}
		String iterSize = args[0];
		int iterVal = Integer.parseInt(iterSize);
		CommonUtils.ITER_SIZE = iterVal;
		if(args.length == 2) {
			String debugMode = args[1];
			if("true".equalsIgnoreCase(debugMode)) {
				CommonUtils.debugMode = true;
			}
		}
		return true;
	}

	public static Dataset loadDataSet() {
		Dataset ds = null;
        Gson gson = new Gson();
        try (FileReader fReader = new FileReader(CommonUtils.JSON_PATH)) {
        	ds = gson.fromJson(fReader, Dataset.class);
        } catch (IOException e) {
        	CommonUtils.generateLogMsg(e.getMessage());
			e.printStackTrace();
		} 
        return ds;
	}
	
}
