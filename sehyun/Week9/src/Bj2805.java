import java.util.*;
import java.io.*;

/**
 * [2805번: 나무 자르기]
 * 문제: 나무의 길이가 주어질 때, 최소한의 나무를 잘라 필요한 나무 길이를 획득할 수 있는 최대 톱날의 높이를 구하시오.
 * 해결: 이분 탐색으로 높이를 조정하며 최대 높이를 구한다.
 */
public class Bj2805 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] tree = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }
        // 나무 높이 오름차순 정렬
        Arrays.sort(tree);
        int left = 1;
        int right = 1000000000;

        // 이분 탐색으로 탐색
        while(left<=right){
            int mid = (left+right)/2;
            long spare = 0;
            for(int i=0; i<N; i++){
                // 나무를 잘랐을 때 획득할 수 있는 나무 길이 카운트
                if(tree[i]-mid > 0) {
                    spare += tree[i] - mid;
                }
            }
//            System.out.println(spare);
            // 자른 나무가 충분하면 깎는 양을 줄이기
            if(spare >= M){
                left = mid+1;
            }
            // 자른 나무가 부족하면 깎는 양을 늘리기
            else {
                right = mid-1;
            }
        }
        System.out.println(right);
        // 메모리 119448KB, 시간: 1080ms
    }
}