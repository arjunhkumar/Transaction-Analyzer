/**
 * 
 */
package in.ac.iitmandi.compl;

import java.util.List;

import in.ac.iitmandi.compl.ds.AbstractTransaction;
import in.ac.iitmandi.compl.ds.Dataset;
import in.ac.iitmandi.compl.ds.value.ValueTransactionMeduim;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class ValueMainMeduim implements MainInterface{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		ValueMainMeduim mainObj = new ValueMainMeduim();
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
		List<AbstractTransaction> valueList = convertToTransaction(ds, new ValueTransactionMeduim());
		double sum =0;
		for(int i = 1; i<=CommonUtils.ITER_SIZE; i++) {
			sum += processTransactions(valueList,i);
		}
		System.out.println("Final value: "+sum);
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(String.format("Analysis execution took %d ms", finishTime - startTime)));
	}

}
