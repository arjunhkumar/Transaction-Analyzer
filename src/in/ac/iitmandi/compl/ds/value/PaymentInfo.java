/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

/**
 * @author arjun
 *
 */
public class PaymentInfo {

	private double transactionAmount;
	private float transactionFeeRate;
	private boolean transactionStatus;
	
	/**
	 * 
	 */
	public PaymentInfo() {
		this.transactionAmount = 0d;
		this.transactionFeeRate = 0f;
		this.transactionStatus = false;
	}

	/**
	 * @param custAccountBalance
	 * @param transactionDate
	 * @param transactionTime
	 * @param transactionAmount
	 * @param transactionFeeRate
	 * @param transactionStatus
	 */
	public PaymentInfo(double amount, float feeRate, boolean status) {
		transactionFeeRate = feeRate;
		transactionStatus = status;
		transactionAmount = amount;
	}
	
	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}

	/**
	 * @return the transactionFeeRate
	 */
	public float getTransactionFeeRate() {
		return transactionFeeRate;
	}

	/**
	 * @return the transactionStatus
	 */
	public boolean isTransactionStatus() {
		return transactionStatus;
	}

	public PaymentInfo genNewPaymentInfo(float feePercent) {
		return new PaymentInfo(transactionAmount,feePercent, transactionStatus); 
	}
	
	public PaymentInfo genNewPaymentInfo(boolean status) {
		return new PaymentInfo(transactionAmount,transactionFeeRate, status); 
	}
	
	public PaymentInfo genNewPaymentInfo(float feePercent, boolean status) {
		return new PaymentInfo(transactionAmount, feePercent, status); 
	}
}
