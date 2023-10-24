import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 1912번 연속합
 * 임의의 수열이 주어졌을 떄, 그 수열에서 연속된 수열의 더한 합이 최대가 되는 경우를 구하여라
 */

/*  문제 풀이 방법
 *  (1)
 */

public class b1912 {

    static int[] arr;
    static int[] dp;
    static int max = -1001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = -1002;

        // dp[i]는 0~i까지 연속합 했을 때 최적합.
        for (int i = 1; i <= N; i++) { // 아이템을 1~ i까지 고려
            // 현 아이템 i만 골랐을 때 vs 1~i-1, 2~i-1 .... i-2~i-1중 최대값+ 아이템 i 한 것 중 큰 값
            dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
        }

        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}

