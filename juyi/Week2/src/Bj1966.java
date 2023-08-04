import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Bj1966 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        
        for(int t = 1; t <= tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            LinkedList<int[]> priority = new LinkedList<>();
            
            st = new StringTokenizer(br.readLine());
            
            for(int i = 0; i < N; i++) {
                priority.offer(new int[] {i, Integer.parseInt(st.nextToken())});
            }
            
            int count = 0;
            
            while(!priority.isEmpty()) {
                int[] front = priority.poll();
                boolean isMax = true;
                
                for(int i = 0; i < priority.size(); i++) {
                    if(front[1] < priority.get(i)[1]) {
                        priority.offer(front);
                        for(int j = 0; j < i; j++) {
                            priority.offer(priority.poll());
                        }
                        isMax = false;
                        break;
                    }
                    
                }
                
                if(!isMax) continue;
                
                count++;
                if(front[0] == M) {
                    break;
                }
                
            }
            
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}