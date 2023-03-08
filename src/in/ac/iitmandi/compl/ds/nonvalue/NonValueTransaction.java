/**
 * 
 */
package in.ac.iitmandi.compl.ds.nonvalue;

import in.ac.iitmandi.compl.ds.CustomerDetails;

/**
 * @author arjun
 *
 */
public class NonValueTransaction {

	private String TransactionID;
	private CustomerDetails custDetails;
	private NonValuePaymentInfo paymentInfo;
	private NonValuePaymentInfo feeInfo;
//	private boolean TransactionStatus;
//	private double TransactionFee;
	
	
	/**
	 * 
	 */
	public NonValueTransaction() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public NonValueTransaction(String transactionID, CustomerDetails custDetails, NonValuePaymentInfo paymentInfo) {
		this.TransactionID = transactionID;
		this.custDetails = custDetails;
//		this.TransactionStatus = transactionStatus;
//		this.TransactionFee = transactionFee;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new NonValuePaymentInfo(paymentInfo.getCustAccountBalance(),paymentInfo.getTransactionDate(),paymentInfo.getTransactionTime(),0,paymentInfo.getTransactionFeeRate(),false);
	}


	/**
	 * @return the transactionID
	 */
	public String getTransactionID() {
		return TransactionID;
	}


	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
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


//	/**
//	 * @return the transactionStatus
//	 */
//	public boolean isTransactionStatus() {
//		return TransactionStatus;
//	}
//
//
//	/**
//	 * @param transactionStatus the transactionStatus to set
//	 */
//	public void setTransactionStatus(boolean transactionStatus) {
//		TransactionStatus = transactionStatus;
//	}
//
//
//	/**
//	 * @return the transactionFee
//	 */
//	public double getTransactionFee() {
//		return TransactionFee;
//	}
//
//
//	/**
//	 * @param transactionFee the transactionFee to set
//	 */
//	public void setTransactionFee(double transactionFee) {
//		TransactionFee = transactionFee;
//	}


	/**
	 * @return the paymentInfo
	 */
	public NonValuePaymentInfo getPaymentInfo() {
		return paymentInfo;
	}


	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(NonValuePaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}


	/**
	 * @return the feeInfo
	 */
	public NonValuePaymentInfo getFeeInfo() {
		return feeInfo;
	}


	/**
	 * @param feeInfo the feeInfo to set
	 */
	public void setFeeInfo(NonValuePaymentInfo feeInfo) {
		this.feeInfo = feeInfo;
	}
	
}
