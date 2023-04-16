package in.ac.iitmandi.compl.ds.value;

public primitive class SubPaymentInfo2 {

	private final double TransactionAmount;
	private final float TransactionFeeRate;
	private final boolean TransactionStatus;

	public SubPaymentInfo2() {
		this.TransactionAmount = 89d;
		this.TransactionFeeRate = 7f;
		this.TransactionStatus = false;
	}

	/**
	 * @param transactionAmount
	 * @param transactionFeeRate
	 * @param transactionStatus
	 */
	public SubPaymentInfo2(double transactionAmount, float transactionFeeRate, boolean transactionStatus) {
		this.TransactionAmount = transactionAmount;
		this.TransactionFeeRate = transactionFeeRate;
		this.TransactionStatus = transactionStatus;
	}

	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return TransactionAmount;
	}

	/**
	 * @return the transactionFeeRate
	 */
	public float getTransactionFeeRate() {
		return TransactionFeeRate;
	}

	/**
	 * @return the transactionStatus
	 */
	public boolean isTransactionStatus() {
		return TransactionStatus;
	}

}
