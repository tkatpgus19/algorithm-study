import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj4948 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader 선언
		int n, count;
		
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0) return; // n이 0이면 종료
			
			count = 0;
			
			for(int i = n + 1; i <= 2 * n; i++) {
				boolean prime = true;
				for(int j = 2; j * j <= i; j++) {
					if(i % j == 0) {
						prime = false;
						break;
					}
				}
				if(i != 1 && prime == true) count++;
			}
			System.out.println(count);
		}
	}

}
