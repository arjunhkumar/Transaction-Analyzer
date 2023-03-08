/**
 * 
 */
package in.ac.iitmandi.compl;

import java.util.ArrayList;
import java.util.List;

import in.ac.iitmandi.compl.ds.Dataset;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.ds.value.PaymentInfo3;
import in.ac.iitmandi.compl.ds.value.ValueTransaction3;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class ValueMainRunner2 implements MainInterface{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		ValueMainRunner2 mainObj = new ValueMainRunner2();
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
		List<ValueTransaction3> valueList = convertToValueTransactions(ds);
		double sum =0;
		for(int i = 1; i<=CommonUtils.ITER_SIZE; i++) {
			sum += processTransactions(valueList,i);
		}
		System.out.println("Final value: "+sum);
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(String.format("Analysis execution took %d ms", finishTime - startTime)));
	
	}

	public List<ValueTransaction3> convertToValueTransactions(Dataset ds) {
		List<ValueTransaction3> transactionList = null;
		if(null != ds && null != ds.getResults() && ds.getResults().length > 0) {
			transactionList = new ArrayList<>();
			for (JSONResult transactionData : ds.getResults()) {
				ValueTransaction3 nonValueTransaction = CommonUtils.convertToVT3(transactionData);
				transactionList.add(nonValueTransaction);
			}
		}
		return transactionList;
	}

	double processTransactions(List<ValueTransaction3> valueList, int divident) {
		double blackHole;
		double avgTransactionAmt = computeAverageTransactionAmount(valueList)/divident;
//		System.out.println(CommonUtils.generateLogMsg("Average Transaction Amount: "+avgTransactionAmt));
		List<ValueTransaction3> workList = new ArrayList<>(valueList);
		double avgProcessingFee = computeAverageProcessingFee(workList,divident/CommonUtils.ITER_SIZE);
//		System.out.println(CommonUtils.generateLogMsg("Average processing fee: "+avgProcessingFee));
		int numberOfCustomers = computeNumberOfCustomers(updateTransactions(workList,divident/CommonUtils.ITER_SIZE));
//		System.out.println(CommonUtils.generateLogMsg("No. of transactions successfull are: "+numberOfCustomers));
		blackHole =  avgTransactionAmt + avgProcessingFee +numberOfCustomers;
		return blackHole;
	}

	private double computeAverageTransactionAmount(List<ValueTransaction3> valueList) {
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(ValueTransaction3 valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getPaymentInfo().getTransactionAmount();
				sum += transactionAmt;
			}
			return sum/valueList.size();
		}
		return 0;
	}
	
	private double computeAverageProcessingFee(List<ValueTransaction3> valueList, float rate) {
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(ValueTransaction3 valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getPaymentInfo().getTransactionAmount();
				double processingFee = transactionAmt*rate;
				sum+=processingFee;
			}
			return sum/valueList.size();
		}
		return 0;
	}
	
	private List<ValueTransaction3> updateTransactions(List<ValueTransaction3> workList, int rate) {
		if(null != workList && !workList.isEmpty()) {
			for(ValueTransaction3 valueTransaction : workList) {
				double transactionAmt = valueTransaction.getPaymentInfo().getTransactionAmount();
				double processingFee = transactionAmt*rate;
				PaymentInfo3 pInfo = new PaymentInfo3(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),false);
				valueTransaction.setFeeInfo(pInfo);
				if(valueTransaction.getPaymentInfo().getCustAccountBalance() >= processingFee) {
					PaymentInfo3 updatedPInfo = new PaymentInfo3(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),true);
					valueTransaction.setFeeInfo(updatedPInfo);
				}
			}
		}
		return workList;
	}

	private int computeNumberOfCustomers(List<ValueTransaction3> updateTransactions) {
		if(null != updateTransactions && !updateTransactions.isEmpty()) {
			int noOfCustomers = 0;
			for(ValueTransaction3 valueTransaction : updateTransactions) {
				if(valueTransaction.getFeeInfo().isTransactionStatus()) {
					noOfCustomers++;
				}
			}
			return noOfCustomers;
		}
		return 0;
	}


}
