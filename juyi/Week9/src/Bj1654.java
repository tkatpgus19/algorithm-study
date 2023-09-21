import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj1654 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] lansun = new int[K];
		for(int i = 0; i < K; i++) {
			lansun[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(lansun);
		
		long i = 1, j = lansun[K-1], length = 0;
		long sum = 0;
		while(i <= j) {
			sum = 0;
			length = (j - i) / 2 + i;
			
			for(int k = 0; k < K; k++) {
				sum += lansun[k] / length;
			}
			
			if(sum >= N) i = length + 1;
			else if(sum < N) j = length - 1;
		}
		
		System.out.println(j);
	}

}
