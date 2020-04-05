/**
 * @author Huandong Chang
 * @version Feb 2020
 */
import java.util.Arrays;

/**
*/
public class LFSR2 {

	private int[] taps;
	private int[] current;

	/**
	constructor class
	*/
	public LFSR2 (int[] current, int[] taps) {
		this.taps=taps;
		this.current = current;
	}

	/**
	sets the tap bits to the given indices
	does not need to be within the length of seed, out of bounds indices will be ignored
	@param newcurrent the array of new current intervals
	@return void
	*/
	public void setcurrent(int[] newcurrent) {
		this.current = newcurrent;
	}

	public int generateNext(){
		int[] temp=new int[current.length];
		int tap_sum=0;
		for (int i=0; i<taps.length;i++){
			tap_sum+=current[current.length-1-taps[i]];
		}

		temp[0]=tap_sum%2;
		for (int i=0;i<current.length-1;i++){
			temp[i+1]=current[i];
		}
		current=temp;
		String str=new String();
		for (int j=0;j<current.length;j++){
			str+=Integer.toString(current[j]);
		}
		int decimal=Integer.parseInt(str,2);

		return decimal;
	}


	 public static void main(String[] args){
		 	int[] seed=new int[]{0,0,1,0,1,1,0,1};
		 int[] taps=new int[]{5,3,2,0};
		 LFSR2 obj=new LFSR2(seed,taps);

		 for (int i=0;i<10;i++){
			 int next=obj.generateNext();
			 System.out.println(next);
		 }
	 }
}
