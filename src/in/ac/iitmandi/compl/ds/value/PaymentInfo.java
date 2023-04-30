/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

/**
 * @author arjun
 *
 */
public class PaymentInfo {

	private final double CustAccountBalance;
	private final double TransactionAmount;
	private final float TransactionFeeRate;
	private final boolean TransactionStatus;
//	public final double TransactionFee;
	
	/**
	 * 
	 */
	public PaymentInfo() {
		this.CustAccountBalance = 0d;
		this.TransactionAmount = 0d;
		this.TransactionFeeRate = 0f;
		this.TransactionStatus = false;
//		this.TransactionFee = 0d;
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
		CustAccountBalance = custAccountBalance;
		TransactionAmount = transactionAmount;
		TransactionFeeRate = transactionFeeRate;
		TransactionStatus = transactionStatus;
	}

	
	
	/**
	 * @return the custAccountBalance
	 */
	public double getCustAccountBalance() {
		return CustAccountBalance;
	}

	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return TransactionAmount;
	}

	/**
	 * @return the transactionFeeRate
	 */
	public float getTransactionFeeRate() {
		return TransactionFeeRate;
	}

	/**
	 * @return the transactionStatus
	 */
	public boolean isTransactionStatus() {
		return TransactionStatus;
	}
	
	public PaymentInfo genNewPaymentInfo(float feePercent) {
		return new PaymentInfo(CustAccountBalance, TransactionAmount, feePercent, TransactionStatus); 
	}
	
	public PaymentInfo genNewPaymentInfo(boolean status) {
		return new PaymentInfo(CustAccountBalance, TransactionAmount, TransactionFeeRate, status); 
	}
	
	public PaymentInfo genNewPaymentInfo(float feePercent, boolean status) {
		return new PaymentInfo(CustAccountBalance, TransactionAmount, feePercent, status); 
	}
}
