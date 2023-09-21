import java.util.*;
import java.io.*;

/**
 * [11663번: 선분 위의 점]
 * 문제: 점의 좌표들이 주어지고 선분의 시작점, 끝점이 주어질 때, 선분 위에 존재하는 점의 개수를 구하시오.
 * 해결: 선분의 시작점, 끝점을 이분탐색을 통해 어느 점을 지나는지 체크하고 개수를 카운트한다.
 */
public class Bj11663 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] dots = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            dots[i] = Long.parseLong(st.nextToken());
        }
        // 오름차순으로 점들 정렬
        Arrays.sort(dots);

        for(int i=0; i<M; i++){
            // 선분을 입력받고 지난 점이 몇개인지 체크
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            int left = 0;
            int right = N-1;
            int start = 0;
            int end = 0;

            // 선분의 시작점 체크
            while(left<=right){
                int mid = (left+right)/2;
                // 시작점에 가까운 점 탐색
                if(n1>dots[mid]){
                    left = mid+1;
                }
                else{
                    right = mid-1;
                }
            }
            // 포함되는 점중 가장 작은 점의 좌표 인덱스 저장
            start = left;

            left = 0;
            right = N-1;
            
            // 선분의 끝점 체크
            while(left<=right){
                int mid = (left+right)/2;
                if(n2<dots[mid]){
                    right = mid-1;
                }
                else{
                    left = mid+1;
                }
            }
            // 포함되는 점중 가장 큰 점의 좌표 인덱스 저장
            end = right+1;
            
            // 위에 놓인 점 개수 저장
            sb.append(end-start).append('\n');
        }
        System.out.println(sb);
        // 메모리: 60775KB, 시간: 920ms
    }
}