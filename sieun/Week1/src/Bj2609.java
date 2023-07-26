/*배운 

BufferedReader 사용법 

- BufferedReader , InputStreamReader, IOException import 해줘야 함
- IOException 예외 처리 해줘야 함
- 한줄에 숫자 2개 받으려면 String으로 받아서 SPLIT 해줘야 함


BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
int a =Integer.parseInt(bf.readLine());
```

*/

/*첫 풀이 - 실패 - 범위 조정 잘못함 */

/*

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj2609 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] input = bf.readLine().split(" ");
		
		int a =Integer.parseInt(input[0]);
		int b = Integer.parseInt(input[1]);
		int num1=0, num2;
		
		//최대 공약수 계산
		for(int i=1; i<Math.min(a, b); i++) {
			if(a%i == 0 && b%i == 0) {
				if(i > num1) num1 = i;
			}
		}
		
		//최소공배수 계산
		num2 = num1*(a/num1)*(b/num1);
	
		System.out.println( num1);
		System.out.println( num2);

	}
}

*/



/*두번째 풀이 - 성공*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj2609 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] input = bf.readLine().split(" ");
		
		int a =Integer.parseInt(input[0]);
		int b = Integer.parseInt(input[1]);
		int num1=0, num2;
		
		//최대 공약수 계산
		for(int i=1; i<=Math.min(a, b); i++) {
			if(a%i == 0 && b%i == 0) {
				if(i > num1) num1 = i;
			}
		}
		
		//최소공배수 계산
		num2 = num1*(a/num1)*(b/num1);
	
		System.out.println( num1);
		System.out.println( num2);

	}
}
