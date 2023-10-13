import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;


/*  2407 조합
 *  nCm의 n과 m이 주어질 때 조합의 결과를 구해라
 */

/*  문제 풀이 방법
 *  조합의 재귀적 공식 nCm = n-1Cm + n-1Cm-1 을 이용
 * (n개 중에서 m개 뽑는 경우의 수는 n번째를 무조건 뽑는다 확정하고, 나머지 중 m-1 뽑기 + n번째 무조건 안 뽑고 n-1개 중 m개 뽑기 )
 */

public class b2407 {

    static BigInteger[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dp = new BigInteger[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if(i>=j){
                    Combination(i,j);
                }
            }
        }

        System.out.println(dp[N][M]);

    }

    public static void Combination(int N, int M) {
        if(M == 0){
            dp[N][M] = BigInteger.valueOf(1);
            return;
        }
        if(N==M){
            dp[N][M] = BigInteger.valueOf(1);
            return;
        }
        dp[N][M] = dp[N-1][M].add(dp[N-1][M-1]);
    }
}
