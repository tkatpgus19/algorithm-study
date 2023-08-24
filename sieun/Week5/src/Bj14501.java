import java.util.*;
import java.io.*;

public class Bj14501 {
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int max = Integer.MIN_VALUE;
		
		int arr[][] = new int [N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			int temp = i;
			int sum = 0;
			while(true) {
				if(temp + arr[temp][0] > N  ) break;
				sum += arr[temp][1];
				//System.out.println("temp : " + temp);
				//System.out.println("day : " + arr[temp][0] + " money : " + arr[temp][1]);
				//System.out.println("sum : " + sum);
				temp += arr[temp][0];
				if(temp >= N ) break;
			}
			max = Math.max(sum, max);
			//System.out.println(max);
			//System.out.println();
			
		}
		
		System.out.println(max);
	}
}

