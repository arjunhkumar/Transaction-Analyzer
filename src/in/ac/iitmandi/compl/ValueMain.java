/**
 * 
 */
package in.ac.iitmandi.compl;

import java.util.ArrayList;
import java.util.List;

import in.ac.iitmandi.compl.ds.Dataset;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.ds.value.ValueTransaction;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class ValueMain{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		if(CommonUtils.validateArgs(args)) {
			CommonUtils.createOutFile();
			startTime = System.currentTimeMillis();
			Dataset ds = CommonUtils.loadDataSet();
			ValueMain mainObj = new ValueMain();
			long analysisTime = mainObj.executeAnalysis(ds);
			finishTime = System.currentTimeMillis();
//			System.out.println(CommonUtils.generateLogMsg(String.format("Total execution took %d ms", finishTime - startTime)));
			CommonUtils.writeToOutFile(analysisTime, finishTime - startTime);
		}
	}
	
	public long executeAnalysis(Dataset ds) {
		long startTime;
		long finishTime;
		List<ValueTransaction> valueList = convertToTransaction(ds);
		startTime = System.currentTimeMillis();
		double sum =0;
		for(int i = 1; i<=CommonUtils.ITER_SIZE; i++) {
			sum += processTransactions(valueList,i);
		}
		System.out.println("Final value: "+sum);
		finishTime = System.currentTimeMillis();
//		System.out.println(CommonUtils.generateLogMsg(String.format("Analysis execution took %d ms", finishTime - startTime)));
		return finishTime - startTime;
	}
	
	public List<ValueTransaction> convertToTransaction(Dataset ds) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		List<ValueTransaction> transactionList = null;
		ValueTransaction transaction = new ValueTransaction();
		if(null != ds && null != ds.getResults() && ds.getResults().length > 0) {
			transactionList = new ArrayList<>();
			for (JSONResult transactionData : ds.getResults()) {
				ValueTransaction valueTransaction = transaction.convertToTransactionObject(transactionData);
				transactionList.add(valueTransaction);
			}
		}
		finishTime = System.currentTimeMillis();
		CommonUtils.printLog(
				String.format("Dataset conversion took "
						+ "%d ms", finishTime - startTime));
		return transactionList;
	}

	public double processTransactions(List<ValueTransaction> valueList, int divident) {
		double blackHole;
		double avgTransactionAmt = computeAverageTransactionAmount(valueList)/divident;
		double avgProcessingFee = computeAverageProcessingFee(valueList,divident/CommonUtils.ITER_SIZE);
		int numberOfCustomers = computeNumberOfCustomers(updateTransactions(valueList,divident/CommonUtils.ITER_SIZE));
		blackHole =  avgTransactionAmt + avgProcessingFee + numberOfCustomers;
		return blackHole;
	}
	
	private double computeAverageTransactionAmount(List<ValueTransaction> valueList) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(ValueTransaction valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getTransactionAmount();
				sum += transactionAmt;
			}
			finishTime = System.currentTimeMillis();
			CommonUtils.printLog(
					String.format("Average Transaction Amount computation took "
							+ "%d ms", finishTime - startTime));
			return sum/valueList.size();
		}
		
		return 0;
	}
	
	private double computeAverageProcessingFee(List<ValueTransaction> valueList, float rate) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(ValueTransaction valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getTransactionAmount();
				double processingFee = transactionAmt*rate;
				sum+=processingFee;
			}
			finishTime = System.currentTimeMillis();
			CommonUtils.printLog(
					String.format("Average Processing Fee computation took "
							+ "%d ms", finishTime - startTime));
			return sum/valueList.size();
		}
		return 0;
	}
	
	private List<ValueTransaction> updateTransactions(List<ValueTransaction> workList, int rate) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != workList && !workList.isEmpty()) {
			for(ValueTransaction valueTransaction : workList) {
				double transactionAmt = valueTransaction.getTransactionAmount();
				double processingFee = transactionAmt*rate;
				valueTransaction.resetFeeInfo(valueTransaction.createNewPaymentObject(processingFee));
				if(valueTransaction.getCustAcctBalance() >= processingFee) {
					valueTransaction.updateTransactionStatus(true);
				}
			}
		}
		finishTime = System.currentTimeMillis();
		CommonUtils.printLog(
				String.format("Updating transactions took "
						+ "%d ms", finishTime - startTime));
		return workList;
	}

	private int computeNumberOfCustomers(List<ValueTransaction> updateTransactions) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != updateTransactions && !updateTransactions.isEmpty()) {
			int noOfCustomers = 0;
			for(ValueTransaction valueTransaction : updateTransactions) {
				if(valueTransaction.getTransactionStatus()) {
					noOfCustomers++;
				}
			}
			finishTime = System.currentTimeMillis();
			CommonUtils.printLog(
					String.format("Customer computation took "
							+ "%d ms", finishTime - startTime));
			return noOfCustomers;
		}
		return 0;
	}
	
}
