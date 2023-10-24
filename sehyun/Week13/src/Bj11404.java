import java.util.*;
import java.io.*;

public class Bj11404{
    static int[][] path;
    static final int INF = 100_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        path = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                path[i][j] = INF;
                if(i==j){
                    path[i][j] = 0;
                }
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken())-1;
            int n2 = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            path[n1][n2] = Math.min(path[n1][n2], weight);
        }

        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    path[i][j] = Math.min(path[i][j], path[i][k]+path[k][j]);
                }
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(path[i][j] == INF){
                    sb.append(0);
                }
                else {
                    sb.append(path[i][j]);
                }
                sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}