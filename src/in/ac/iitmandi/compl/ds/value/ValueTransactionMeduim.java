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
public class ValueTransactionMeduim extends AbstractTransaction {

	private PaymentInfoMeduim paymentInfo;
	private PaymentInfoMeduim feeInfo;
	
	/**
	 * 
	 */
	public ValueTransactionMeduim() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public ValueTransactionMeduim(String transactionID, CustomerDetails custDetails, PaymentInfoMeduim paymentInfo) {
		this.TransactionID = transactionID;
		this.custDetails = custDetails;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new PaymentInfoMeduim(paymentInfo.getCustAccountBalance(),paymentInfo.getTransactionDate(),paymentInfo.getTransactionTime(),0,paymentInfo.getTransactionFeeRate(),false);
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
		if(paymentInfo instanceof PaymentInfoMeduim) {
			this.setFeeInfo((PaymentInfoMeduim)paymentInfo);
		}
	}

	@Override
	public void updateTransactionStatus(boolean status) {
		this.setFeeInfo(new PaymentInfoMeduim(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionDate(), this.getFeeInfo().getTransactionTime(), this.getFeeInfo().getTransactionAmount(), this.getPaymentInfo().getTransactionFeeRate(), status));
	}

	@Override
	public AbstractTransaction convertToTransactionObject(JSONResult result) {
		CustomerDetails cDetails = new CustomerDetails(result.getCustomerID(), result.getCustomerDOB(), result.getCustGender(), result.getCustLocation());
		PaymentInfoMeduim pi = createValuePaymentInfo(result);
		return new ValueTransactionMeduim(result.getTransactionID(), cDetails, pi);
	}
	
	@Override
	public double computeFieldSum(int n_iterations) {
		return this.getFieldSum(n_iterations);
	}
	
	private PaymentInfoMeduim createValuePaymentInfo(JSONResult result) {
		double cAccBalance = 0;
		if(result.getCustAccountBalance() != null && !result.getCustAccountBalance().isEmpty()) {
			cAccBalance =  Double.parseDouble(result.getCustAccountBalance());
		}
		int paymentDate = CommonUtils.formatDateString(result.getTransactionDate());
		int paymentTime = result.getTransactionTime();
		return new PaymentInfoMeduim(cAccBalance, paymentDate, paymentTime, result.getTransactionAmount(), 0, false);
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


	/**
	 * @return the paymentInfo
	 */
	public PaymentInfoMeduim getPaymentInfo() {
		return paymentInfo;
	}


	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(PaymentInfoMeduim paymentInfo) {
		this.paymentInfo = paymentInfo;
	}


	/**
	 * @return the feeInfo
	 */
	public PaymentInfoMeduim getFeeInfo() {
		return feeInfo;
	}


	/**
	 * @param feeInfo the feeInfo to set
	 */
	public void setFeeInfo(PaymentInfoMeduim feeInfo) {
		this.feeInfo = feeInfo;
	}
	
}
