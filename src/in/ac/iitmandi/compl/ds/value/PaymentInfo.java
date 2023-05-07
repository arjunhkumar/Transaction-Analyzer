/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

/**
 * @author arjun
 *
 */
public primitive  class PaymentInfo {

	private final double transactionAmount;
	private final float transactionFeeRate;
	private final boolean transactionStatus;
//	private double dummyField1;
//	private double dummyField2;
//	private double dummyField3;
	
	/**
	 * 
	 */
	public PaymentInfo() {
		this.transactionAmount = 0d;
		this.transactionFeeRate = 0f;
		this.transactionStatus = false;
//		this.dummyField1 = 0d;
//		this.dummyField2 = 0d;
//		this.dummyField3 = 0d;
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
		this.transactionFeeRate = feeRate;
		this.transactionStatus = status;
		this.transactionAmount = amount;
//		this.dummyField1 = amount + 6d ;
//		this.dummyField2 = amount * 6d ;
//		this.dummyField3 = amount - 6d ;
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

//	/**
//	 * @return the dummyField1
//	 */
//	public double getDummyField1() {
//		return dummyField1;
//	}
//
//	/**
//	 * @return the dummyField2
//	 */
//	public double getDummyField2() {
//		return dummyField2;
//	}
//
//	/**
//	 * @return the dummyField3
//	 */
//	public double getDummyField3() {
//		return dummyField3;
//	}
	
	
	
}
