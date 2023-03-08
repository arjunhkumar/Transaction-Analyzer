/**
 * 
 */
package in.ac.iitmandi.compl;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import in.ac.iitmandi.compl.ds.Dataset;
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
        long startTime;
        long finishTime;
        startTime = System.currentTimeMillis();
        try (FileReader fReader = new FileReader(CommonUtils.JSON_PATH)) {
        	ds = gson.fromJson(fReader, Dataset.class);
        } catch (IOException e) {
        	CommonUtils.generateLogMsg(e.getMessage());
			e.printStackTrace();
		} 
        finishTime = System.currentTimeMillis();
//        System.out.println(CommonUtils.generateLogMsg("Dataset loaded succesfully"));;
        if(!(null == ds) && !(null==ds.results)) {
//        	System.out.println(CommonUtils.generateLogMsg("Size of results: "+ds.results.length));
        }
//        System.out.println(CommonUtils.generateLogMsg(String.format("Loading dataset took %d ms", finishTime - startTime)));
        return ds;
	}
	
	void executeAnalysis(Dataset ds);
	
}
