import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj17626 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());	// n 입력 받기
		int[] dp = new int[n + 1];	// dp 배열 생성
		
		dp[1] = 1;	// 1은 1
		
		int min = 0;	// 최소값
		for(int i = 2; i < n + 1; i++) {
			min = Integer.MAX_VALUE;
			
			for(int j = 1; j * j <= i; j++) {	// 1부터 제곱근까지 반복
				int temp = i - j * j;
				min = Math.min(min, dp[temp]);	// 최소값 찾기
			}
			
			dp[i] = min + 1;	// 최소값 + 1
		}
		
		System.out.println(dp[n]);
		
	}
}
