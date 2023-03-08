/**
 * 
 */
package in.ac.iitmandi.compl;

import java.util.ArrayList;
import java.util.List;

import in.ac.iitmandi.compl.ds.Dataset;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.ds.nonvalue.NonValuePaymentInfo;
import in.ac.iitmandi.compl.ds.value.IntermediateValueTransaction;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class IntermediateValueMain implements MainInterface{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		IntermediateValueMain mainObj = new IntermediateValueMain();
		if(mainObj.validateArgs(args)) {
			Dataset ds = mainObj.loadDataSet();
			mainObj.executeAnalysis(ds);
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg(String.format("Total execution took %d ms", finishTime - startTime)));
		}
	}
	
	public void executeAnalysis(Dataset ds) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		List<IntermediateValueTransaction> valueList = convertToValueTransactions(ds);
		double sum =0;
		for(int i = 1; i<=CommonUtils.ITER_SIZE; i++) {
			sum += processTransactions(valueList,i);
		}
		System.out.println("Final value: "+sum);
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(String.format("Analysis execution took %d ms", finishTime - startTime)));
	}

	List<IntermediateValueTransaction> convertToValueTransactions(Dataset ds) {
		List<IntermediateValueTransaction> transactionList = null;
		if(null != ds && null != ds.getResults() && ds.getResults().length > 0) {
			transactionList = new ArrayList<>();
			for (JSONResult transactionData : ds.getResults()) {
				IntermediateValueTransaction valueTransaction = CommonUtils.convertToIVT(transactionData);
				transactionList.add(valueTransaction);
			}
		}
		return transactionList;
	}

	double processTransactions(List<IntermediateValueTransaction> valueList, int divident) {
		double blackHole;
		double avgTransactionAmt = computeAverageTransactionAmount(valueList)/divident;
//		System.out.println(CommonUtils.generateLogMsg("Average Transaction Amount: "+avgTransactionAmt));
		List<IntermediateValueTransaction> workList = new ArrayList<>(valueList);
		double avgProcessingFee = computeAverageProcessingFee(workList,divident/CommonUtils.ITER_SIZE);
//		System.out.println(CommonUtils.generateLogMsg("Average processing fee: "+avgProcessingFee));
		int numberOfCustomers = computeNumberOfCustomers(updateTransactions(workList,divident/CommonUtils.ITER_SIZE));
//		System.out.println(CommonUtils.generateLogMsg("No. of transactions successfull are: "+numberOfCustomers));
		blackHole = avgTransactionAmt + avgProcessingFee + numberOfCustomers;
		return blackHole;
	}

	private double computeAverageTransactionAmount(List<IntermediateValueTransaction> valueList) {
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(IntermediateValueTransaction valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getPaymentInfo().getTransactionAmount();
				sum += transactionAmt;
			}
			return sum/valueList.size();
		}
		return 0;
	}
	
	private double computeAverageProcessingFee(List<IntermediateValueTransaction> valueList, float rate) {
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(IntermediateValueTransaction valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getPaymentInfo().getTransactionAmount();
				double processingFee = transactionAmt*rate;
				sum+=processingFee;
			}
			return sum/valueList.size();
		}
		return 0;
	}
	
	private List<IntermediateValueTransaction> updateTransactions(List<IntermediateValueTransaction> workList, int rate) {
		if(null != workList && !workList.isEmpty()) {
			for(IntermediateValueTransaction valueTransaction : workList) {
				double transactionAmt = valueTransaction.getPaymentInfo().getTransactionAmount();
				double processingFee = transactionAmt*rate;
				NonValuePaymentInfo pInfo = new NonValuePaymentInfo(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),false);
				valueTransaction.setFeeInfo(pInfo);
				if(valueTransaction.getPaymentInfo().getCustAccountBalance() >= processingFee) {
					NonValuePaymentInfo updatedPInfo = new NonValuePaymentInfo(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),true);
					valueTransaction.setFeeInfo(updatedPInfo);
				}
			}
		}
		return workList;
	}

	private int computeNumberOfCustomers(List<IntermediateValueTransaction> updateTransactions) {
		if(null != updateTransactions && !updateTransactions.isEmpty()) {
			int noOfCustomers = 0;
			for(IntermediateValueTransaction valueTransaction : updateTransactions) {
				if(valueTransaction.getFeeInfo().isTransactionStatus()) {
					noOfCustomers++;
				}
			}
			return noOfCustomers;
		}
		return 0;
	}


}
