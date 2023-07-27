import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj11653 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader 선언
		int N = Integer.parseInt(br.readLine()); // N 입력 받기
		
		int i = 2; // 소인수분해 2부터 시작
		while(true) { // N이 1이 될 때까지 반복
			if(N == 1) return; // N이 1이면 main 종료
			if(N % i == 0) { // N이 i로 나누어 떨어지면 소인수분해 
				System.out.println(i); // i 출력
				N /= i; // N을 i로 나눈 몫으로 변환
				i = 2;  // 다시 2부터 소인수분해 시작
			}
			else i++; // N이 i로 나누어 떨어지지 않으면 i 증가
		}
	}
}
