import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	Bj10844 : 쉬운 계단 수
 * 
 * 	1 -> 0, 2 / 2 -> 1, 3 ... 반복
 *  dp를 2차원 배열로 생성하여 행은 n, 열은 0부터 9까지의 경우의 수
 *  
 */
public class Bj10844 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[N + 1][10];
		
		for(int i = 1; i < 10; i++) {
			dp[1][i] = 1;	// N이 1일 때는 0 제외 자기 자신 숫자만 가능
		}
		
		for(int i = 2; i < N + 1; i++) {	// N 2부터
			for(int j = 0; j < 10; j++) {	// 0부터 9까지
				if(j == 0) {	// 0은 전 숫자가 1일 경우만 가능
					dp[i][0] = dp[i - 1][1] % 1000000000;
				}
				else if(j == 9) {	// 9는 전 숫자가 8일 경우만 가능
					dp[i][9] = dp[i - 1][8] % 1000000000;
				}
				else {	// 나머지 경우에는 하나 작은 수, 큰 수
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
				}
			}
		}
		
		long result = 0;
		for(int i = 0; i < 10; i++) {
			result += dp[N][i];	// 결과는 0부터 9까지의 경우의 수의 합
			result %= 1000000000;
		}
		
		System.out.println(result % 1000000000);
	}

}
