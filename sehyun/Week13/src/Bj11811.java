import java.util.*;
import java.io.*;

public class Bj11811{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            int max = Integer.MIN_VALUE;
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                max = Math.max(max, Integer.parseInt(st.nextToken()));
            }
            sb.append(max).append(' ');
        }
        sb.append('\n');
        System.out.println(sb);
    }
}