/**
 * 
 */
package in.ac.iitmandi.compl.obsolete;

import in.ac.iitmandi.compl.ds.CustomerDetails;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class NonAbstractedValueTransaction {

	protected String TransactionID;
	protected CustomerDetails custDetails;
	private SubPaymentInfo1 paymentInfo1;
	private SubPaymentInfo2 paymentInfo2;
	private SubPaymentInfo1 feeInfo1;
	private SubPaymentInfo2 feeInfo2;
	/**
	 * 
	 */
	public NonAbstractedValueTransaction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public NonAbstractedValueTransaction(String transactionID, CustomerDetails custDetails, SubPaymentInfo1 paymentInfo1, SubPaymentInfo2 paymentInfo2) {
		this.TransactionID = transactionID;
		this.custDetails = custDetails;
		this.paymentInfo1 = paymentInfo1;
		this.paymentInfo2 = paymentInfo2;
		this.feeInfo1 = new SubPaymentInfo1(paymentInfo1.getCustAccountBalance(),paymentInfo1.getTransactionDate(),paymentInfo1.getTransactionTime());
		this.feeInfo2 = new SubPaymentInfo2(0,paymentInfo2.getTransactionFeeRate(),false);
	}
	
	public double getTransactionAmount() {
		return this.getPaymentInfo2().getTransactionAmount();
	}

	public double getCustAcctBalance() {
		return this.getPaymentInfo1().getCustAccountBalance();
	}

	public boolean getTransactionStatus() {
		return this.getPaymentInfo2().isTransactionStatus();
	}

	protected void computeAverageTime(long iterTime) {
		CommonUtils.averageTime+=iterTime;
	}
	/**
	 * @return the transactionID
	 */
	public String getTransactionID() {
		return TransactionID;
	}

	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	/**
	 * @return the custDetails
	 */
	public CustomerDetails getCustDetails() {
		return custDetails;
	}

	/**
	 * @param custDetails the custDetails to set
	 */
	public void setCustDetails(CustomerDetails custDetails) {
		this.custDetails = custDetails;
	}
//	
//	public NonAbstractedPaymentInfo createNewPaymentObject(double processingFee) {
//		return new NonAbstractedPaymentInfo(this.getPaymentInfo().getCustAccountBalance(), this.getPaymentInfo().getTransactionDate(), this.getPaymentInfo().getTransactionTime(), processingFee, this.getPaymentInfo().getTransactionFeeRate(), false); 
//	}
	
	public SubPaymentInfo2 createNewPaymentObject(double processingFee) {
		return new SubPaymentInfo2(processingFee, this.getPaymentInfo2().getTransactionFeeRate(), false); 
	}

//	public void resetFeeInfo(NonAbstractedPaymentInfo paymentInfo) {
//		if(paymentInfo instanceof NonAbstractedPaymentInfo) {
//			this.setFeeInfo((NonAbstractedPaymentInfo)paymentInfo);
//		}
//	}
	
	public void resetFeeInfo(SubPaymentInfo2 paymentInfo) {
			this.setFeeInfo2(paymentInfo);
	}

//	public void updateTransactionStatus(boolean status) {
//		this.setFeeInfo(new NonAbstractedPaymentInfo(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionDate(), this.getFeeInfo().getTransactionTime(), this.getFeeInfo().getTransactionAmount(), this.getPaymentInfo().getTransactionFeeRate(), status));
//	}
	
	public void updateTransactionStatus(boolean status) {
		this.setFeeInfo2(new SubPaymentInfo2(this.getFeeInfo2().getTransactionAmount(), this.getPaymentInfo2().getTransactionFeeRate(), status));
	}

	public NonAbstractedValueTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		SubPaymentInfo1 pi1 = createValuePaymentInfo1(result);
		SubPaymentInfo2 pi2 = createValuePaymentInfo2(result);
		return new NonAbstractedValueTransaction(result.getTransactionID(), cDetails, pi1,pi2);
	}
	
	public double computeFieldSum(int n_iterations) {
		return this.getFieldSum(n_iterations);
	}
	
	private SubPaymentInfo1 createValuePaymentInfo1(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = CommonUtils.formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new SubPaymentInfo1(cAccBalance, paymentDate, paymentTime);
	}
	
	private SubPaymentInfo2 createValuePaymentInfo2(JSONResult result) {
		return new SubPaymentInfo2(result.getTransactionAmount(), 0, false);
	}
	
	
	private double getFieldSum(int iterVal) {
		long startTime;
		long finishTime;
//		long i1 = 0;
//		long i2 = 0;
//		long i3 = 0;
//		long i4 = 0;
//		long i5 = 0;
//		long i6 = 0;
		startTime = System.nanoTime();
		double sum = 0;
		for(int i =0; i<iterVal;i++) {
//			NonAbstractedPaymentInfo currentPaymentInfo = this.getPaymentInfo();
//			i1 = System.nanoTime();
			sum += this.getPaymentInfo1().getCustAccountBalance();
//			i2 = System.nanoTime();
//			sum += this.getPaymentInfo2().getTransactionAmount();
////			i3 = System.nanoTime();
//			sum += this.getPaymentInfo1().getTransactionDate();
////			i4 = System.nanoTime();
//			sum += this.getPaymentInfo2().getTransactionFeeRate();
////			i5 = System.nanoTime();
//			sum += this.getPaymentInfo1().getTransactionTime();
//			i6 = System.nanoTime();
		}
		finishTime = System.nanoTime();
		computeAverageTime(finishTime - startTime);
//		System.out.println(CommonUtils.generateLogMsg(
//				 String.format("I0 "
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
//				String.format("I4 "
//						+ "%d ms", i6 - i5)));
		System.out.println(CommonUtils.generateLogMsg(
				String.format("Field sum computation took "
						+ "%d ns", finishTime - startTime)));
		return sum;
	}

	/**
	 * @return the paymentInfo1
	 */
	public SubPaymentInfo1 getPaymentInfo1() {
		return paymentInfo1;
	}

	/**
	 * @param paymentInfo1 the paymentInfo1 to set
	 */
	public void setPaymentInfo1(SubPaymentInfo1 paymentInfo1) {
		this.paymentInfo1 = paymentInfo1;
	}

	/**
	 * @return the paymentInfo2
	 */
	public SubPaymentInfo2 getPaymentInfo2() {
		return paymentInfo2;
	}

	/**
	 * @param paymentInfo2 the paymentInfo2 to set
	 */
	public void setPaymentInfo2(SubPaymentInfo2 paymentInfo2) {
		this.paymentInfo2 = paymentInfo2;
	}

	/**
	 * @return the feeInfo1
	 */
	public SubPaymentInfo1 getFeeInfo1() {
		return feeInfo1;
	}

	/**
	 * @param feeInfo1 the feeInfo1 to set
	 */
	public void setFeeInfo1(SubPaymentInfo1 feeInfo1) {
		this.feeInfo1 = feeInfo1;
	}

	/**
	 * @return the feeInfo2
	 */
	public SubPaymentInfo2 getFeeInfo2() {
		return feeInfo2;
	}

	/**
	 * @param feeInfo2 the feeInfo2 to set
	 */
	public void setFeeInfo2(SubPaymentInfo2 feeInfo2) {
		this.feeInfo2 = feeInfo2;
	}
	
//	/**
//	 * @return the paymentInfo
//	 */
//	public NonAbstractedPaymentInfo getPaymentInfo() {
//		return paymentInfo;
//	}
//
//	/**
//	 * @param paymentInfo the paymentInfo to set
//	 */
//	public void setPaymentInfo(NonAbstractedPaymentInfo paymentInfo) {
//		this.paymentInfo = paymentInfo;
//	}
//
//
//	/**
//	 * @return the feeInfo
//	 */
//	public NonAbstractedPaymentInfo getFeeInfo() {
//		return feeInfo;
//	}
//
//	/**
//	 * @param feeInfo the feeInfo to set
//	 */
//	public void setFeeInfo(NonAbstractedPaymentInfo feeInfo) {
//		this.feeInfo = feeInfo;
//	}

}
