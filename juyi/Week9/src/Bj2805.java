import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj2805 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] tree = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tree);
		
		int i = 0, j = tree[N-1], height = 0;
		long sum = 0;
		while(i <= j) {
			height = (j - i) / 2 + i;
			sum = 0;
			for(int k = 0; k < N; k++) {
				if(tree[k] <= height) continue;
				sum += tree[k] - height;
			}
			
			if(sum > M) i = height + 1;
			else if(sum < M) j = height - 1;
			else break;
		}
		if(sum < M) height--;
		System.out.println(height);
	}

}
