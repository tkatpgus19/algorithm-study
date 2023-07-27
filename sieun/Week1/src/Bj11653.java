import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj11653 {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(bf.readLine());
		
		while(true) {
			if(num == 1) break;
			for(int i=2; i<= num; i++) {
				if(num%i == 0) {
					System.out.println(i);
					num=num/i;
					break;
				}
			}
		}
		
		
	}
}