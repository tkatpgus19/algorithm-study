import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*  10844 쉬운 계단수
 * */

/*  문제 풀이 방법
 *  DP
 * */

public class b10844 {

    static long [][] dp;

    static long MOD = 1000000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N== 1){
            System.out.println(9);
            return;
        }

        dp = new long[N+1][10];
        Arrays.fill(dp[1], 1);



        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {

                if(j-1>=0 && j+1 <10){
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%MOD;
                } else if (j-1 < 0) {
                    dp[i][j] = dp[i-1][j+1]%MOD;
                } else {
                    dp[i][j] = dp[i-1][j-1]%MOD;
                }
            }
        }

//        for (int [] a:
//             dp) {
//            System.out.println(Arrays.toString(a));
//        }

        long totalCnt = 0;
        for (int i = 1; i < 10; i++) {
            totalCnt +=(dp[N][i]%MOD);
        }

        System.out.println(totalCnt%MOD);
    }
}