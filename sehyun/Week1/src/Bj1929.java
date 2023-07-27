import java.io.*;
import java.util.*;

public class Bj1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[N+1];
        isPrime[1] = true;

        // 에라토스테네스의 체 활용
        for(int i=2; i<N+1; i++){
            if(isPrime[i]){
                continue;
            }
            for(int j=i*2; j<N+1; j+=i){
                isPrime[j] = true;
            }
        }

        for(int i=M; i<N+1; i++){
            if(!isPrime[i]){
                sb.append(i).append('\n');
            }
        }
        System.out.println(sb);

        // 메모리: 17708KB, 시간: 184ms

        // System.out.println 사용시
        // 메모리: 38636KB, 시간: 1112ms
    }
}
