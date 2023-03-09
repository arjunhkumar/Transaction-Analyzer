/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

import in.ac.iitmandi.compl.ds.AbstractPayment;

/**
 * @author arjun
 *
 */
public class PaymentInfoSmall extends AbstractPayment {

	private final int CustAccountBalance;
	private final int TransactionDate;
	private final int TransactionTime;
	private final int TransactionAmount;
	private final int TransactionFeeRate;
	private final boolean TransactionStatus;
//	public final double TransactionFee;
	
	/**
	 * 
	 */
	public PaymentInfoSmall() {
		this.CustAccountBalance = 0;
		this.TransactionDate = 0;
		this.TransactionTime = 0;
		this.TransactionAmount = 0;
		this.TransactionFeeRate = 0;
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
	public PaymentInfoSmall(double custAccountBalance, int transactionDate, int transactionTime, double transactionAmount,
			float transactionFeeRate, boolean transactionStatus) {
		Double d = custAccountBalance;
		CustAccountBalance = d.intValue();
		TransactionDate = transactionDate;
		TransactionTime = transactionTime;
		d = transactionAmount;
		TransactionAmount = d.intValue();
		Float f = transactionFeeRate;
		TransactionFeeRate = f.intValue();
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
	
	public PaymentInfoSmall genNewPaymentInfo(float feePercent) {
		return new PaymentInfoSmall(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, feePercent, TransactionStatus); 
	}
	
	public PaymentInfoSmall genNewPaymentInfo(boolean status) {
		return new PaymentInfoSmall(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, TransactionFeeRate, status); 
	}
	
	public PaymentInfoSmall genNewPaymentInfo(float feePercent, boolean status) {
		return new PaymentInfoSmall(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, feePercent, status); 
	}
	
//	public PaymentInfo genNewPaymentInfo(double fee, boolean status) {
//		return new PaymentInfo(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, TransactionFeeRate, status,fee); 
//	}
	
}
