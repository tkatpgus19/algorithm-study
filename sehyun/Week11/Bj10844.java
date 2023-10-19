import java.io.*;

/**
 * [10844: 쉬운 계단 수]
 * 문제: 각 자리의 수 차가 1인 계단 수의 개수를 구하시오.
 * 해결: 각 숫자마다 계단수를 구하고, 이를 숫자 개수를 늘려가며 해답을 구한다.
 */
public class Bj10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][10];

        for(int i=1; i<10; i++){
            dp[1][i] = 1;
        }

        for(int i=2; i<N+1; i++){
            for(int j=0; j<10; j++){
                if(j == 0){
                    dp[i][0] = dp[i-1][1] % 1_000_000_000;
                }
                else if(j == 9){
                    dp[i][9] = dp[i-1][8] % 1_000_000_000;
                }
                else{
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1_000_000_000;
                }
            }
        }

        long answer = 0;
        for(int i=0; i<10; i++){
            answer += dp[N][i];
            answer %= 1_000_000_000;
        }
        System.out.println(answer% 1_000_000_000);

        // 메모리: 14224KB, 시간: 124ms
    }
}
