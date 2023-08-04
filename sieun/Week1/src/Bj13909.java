/*첫번쨰 풀이 - 실패 : 메모리 제한 
 * array 사용 방법 말고 다른 방법 찾아야 할 듯? 
 * 규칙이 있나..?
 * 
 * */

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Bj13909 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//N 입력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		//배열 N+1개 0으로 
		int[] windows = new int[N+1];
		int ans = 0;
		int num = 1;
		
		for(int i=1; i<=N; i++) {
			while(i*num < N) {
				if(windows[i*num] == 0) windows[i*num] = 1;
				else windows[i*num] = 0;
				num ++;
			}
			num = 1; 
		}
		
		for(int i=1; i<=N; i++) {
			if(windows[i]==1) ans ++;
		}
		System.out.println(ans);
		
		
	}

}
*/


/*두번째 풀이 - 성공 : 규칙 찾아서..!




  */
import java.io.IOException;

public class Bj13909{
	
	public static void main(String [] args) throws NumberFormatException, IOException{
		
	}
}