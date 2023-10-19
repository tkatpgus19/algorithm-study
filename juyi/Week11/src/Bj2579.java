import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj2579 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		 int N = Integer.parseInt(br.readLine());	// N 입력 받기
		 
		 int[] num = new int[N + 1];	// num 배열 생성
		 for(int i = 1; i < N + 1; i++) {
			 num[i] = Integer.parseInt(br.readLine());	// num 배열 입력 받기
		 }
		 
		 int[] dp = new int[N + 1];	// dp 배열 생성
		 
		 dp[1] = num[1];	// dp[1]은 첫 번째 계단밖에 없음

		 for(int i = 2; i < N + 1; i++) {	// 2부터 반복
			 if(i == 2) dp[i] = num[1] + num[2];	// dp[2]는 첫 번째 + 두 번째 계단
			 else if(i == 3) dp[i] = Math.max(num[1], num[2]) + num[3];	// dp[3]은 첫 번째 + 세 번째이거나 두 번째 + 세 번째
			 else dp[i] = Math.max(dp[i - 3] + num[i - 1], dp[i - 2]) + num[i];
		 }
		 
		 System.out.println(dp[N]);
	}

}
