/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

/**
 * @author arjun
 *
 */
public class PaymentInfo {

	private final double custAccountBalance;
	private final double transactionAmount;
	private final float transactionFeeRate;
	private final boolean transactionStatus;
	
	/**
	 * 
	 */
	public PaymentInfo() {
		this.custAccountBalance = 0d;
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
	public PaymentInfo(double custAccountBalance, double transactionAmount, float transactionFeeRate, 
			boolean transactionStatus) {
		this.custAccountBalance = custAccountBalance;
		this.transactionAmount = transactionAmount;
		this.transactionFeeRate = transactionFeeRate;
		this.transactionStatus = transactionStatus;
	}

	
	
	/**
	 * @return the custAccountBalance
	 */
	public double getCustAccountBalance() {
		return custAccountBalance;
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
		return new PaymentInfo(custAccountBalance, transactionAmount, feePercent, transactionStatus); 
	}
	
	public PaymentInfo genNewPaymentInfo(boolean status) {
		return new PaymentInfo(custAccountBalance, transactionAmount, transactionFeeRate, status); 
	}
	
	public PaymentInfo genNewPaymentInfo(float feePercent, boolean status) {
		return new PaymentInfo(custAccountBalance, transactionAmount, feePercent, status); 
	}
}
