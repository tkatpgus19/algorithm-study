// 첫 도전 : 시간 초과 

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj1929 {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] input = bf.readLine().split(" ");
		
		int M = Integer.parseInt(input[0]);
		int N = Integer.parseInt(input[1]);
		boolean check = true;
		
		for(int i=M;i<=N; i++) {
			
			for(int j=2; j< i; j++) {
				//System.out.println("i%j : " + i%j);
				if(i%j == 0) {
					check = false;
					break;
				} else continue;
			}
			if(check) System.out.println(i);
			check = true;
			
		}		
	}
}
*/


/*
 * 배운 것
 * 첫 제출 실패 이유 : 시간 계산 실패. 
 * 문제 볼때 범위 잘 확인 하기
 *
 * 시간 줄이 법
 * buffered reader 사용
 * 1. 범위 줄이기 
 * 2. 다른 알고리즘 사용  
 * 
 * 소수 구하는 문제 - 에라토스테네스의 체 사용하기
 * 
 * 에라토스테네스 체 
 * 에라토스테네스의 체는 가장 먼저 소수를 판별할 범위만큼 배열을 할당하여, 해당하는 값을 넣어주고, 
 * 이후에 하나씩 지워나가는 방법을 이용한다.

배열을 생성하여 초기화한다.
2부터 시작해서 특정 수의 배수에 해당하는 수를 모두 지운다.(지울 때 자기자신은 지우지 않고, 이미 지워진 수는 건너뛴다.)
2부터 시작하여 남아있는 수를 모두 출력한다.
 * 
 * 
 * */
 


/*두번째 풀이 - 성공*/


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
 
public class Bj1929 {
	public static boolean[] prime;
	public static void main(String[] args) throws IOException {
 
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = bf.readLine().split(" ");
		int M = Integer.parseInt(input[0]);
		int N = Integer.parseInt(input[1]);
		
		
		//배열 초기화 
		prime = new boolean[N + 1];
		prime[0] = prime[1] = true;
		
		//2부터 시작해서 특정 수의 배수에 해당하는 수를 모두 지운다.(
		for(int i = 2; i <= Math.sqrt(prime.length); i++) {
			if(prime[i]) continue;
			for(int j = i * i; j < prime.length; j += i) {
				prime[j] = true;
			}
		}
		
		
		for(int i = M; i <= N; i++) {
			// false = 소수 
			if(!prime[i]) System.out.println(i);
		}
	
	}
}
