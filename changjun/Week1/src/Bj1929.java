import java.util.Scanner;

public class Bj1929 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		Boolean[] primeList = new Boolean[N+1];
		
		//배열 0,1 제외 전부 true로 초기화.
		for(int i=2;i<=N;i++) {
			primeList[i] = true;
		}
		
	    primeList[1] = false; // 1은 소수가 아닙니다.
		
	    //에라토스테네스의 체 
		//소수인지 확인하려면 루트(N) 까지만 나눠보면 됨.
		for(int p=2;p<=Math.sqrt(N);p++) {
			// 그 수가 이미 지워졌다면 바로 continue
			if(primeList[p]==false)
				continue;
			
			// 자기 자신을 제외한 배수들 전부 지움.
			for(int n=p*2;n<=N;n+=p){
				if(primeList[n])
					primeList[n] = false;
			}
		}
		
		for(int i=M;i<=N;i++) {
			if(primeList[i]==true)
				System.out.println(i);
		}
	}
}

//배운 점.
//배열 Boolean으로 선언 시 Object니까 초기화 해주어야 함.
//안 하고 읽으면 바로 Null