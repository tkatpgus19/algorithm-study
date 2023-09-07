import java.io.*;
import java.util.*;


public class Bj14888 {
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE; //최대, 최소
	static int N , number[], operator[]; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken()); //N 입력
		number = new int [N]; //숫자 array
		operator = new int [4]; //연산자는 총 4개
		
		//숫자들 N개 입력 받기
		st = new StringTokenizer(bf.readLine());
		for(int i=0; i<N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		//연산자 개수 입력 받기 
		// i에 따라 연산자의 종류가 달라짐
		// 0 : +, 1 : - , 2: *, 3 : /
		st = new StringTokenizer(bf.readLine());
		for(int i=0; i<4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		//dfs로 탐색
		dfs(number[0],1); //제일 첫 숫자, 다음 idx 번호
		
		System.out.println(max); //최대 출력
		System.out.println(min); //최소 출력
	}
	
	
	//dfs 구현
	private static void dfs(int num, int idx) {
		//기저조건
		//idx가 N 일때
		if(idx == N) {
			max = Math.max(num,max); //최대 구함
			min = Math.min(num,min); //최소 구함
			return;
		}
		
		//연산자 마다 체크
		for(int i=0; i<4; i++) {
			//0 이상일 경우만 확인해도 무방
			if(operator[i]> 0) {
				
				operator[i]--;//하나 없애줌
	
				if(i == 0) //+일 경우
					dfs(num + number[idx], idx + 1);
				if(i == 1) //- 일경우
					dfs(num - number[idx], idx + 1);
				if(i ==2) // * 일 경우
					dfs(num * number[idx], idx + 1);
				if(i ==3)// / 일 경우
					dfs(num / number[idx], idx + 1);
				
				operator[i]++; //다시 돌아오니까 +1 해줌
				
			}
		}
		
	}
}