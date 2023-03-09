/**
 * 
 */
package in.ac.iitmandi.compl.utils;

import in.ac.iitmandi.compl.ds.CustomerDetails;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.ds.nonvalue.NonValuePaymentInfo;
import in.ac.iitmandi.compl.ds.nonvalue.NonValueTransaction;
import in.ac.iitmandi.compl.ds.value.IntermediateValueTransaction;
import in.ac.iitmandi.compl.ds.value.PaymentInfo;
import in.ac.iitmandi.compl.ds.value.PaymentInfoMeduim;
import in.ac.iitmandi.compl.ds.value.PaymentInfoXLarge;
import in.ac.iitmandi.compl.ds.value.ValueTransaction;
import in.ac.iitmandi.compl.ds.value.ValueTransactionXLarge;
import in.ac.iitmandi.compl.ds.value.ValueTransactionMeduim;

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
	
	public static String generateErrorMsg(String msg) {
		return PREPENDERRORVAL + msg;
	}
	
	public static String generateLogMsg(String msg) {
		return PREPENDLOGVAL + msg;
	}
	
	public static ValueTransaction convertToVT(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfo pi = INSTANCE.createPaymentInfo(result);
		return new ValueTransaction(result.getTransactionID(), cDetails, pi);
	}
	public static ValueTransactionXLarge convertToVT2(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfoXLarge pi = INSTANCE.createPaymentInfo2(result);
		return new ValueTransactionXLarge(result.getTransactionID(), cDetails, pi);
	}
	public static ValueTransactionMeduim convertToVT3(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfoMeduim pi = INSTANCE.createPaymentInfo3(result);
		return new ValueTransactionMeduim(result.getTransactionID(), cDetails, pi);
	}
	
	
	public static NonValueTransaction convertToNVT(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		NonValuePaymentInfo pi = createNonValuePaymentInfo(result);
		return new NonValueTransaction(result.getTransactionID(), cDetails, pi);
	}
	
	public static IntermediateValueTransaction convertToIVT(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfo pi = INSTANCE.createPaymentInfo(result);
		return new IntermediateValueTransaction(result.getTransactionID(), cDetails, pi);
	}

	private PaymentInfo createPaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new PaymentInfo(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
	}
	
	private PaymentInfoXLarge createPaymentInfo2(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new PaymentInfoXLarge(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
	}
	
	private PaymentInfoMeduim createPaymentInfo3(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new PaymentInfoMeduim(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
	}
	
	
	private static NonValuePaymentInfo createNonValuePaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new NonValuePaymentInfo(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
	}
	
//	private static PaymentInfo createPaymentInfo(JSONResult result, float feeRate) {
//		double cAccBalance = Double.parseDouble(result.getCustAccountBalance());
//		int paymentDate = formatDateString(result.getTransactionDate());
//		int paymentTime = result.getTransactionTime();
//		return new PaymentInfo(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), feeRate, false, 0);
//	}

//	private static void createPaymentInfo(JSONResult result, boolean status) {
//		double cAccBalance = Double.parseDouble(result.getCustAccountBalance());
//		int paymentDate = formatDateString(result.getTransactionDate());
//		int paymentTime = result.getTransactionTime();
//		PaymentInfo pi = new PaymentInfo(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), feeRate, false, 0);
//	}
	
	public static int formatDateString(String transactionDate) {
		if(null != transactionDate && "" != transactionDate) {
			String formattedString = transactionDate.replaceAll("/", "");
			return Integer.parseInt(formattedString);
		}
		return 0;
	}

}
