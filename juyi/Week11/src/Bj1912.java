import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 시간초과
 */
public class Bj1912 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] num = new int[n + 1];
		int[] dp = new int[n + 1];
		for(int i = 1; i < n + 1; i++) {
			dp[i] = Integer.MIN_VALUE;
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < n + 1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			if(dp[1] < num[i]) dp[1] = num[i];
		}

		int max = dp[1];
		for(int i = 2; i < n + 1; i++) {	// dp 배열 인덱스
			for(int j = 1; j <= n - i + 1; j++) {	// 어디 고를지
				int sum = 0;
				for(int k = j; k < j + i; k++) {	// 연속합 구하기
					sum += num[k];
				}
				dp[i] = Math.max(dp[i], sum);
				if(max < dp[i]) max = dp[i];
			}
		}
		
		System.out.println(max);

	}

}
