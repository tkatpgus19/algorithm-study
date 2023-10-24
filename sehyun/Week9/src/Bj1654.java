import java.util.*;
import java.io.*;

/**
 * [1654번: 랜선 자르기]
 * 문제: 랜선의 길이가 각각 주어질 때, 랜선을 동일하게 잘라 필요한 개수를 채울 수 있는 최대 길이를 구하시오.
 * 해결: 이분탐색을 통해 적정값을 찾고 이 값으로 모든 랜선을 나눈 몪의 합이 필요한 랜선 개수가 되면서 최댓값인 해를 구한다.
 */
public class Bj1654 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long[] LAN = new long[K];


        for(int i=0; i<K; i++){
            LAN[i] = Long.parseLong(br.readLine());
        }
        
        // 전선 길이 오름차순 정렬
        Arrays.sort(LAN);

        long left = 1;
        long right = LAN[K-1]+1;
        long answer = 0;

        // 이분 탐색
        while(left<=right){
            long mid = (left+right)/2;
            long result = 0;

            // 모든 랜선을 중앙값으로 나누고, 몫(필요개수)을 카운트
            for(int i=0; i<K; i++){
                result += LAN[i]/mid;
            }

            // 필요 개수보다 부족하면 더 짧게 자르기 위해 왼쪽으로 이동
            if(result < N){
                right = mid-1;
                answer = right;
            }
            else{
                left = mid+1;
            }
        }
        System.out.println(answer);
        // 메모리: 17208KB, 시간 200ms
    }
}