/**
 * 
 */
package in.ac.iitmandi.compl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;

import in.ac.iitmandi.compl.ds.Dataset;
import in.ac.iitmandi.compl.ds.nonvalue.NonValueTransaction;
import in.ac.iitmandi.compl.ds.value.IntermediateValueTransaction;
import in.ac.iitmandi.compl.ds.value.ValueTransaction;
import in.ac.iitmandi.compl.ds.value.ValueTransaction2;
import in.ac.iitmandi.compl.ds.value.ValueTransaction3;
import in.ac.iitmandi.compl.utils.CommonUtils;

/**
 * @author arjun
 *
 */
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx12G"})
@State(Scope.Benchmark)
public class Harness implements MainInterface {

	public List<NonValueTransaction> nonValueList;
	public List<ValueTransaction> valueList;
	public List<ValueTransaction2> valueList2;
	public List<ValueTransaction3> valueList3;
	public List<IntermediateValueTransaction> iValueList;
	
	public Harness() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws Exception {
//		setUpBenchmark();
        Main.main(args);
    }

	@Setup(Level.Invocation)
	public void setUpBenchmark() {
//		if(mainObj.validateArgs(args)) {
		Dataset ds = loadDataSet();
		convertDataSet(ds);
		this.valueList = new ArrayList<>();
		
//		}
	}

	@Override
	public void executeAnalysis(Dataset ds) {
//		runNonValueAnalysis();
//		runIValueAnalysis();
//		runValueAnalysis();
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@Warmup(iterations = 2)
	@Measurement(iterations = 2, timeUnit =  TimeUnit.MICROSECONDS)
	public void runNonValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		NonValueMain nvMainObj = new NonValueMain();
//		System.out.println("Args: "+mainObj.nonValueList);
//		System.out.println("Args1: "+mainObj.valueList);
		CommonUtils.ITER_SIZE = 100;
		double retObj = nvMainObj.processTransactions(mainObj.nonValueList, randomGen.nextInt(100));
		blackhole.consume(retObj);
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@Warmup(iterations = 2)
	@Measurement(iterations = 2, timeUnit =  TimeUnit.MICROSECONDS)
	public void runIValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		IntermediateValueMain ivMainObj = new IntermediateValueMain();
		CommonUtils.ITER_SIZE = 100;
		double retObj = ivMainObj.processTransactions(mainObj.iValueList, randomGen.nextInt(100));
		blackhole.consume(retObj);
	}
	
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@Warmup(iterations = 2)
	@Measurement(iterations = 2, timeUnit =  TimeUnit.MICROSECONDS)
	public void runValueAnalysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMain vMainObj = new ValueMain();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(mainObj.valueList, randomGen.nextInt(100));
		blackhole.consume(retObj);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@Warmup(iterations = 2)
	@Measurement(iterations = 2, timeUnit =  TimeUnit.MICROSECONDS)
	public void runValue2Analysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainRunner vMainObj = new ValueMainRunner();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(mainObj.valueList2, randomGen.nextInt(100));
		blackhole.consume(retObj);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@Warmup(iterations = 2)
	@Measurement(iterations = 2, timeUnit =  TimeUnit.MICROSECONDS)
	public void runValue3Analysis(Harness mainObj, Blackhole blackhole) {
		Random randomGen = new Random();
		ValueMainRunner2 vMainObj = new ValueMainRunner2();
		CommonUtils.ITER_SIZE = 100;
		double retObj = vMainObj.processTransactions(mainObj.valueList3, randomGen.nextInt(100));
		blackhole.consume(retObj);
	}
	
	
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

	public void convertDataSet(Dataset ds) {
		NonValueMain nvMainObj = new NonValueMain();
		this.nonValueList = nvMainObj.convertToNonValueTransactions(ds);
		IntermediateValueMain ivMainObj = new IntermediateValueMain();
		this.iValueList = ivMainObj.convertToValueTransactions(ds);
		
		ValueMain vMainObj = new ValueMain();
		this.valueList = vMainObj.convertToValueTransactions(ds);
		
		ValueMainRunner vMainObj2 = new ValueMainRunner();
		this.valueList2 = vMainObj2.convertToValueTransactions(ds);
		
		ValueMainRunner2 vMainObj3 = new ValueMainRunner2();
		this.valueList3 = vMainObj3.convertToValueTransactions(ds);
	}

	
}
