/**
 * 
 */
package in.ac.iitmandi.compl.ds.value;

/**
 * @author arjun
 *
 */
public class DummyInlineClassDouble {

	/**
	 * 
	 */
	
	private final float[] arr;
	
	public DummyInlineClassDouble() {
		arr = new float[100];
		for(int k=0;k<100;k++) {
			this.arr[k] = k;
		}
	}

	/**
	 * @return the arr
	 */
	public float[] getArr() {
		return arr;
	}

}
