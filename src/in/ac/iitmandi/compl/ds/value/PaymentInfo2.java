/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

/**
 * @author arjun
 *
 */
public primitive class PaymentInfo2 {

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
	private final double unusedField10;
	private final double unusedField11;
	private final double unusedField12;
	private final double unusedField13;
	private final double unusedField14;
	private final double unusedField15;
	private final double unusedField16;
	private final double unusedField17;
	private final double unusedField18;
	private final double unusedField19;
//	public final double TransactionFee;
	
	/**
	 * 
	 */
	public PaymentInfo2() {
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
		this.unusedField10 = 9d;
		this.unusedField11 = 9d;
		this.unusedField12 = 9d;
		this.unusedField13 = 9d;
		this.unusedField14  = 9d;
		this.unusedField15 = 9d;
		this.unusedField16 = 9d;
		this.unusedField17 = 9d;
		this.unusedField18 = 9d;
		this.unusedField19 = 9d;
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
	public PaymentInfo2(double custAccountBalance, int transactionDate, int transactionTime, double transactionAmount,
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
		this.unusedField10 = 9d;
		this.unusedField11 = 9d;
		this.unusedField12 = 9d;
		this.unusedField13 = 9d;
		this.unusedField14  = 9d;
		this.unusedField15 = 9d;
		this.unusedField16 = 9d;
		this.unusedField17 = 9d;
		this.unusedField18 = 9d;
		this.unusedField19 = 9d;
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
	
	public PaymentInfo2 genNewPaymentInfo(float feePercent) {
		return new PaymentInfo2(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, feePercent, TransactionStatus); 
	}
	
	public PaymentInfo2 genNewPaymentInfo(boolean status) {
		return new PaymentInfo2(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, TransactionFeeRate, status); 
	}
	
	public PaymentInfo2 genNewPaymentInfo(float feePercent, boolean status) {
		return new PaymentInfo2(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, feePercent, status); 
	}
	
//	public PaymentInfo genNewPaymentInfo(double fee, boolean status) {
//		return new PaymentInfo(CustAccountBalance, TransactionDate, TransactionTime, TransactionAmount, TransactionFeeRate, status,fee); 
//	}
	
}
