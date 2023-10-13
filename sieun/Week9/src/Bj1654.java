import java.io.*;
import java.util.*;

public class Bj1654 {
	static int K, N;
	static long [] line ;
	static StringBuilder sb = new StringBuilder ();
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		K = Integer.parseInt(st.nextToken()); //이미 가지고 있는 랜선의 수 
		N = Integer.parseInt(st.nextToken()); //필요한 랜선의 개수
		line = new long[K];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(bf.readLine());
			line[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(line);
		
		System.out.println(binary(line[0]));
		
	}
	
	public static long binary(long shortest) {
		long ans = 0;
		long left = 0, right = shortest;
		
		while(left<=right) {
			long mid = (left + right)/2;
			int sum =0;
			
			if(mid != 0) {
				for(int i=0; i<K ; i++) {
					sum += line[i]/mid;
				}
			}else {
				
				return 1;
			}
			
			
			if(sum >= N) {
				ans = Math.max(mid, ans);
				left = mid + 1;
			}else {
				right= mid -1;
			}
		}
		return ans;
	}
}