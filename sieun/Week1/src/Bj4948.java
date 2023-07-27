
//첫번째 코드
/*
 bj 192 참고
 에라토스테네스의 체 -틀림 근데 왜 틀렸는지 모르겠음 - 해결 : N 보다 크고!!!! 문제 제대로 읽
 13일때 에러 
 
 
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj4948 {
	public static boolean[] prime;
	public static void main(String[] args) throws IOException {
 
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String[] input = bf.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			
			//입력이 0이면 탈
			if(N == 0) break;
			
			//배열 초기화 
			prime = new boolean[2*N + 1];
			prime[0] = prime[1] = true;
			int count = 0;
			
			//2부터 시작해서 특정 수의 배수에 해당하는 수를 모두 지운다.
			for(int i = 2; i < Math.sqrt(prime.length); i++) {
				if(prime[i]) continue;
				for(int j = i * i; j < prime.length; j += i) {
					prime[j] = true;
				}
			}
			
			
			for(int i = N+1; i <= 2*N; i++) {
				// false = 소수 
				if(!prime[i])count++;
			}
			System.out.println(count);
			
		}
		
	
	}
}