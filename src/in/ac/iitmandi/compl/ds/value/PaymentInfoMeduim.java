/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

import in.ac.iitmandi.compl.ds.AbstractPayment;

/**
 * @author arjun
 *
 */
public primitive class PaymentInfoMeduim extends AbstractPayment{

	private final double CustAccountBalance;
	private final int TransactionDate;
	private final int TransactionTime;
	private final double TransactionAmount;
	private final float TransactionFeeRate;
	private final boolean TransactionStatus;
	private final double unusedField1;
	private final double unusedField2;
	private final double unusedField3;
	private final double unusedField4;
	private final double unusedField5;
	private final double unusedField6;
	private final double unusedField7;
	private final double unusedField8;
	private final double unusedField9;
//	public final double TransactionFee;
	
	/**
	 * 
	 */
	public PaymentInfoMeduim() {
		this.CustAccountBalance = 0d;
		this.TransactionDate = 0;
		this.TransactionTime = 0;
		this.TransactionAmount = 0d;
		this.TransactionFeeRate = 0f;
		this.TransactionStatus = false;
		this.unusedField1 = 9d;
		this.unusedField2 = 9d;
		this.unusedField3 = 9d;
		this.unusedField4  = 9d;
		this.unusedField5 = 9d;
		this.unusedField6 = 9d;
		this.unusedField7 = 9d;
		this.unusedField8 = 9d;
		this.unusedField9 = 9d;
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
	public PaymentInfoMeduim(double custAccountBalance, int transactionDate, int transactionTime, double transactionAmount,
			float transactionFeeRate, boolean transactionStatus) {
		CustAccountBalance = custAccountBalance;
		TransactionDate = transactionDate;
		TransactionTime = transactionTime;
		TransactionAmount = transactionAmount;
		TransactionFeeRate = transactionFeeRate;
		TransactionStatus = transactionStatus;
		this.unusedField1 = 9d;
		this.unusedField2 = 9d;
		this.unusedField3 = 9d;
		this.unusedField4  = 9d;
		this.unusedField5 = 9d;
		this.unusedField6 = 9d;
		this.unusedField7 = 9d;
		this.unusedField8 = 9d;
		this.unusedField9 = 9d;
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
	
	/**
	 * @return the unusedField1
	 */
	public double getUnusedField1() {
		return unusedField1;
	}

	/**
	 * @return the unusedField2
	 */
	public double getUnusedField2() {
		return unusedField2;
	}

	/**
	 * @return the unusedField3
	 */
	public double getUnusedField3() {
		return unusedField3;
	}

	/**
	 * @return the unusedField4
	 */
	public double getUnusedField4() {
		return unusedField4;
	}

	/**
	 * @return the unusedField5
	 */
	public double getUnusedField5() {
		return unusedField5;
	}

	/**
	 * @return the unusedField6
	 */
	public double getUnusedField6() {
		return unusedField6;
	}

	/**
	 * @return the unusedField7
	 */
	public double getUnusedField7() {
		return unusedField7;
	}

	/**
	 * @return the unusedField8
	 */
	public double getUnusedField8() {
		return unusedField8;
	}

	/**
	 * @return the unusedField9
	 */
	public double getUnusedField9() {
		return unusedField9;
	}

	public PaymentInfoMeduim genNewPaymentInfo(float feePercent) {
		return new PaymentInfoMeduim(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, feePercent, TransactionStatus); 
	}
	
	public PaymentInfoMeduim genNewPaymentInfo(boolean status) {
		return new PaymentInfoMeduim(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, TransactionFeeRate, status); 
	}
	
	public PaymentInfoMeduim genNewPaymentInfo(float feePercent, boolean status) {
		return new PaymentInfoMeduim(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, feePercent, status); 
	}
	
//	public PaymentInfo genNewPaymentInfo(double fee, boolean status) {
//		return new PaymentInfo(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, TransactionFeeRate, status,fee); 
//	}
	
}
