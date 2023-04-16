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
public class ValueTransactionXLarge extends AbstractTransaction {

	private PaymentInfoXLarge paymentInfo;
	private PaymentInfoXLarge feeInfo;
	
	/**
	 * 
	 */
	public ValueTransactionXLarge() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param transactionID
	 * @param custDetails
	 * @param transactionStatus
	 * @param transactionFee
	 */
	public ValueTransactionXLarge(String transactionID, CustomerDetails custDetails, PaymentInfoXLarge paymentInfo) {
//		this.TransactionID = transactionID;
//		this.custDetails = custDetails;
		this.paymentInfo = paymentInfo;
		this.feeInfo = new PaymentInfoXLarge(paymentInfo.getCustAccountBalance(),paymentInfo.getTransactionDate(),paymentInfo.getTransactionTime(),0,paymentInfo.getTransactionFeeRate(),false);
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
		if(paymentInfo instanceof PaymentInfoXLarge) {
			this.setFeeInfo((PaymentInfoXLarge)paymentInfo);
		}
	}

	@Override
	public void updateTransactionStatus(boolean status) {
		this.setFeeInfo(new PaymentInfoXLarge(this.getFeeInfo().getCustAccountBalance(), this.getFeeInfo().getTransactionDate(), this.getFeeInfo().getTransactionTime(), this.getFeeInfo().getTransactionAmount(), this.getPaymentInfo().getTransactionFeeRate(), status));
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
			PaymentInfoXLarge currentPaymentInfo = this.getPaymentInfo();
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
	public PaymentInfoXLarge getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(PaymentInfoXLarge paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	/**
	 * @return the feeInfo
	 */
	public PaymentInfoXLarge getFeeInfo() {
		return feeInfo;
	}

	/**
	 * @param feeInfo the feeInfo to set
	 */
	public void setFeeInfo(PaymentInfoXLarge feeInfo) {
		this.feeInfo = feeInfo;
	}
	
}
