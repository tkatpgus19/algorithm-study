import java.io.*;

public class Bj4948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1 <= n <= 123,456 이고 2 <= 2n <= 대략 250,000 
        final int MAX = 250000;

        // 에라토스테네스의 체 사용
        boolean[] isPrime = new boolean[MAX];
        for(int i=2; i<MAX; i++){
            if(!isPrime[i]){
                for(int j=2*i; j<MAX; j+=i){
                    isPrime[j] = true;
                }
            }
        }

        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n == 0){
                break;
            }
            int answer = 0;
            for(int i=n+1; i<2*n+1; i++){
                if(!isPrime[i]){
                    answer++;
                }
            }
            sb.append(answer).append('\n');
        }

        System.out.println(sb);

        // 메모리: 12196KB, 시간: 132ms
        // System.out.print 사용시
        // 메모리: 12548KB, 시간: 136ms
    }
}
