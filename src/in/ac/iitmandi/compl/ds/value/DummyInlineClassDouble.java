/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

/**
 * @author arjun
 *
 */
public primitive class DummyInlineClassDouble {

	/**
	 * 
	 */
	
	private final float[] arr;
	private final int SIZE = 1000;
	
	public DummyInlineClassDouble() {
		arr = new float[SIZE];
		for(int k=0;k<SIZE;k++) {
			this.arr[k] = k;
		}
	}

	/**
	 * @return the arr
	 */
	public float[] getArr() {
		return arr;
	}

	public long getFieldSum() {
		long sum = 0;
		for(int k=SIZE-1;k>=0;--k) {
			sum +=this.arr[k];
		}
		return sum;
	}
	
}
