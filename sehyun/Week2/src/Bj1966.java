import java.io.*;
import java.util.*;

/**
 * 프린터 큐
 * 문제: 각 문서들의 중요도가 주어졌을 때 특정 문서가 출력되는 순서를 출력하시오.
 */
public class Bj1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            Deque<Integer> idxDeque = new ArrayDeque<>();
            Deque<Integer> priDeque = new ArrayDeque<>();

            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<N; i++){
                idxDeque.addLast(i);
                priDeque.addLast(Integer.parseInt(st.nextToken()));
            }

            int answer = 0;
            while(!priDeque.isEmpty()){
                if(priDeque.peekFirst() != Collections.max(priDeque)){
                    priDeque.addLast(priDeque.removeFirst());
                    idxDeque.addLast(idxDeque.removeFirst());
                } else if(idxDeque.peekFirst() == M){
                    sb.append(answer+1).append('\n');
                    break;
                } else{
                    priDeque.removeFirst();
                    idxDeque.removeFirst();
                    answer++;
                }
            }
        }
        System.out.println(sb);

        // 메모리 : 12808KB, 시간 : 116ms
    }
}
