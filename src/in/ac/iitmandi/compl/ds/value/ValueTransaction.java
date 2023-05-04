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
	private TransactionInfo paymentInfo;
	private PaymentInfo feeInfo;
	private DummyInlineClassInt dummyField;
	
	
	/**
	 * 
	 */
	public ValueTransaction() {
		this.transactionID = "";
		this.custDetails = new CustomerDetails();
		this.paymentInfo = new TransactionInfo();
		this.feeInfo = new PaymentInfo();
		this.dummyField = new DummyInlineClassInt();
	}

	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public ValueTransaction(String transactionID, CustomerDetails custDetails, TransactionInfo transInfo, PaymentInfo payInfo) {
		this.transactionID = transactionID;
		this.custDetails = custDetails;
		this.paymentInfo = transInfo;
		this.feeInfo = payInfo;
		this.dummyField = new DummyInlineClassInt(4,9,2);
	}
	
	public double getTransactionAmount() {
		return this.getPaymentInfo().getTransactionAmount();
	}

	public double getCustAcctBalance() {
		return this.getPaymentInfo().getCustAccountBalance();
	}

	public boolean getTransactionStatus() {
		return this.getFeeInfo().isTransactionStatus();
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
		return new PaymentInfo(processingFee, this.getFeeInfo().getTransactionFeeRate(), false); 
	}

	public void resetFeeInfo(PaymentInfo paymentInfo) {
		this.setFeeInfo(paymentInfo);
	}

	public void updateTransactionStatus(boolean status) {
		PaymentInfo pInfo = new PaymentInfo(this.getFeeInfo().getTransactionAmount(), this.getFeeInfo().getTransactionFeeRate(), status);
		this.setFeeInfo(pInfo);
	}

	public ValueTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		TransactionInfo ti = createValueTransactionInfo(result);
		PaymentInfo pi = createValuePaymentInfo(result);
		return new ValueTransaction(result.getTransactionID(), cDetails, ti,pi);
	}
	
	
	private TransactionInfo createValueTransactionInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		return new TransactionInfo(cAccBalance,result.getTransactionAmount());
	}
	
	private PaymentInfo createValuePaymentInfo(JSONResult result) {
		return new PaymentInfo(result.getTransactionAmount(),0,false);
	}
	
	/**
	 * @return the paymentInfo
	 */
	public TransactionInfo getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(TransactionInfo paymentInfo) {
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

	/**
	 * @return the dummyField
	 */
	public DummyInlineClassInt getDummyField() {
		return dummyField;
	}

	/**
	 * @param dummyField the dummyField to set
	 */
	public void setDummyField(DummyInlineClassInt dummyField) {
		this.dummyField = dummyField;
	}

}
