/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

import in.ac.iitmandi.compl.ds.AbstractPayment;

/**
 * @author arjun
 *
 */
public class PaymentInfoBoxBox extends AbstractPayment{

	/**
	 * 
	 */
	private PaymentInfoBox paymentInfo;
	
	public PaymentInfoBoxBox(double custAccountBalance, int transactionDate, int transactionTime, double transactionAmount,
			float transactionFeeRate, boolean transactionStatus) {
		this.paymentInfo = new PaymentInfoBox(custAccountBalance, transactionDate, transactionTime, transactionAmount, transactionFeeRate, transactionStatus);
	}
	
	/**
	 * @param paymentInfo
	 */
	public PaymentInfoBoxBox(PaymentInfoBox paymentInfo) {
		this.paymentInfo = paymentInfo;
	}


	/**
	 * @return the paymentInfo
	 */
	public PaymentInfoBox getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(PaymentInfoBox paymentInfo) {
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
