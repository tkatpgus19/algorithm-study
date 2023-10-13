import java.util.*;
import java.io.*;

public class Bj2579 {
	static int N;
	static int stairs[] ;
	static int dp[] = new int [1000];
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		
		stairs = new int[N+1];
		
		for(int i=0; i<N; i++) {
			 st = new StringTokenizer(bf.readLine());
			 stairs[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[1] = stairs[1];
		dp[2] = stairs[2];
		
		int cnt = 1;
		for(int i=3; i<=N; i++) {
			
			if(cnt == 3) {
				dp[i] = stairs[i] + dp[i -1];
				cnt = 0;
			}
			
			if(dp[i-1] > dp[i-2]) {
				dp[i] = stairs[i] + dp[i -1];
				cnt = 0;
			}
			else {
				dp[i] = stairs[i] + dp[i -2];
				cnt ++;
			}
//			dp[i] = stairs[i] +  Math.max(dp[i-1], dp[i -2 ]);
		}
		
		System.out.println(dp[N-1]);
		
	}
}
