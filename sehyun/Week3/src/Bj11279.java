import java.util.*;
import java.io.*;

/**
 * [11279번 최대 힙]
 * 문제: 정수가 주어질 때 최대힙을 구현하시오.
 * 해결: 말 그대로 우선순위 큐를 사용해 값을 넣고 빼도록 한다. 뺄 때 큐가 빈 상태인지만 체크하면 된다.
 *      treeMap 을 이용해 구현할 수도 있다.
 */
public class Bj11279 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());
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

        // 메모리: 26480KB, 시간: 340ms
        // TreeMap 사용시
        // 메모리: 29625KB, 시간: 496ms
    }
}

// TreeMap 으로 구현

//import java.util.*;
//import java.io.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        TreeMap<Integer, Integer> map = new TreeMap<>();
//        int N = Integer.parseInt(br.readLine());
//        int num;
//
//        for(int i=0; i<N; i++){
//            num = Integer.parseInt(br.readLine());
//            if(num == 0){
//                if(map.isEmpty()){
//                    sb.append(0);
//                } else{
//                    sb.append(map.lastKey());
//                    if(map.get(map.lastKey()) == 1){
//                        map.remove(map.lastKey());
//                    } else{
//                        map.put(map.lastKey(), map.get(map.lastKey())-1);
//                    }
//                }
//                sb.append('\n');
//            } else{
//                map.put(num, map.getOrDefault(num, 0)+1);
//            }
//        }
//        System.out.println(sb);
//    }
//}
