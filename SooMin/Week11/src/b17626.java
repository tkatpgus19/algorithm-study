import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 *  17626 Four Squares
 *  자연수를 입력 받는다.
 *  해당 숫자를 제곱수들의 최소 개수로 표현 했을 때, 그 개수를 출력해라
 */

/*  문제 풀이 방법
    (1) DP[i][k]를 i번째 제곱수까지 고려했을 때, K를  제곱수의 합으로 표현 시 개수라고 하자
    (2) K의 제곱근보다 작은 수의 제곱수들을 구한다.
    (3) 필요한 제곱 수의 개수가 4를 넘어가는 시점부터 계산하지 않고 다음 행으로 넘어간다.
            (계산 방법 -> 현재 제곱수로 나누어봄. 몫-> 현재 제곱 수가 쓰인 횟수,
                                            나머지 -> dp[i-1][나머지] 행에서 그 값 만들기위해 쓰인 제곱수가 적혀 있음.
                                            그 수들의 합을 이전 답습과 비교하는 것임)
    (4) 계산식은 현재 고려하는 제곱수보다 K가 작으면 이전꺼 답습 (dp[i][k] = dp[i-1][k]),
        크면 답습할 내용과, 현재값 제외하고 제곱 수 센 것 중 더 최소 개수를 저장 (dp[i][k] = Math.min(dp[i-1][k], V+dp[i-1][k-1]
    (5) 배열 다 채우고 마지막 dp[i-1][k-1] 출력

*/

public class b17626 {

    static boolean [] isNotPrime;
    static int [][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[(int)Math.sqrt(N) +1][N+1];

        outLoop:
        for (int i = 1; i <= Math.sqrt(N); i++) {
            for (int j = 1; j <= N; j++) {

                // 초기 값 설정
                if(i == 1){
                    dp[i][j] = j;
                    continue ;
                }

                if(i*i > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    int cnt = j/(i*i) + dp[i-1][j%(i*i)];
                    dp[i][j] = Math.min(dp[i-1][j], cnt);

                }
            }
        }

        System.out.println(dp[(int)Math.sqrt(N)][N]);
    }
}