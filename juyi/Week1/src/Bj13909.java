import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj13909 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader 선언
		int N = Integer.parseInt(br.readLine()); // N 입력 받기
		
		int count = 0, i = 1;
		
		while(i * i <= N) { // 제곱수의 개수를 찾기 위한 반복문
			count++;
			i++;
		}
		
		System.out.println(count);
		
	}
}
