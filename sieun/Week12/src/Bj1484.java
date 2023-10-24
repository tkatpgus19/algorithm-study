import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Bj1484 {
	static int G;
	static int oldWeight;
	static ArrayList<Integer> weight = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		G = Integer.parseInt(st.nextToken());
		
		
		twoPointer();
		
		if(weight.isEmpty())
			sb.append(-1);
		else 
			weightList();
		
		System.out.println(sb);
			
		
	}
	
	private static void twoPointer() throws InterruptedException {
		
		int start=1, end =1;
		while(end <= 100000) {
			//Thread.sleep(1000);
			if(G < Math.pow(end, 2) - Math.pow(start, 2))
				start ++;
			if(G > Math.pow(end, 2) - Math.pow(start, 2))
				end ++;
			if(G == Math.pow(end, 2) - Math.pow(start, 2)) {
				weight.add(end);
				end ++;
			}
				
		}

	}

	private static void weightList() {
		Collections.sort(weight);
		for(int i=0;i<weight.size(); i++)
			sb.append(weight.get(i)).append("\n");
		
	}
}
