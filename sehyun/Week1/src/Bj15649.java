import java.io.*;
import java.util.*;

public class Bj15649 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] output = new int[M];
        boolean[] visited = new boolean[N];

        permutation(visited, output, N, M, 0);
        System.out.println(sb);

        // 메모리: 19956KB, 시간: 164ms
    }

    // dfs와 유사한 백트래킹 순열함수
    static void permutation(boolean[] visited, int[] output, int N, int M, int cnt){
        if(cnt == M){
            for(int i : output){
                sb.append(i).append(' ');
            }
            sb.append('\n');
            return;
        }
        // 탐색하며 방문하지 않은 요소를 방문처리 + output값 세팅
        // 백트래킹을 위해 다시 방문처리 취소
        for(int i=0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                output[cnt] = i+1;
                permutation(visited, output, N, M, cnt+1);
                visited[i] = false;
            }
        }
    }

}
