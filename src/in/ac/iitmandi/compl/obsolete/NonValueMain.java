/**
 * 
 */
package in.ac.iitmandi.compl.obsolete;

import java.util.List;

import in.ac.iitmandi.compl.ds.Dataset;
import in.ac.iitmandi.compl.ds.nonvalue.NonValueTransaction;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class NonValueMain implements MainInterface{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		NonValueMain mainObj = new NonValueMain();
		if(mainObj.validateArgs(args)) {
			Dataset ds = mainObj.loadDataSet();
			mainObj.executeAnalysis(ds);
			finishTime = System.currentTimeMillis();
//			System.out.println(CommonUtils.generateLogMsg(
//					String.format("Average time for field sum computation:"
//							+ " %d ns", (CommonUtils.averageTime/CommonUtils.ITER_SIZE))));
			System.out.println(CommonUtils.generateLogMsg(String.format("Total execution took %d ms", finishTime - startTime)));
		}
	}
	
	public void executeAnalysis(Dataset ds) {
		long startTime;
		long finishTime;
		List<AbstractTransaction> valueList = convertToTransaction(ds, new NonValueTransaction());
		startTime = System.currentTimeMillis();
		double sum =0;
		for(int i = 1; i<=CommonUtils.ITER_SIZE; i++) {
			sum += processTransactions(valueList,i);
		}
		System.out.println("Final value: "+sum);
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(String.format("Analysis execution took %d ms", finishTime - startTime)));
	
	}

}
