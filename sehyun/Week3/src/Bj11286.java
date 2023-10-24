import java.util.*;
import java.io.*;

/**
 * [11286번 절댓값 힙]
 * 문제: 정수를 입력받았을 때, 절댓값이 가장 작은 값을 출력하시오.
 * 해결: 간단히 우선순위 큐에 comparator 인터페이스의 구현체를 람다식으로 구현하면 해결할 수 있다.
 */
public class Bj11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pQueue = new PriorityQueue<>((i1, i2)->{
            if(Math.abs(i1) == Math.abs(i2)){
                return i1-i2;
            }
            return Math.abs(i1)-Math.abs(i2);
        });

        int N = Integer.parseInt(br.readLine());
        int num;

        for(int i=0; i<N; i++){
            num = Integer.parseInt(br.readLine());
            if(num == 0){
                if(pQueue.isEmpty()){
                    sb.append(0);
                } else{
                    sb.append(pQueue.remove());
                }
                sb.append('\n');
            } else{
                pQueue.add(num);
            }
        }
        System.out.println(sb);
    }
}
