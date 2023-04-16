/**
 * 
 */
package in.ac.iitmandi.compl.ds;

import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public abstract class AbstractTransaction {

	/**
	 * 
	 */
	protected String TransactionID;
	protected CustomerDetails custDetails;
	
	/**
	 * 
	 */
	public AbstractTransaction() {
		// TODO Auto-generated constructor stub
	}

	public AbstractTransaction(String transactionID, CustomerDetails custDetails) {
		this.TransactionID = transactionID;
		this.custDetails = custDetails;
	}

	public abstract double getTransactionAmount();
	public abstract double getCustAcctBalance();
	public abstract double computeFieldSum(int n_iterations);
	public abstract boolean getTransactionStatus();
	public abstract AbstractPayment createNewPaymentObject(double processingFee);
	public abstract void resetFeeInfo(AbstractPayment paymentInfo);
	public abstract void updateTransactionStatus(boolean status);
	public abstract AbstractTransaction convertToTransactionObject(JSONResult resultData);
	
	protected void computeAverageTime(long iterTime) {
		CommonUtils.averageTime+=iterTime;
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
	
}
