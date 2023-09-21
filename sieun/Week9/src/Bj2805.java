import java.io.*;
import java.util.*;

public class Bj2805 {
	static int N, M;
	static long[] tree;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new long[N];
		
		st = new StringTokenizer(bf.readLine());
		for(int i=0; i<N; i++) {
			tree[i] = Long.parseLong(st.nextToken());
			
		}
		
		Arrays.sort(tree);
		System.out.println(Binary(tree[N-1]));

	}
	
	public static long Binary(long maxTree) {
		long ans = 0;
		long left = 0, right = maxTree; 
		
		while(left <= right) {
			long sum = 0;
			long mid = (left + right) /2;
			//나무 자르기
			for(int i=0; i<N; i++) {
				if(tree[i]>mid) {
					sum += tree[i] - mid;
				}
			}
			
			//M
			if(sum >= M ) {
				ans = mid;
				left = mid +1;
			}else {
				right = mid -1;
			}
			
		}
		
		return ans;
	}
}
