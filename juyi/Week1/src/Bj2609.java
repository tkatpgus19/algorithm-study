import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj2609 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader 선언
		StringTokenizer st = new StringTokenizer(br.readLine()); // 공백으로 구분하여 입력
		int a = Integer.parseInt(st.nextToken()); // String을 int로 변환
		int b = Integer.parseInt(st.nextToken()); // String을 int로 변환
		
		// 유클리드 호제법 사용
		// A = BQ + R1 -> B = R1Q + R2 -> R1 = R2Q + R3 ... 나누어떨어지기 전의 R이 최대공약수
		int r, aa = 0, bb = 0;
		
		if(a % b == 0) r = b;
		else {
			r = a % b; // 첫번째 나머지 저장
			aa = b;    // 새로 나누어질 수를 새로운 변수에 저장
			bb = r;    // 새로 나누게 될 수를 새로운 변수에 저장
			
			while(true) {
				if(aa % bb == 0) break; // 나머지가 0이면 r에 저장하지 않고 탈출
				r = aa % bb;            // 나머지가 0이 아닐 때 새로 저장
				aa = bb;                // 나머지가 0이 아닐 때 새로 저장
				bb = r;                 // 나머지가 0이 아닐 때 새로 저장
			}
		}
		
		System.out.println(r); // 나머지가 0이 되기 전의 r이 최대공약수. 출력
		System.out.println(a * b / r); // AB = L(최소공배수)G(최대공약수) 공식 이용
	}

}
