import java.math.BigInteger;
import java.util.*;
import java.io.*;

/**
 * [2407: 조합]
 * 문제: nCm을 구하시오.
 * 해결: 파스칼 삼각형을 그려 규칙을 찾고 해결한다.
 */
public class Bj2407 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        BigInteger[][] dp = new BigInteger[101][101];
        dp[1][0] = dp[1][1] = BigInteger.ONE;

        for(int i=2; i<101; i++){
            for(int j=0; j<i+1; j++){
                if(j == 0 || j == i){
                    dp[i][j] = BigInteger.ONE;
                }
                else {
                    dp[i][j] = dp[i - 1][j - 1].add(dp[i - 1][j]);
                }
            }
        }
        System.out.println(dp[N][M]);

        // 14644KB, 124ms
    }
}
