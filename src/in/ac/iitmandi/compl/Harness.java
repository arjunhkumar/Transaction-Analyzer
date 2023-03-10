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
import in.ac.iitmandi.compl.ds.value.ValueTransactionXLarge;
import in.ac.iitmandi.compl.ds.value.ValueTransactionMeduim;
import in.ac.iitmandi.compl.ds.value.ValueTransactionSmall;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx12G"})
@State(Scope.Benchmark)
@Warmup(iterations = 3, timeUnit =  TimeUnit.NANOSECONDS)
@Measurement(iterations = 2, timeUnit =  TimeUnit.NANOSECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
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
		setupValueBenchSmall();
		setupValueBenchXLarge();
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
	
	private void setupValueBenchXLarge() {
		ValueMainXLarge vMainObj = new ValueMainXLarge();
		List<AbstractTransaction> vMainSetupData = vMainObj.convertToTransaction(ds, new ValueTransactionXLarge());
		if(this.benchmarkSetupMap == null) {
			benchmarkSetupMap = new HashMap<>();
		}
		benchmarkSetupMap.put(CommonUtils.VALUEBENCHXLARGE, vMainSetupData);
	}
	
	private void setupValueBenchLarge() {
		ValueMainXLarge vMainObj = new ValueMainXLarge();
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
	
	private void setupValueBenchSmall() {
		ValueMainSmall vMainObj = new ValueMainSmall();
		List<AbstractTransaction> vMainSetupData = vMainObj.convertToTransaction(ds, new ValueTransactionSmall());
		if(this.benchmarkSetupMap == null) {
			benchmarkSetupMap = new HashMap<>();
		}
		benchmarkSetupMap.put(CommonUtils.VALUEBENCHSMALL, vMainSetupData);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.All)
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
	@BenchmarkMode(Mode.All)
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
	@BenchmarkMode(Mode.All)
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
	@BenchmarkMode(Mode.All)
	public void runXLargeValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainXLarge vMainObj = new ValueMainXLarge();
		this.transactionList = vMainObj.convertToTransaction(ds, new ValueTransactionXLarge());
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.transactionList, randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	@BenchmarkMode(Mode.All)
	public void runLargeValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainXLarge vMainObj = new ValueMainXLarge();
		this.transactionList = vMainObj.convertToTransaction(ds, new ValueTransactionLarge());
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.transactionList, randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	@BenchmarkMode(Mode.All)
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
	@BenchmarkMode(Mode.All)
	public void runSmallValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainSmall vMainObj = new ValueMainSmall();
		this.transactionList = vMainObj.convertToTransaction(ds, new ValueTransactionSmall());
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.transactionList, randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	@BenchmarkMode(Mode.All)
	public void runValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMain vMainObj = new ValueMain();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.VALUEBENCH), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	@BenchmarkMode(Mode.All)
	public void runNonValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMain vMainObj = new ValueMain();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.NONVALUEBENCH), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}

	@Benchmark
	@BenchmarkMode(Mode.All)
	public void runIValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		IntermediateValueMain iMainObj = new IntermediateValueMain();
		CommonUtils.ITER_SIZE = 100;
		double retObj = iMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.INTERMEDIATEVALUEBENCH), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	@BenchmarkMode(Mode.All)
	public void runXLargeValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainXLarge vMainObj = new ValueMainXLarge();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.VALUEBENCHXLARGE), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	@BenchmarkMode(Mode.All)
	public void runLargeValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainXLarge vMainObj = new ValueMainXLarge();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.VALUEBENCHLARGE), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	
	@Benchmark
	@BenchmarkMode(Mode.All)
	public void runMeduimValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainMeduim vMainObj = new ValueMainMeduim();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.VALUEBENCHMEDUIM), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
	@Benchmark
	@BenchmarkMode(Mode.All)
	public void runSmallValueAnalysisWithData(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainSmall vMainObj = new ValueMainSmall();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(this.benchmarkSetupMap.get(CommonUtils.VALUEBENCHSMALL), randomGen.nextInt(100));
		blackhole.consume(retObj);
		this.transactionList.clear();
	}
	
//	@Benchmark
//	@BenchmarkMode(Mode.All)
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
//	@BenchmarkMode(Mode.All)
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
//	@BenchmarkMode(Mode.All)
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
//	@BenchmarkMode(Mode.All)
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
//	@BenchmarkMode(Mode.All)
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
