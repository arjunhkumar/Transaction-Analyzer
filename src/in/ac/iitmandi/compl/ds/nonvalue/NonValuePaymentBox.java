/**
 * 
 */
package in.ac.iitmandi.compl.ds.nonvalue;

/**
 * @author arjun
 *
 */
public class NonValuePaymentBox {

	/**
	 * 
	 */
	NonValuePaymentInfoField paymentInfo;
	
	/**
	 * @param paymentInfo
	 */
	public NonValuePaymentBox(NonValuePaymentInfoField paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public NonValuePaymentBox(double custAccountBalance, int transactionDate, int transactionTime, double transactionAmount,
			float transactionFeeRate, boolean transactionStatus) {
		this.paymentInfo =  new NonValuePaymentInfoField(custAccountBalance,transactionDate,transactionTime,transactionAmount,transactionFeeRate,transactionStatus);
	}

	/**
	 * @return the paymentInfo
	 */
	public NonValuePaymentInfoField getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(NonValuePaymentInfoField paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

}
