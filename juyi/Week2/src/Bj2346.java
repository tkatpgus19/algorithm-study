import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Bj2346 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        Deque<int[]> ballons = new ArrayDeque<>();
        
        for(int i = 0; i < N; i++) {
            ballons.offer(new int[] { i + 1, Integer.parseInt(st.nextToken())});
        }
        int[] front = ballons.poll();
        sb.append(front[0]).append(" ");
        
        while(!ballons.isEmpty()) {
            if(front[1] > 0) {
                for(int i = 1; i < front[1]; i++) {
                    ballons.offerLast(ballons.pollFirst());
                }
            }
            else {
                for(int i = 0; i < -front[1]; i++) {
                    ballons.offerFirst(ballons.pollLast());
                }
            }
            front = ballons.poll();
            sb.append(front[0]).append(" ");
            
        }
        
        System.out.println(sb);
    }

}