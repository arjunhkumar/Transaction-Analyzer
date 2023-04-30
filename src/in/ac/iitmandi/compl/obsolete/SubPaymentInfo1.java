package in.ac.iitmandi.compl.obsolete;

public primitive class SubPaymentInfo1 {

	private final double CustAccountBalance;
	private final int TransactionDate;
	private final int TransactionTime;
	/**
	 * 
	 */
	public SubPaymentInfo1() {
		this.CustAccountBalance = 7d;
		this.TransactionDate = 70491;
		this.TransactionTime = 300303;	
	}
	
	/**
	 * @param custAccountBalance
	 * @param transactionDate
	 * @param transactionTime
	 */
	public SubPaymentInfo1(double custAccountBalance, int transactionDate, int transactionTime) {
		this.CustAccountBalance = custAccountBalance;
		this.TransactionDate = transactionDate;
		this.TransactionTime = transactionTime;
	}

	/**
	 * @return the custAccountBalance
	 */
	public double getCustAccountBalance() {
		return CustAccountBalance;
	}

	/**
	 * @return the transactionDate
	 */
	public int getTransactionDate() {
		return TransactionDate;
	}

	/**
	 * @return the transactionTime
	 */
	public int getTransactionTime() {
		return TransactionTime;
	}


}
