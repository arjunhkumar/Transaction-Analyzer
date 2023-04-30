/**
 * 
 */
package in.ac.iitmandi.compl.obsolete;

import in.ac.iitmandi.compl.ds.CustomerDetails;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.ds.nonvalue.NonValuePaymentInfo;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class IntermediateValueTransaction extends AbstractTransaction{

	private PaymentInfoBackup paymentInfo;
	private NonValuePaymentInfo feeInfo;
	
	/**
	 * 
	 */
	public IntermediateValueTransaction() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public IntermediateValueTransaction(String transactionID, CustomerDetails custDetails, PaymentInfoBackup paymentInfo) {
//		this.TransactionID = transactionID;
//		this.custDetails = custDetails;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new NonValuePaymentInfo(paymentInfo.getCustAccountBalance(),paymentInfo.getTransactionDate(),paymentInfo.getTransactionTime(),0,paymentInfo.getTransactionFeeRate(),false);
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
		return new PaymentInfoBackup(this.getPaymentInfo().getCustAccountBalance(), this.getPaymentInfo().getTransactionDate(), this.getPaymentInfo().getTransactionTime(), processingFee, this.getPaymentInfo().getTransactionFeeRate(), false); 
	}

	@Override
	public void resetFeeInfo(AbstractPayment paymentInfo) {
		if(paymentInfo instanceof NonValuePaymentInfo) {
			this.setFeeInfo((NonValuePaymentInfo)paymentInfo);
		}
	}

	@Override
	public void updateTransactionStatus(boolean status) {
		this.resetFeeInfo(new PaymentInfoBackup(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionDate(), this.getFeeInfo().getTransactionTime(), this.getFeeInfo().getTransactionAmount(), this.getPaymentInfo().getTransactionFeeRate(), status));
	}

	@Override
	public AbstractTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfoBackup pi = createValuePaymentInfo(result);
		return new IntermediateValueTransaction(result.getTransactionID(), cDetails, pi);
	}
	
	@Override
	public double computeFieldSum(int n_iterations) {
		return this.getFieldSum(n_iterations);
	}
	
	private PaymentInfoBackup createValuePaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = CommonUtils.formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new PaymentInfoBackup(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
	}
	
	private double getFieldSum(int iterVal) {
		double sum = 0;
		for(int i =0; i<iterVal;i++) {
			PaymentInfoBackup currentPaymentInfo = this.getPaymentInfo();
			sum += currentPaymentInfo.getCustAccountBalance();
			sum += currentPaymentInfo.getTransactionAmount();
			sum += currentPaymentInfo.getTransactionDate();
			sum += currentPaymentInfo.getTransactionFeeRate();
			sum += currentPaymentInfo.getTransactionTime();
		}
		return sum;
	}
	
	/**
	 * @return the paymentInfo
	 */
	public PaymentInfoBackup getPaymentInfo() {
		return paymentInfo;
	}


	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(PaymentInfoBackup paymentInfo) {
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
