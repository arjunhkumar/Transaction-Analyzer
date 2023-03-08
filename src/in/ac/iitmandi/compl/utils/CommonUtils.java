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
import in.ac.iitmandi.compl.ds.value.PaymentInfo2;
import in.ac.iitmandi.compl.ds.value.PaymentInfo3;
import in.ac.iitmandi.compl.ds.value.ValueTransaction;
import in.ac.iitmandi.compl.ds.value.ValueTransaction2;
import in.ac.iitmandi.compl.ds.value.ValueTransaction3;

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
	public static ValueTransaction2 convertToVT2(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfo2 pi = INSTANCE.createPaymentInfo2(result);
		return new ValueTransaction2(result.getTransactionID(), cDetails, pi);
	}
	public static ValueTransaction3 convertToVT3(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfo3 pi = INSTANCE.createPaymentInfo3(result);
		return new ValueTransaction3(result.getTransactionID(), cDetails, pi);
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
	
	private PaymentInfo2 createPaymentInfo2(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new PaymentInfo2(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
	}
	
	private PaymentInfo3 createPaymentInfo3(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new PaymentInfo3(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
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
