package in.ac.iitmandi.compl.ds.value;

public class TransactionInfo {

	
	private double custAccountBalance;
	private double transactionAmount;
	
	public TransactionInfo() {
		this.custAccountBalance = 0d;
		this.transactionAmount = 0d;
	}

	/**
	 * @param custAccountBalance
	 * @param transactionAmount
	 */
	public TransactionInfo(double custAccountBalance, double transactionAmount) {
		this.custAccountBalance = custAccountBalance;
		this.transactionAmount = transactionAmount;
	}

	/**
	 * @return the custAccountBalance
	 */
	public double getCustAccountBalance() {
		return custAccountBalance;
	}

	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}
	
}
