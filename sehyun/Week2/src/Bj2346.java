import java.util.*;
import java.io.*;

/**
 * 풍선 터뜨리기
 * 문제: 풍선을 터뜨리고 나온 쪽지의 수 만큼 이동하며 반복해서 풍선을 터뜨릴 때, 각 풍선이 터진 순서를 출력하시오.
 */
public class Bj2346 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Integer> idxDeque = new ArrayDeque<>();
        Deque<Integer> moveDeque = new ArrayDeque<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            idxDeque.addLast(i+1);
            moveDeque.addLast(Integer.parseInt(st.nextToken()));
        }

        int idx = idxDeque.removeFirst();
        int move = moveDeque.removeFirst();
        sb.append(idx).append(' ');

        while(!moveDeque.isEmpty()){
            if(move > 0){
                for(int i=0; i<move-1; i++){
                    idxDeque.addLast(idxDeque.removeFirst());
                    moveDeque.addLast(moveDeque.removeFirst());
                }
            } else{
                for(int i=0; i>move; i--){
                    idxDeque.addFirst(idxDeque.removeLast());
                    moveDeque.addFirst(moveDeque.removeLast());
                }
            }
            idx = idxDeque.removeFirst();
            move = moveDeque.removeFirst();
            sb.append(idx).append(' ');
        }
        System.out.println(sb);
        // 메모리 : 12808KB, 시간 : 116ms
    }
}
