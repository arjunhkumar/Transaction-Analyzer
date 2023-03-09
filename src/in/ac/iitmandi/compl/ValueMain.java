/**
 * 
 */
package in.ac.iitmandi.compl;

import java.util.List;

import in.ac.iitmandi.compl.ds.AbstractTransaction;
import in.ac.iitmandi.compl.ds.Dataset;
import in.ac.iitmandi.compl.ds.value.ValueTransaction;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
public class ValueMain implements MainInterface{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime;
		long finishTime;
		startTime = System.currentTimeMillis();
		ValueMain mainObj = new ValueMain();
		if(mainObj.validateArgs(args)) {
			startTime = System.currentTimeMillis();
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
		List<AbstractTransaction> valueList = convertToTransaction(ds, new ValueTransaction());
		double sum =0;
		for(int i = 1; i<=CommonUtils.ITER_SIZE; i++) {
			sum += processTransactions(valueList,i);
		}
		System.out.println("Final value: "+sum);
		finishTime = System.currentTimeMillis();
		System.out.println(CommonUtils.generateLogMsg(String.format("Analysis execution took %d ms", finishTime - startTime)));
	}

}
