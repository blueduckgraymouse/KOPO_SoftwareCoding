package review1;

public class R2 {

	public static void main(String[] args) {
		String[] v 		= {"190", "780",  "0.19"};
		String[] unit 	= {"m/s", "km/h", "m/ms"};
		
		compare(v, unit);
	}
	
	private static void compare(String[] v, String[] unit) {
		double[] v_std = new double[3];
		
		// convert velocities, standard : km/h
		v_std[0] = Double.parseDouble(v[0]) * 3600 / 1000;			// m/s
		v_std[1] = Double.parseDouble(v[1]);						// km/h
		v_std[2] = Double.parseDouble(v[2]) * 3600 * 1000 / 1000;	// m/ms
		
		int index_max = 0;
		
		// find a maximum velocity
		for (int i = 0 ; i < v_std.length ; i++) {
			if (v_std[i] > v_std[index_max]) {
				index_max = i;
			}
		}
		
		// print the maximum velocity
		System.out.println(v[index_max] + " " + unit[index_max]);
	}
	
}