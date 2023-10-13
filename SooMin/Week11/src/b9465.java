import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*  b9465    스티커
 *  스티커를 떼면 상하좌우가  같이 떼짐. 이때 상하좌우 떼진 스티커는 쓸 수 없음 (내가 떼려 한 것만 쓸 수 있음)
 *  스티커를 떼서 그 스티커 집합의 가치 가중치가 최대가 되도록 하여라
 */

/*  문제 풀이 방법
 *  (1) 일단 2줄 밖에 없으므로, 처음은 윗줄 arr[1][k]를 생각해보자.
 *  (2) dp[1][k]를 0~k까지 고려했을 때, 최대값이라 하면, dp[1][k]는 arr[1][k] + (대각선 왼쪽 아래, 대각선 왼쪽 아래의 왼쪽) 중 최댓값
 */

public class b9465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[][] arr;
        int[][] dp;

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            arr = new int[3][N + 1];
            dp = new int[3][N + 1];

            for (int i = 1; i <= 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[1][1] = arr[1][1];
            dp[2][1] = arr[2][1];

            for (int j = 2; j <= N; j++) {

                dp[1][j] = arr[1][j] + Math.max(dp[2][j - 1], dp[2][j - 2]);
                dp[2][j] = arr[2][j] + Math.max(dp[1][j - 1], dp[1][j - 2]);

            }

            // for (int[] is : dp) {
            //     System.out.println(Arrays.toString(is));
            // }

            System.out.println(Math.max(dp[1][N], dp[2][N]));
        }
    }
}
