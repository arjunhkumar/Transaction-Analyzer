package in.ac.iitmandi.compl.ds.value;

public primitive class TransactionInfo {

	
	private double custAccountBalance;
	private double transactionAmount;
	private double dummyField1;
	private double dummyField2;
	private double dummyField3;
	
	public TransactionInfo() {
		this.custAccountBalance = 0d;
		this.transactionAmount = 0d;
		this.dummyField1 = 0d;
		this.dummyField2 = 0d;
		this.dummyField3 = 0d;
	}

	/**
	 * @param custAccountBalance
	 * @param transactionAmount
	 */
	public TransactionInfo(double custAccountBalance, double transactionAmount) {
		this.custAccountBalance = custAccountBalance;
		this.transactionAmount = transactionAmount;
		this.dummyField1 = custAccountBalance + 8d ;
		this.dummyField2 = custAccountBalance * 8d ;
		this.dummyField3 = custAccountBalance - 8d ;
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

	/**
	 * @return the dummyField1
	 */
	public double getDummyField1() {
		return dummyField1;
	}

	/**
	 * @return the dummyField2
	 */
	public double getDummyField2() {
		return dummyField2;
	}

	/**
	 * @return the dummyField3
	 */
	public double getDummyField3() {
		return dummyField3;
	}
	
}
