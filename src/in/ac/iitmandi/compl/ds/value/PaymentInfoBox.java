/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

/**
 * @author arjun
 *
 */
public class PaymentInfoBox {

	/**
	 * 
	 */
	private PaymentInfoField paymentInfo;
	
	public PaymentInfoBox(double custAccountBalance, int transactionDate, int transactionTime, double transactionAmount,
			float transactionFeeRate, boolean transactionStatus) {
		this.paymentInfo = new PaymentInfoField(custAccountBalance, transactionDate, transactionTime, transactionAmount, transactionFeeRate, transactionStatus);
	}
	
	/**
	 * @param paymentInfo
	 */
	public PaymentInfoBox(PaymentInfoField paymentInfo) {
		this.paymentInfo = paymentInfo;
	}


	/**
	 * @return the paymentInfo
	 */
	public PaymentInfoField getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(PaymentInfoField paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	
}
