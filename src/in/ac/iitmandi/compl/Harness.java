/**
 * 
 */
package in.ac.iitmandi.compl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;

import in.ac.iitmandi.compl.ds.AbstractTransaction;
import in.ac.iitmandi.compl.ds.Dataset;
import in.ac.iitmandi.compl.ds.nonvalue.NonValueTransaction;
import in.ac.iitmandi.compl.ds.value.IntermediateValueTransaction;
import in.ac.iitmandi.compl.ds.value.ValueTransaction;
import in.ac.iitmandi.compl.ds.value.ValueTransactionLarge;
import in.ac.iitmandi.compl.ds.value.ValueTransactionMeduim;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx12G"})
@State(Scope.Benchmark)
@Warmup(iterations = 2, timeUnit =  TimeUnit.NANOSECONDS)
@Measurement(iterations = 2, timeUnit =  TimeUnit.NANOSECONDS)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class Harness implements MainInterface{
	
	public Dataset ds;
	public List<AbstractTransaction> transactionList;
	public Map<Integer,List<AbstractTransaction>> benchmarkSetupMap; 
	
	public Harness() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws Exception {
//		setUpBenchmark();
        Main.main(args);
    }

	@Setup(Level.Invocation)
	public void setUpBenchmark() {
		this.ds = loadDataSet();
		this.transactionList = new ArrayList<>();
		setupNonValueBench();
		setupValueBench();
		setupIValueBench();
		setupValueBenchLarge();
		setupValueBenchMeduim();
	}

	private void setupNonValueBench() {
		NonValueMain nvMainObj = new NonValueMain();
		List<AbstractTransaction> nvMainSetupData = nvMainObj.convertToTransaction(ds, new NonValueTransaction());
		if(this.benchmarkSetupMap == null) {
			benchmarkSetupMap = new HashMap<>();
		}
		benchmarkSetupMap.put(CommonUtils.NONVALUEBENCH, nvMainSetupData);
	}
	
	private void setupValueBench() {
		ValueMain vMainObj = new ValueMain();
		List<AbstractTransaction> vMainSetupData = vMainObj.convertToTransaction(ds, new ValueTransaction());
		if(this.benchmarkSetupMap == null) {
			benchmarkSetupMap = new HashMap<>();
		}
		benchmarkSetupMap.put(CommonUtils.VALUEBENCH, vMainSetupData);
	}
	
	private void setupIValueBench() {
		IntermediateValueMain vMainObj = new IntermediateValueMain();
		List<AbstractTransaction> vMainSetupData = vMainObj.convertToTransaction(ds, new IntermediateValueTransaction());
		if(this.benchmarkSetupMap == null) {
			benchmarkSetupMap = new HashMap<>();
		}
		benchmarkSetupMap.put(CommonUtils.INTERMEDIATEVALUEBENCH, vMainSetupData);
	}
	
	private void setupValueBenchLarge() {
		ValueMainLarge vMainObj = new ValueMainLarge();
		List<AbstractTransaction> vMainSetupData = vMainObj.convertToTransaction(ds, new ValueTransactionLarge());
		if(this.benchmarkSetupMap == null) {
			benchmarkSetupMap = new HashMap<>();
		}
		benchmarkSetupMap.put(CommonUtils.VALUEBENCHLARGE, vMainSetupData);
	}
	
	private void setupValueBenchMeduim() {
		ValueMainMeduim vMainObj = new ValueMainMeduim();
		List<AbstractTransaction> vMainSetupData = vMainObj.convertToTransaction(ds, new ValueTransactionMeduim());
		if(this.benchmarkSetupMap == null) {
			benchmarkSetupMap = new HashMap<>();
		}
		benchmarkSetupMap.put(CommonUtils.VALUEBENCHMEDUIM, vMainSetupData);
	}
	
	@Benchmark
	public void runNonValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		NonValueMain nvMainObj = new NonValueMain();
		this.transactionList = nvMainObj.convertToTransaction(ds, new NonValueTransaction());
		CommonUtils.ITER_SIZE = 100;
		double retObj = nvMainObj.processTransactions(this.transactionList, randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	public void runValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMain vMainObj = new ValueMain();
		this.transactionList = vMainObj.convertToTransaction(ds, new ValueTransaction());
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.transactionList, randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}

	@Benchmark
	public void runIValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		IntermediateValueMain iMainObj = new IntermediateValueMain();
		this.transactionList = iMainObj.convertToTransaction(ds, new ValueTransaction());
		CommonUtils.ITER_SIZE = 100;
		double retObj = iMainObj.processTransactions(this.transactionList, randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	public void runLargeValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainLarge vMainObj = new ValueMainLarge();
		this.transactionList = vMainObj.convertToTransaction(ds, new ValueTransactionLarge());
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.transactionList, randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	public void runMeduimValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainMeduim vMainObj = new ValueMainMeduim();
		this.transactionList = vMainObj.convertToTransaction(ds, new ValueTransactionMeduim());
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.transactionList, randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	
	@Benchmark
	public void runValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMain vMainObj = new ValueMain();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.VALUEBENCH), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	public void runNonValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMain vMainObj = new ValueMain();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.NONVALUEBENCH), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}

	@Benchmark
	public void runIValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		IntermediateValueMain iMainObj = new IntermediateValueMain();
		CommonUtils.ITER_SIZE = 100;
		double retObj = iMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.INTERMEDIATEVALUEBENCH), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	public void runLargeValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainLarge vMainObj = new ValueMainLarge();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.VALUEBENCHLARGE), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	public void runMeduimValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainMeduim vMainObj = new ValueMainMeduim();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.VALUEBENCHMEDUIM), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	
//	@Benchmark
//	@BenchmarkMode(Mode.AverageTime)
//	@Warmup(iterations = 2)
//	@Measurement(iterations = 2, timeUnit =  TimeUnit.MICROSECONDS)
//	public void runValueAnalysis(Harness mainObj, Blackhole blackhole) {
//		Random randomGen = new Random();
//		ValueMain vMainObj = new ValueMain();
//		CommonUtils.ITER_SIZE = 100;
//		double retObj = vMainObj.processTransactions(mainObj.valueList, randomGen.nextInt(100));
//		blackhole.consume(retObj);
//	}
	
//	@Benchmark
//	@BenchmarkMode(Mode.AverageTime)
//	@Warmup(iterations = 2)
//	@Measurement(iterations = 2, timeUnit =  TimeUnit.MICROSECONDS)
//	public void runValue2Analysis(Harness mainObj, Blackhole blackhole) {
//		Random randomGen = new Random();
//		ValueMainLarge vMainObj = new ValueMainLarge();
//		CommonUtils.ITER_SIZE = 100;
//		double retObj = vMainObj.processTransactions(mainObj.valueList2, randomGen.nextInt(100));
//		blackhole.consume(retObj);
//	}
//	
//	@Benchmark
//	@BenchmarkMode(Mode.AverageTime)
//	@Warmup(iterations = 2)
//	@Measurement(iterations = 2, timeUnit =  TimeUnit.MICROSECONDS)
//	public void runValue3Analysis(Harness mainObj, Blackhole blackhole) {
//		Random randomGen = new Random();
//		ValueMainMeduim vMainObj = new ValueMainMeduim();
//		CommonUtils.ITER_SIZE = 100;
//		double retObj = vMainObj.processTransactions(mainObj.valueList3, randomGen.nextInt(100));
//		blackhole.consume(retObj);
//	}
	
	
//	@Benchmark
//	@BenchmarkMode(Mode.AverageTime)
//	@Warmup(iterations = 10)
//	@Measurement(iterations = 10, timeUnit =  TimeUnit.MILLISECONDS)
//	public void runIValueAnalysis(Blackhole blackhole) {
//		Random randomGen = new Random();
//		IntermediateValueMain ivMainObj = new IntermediateValueMain();
//		double retObj = ivMainObj.processTransactions(iValueList, randomGen.nextInt(100));
//		blackhole.consume(retObj);
//	}
//	
//	@Benchmark
//	@BenchmarkMode(Mode.AverageTime)
//	@Warmup(iterations = 10)
//	@Measurement(iterations = 10, timeUnit =  TimeUnit.MILLISECONDS)
//	public void runValueAnalysis(Blackhole blackhole) {
//		Random randomGen = new Random();
//		ValueMain vMainObj = new ValueMain();
//		double retObj = vMainObj.processTransactions(valueList, randomGen.nextInt(100));
//		blackhole.consume(retObj);
//	}

//	public void convertDataSet(Dataset ds) {
//		NonValueMain nvMainObj = new NonValueMain();
//		this.transactionList = nvMainObj.convertToTransaction(ds, null);
//		this.transactionList.clear();
//		
//		IntermediateValueMain ivMainObj = new IntermediateValueMain();
//		this.iValueList = ivMainObj.convertToValueTransactions(ds);
//		
//		ValueMain vMainObj = new ValueMain();
//		this.valueList = vMainObj.convertToValueTransactions(ds);
//		
//		ValueMainLarge vMainObj2 = new ValueMainLarge();
//		this.valueList2 = vMainObj2.convertToValueTransactions(ds);
//		
//		ValueMainMeduim vMainObj3 = new ValueMainMeduim();
//		this.valueList3 = vMainObj3.convertToValueTransactions(ds);
//	}

	@Override
	public void executeAnalysis(Dataset ds) {
		// TODO Auto-generated method stub
	}

	
}
