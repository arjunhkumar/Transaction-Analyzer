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
public class ValueTransactionSmall extends AbstractTransaction {

	private PaymentInfoSmall paymentInfo;
	private PaymentInfoSmall feeInfo;
	
	/**
	 * 
	 */
	public ValueTransactionSmall() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public ValueTransactionSmall(String transactionID, CustomerDetails custDetails, PaymentInfoSmall paymentInfo) {
//		this.TransactionID = transactionID;
//		this.custDetails = custDetails;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new PaymentInfoSmall(paymentInfo.getCustAccountBalance(),paymentInfo.getTransactionDate(),paymentInfo.getTransactionTime(),0,paymentInfo.getTransactionFeeRate(),false);
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
		return new PaymentInfoSmall(this.getPaymentInfo().getCustAccountBalance(), this.getPaymentInfo().getTransactionDate(), this.getPaymentInfo().getTransactionTime(), processingFee, this.getPaymentInfo().getTransactionFeeRate(), false); 
	}

	@Override
	public void resetFeeInfo(AbstractPayment paymentInfo) {
		if(paymentInfo instanceof PaymentInfoSmall) {
			this.setFeeInfo((PaymentInfoSmall)paymentInfo);
		}
	}

	@Override
	public void updateTransactionStatus(boolean status) {
		this.setFeeInfo(new PaymentInfoSmall(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionDate(), this.getFeeInfo().getTransactionTime(), this.getFeeInfo().getTransactionAmount(), this.getPaymentInfo().getTransactionFeeRate(), status));
	}

	@Override
	public AbstractTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfoSmall pi = createValuePaymentInfo(result);
		return new ValueTransactionSmall(result.getTransactionID(), cDetails, pi);
	}
	
	@Override
	public double computeFieldSum(int n_iterations) {
		return this.getFieldSum(n_iterations);
	}
	
	private PaymentInfoSmall createValuePaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = CommonUtils.formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new PaymentInfoSmall(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
	}
	
	private double getFieldSum(int iterVal) {
		double sum = 0;
		for(int i =0; i<iterVal;i++) {
			PaymentInfoSmall currentPaymentInfo = this.getPaymentInfo();
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
	public PaymentInfoSmall getPaymentInfo() {
		return paymentInfo;
	}


	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(PaymentInfoSmall paymentInfo) {
		this.paymentInfo = paymentInfo;
	}


	/**
	 * @return the feeInfo
	 */
	public PaymentInfoSmall getFeeInfo() {
		return feeInfo;
	}


	/**
	 * @param feeInfo the feeInfo to set
	 */
	public void setFeeInfo(PaymentInfoSmall feeInfo) {
		this.feeInfo = feeInfo;
	}
	
}