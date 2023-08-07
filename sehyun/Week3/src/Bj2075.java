import java.util.*;
import java.io.*;

/**
 * [2075번 N번째 큰수]
 * 문제: NxN 표에 각 정수들이 주어질 때, N번째 큰 수를 출력하시오.
 * 해결: 단순히 생각하면 가장 마지막 행이 가장 큰 수들의 모임일 것 같지만
 *      그 윗단에 더 큰 수가 일부 존재할 수 있어 단순히 구현하기는 불가능하다.
 *      그냥 우선순위 큐에 모두 넣고 수를 반복해서 뽑을 때 N 번째 뽑힌 수가 정답이 된다.
 */
public class Bj2075 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                pQueue.add(Integer.parseInt(st.nextToken()));
            }
        }
        for(int i=0; i<N-1; i++) {
            pQueue.remove();
        }
        System.out.println(pQueue.remove());

        // 메모리: 275112KB, 시간: 920ms
    }
}
