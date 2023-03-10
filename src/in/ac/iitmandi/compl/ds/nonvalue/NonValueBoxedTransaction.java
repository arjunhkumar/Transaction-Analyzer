/**
 * 
 */
package in.ac.iitmandi.compl.ds.nonvalue;

import in.ac.iitmandi.compl.ds.AbstractPayment;
import in.ac.iitmandi.compl.ds.AbstractTransaction;
import in.ac.iitmandi.compl.ds.CustomerDetails;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class NonValueBoxedTransaction extends AbstractTransaction{

	private NonValuePaymentBoxBox paymentInfo;
	private NonValuePaymentBoxBox feeInfo;
	
	
	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public NonValueBoxedTransaction(String transactionID, CustomerDetails custDetails, NonValuePaymentBoxBox paymentInfo) {
		this.TransactionID = transactionID;
		this.custDetails = custDetails;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new NonValuePaymentBoxBox(paymentInfo.getCustAccountBalance(),paymentInfo.getTransactionDate(),paymentInfo.getTransactionTime(),0,paymentInfo.getTransactionFeeRate(),false);
	}
	
	public NonValueBoxedTransaction() {
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
		return new NonValuePaymentBoxBox(this.getPaymentInfo().getCustAccountBalance(), this.getPaymentInfo().getTransactionDate(), this.getPaymentInfo().getTransactionTime(), processingFee, this.getPaymentInfo().getTransactionFeeRate(), false); 
	}

	@Override
	public void resetFeeInfo(AbstractPayment paymentInfo) {
		if(paymentInfo instanceof NonValuePaymentBoxBox) {
			this.setFeeInfo((NonValuePaymentBoxBox)paymentInfo);
		}
	}

	@Override
	public void updateTransactionStatus(boolean status) {
		this.setFeeInfo(new NonValuePaymentBoxBox(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionDate(), this.getFeeInfo().getTransactionTime(), this.getFeeInfo().getTransactionAmount(), this.getPaymentInfo().getTransactionFeeRate(), status));
	}
	
	@Override
	public AbstractTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		NonValuePaymentBoxBox pi = createNonValuePaymentInfo(result);
		return new NonValueBoxedTransaction(result.getTransactionID(), cDetails, pi);
	}
	
	@Override
	public double computeFieldSum(int n_iterations) {
		return this.getFieldSum(n_iterations);
	}
	
	private NonValuePaymentBoxBox createNonValuePaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = CommonUtils.formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new NonValuePaymentBoxBox(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
	}
	
	private double getFieldSum(int iterVal) {
		double sum = 0;
		for(int i =0; i<iterVal;i++) {
			sum += this.getPaymentInfo().getCustAccountBalance();
			sum += this.getPaymentInfo().getTransactionAmount();
			sum += this.getPaymentInfo().getTransactionDate();
			sum += this.getPaymentInfo().getTransactionFeeRate();
			sum += this.getPaymentInfo().getTransactionTime();
		}
		return sum;
	}
	
	/**
	 * @return the paymentInfo
	 */
	public NonValuePaymentBoxBox getPaymentInfo() {
		return paymentInfo;
	}


	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(NonValuePaymentBoxBox paymentInfo) {
		this.paymentInfo = paymentInfo;
	}


	/**
	 * @return the feeInfo
	 */
	public NonValuePaymentBoxBox getFeeInfo() {
		return feeInfo;
	}


	/**
	 * @param feeInfo the feeInfo to set
	 */
	public void setFeeInfo(NonValuePaymentBoxBox feeInfo) {
		this.feeInfo = feeInfo;
	}

}
