/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

import in.ac.iitmandi.compl.ds.CustomerDetails;
import in.ac.iitmandi.compl.ds.JSONResult;

/**
 * @author arjun
 *
 */
public class ValueTransaction {

	private String transactionID;
	private CustomerDetails custDetails;
	private PaymentInfo paymentInfo;
	private PaymentInfo feeInfo;
	/**
	 * 
	 */
	public ValueTransaction() {
		this.transactionID = "";
		this.custDetails = new CustomerDetails();
		this.paymentInfo = new PaymentInfo();
		this.feeInfo = new PaymentInfo();
	}

	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public ValueTransaction(String transactionID, CustomerDetails custDetails, PaymentInfo paymentInfo) {
		this.transactionID = transactionID;
		this.custDetails = custDetails;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new PaymentInfo(paymentInfo.getCustAccountBalance(),0,paymentInfo.getTransactionFeeRate(),false);
	}
	
	public double getTransactionAmount() {
		return this.getPaymentInfo().getTransactionAmount();
	}

	public double getCustAcctBalance() {
		return this.getPaymentInfo().getCustAccountBalance();
	}

	public boolean getTransactionStatus() {
		return this.getPaymentInfo().isTransactionStatus();
	}

	/**
	 * @return the transactionID
	 */
	public String getTransactionID() {
		return transactionID;
	}

	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	/**
	 * @return the custDetails
	 */
	public CustomerDetails getCustDetails() {
		return custDetails;
	}

	/**
	 * @param custDetails the custDetails to set
	 */
	public void setCustDetails(CustomerDetails custDetails) {
		this.custDetails = custDetails;
	}

	public PaymentInfo createNewPaymentObject(double processingFee) {
		return new PaymentInfo(this.getPaymentInfo().getCustAccountBalance(), processingFee, this.getPaymentInfo().getTransactionFeeRate(), false); 
	}

	public void resetFeeInfo(PaymentInfo paymentInfo) {
		if(paymentInfo instanceof PaymentInfo) {
			this.setFeeInfo((PaymentInfo)paymentInfo);
		}
	}

	public void updateTransactionStatus(boolean status) {
		PaymentInfo pInfo = new PaymentInfo(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionAmount(), this.getPaymentInfo().getTransactionFeeRate(), status);
		this.setFeeInfo(pInfo);
	}

	public ValueTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfo pi = createValuePaymentInfo(result);
		return new ValueTransaction(result.getTransactionID(), cDetails, pi);
	}
	
	public double computeFieldSum(int n_iterations) {
		return this.getFieldSum(n_iterations);
	}
	
	private PaymentInfo createValuePaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		return new PaymentInfo(cAccBalance,result.getTransactionAmount(), 0, false);
	}
	
	private double getFieldSum(int iterVal) {
		double sum = 0;
		for(int i =0; i<iterVal;i++) {
			sum += this.getPaymentInfo().getCustAccountBalance();
			sum += this.getPaymentInfo().getTransactionAmount();
			sum += this.getPaymentInfo().getTransactionFeeRate();
		}
		return sum;
	}
	
	/**
	 * @return the paymentInfo
	 */
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}


	/**
	 * @return the feeInfo
	 */
	public PaymentInfo getFeeInfo() {
		return feeInfo;
	}

	/**
	 * @param feeInfo the feeInfo to set
	 */
	public void setFeeInfo(PaymentInfo feeInfo) {
		this.feeInfo = feeInfo;
	}

}
