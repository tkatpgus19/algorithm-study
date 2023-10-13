import java.io.*;

/**
 * [2579: 계단 오르기]
 * 문제: 한 칸 혹은 두 칸씩 계단을 오를 때 얻을 수 있는 최대 점수를 구하시오.
 * 해결: 점화식을 세워 푼다
 */
public class Bj2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] point = new int[N];
        int[][] dp = new int[N][2];

        for(int i=0; i<N; i++){
            point[i] = Integer.parseInt(br.readLine());
        }
        if(N != 1) {
            dp[0][0] = dp[0][1] = point[0];
            dp[1][0] = point[1];
            dp[1][1] = dp[0][0] + point[1];

            for(int i=0; i<N-2; i++){
                // 계단 오르기
                dp[i+2][0] = Math.max(dp[i][0]+point[i+2], dp[i][1]+point[i+2]);
                dp[i+2][1] = Math.max(dp[i+1][0]+point[i+2], dp[i+2][1]);
            }

            System.out.println(Math.max(dp[N-1][0], dp[N-1][1]));
        }
        else{
            System.out.println(point[0]);
        }
        // 메모리: 14188KB, 128ms
    }
}
