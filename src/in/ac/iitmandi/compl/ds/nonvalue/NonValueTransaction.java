/**
 * 
 */
package in.ac.iitmandi.compl.ds.nonvalue;

import in.ac.iitmandi.compl.ds.CustomerDetails;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.obsolete.AbstractPayment;
import in.ac.iitmandi.compl.obsolete.AbstractTransaction;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class NonValueTransaction extends AbstractTransaction{

	private NonValuePaymentInfo paymentInfo;
	private NonValuePaymentInfo feeInfo;
	
	
	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public NonValueTransaction(String transactionID, CustomerDetails custDetails, NonValuePaymentInfo paymentInfo) {
//		this.TransactionID = transactionID;
//		this.custDetails = custDetails;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new NonValuePaymentInfo(paymentInfo.getCustAccountBalance(),paymentInfo.getTransactionDate(),paymentInfo.getTransactionTime(),0,paymentInfo.getTransactionFeeRate(),false);
	}
	
	public NonValueTransaction() {
	}

	@Override
	public double getTransactionAmount() {
		return this.getPaymentInfo().getTransactionAmount();
	}

	@Override
	public double getCustAcctBalance() {
		return this.getPaymentInfo().getCustAccountBalance();
	}

	@Override
	public boolean getTransactionStatus() {
		return this.getPaymentInfo().isTransactionStatus();
	}

	@Override
	public AbstractPayment createNewPaymentObject(double processingFee) {
		return new NonValuePaymentInfo(this.getPaymentInfo().getCustAccountBalance(), this.getPaymentInfo().getTransactionDate(), this.getPaymentInfo().getTransactionTime(), processingFee, this.getPaymentInfo().getTransactionFeeRate(), false); 
	}

	@Override
	public void resetFeeInfo(AbstractPayment paymentInfo) {
		if(paymentInfo instanceof NonValuePaymentInfo) {
			this.setFeeInfo((NonValuePaymentInfo)paymentInfo);
		}
	}

	@Override
	public void updateTransactionStatus(boolean status) {
		NonValuePaymentInfo pInfo = new NonValuePaymentInfo(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionDate(), this.getFeeInfo().getTransactionTime(), this.getFeeInfo().getTransactionAmount(), this.getPaymentInfo().getTransactionFeeRate(), status);
		this.setFeeInfo(pInfo);
	}
	
	@Override
	public AbstractTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		NonValuePaymentInfo pi = createNonValuePaymentInfo(result);
		return new NonValueTransaction(result.getTransactionID(), cDetails, pi);
	}
	
	@Override
	public double computeFieldSum(int n_iterations) {
		return this.getFieldSum(n_iterations);
	}
	
	private NonValuePaymentInfo createNonValuePaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = CommonUtils.formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new NonValuePaymentInfo(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
	}
	
	private double getFieldSum(int iterVal) {
//		long startTime;
//		long finishTime;
//		long i1 = 0;
//		long i2 = 0;
//		long i3 = 0;
//		long i4 = 0;
//		long i5 = 0;
//		long i6 = 0;
//		startTime = System.nanoTime();
		double sum = 0;
		for(int i =0; i<iterVal;i++) {
//			i1 = System.nanoTime();
			sum += this.getPaymentInfo().getCustAccountBalance();
//			i2 = System.nanoTime();
			sum += this.getPaymentInfo().getTransactionAmount();
//			i3 = System.nanoTime();
			sum += this.getPaymentInfo().getTransactionDate();
//			i4 = System.nanoTime();
			sum += this.getPaymentInfo().getTransactionFeeRate();
//			i5 = System.nanoTime();
			sum += this.getPaymentInfo().getTransactionTime();
//			i6 = System.nanoTime();
		}
//		finishTime = System.nanoTime();
//		computeAverageTime(finishTime - startTime);
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I0 "
//						+ "%d ms", i2 - i1)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I1 "
//						+ "%d ms", i3 - i2)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I2 "
//						+ "%d ms", i4 - i3)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I3 "
//						+ "%d ms", i5 - i4)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I0 "
//						+ "%d ms", i6 - i5)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("Field sum computation took "
//						+ "%d ns", finishTime - startTime)));
		return sum;
	}
	
	/**
	 * @return the paymentInfo
	 */
	public NonValuePaymentInfo getPaymentInfo() {
		return paymentInfo;
	}


	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(NonValuePaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}


	/**
	 * @return the feeInfo
	 */
	public NonValuePaymentInfo getFeeInfo() {
		return feeInfo;
	}


	/**
	 * @param feeInfo the feeInfo to set
	 */
	public void setFeeInfo(NonValuePaymentInfo feeInfo) {
		this.feeInfo = feeInfo;
	}

}
