import java.io.*;

public class Bj10974 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[N];
        int[] output = new int[N];

        permutation(visited, output, N, 0);
        System.out.println(sb);

        // 메모리: 19864KB, 시간: 156ms
        // System.out.print 사용시
        // 메모리: 62428KB, 시간: 2328ms
    }

    // 15649번과 사실상 동일한 문제
    static void permutation(boolean[] visited, int[] output, int N, int cnt){
        if(cnt == N){
            for(int i : output){
                sb.append(i).append(' ');
            }
            sb.append('\n');
            return;
        }
        for(int i=0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                output[cnt] = i+1;
                permutation(visited, output, N, cnt+1);
                visited[i] = false;
            }
        }
    }
}
