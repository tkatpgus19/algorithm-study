import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1929 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader 선언
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 구분
		int M = Integer.parseInt(st.nextToken()); // M 입력
		int N = Integer.parseInt(st.nextToken()); // N 입력
		
		for(int i = M; i <= N; i++) {
			boolean prime = true;
			for(int j = 2; j <= Math.sqrt(i); j++) {
				if(i % j == 0) {
					prime = false; // 나누어떨어진다면 소수가 아니므로 false 처리
					break;
				}
			}
			if(i != 1 && prime == true) System.out.println(i); // 1은 소수가 아니므로 1 제외
		}
	}
}