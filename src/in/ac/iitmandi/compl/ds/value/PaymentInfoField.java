/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

import in.ac.iitmandi.compl.ds.AbstractPayment;

/**
 * @author arjun
 *
 */
public primitive class PaymentInfoField {

	private final double CustAccountBalance;
	private final int TransactionDate;
	private final int TransactionTime;
	private final double TransactionAmount;
	private final float TransactionFeeRate;
	private final boolean TransactionStatus;
//	public final double TransactionFee;
	
	/**
	 * 
	 */
	public PaymentInfoField() {
		this.CustAccountBalance = 0d;
		this.TransactionDate = 0;
		this.TransactionTime = 0;
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
	public PaymentInfoField(double custAccountBalance, int transactionDate, int transactionTime, double transactionAmount,
			float transactionFeeRate, boolean transactionStatus) {
		CustAccountBalance = custAccountBalance;
		TransactionDate = transactionDate;
		TransactionTime = transactionTime;
		TransactionAmount = transactionAmount;
		TransactionFeeRate = transactionFeeRate;
		TransactionStatus = transactionStatus;
//		TransactionFee = transactionFee;
	}

	
	
	/**
	 * @return the custAccountBalance
	 */
	public double getCustAccountBalance() {
		return CustAccountBalance;
	}

	/**
	 * @return the transactionDate
	 */
	public int getTransactionDate() {
		return TransactionDate;
	}

	/**
	 * @return the transactionTime
	 */
	public int getTransactionTime() {
		return TransactionTime;
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
	
	public PaymentInfoField genNewPaymentInfo(float feePercent) {
		return new PaymentInfoField(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, feePercent, TransactionStatus); 
	}
	
	public PaymentInfoField genNewPaymentInfo(boolean status) {
		return new PaymentInfoField(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, TransactionFeeRate, status); 
	}
	
	public PaymentInfoField genNewPaymentInfo(float feePercent, boolean status) {
		return new PaymentInfoField(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, feePercent, status); 
	}
	
//	public PaymentInfo genNewPaymentInfo(double fee, boolean status) {
//		return new PaymentInfo(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, TransactionFeeRate, status,fee); 
//	}
	
}
