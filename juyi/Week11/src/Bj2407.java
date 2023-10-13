import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Bj2407 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	// n 입력
		int m = Integer.parseInt(st.nextToken());	// m 입력
		BigInteger[] dp = new BigInteger[m + 1];	// dp 배열 생성, n이 100까지이므로 최대값이 100C50이 됨. => long의 범위도 벗어나므로 BigInteger를 사용
		dp[1] = new BigInteger(Integer.toString(n));	// dp[1] 초기화
		
		for(int i = 2; i < m + 1; i++) {	// 2부터 반복
			BigInteger temp = dp[i - 1].multiply(new BigInteger(Integer.toString(n - i + 1)));	// dp[i] = dp[i-1]*(n-i+1)/i
			dp[i] = temp.divide(new BigInteger(Integer.toString(i)));
		}
		
		System.out.println(dp[m]);	// 출력
	}

}
