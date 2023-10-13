import java.io.*;

/**
 * [17626번: Four Squares]
 * 문제: 자연수가 주어질 때, 해당 수를 최소 개수의 제곱수 합으로 표현하는 경우를 구하시오.
 * 해결: 점화식을 세워 푼다
 */
public class Bj17626 {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        dp[1] = 1;
        for(int i=2; i<N+1; i++){
            int tmp = Integer.MAX_VALUE;
            for(int j=1; j*j<i+1; j++){
                tmp = Math.min(dp[i-j*j], tmp);
            }
            dp[i] = tmp+1;
        }
        System.out.println(dp[N]);

        // 메모리: 14596KB, 시간: 144ms
    }

}