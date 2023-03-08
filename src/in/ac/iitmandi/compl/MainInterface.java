/**
 * 
 */
package in.ac.iitmandi.compl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import in.ac.iitmandi.compl.ds.AbstractTransaction;
import in.ac.iitmandi.compl.ds.Dataset;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public interface MainInterface {

	default boolean validateArgs(String[] args) {
		if(! (args.length == 1)) {
			System.out.println(CommonUtils.generateErrorMsg("No. of arguments is incorrect."));
			System.out.println(CommonUtils.generateErrorMsg("Exiting without executing."));
			return false;
		}
		String iterSize = args[0];
		int iterVal = Integer.parseInt(iterSize);
		CommonUtils.ITER_SIZE = iterVal;
		return true;
	}
	
	default Dataset loadDataSet() {
		Dataset ds = null;
        Gson gson = new Gson();
//        long startTime;
//        long finishTime;
//        startTime = System.currentTimeMillis();
        try (FileReader fReader = new FileReader(CommonUtils.JSON_PATH)) {
        	ds = gson.fromJson(fReader, Dataset.class);
        } catch (IOException e) {
        	CommonUtils.generateLogMsg(e.getMessage());
			e.printStackTrace();
		} 
//        finishTime = System.currentTimeMillis();
//        System.out.println(CommonUtils.generateLogMsg("Dataset loaded succesfully"));;
        if(!(null == ds) && !(null==ds.results)) {
//        	System.out.println(CommonUtils.generateLogMsg("Size of results: "+ds.results.length));
        }
//        System.out.println(CommonUtils.generateLogMsg(String.format("Loading dataset took %d ms", finishTime - startTime)));
        return ds;
	}
	
	void executeAnalysis(Dataset ds);
	
	default List<AbstractTransaction> convertToTransaction(Dataset ds,AbstractTransaction transaction) {
		List<AbstractTransaction> transactionList = null;
		if(null != ds && null != ds.getResults() && ds.getResults().length > 0) {
			transactionList = new ArrayList<>();
			for (JSONResult transactionData : ds.getResults()) {
				AbstractTransaction nonValueTransaction = transaction.convertToTransactionObject(transactionData);
				transactionList.add(nonValueTransaction);
			}
		}
		return transactionList;
	}
	
	default double processTransactions(List<AbstractTransaction> valueList, int divident) {
		double blackHole;
		double avgTransactionAmt = computeAverageTransactionAmount(valueList)/divident;
//		System.out.println(CommonUtils.generateLogMsg("Average Transaction Amount: "+avgTransactionAmt));
		List<AbstractTransaction> workList = new ArrayList<>(valueList);
		double avgProcessingFee = computeAverageProcessingFee(workList,divident/CommonUtils.ITER_SIZE);
//		System.out.println(CommonUtils.generateLogMsg("Average processing fee: "+avgProcessingFee));
		int numberOfCustomers = computeNumberOfCustomers(updateTransactions(workList,divident/CommonUtils.ITER_SIZE));
//		System.out.println(CommonUtils.generateLogMsg("No. of transactions successfull are: "+numberOfCustomers));
		blackHole =  avgTransactionAmt + avgProcessingFee +numberOfCustomers;
		return blackHole;
	}
	
	default double computeAverageTransactionAmount(List<AbstractTransaction> valueList) {
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(AbstractTransaction valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getTransactionAmount();
				sum += transactionAmt;
			}
			return sum/valueList.size();
		}
		return 0;
	}
	
	default double computeAverageProcessingFee(List<AbstractTransaction> valueList, float rate) {
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(AbstractTransaction valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getTransactionAmount();
				double processingFee = transactionAmt*rate;
				sum+=processingFee;
			}
			return sum/valueList.size();
		}
		return 0;
	}
	
	default List<AbstractTransaction> updateTransactions(List<AbstractTransaction> workList, int rate) {
		if(null != workList && !workList.isEmpty()) {
			for(AbstractTransaction valueTransaction : workList) {
				double transactionAmt = valueTransaction.getTransactionAmount();
				double processingFee = transactionAmt*rate;
//				PaymentInfo pInfo = new PaymentInfo(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),false);
//				valueTransaction.setFeeInfo(pInfo);
				valueTransaction.resetFeeInfo(valueTransaction.createNewPaymentObject(processingFee));
				if(valueTransaction.getCustAcctBalance() >= processingFee) {
//					PaymentInfo updatedPInfo = new PaymentInfo(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),true);
//					valueTransaction.setFeeInfo(updatedPInfo);
					valueTransaction.updateTransactionStatus(true);
				}
			}
		}
		return workList;
	}

	default int computeNumberOfCustomers(List<AbstractTransaction> updateTransactions) {
		if(null != updateTransactions && !updateTransactions.isEmpty()) {
			int noOfCustomers = 0;
			for(AbstractTransaction valueTransaction : updateTransactions) {
				if(valueTransaction.getTransactionStatus()) {
					noOfCustomers++;
				}
			}
			return noOfCustomers;
		}
		return 0;
	}
	
	
}
