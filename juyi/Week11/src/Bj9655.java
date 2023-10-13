import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj9655 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());	// N 입력 받기
		boolean[] dp = new boolean[N + 1];	// dp 배열 생성

		dp[1] = true;	// 돌 1개는 상근이가 이김
		
		if(N != 1) {	// N이 1이 아니면
			dp[2] = false;	// 돌 2개는 창영이가 이김

			for(int i = 3; i < N + 1; i++) {	// 3부터 반복
				dp[i] = !dp[i - 1];	// 이전 값의 not
			}
		}
		System.out.println(dp[N]? "SK":"CY");	// true이면 상근이 false면 창영이
	}

}
