import java.util.*;

// 창문닫기  
public class Bj13909 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double N = sc.nextDouble();
		
		//N번째 창문의 약수의 개수가 홀수면 1(열림), 짝수면 0(닫힘)
		//약수의 개수가 홀수인 수는 제곱수들.
		//따라서 <=N 인 제곱수들을 구하면 된다.
		System.out.println((int)Math.floor(Math.sqrt(N)));
	}
}

//배운것
//Math.floor -> 버림