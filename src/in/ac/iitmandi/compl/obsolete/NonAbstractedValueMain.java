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
public class NonAbstractedValueMain{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		NonAbstractedValueMain mainObj = new NonAbstractedValueMain();
		if(mainObj.validateArgs(args)) {
			startTime = System.currentTimeMillis();
			Dataset ds = mainObj.loadDataSet();
			mainObj.executeAnalysis(ds);
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Average time for field sum computation:"
							+ " %d ns", (CommonUtils.averageTime/CommonUtils.ITER_SIZE))));
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg(String.format("Total execution took %d ms", finishTime - startTime)));
		}
	}
	
	public void executeAnalysis(Dataset ds) {
		long startTime;
		long finishTime;
		List<NonAbstractedValueTransaction> valueList = convertToTransaction(ds, new NonAbstractedValueTransaction());
		startTime = System.currentTimeMillis();
		double sum =0;
		for(int i = 1; i<=CommonUtils.ITER_SIZE; i++) {
			sum += processTransactions(valueList,i);
		}
		System.out.println("Final value: "+sum);
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(String.format("Analysis execution took %d ms", finishTime - startTime)));
	}

	
	 boolean validateArgs(String[] args) {
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
	
	public Dataset loadDataSet() {
		Dataset ds = null;
        Gson gson = new Gson();
        try (FileReader fReader = new FileReader(CommonUtils.JSON_PATH)) {
        	ds = gson.fromJson(fReader, Dataset.class);
        } catch (IOException e) {
        	CommonUtils.generateLogMsg(e.getMessage());
			e.printStackTrace();
		} 
//        if(!(null == ds) && !(null==ds.results)) {
//        	System.out.println(CommonUtils.generateLogMsg("Size of results: "+ds.results.length));
//        }
//        System.out.println(CommonUtils.generateLogMsg(String.format("Loading dataset took %d ms", finishTime - startTime)));
        return ds;
	}
	
	 List<NonAbstractedValueTransaction> convertToTransaction(Dataset ds,NonAbstractedValueTransaction transaction) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		List<NonAbstractedValueTransaction> transactionList = null;
		if(null != ds && null != ds.getResults() && ds.getResults().length > 0) {
			transactionList = new ArrayList<>();
			for (JSONResult transactionData : ds.getResults()) {
				NonAbstractedValueTransaction nonValueTransaction = transaction.convertToTransactionObject(transactionData);
				transactionList.add(nonValueTransaction);
			}
		}
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(
				String.format("Dataset conversion took "
						+ "%d ms", finishTime - startTime)));
		return transactionList;
	}
	
	 double processTransactions(List<NonAbstractedValueTransaction> valueList, int divident) {
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
		double accessVal = increasePrimitiveAccessOperation(valueList,10000);
		blackHole =  avgTransactionAmt + avgProcessingFee +numberOfCustomers+ accessVal;
//		blackHole = accessVal;
		return blackHole;
	}
	
	 double computeAverageTransactionAmount(List<NonAbstractedValueTransaction> valueList) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(NonAbstractedValueTransaction valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getTransactionAmount();
				sum += transactionAmt;
			}
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Average Transaction Amount computation took "
							+ "%d ms", finishTime - startTime)));
			return sum/valueList.size();
		}
		
		return 0;
	}
	
	 double computeAverageProcessingFee(List<NonAbstractedValueTransaction> valueList, float rate) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(NonAbstractedValueTransaction valueTransaction : valueList) {
				double transactionAmt = valueTransaction.getTransactionAmount();
				double processingFee = transactionAmt*rate;
				sum+=processingFee;
			}
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Average Processing Fee computation took "
							+ "%d ms", finishTime - startTime)));
			return sum/valueList.size();
		}
		return 0;
	}
	
	 List<NonAbstractedValueTransaction> updateTransactions(List<NonAbstractedValueTransaction> workList, int rate) {
		long startTime;
		long finishTime;
		long i0 = 0;
		long i1 = 0;
		long i2 = 0;
		long i3 = 0;
		startTime = System.currentTimeMillis();
		if(null != workList && !workList.isEmpty()) {
			for(NonAbstractedValueTransaction valueTransaction : workList) {
				i0 = System.currentTimeMillis();
				double transactionAmt = valueTransaction.getTransactionAmount();
				double processingFee = transactionAmt*rate;
				i1 = System.currentTimeMillis();
//				PaymentInfo pInfo = new PaymentInfo(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),false);
//				valueTransaction.setFeeInfo(pInfo);
				valueTransaction.resetFeeInfo(valueTransaction.createNewPaymentObject(processingFee));
				i2 = System.currentTimeMillis();
				if(valueTransaction.getCustAcctBalance() >= processingFee) {
//					PaymentInfo updatedPInfo = new PaymentInfo(valueTransaction.getPaymentInfo().getCustAccountBalance(),valueTransaction.getPaymentInfo().getTransactionDate(),valueTransaction.getPaymentInfo().getTransactionTime(),processingFee,valueTransaction.getPaymentInfo().getTransactionFeeRate(),true);
//					valueTransaction.setFeeInfo(updatedPInfo);
					valueTransaction.updateTransactionStatus(true);
				}
				i3 = System.currentTimeMillis();
			}
		}
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(
				String.format("I1 took "
						+ "%d ms", i1 - i0)));
		System.out.println(CommonUtils.generateLogMsg(
				String.format("I2 took "
						+ "%d ms", i2 - i1)));
		System.out.println(CommonUtils.generateLogMsg(
				String.format("I3 took "
						+ "%d ms", i3 - i2)));
		System.out.println(CommonUtils.generateLogMsg(
				String.format("Updating transactions took "
						+ "%d ms", finishTime - startTime)));
		return workList;
	}

	 int computeNumberOfCustomers(List<NonAbstractedValueTransaction> updateTransactions) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		if(null != updateTransactions && !updateTransactions.isEmpty()) {
			int noOfCustomers = 0;
			for(NonAbstractedValueTransaction valueTransaction : updateTransactions) {
				if(valueTransaction.getTransactionStatus()) {
					noOfCustomers++;
				}
			}
			finishTime = System.currentTimeMillis();
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Customer computation took "
							+ "%d ms", finishTime - startTime)));
			return noOfCustomers;
		}
		return 0;
	}
	
	 double increasePrimitiveAccessOperation(List<NonAbstractedValueTransaction> valueList,int randomInt) {
		long startTime;
		long finishTime;
//		long i1 =0;
//		long i2 =0;
		startTime = System.currentTimeMillis();
		if(null != valueList && !valueList.isEmpty()) {
			double sum = 0;
			for(NonAbstractedValueTransaction valueTransaction : valueList) {
//				i1 = System.currentTimeMillis();
				double transactionAmt = valueTransaction.computeFieldSum(randomInt);
				sum += transactionAmt;
//				i2 = System.currentTimeMillis();
			}
			finishTime = System.currentTimeMillis();
//			System.out.println(CommonUtils.generateLogMsg(
//					String.format("Field sum computation at parent took "
//							+ "%d ms", i2 - i1)));
			System.out.println(CommonUtils.generateLogMsg(
					String.format("Primitive access operation took "
							+ "%d ms", finishTime - startTime)));
			return sum/valueList.size();
		}
		return 0;
	}
	
}
