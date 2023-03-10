/**
 * 
 */
package in.ac.iitmandi.compl.ds.nonvalue;

import in.ac.iitmandi.compl.ds.AbstractPayment;

/**
 * @author arjun
 *
 */
public class NonValuePaymentBoxBox extends AbstractPayment{

	NonValuePaymentBox paymentInfo;
	
	/**
	 * @param paymentInfo
	 */
	public NonValuePaymentBoxBox(NonValuePaymentBox paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	/**
	 * 
	 */
	public NonValuePaymentBoxBox(double custAccountBalance, int transactionDate, int transactionTime, double transactionAmount,
			float transactionFeeRate, boolean transactionStatus) {
		this.paymentInfo = new NonValuePaymentBox(custAccountBalance, transactionDate, transactionTime, transactionAmount, transactionFeeRate, transactionStatus);
	}

	/**
	 * @return the paymentInfo
	 */
	public NonValuePaymentBox getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(NonValuePaymentBox paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	/**
	 * @return the custAccountBalance
	 */
	public double getCustAccountBalance() {
		return this.getPaymentInfo().getPaymentInfo().getCustAccountBalance();
	}

	/**
	 * @return the transactionDate
	 */
	public int getTransactionDate() {
		return this.getPaymentInfo().getPaymentInfo().getTransactionDate();
	}

	/**
	 * @return the transactionTime
	 */
	public int getTransactionTime() {
		return this.getPaymentInfo().getPaymentInfo().getTransactionTime();
	}

	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return this.getPaymentInfo().getPaymentInfo().getTransactionAmount();
	}

	/**
	 * @return the transactionFeeRate
	 */
	public float getTransactionFeeRate() {
		return this.getPaymentInfo().getPaymentInfo().getTransactionFeeRate();
	}

	/**
	 * @return the transactionStatus
	 */
	public boolean isTransactionStatus() {
		return this.getPaymentInfo().getPaymentInfo().isTransactionStatus();
	}
	
}
