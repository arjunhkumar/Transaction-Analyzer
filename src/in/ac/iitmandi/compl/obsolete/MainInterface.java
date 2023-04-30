/**
 * 
 */
package in.ac.iitmandi.compl.obsolete;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import in.ac.iitmandi.compl.ds.Dataset;
import in.ac.iitmandi.compl.ds.JSONResult;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public interface MainInterface {

	default boolean validateArgs(String[] args) {
		if(! (args.length == 1 || args.length == 2)) {
			System.out.println(CommonUtils.generateErrorMsg("No. of arguments is incorrect."));
			System.out.println(CommonUtils.generateErrorMsg("Exiting without executing."));
			return false;
		}
		String iterSize = args[0];
		int iterVal = Integer.parseInt(iterSize);
		CommonUtils.ITER_SIZE = iterVal;
		if(args.length == 2) {
			String debugMode = args[1];
			if("true".equalsIgnoreCase(debugMode)) {
				CommonUtils.debugMode = true;
			}
		}
		return true;
	}
	
	default Dataset loadDataSet() {
		Dataset ds = null;
        Gson gson = new Gson();
        try (FileReader fReader = new FileReader(CommonUtils.JSON_PATH)) {
        	ds = gson.fromJson(fReader, Dataset.class);
        } catch (IOException e) {
        	CommonUtils.generateLogMsg(e.getMessage());
			e.printStackTrace();
		} 
        if(!(null == ds) && !(null==ds.results)) {
//        	System.out.println(CommonUtils.generateLogMsg("Size of results: "+ds.results.length));
        }
//        System.out.println(CommonUtils.generateLogMsg(String.format("Loading dataset took %d ms", finishTime - startTime)));
        return ds;
	}
	
	void executeAnalysis(Dataset ds);
	
	default List<AbstractTransaction> convertToTransaction(Dataset ds,AbstractTransaction transaction) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		List<AbstractTransaction> transactionList = null;
		if(null != ds && null != ds.getResults() && ds.getResults().length > 0) {
			transactionList = new ArrayList<>();
			for (JSONResult transactionData : ds.getResults()) {
				AbstractTransaction nonValueTransaction = transaction.convertToTransactionObject(transactionData);
				transactionList.add(nonValueTransaction);
			}
		}
		finishTime = System.currentTimeMillis();
		CommonUtils.printLog(
				String.format("Dataset conversion took "
						+ "%d ms", finishTime - startTime));
		return transactionList;
	}
	
	default double processTransactions(List<AbstractTransaction> valueList, int divident) {
		double blackHole;
		double avgTransactionAmt = computeAverageTransactionAmount(valueList)/divident;
//		System.out.println(CommonUtils.generateLogMsg("Average Transaction Amount: "+avgTransactionAmt));
//		List<AbstractTransaction> workList = new ArrayList<>(valueList);
		double avgProcessingFee = computeAverageProcessingFee(valueList,divident/CommonUtils.ITER_SIZE);
//		avgProcessingFee += computeAverageProcessingFee(valueList,(divident*2)/CommonUtils.ITER_SIZE);
//		System.out.println(CommonUtils.generateLogMsg("Average processing fee: "+avgProcessingFee));
//		List<AbstractTransaction> workList1 = new ArrayList<>(workList);
		int numberOfCustomers = computeNumberOfCustomers(updateTransactions(valueList,divident/CommonUtils.ITER_SIZE));
//		numberOfCustomers += computeNumberOfCustomers(updateTransactions(valueList,(divident*2)/CommonUtils.ITER_SIZE));
//		System.out.println(CommonUtils.generateLogMsg("No. of transactions successfull are: "+numberOfCustomers));
//		double accessVal = increasePrimitiveAccessOperation(valueList,1000);
		blackHole =  avgTransactionAmt + avgProcessingFee + numberOfCustomers;
//		blackHole = accessVal;
		return blackHole;
	}
	
	default double computeAverageTransactionAmount(List<AbstractTransaction> valueList) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(AbstractTransaction valueTransaction : valueList) {
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
	
	default double computeAverageProcessingFee(List<AbstractTransaction> valueList, float rate) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(AbstractTransaction valueTransaction : valueList) {
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
	
	default List<AbstractTransaction> updateTransactions(List<AbstractTransaction> workList, int rate) {
		long startTime;
		long finishTime;
//		long i0 = 0;
//		long i1 = 0;
//		long i2 = 0;
//		long i3 = 0;
		startTime = System.currentTimeMillis();
//		int count = 0;
		if(null != workList && !workList.isEmpty()) {
			for(AbstractTransaction valueTransaction : workList) {
//				i0 = System.currentTimeMillis();
				double transactionAmt = valueTransaction.getTransactionAmount();
				double processingFee = transactionAmt*rate;
//				i1 = System.currentTimeMillis();
//				PaymentInfo pInfo = new PaymentInfo(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),false);
//				valueTransaction.setFeeInfo(pInfo);
				valueTransaction.resetFeeInfo(valueTransaction.createNewPaymentObject(processingFee));
////				i2 = System.currentTimeMillis();
				if(valueTransaction.getCustAcctBalance() >= processingFee) {
//					count++;
					valueTransaction.updateTransactionStatus(true);
				}
//				i3 = System.currentTimeMillis();
			}
		}
//		System.out.println("Value Transactions count:"+count);
		finishTime = System.currentTimeMillis();
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I1 took "
//						+ "%d ms", i1 - i0)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I2 took "
//						+ "%d ms", i2 - i1)));
//		System.out.println(CommonUtils.generateLogMsg(
//				String.format("I3 took "
//						+ "%d ms", i3 - i2)));
		CommonUtils.printLog(
				String.format("Updating transactions took "
						+ "%d ms", finishTime - startTime));
		return workList;
	}

	default int computeNumberOfCustomers(List<AbstractTransaction> updateTransactions) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != updateTransactions && !updateTransactions.isEmpty()) {
			int noOfCustomers = 0;
			for(AbstractTransaction valueTransaction : updateTransactions) {
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
	
	default double increasePrimitiveAccessOperation(List<AbstractTransaction> valueList,int randomInt) {
		long startTime;
		long finishTime;
//		long i1 =0;
//		long i2 =0;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(AbstractTransaction valueTransaction : valueList) {
//				i1 = System.currentTimeMillis();
				double transactionAmt = valueTransaction.computeFieldSum(randomInt);
				sum += transactionAmt;
//				i2 = System.currentTimeMillis();
			}
			finishTime = System.currentTimeMillis();
//			System.out.println(CommonUtils.generateLogMsg(
//					String.format("Field sum computation at parent took "
			CommonUtils.printLog(
					String.format("Primitive access operation took "
							+ "%d ms", finishTime - startTime));
			return sum/valueList.size();
		}
		return 0;
	}
	
}
