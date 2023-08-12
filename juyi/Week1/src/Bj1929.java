import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1929 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
        
		boolean[] isPrime = new boolean[N + 1];
		
//		for(int i = M; i <= N; i++) {
//			boolean prime = true;
//			for(int j = 2; j <= Math.sqrt(i); j++) {
//				if(i % j == 0) {
//					prime = false; // 나누어떨어진다면 소수가 아니므로 false 처리
//					break;
//				}
//			}
//			if(i != 1 && prime == true) System.out.println(i); // 1은 소수가 아니므로 1 제외
//		}
		
		//에라토스테네스의 체 사용
        for(int i = 2; i < N + 1; i++) {
            if(isPrime[i] == true) continue;
            
            for(int j = 2 * i; j < N + 1; j += i) {
                isPrime[j] = true;
            }
        }
        
        for(int i = M; i < N + 1; i++){
            if(isPrime[i] != true) sb.append(i).append("\n");
        }
        
        System.out.println(sb);
	}
}