import java.io.*;
import java.util.*;

/**
 * 요세푸스 문제
 * 문제: 원을 이루고 앉아있는 사람들 사이에서 반복적으로 K번째 사람들을 제거할 때, 제거되는 순서를 출력하시오.
 */
public class Bj1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder("<");
        Deque<Integer> deque = new ArrayDeque<>();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i=1; i<N+1; i++){
            deque.addLast(i);
        }
        while(!deque.isEmpty()){
            for(int i=0; i<K-1; i++){
                deque.addLast(deque.removeFirst());
            }
            sb.append(deque.removeFirst()).append(',').append(' ');
        }
        sb.delete(sb.length()-2, sb.length());  // 출력 형식을 맞추기 위해 마지막 요소의 ',', 공백 제거
        sb.append('>');
        System.out.println(sb);

        // 메모리 : 13156KB, 시간 : 212ms
    }
}
