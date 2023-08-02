import java.io.*;
import java.util.*;

/**
 * 탑
 * 문제: 특정 높이의 탑에서 왼쪽으로 레이저를 쏘았을 때 이 레이저를 수신하는 탑의 번호를 출력하시오.
 */
public class Bj2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] height = new int[N];

        st = new StringTokenizer(br.readLine());
        // 각 탑의 높이와 위치를 스택에 저장한다.
        for(int i=0; i<N; i++){
            height[i] = Integer.parseInt(st.nextToken());
        }

        Deque<int[]> stack = new ArrayDeque<>();
//        Stack<int[]> stack = new Stack<>();

        // 각 탑을 돌며 어느 탑에서 레이저를 수신하는지 체크
        for(int i=0; i<N; i++){
            // 첫 탑은 왼쪽에 아무 탑도 없으므로 0을 추가한다.
            if(stack.isEmpty()){
                sb.append(0).append(' ');
            } else{
                // 스택이 비거나 스택안에 해당 탑 보다 높은 탑이 존재하면 탈출한다.
                while(true){
                    if(stack.isEmpty()){
                        // 레이저를 수신하는 탑이 없다.
                        sb.append(0).append(' ');
                        break;
                    } else if(stack.peekLast()[0] > height[i]){
                        // 레이저를 수신하는 탑의 인덱스를 저장한다.
                        sb.append(stack.peekLast()[1]).append(' ');
                        break;
                    } else{
                        // 필요없는 탑은 제거해간다.
                        stack.removeLast();
                    }
                }
            }
            stack.addLast(new int[]{height[i], i+1});
        }

        System.out.println(sb);

        // 메모리 : 109036KB, 시간 : 584ms
        // Stack 사용시
        // 메모리 : 109984KB, 시간 : 652ms
    }
}
