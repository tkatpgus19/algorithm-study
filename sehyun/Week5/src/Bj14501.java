import java.util.*;
import java.io.*;

/**
 * [14501번 퇴사]
 * 문제: 상담 일정표가 주어질 때 상담을 통해 얻을 수 있는 최대의 수익을 구하시오.
 * 해결: dp, a(n+k) = max(a(n) + value, a(n+k))
 */
public class Bj14501 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 누적되는 수익을 담기위한 dp배열 선언
        // 최종수익은 N+1번째(N인덱스)에 저장되므로 N+1사이즈 배열 선언
        int[] dp = new int[N+1];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());

            // 만약 해당 작업이 N일 안에 수행할 수 있는 작업이면 수행
            // i+time번째 날의 누적수익은
            // 1. i번째 날의 수익 + time일이 걸리는 작업을 수행해 얻은 수익과
            // 2. 단순히 이전까지 쌓여있던 누적 수익을 비교하여 최대인 값으로 갱신
            if(i+time <= N){
                dp[i+time] = Math.max(dp[i+time], dp[i]+pay);
            }
            
            // 수익은 누적되므로 이전까지 누적된 수익과 작업 후 이전에 갱신했던 값 중 최댓값으로 갱신
            dp[i+1] = Math.max(dp[i], dp[i+1]);
        }
        System.out.println(dp[N]);

        // 메모리: 14188KB, 시간: 124ms
    }
}
