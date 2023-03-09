/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

import in.ac.iitmandi.compl.ds.AbstractPayment;
import in.ac.iitmandi.compl.ds.AbstractTransaction;
import in.ac.iitmandi.compl.ds.CustomerDetails;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class ValueTransactionLarge extends AbstractTransaction {

	private PaymentInfoLarge paymentInfo;
	private PaymentInfoLarge feeInfo;
	
	/**
	 * 
	 */
	public ValueTransactionLarge() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public ValueTransactionLarge(String transactionID, CustomerDetails custDetails, PaymentInfoLarge paymentInfo) {
		this.TransactionID = transactionID;
		this.custDetails = custDetails;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new PaymentInfoLarge(paymentInfo.getCustAccountBalance(),paymentInfo.getTransactionDate(),paymentInfo.getTransactionTime(),0,paymentInfo.getTransactionFeeRate(),false);
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
		return new PaymentInfo(this.getPaymentInfo().getCustAccountBalance(), this.getPaymentInfo().getTransactionDate(), this.getPaymentInfo().getTransactionTime(), processingFee, this.getPaymentInfo().getTransactionFeeRate(), false); 
	}

	@Override
	public void resetFeeInfo(AbstractPayment paymentInfo) {
		if(paymentInfo instanceof PaymentInfoLarge) {
			this.setFeeInfo((PaymentInfoLarge)paymentInfo);
		}
	}

	@Override
	public void updateTransactionStatus(boolean status) {
		this.setFeeInfo(new PaymentInfoLarge(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionDate(), this.getFeeInfo().getTransactionTime(), this.getFeeInfo().getTransactionAmount(), this.getPaymentInfo().getTransactionFeeRate(), status));
	}

	@Override
	public AbstractTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfo pi = createValuePaymentInfo(result);
		return new ValueTransaction(result.getTransactionID(), cDetails, pi);
	}
	
	@Override
	public double computeFieldSum(int n_iterations) {
		return this.getFieldSum(n_iterations);
	}
	
	private PaymentInfo createValuePaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = CommonUtils.formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new PaymentInfo(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
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
	public PaymentInfoLarge getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(PaymentInfoLarge paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	/**
	 * @return the feeInfo
	 */
	public PaymentInfoLarge getFeeInfo() {
		return feeInfo;
	}

	/**
	 * @param feeInfo the feeInfo to set
	 */
	public void setFeeInfo(PaymentInfoLarge feeInfo) {
		this.feeInfo = feeInfo;
	}
	
}
