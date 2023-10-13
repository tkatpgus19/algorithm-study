import java.io.*;
import java.util.*;

/**
 * [1912: 연속합]
 * 문제: 임의의 수열이 주어질 때, 연속된 몇 개의 수를 선택해 구할 수 있는 최대 합을 구하시오.
 * 해결: 누적합을 구하다가 새로 등장하는 값이 더 크면 값을 갱신시켜 최대값을 찾는다.
 */
public class Bj1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] dp = new int[N];
        int[] point = new int[N];

        for(int i=0; i<N; i++){
            point[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = point[0];

        for(int i=0; i<N-1; i++){
            dp[i+1] = Math.max(dp[i]+point[i+1], point[i+1]);
        }
        int answer = Integer.MIN_VALUE;
        for(int i=0; i<N; i++){
            if(dp[i] > answer){
                answer = dp[i];
            }
        }
        System.out.println(answer);

        // 메모리: 24124KB, 시간: 276ms
    }
}