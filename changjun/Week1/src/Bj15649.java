//BufferedReader, BufferedWriter,IOException,InputStreamReader,OutputStreamWriter 전부 java.io 
import java.io.*;
import java.util.StringTokenizer;


// N과 M
public class Bj15649 {
	static int N;
	static int M;
	static int[] arr;
	static int[] isUsed;
	public static StringBuilder sb = new StringBuilder();
	
	//순열 -> 백트래킹 -> 재귀 
	static void nPm(int dep) {
		// M개 채웠으면 출력하고 종료 
		if(dep == M) {
			for(int n : arr) {
				sb.append(n+1).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		//작은 수부터 확인해서 안 썼으면 채우고 다음 칸으로.
		//다음 재귀 호출 후에는 for문 i++으로 넘어가기 전 안 쓴 상태로 돌려놓음.
		for(int i=0;i<N;i++) {
			if(isUsed[i]==0) {
				arr[dep] = i;
				isUsed[i] = 1;
				nPm(dep+1);
				isUsed[i] = 0;
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		String s = bufferedReader.readLine();
		
		StringTokenizer st = new StringTokenizer(s); //StringTokenizer 인자값에 입력 문자열 넣음
		N = Integer.parseInt(st.nextToken()); //첫번째 호출
		M = Integer.parseInt(st.nextToken()); //두번째 호출
		
		arr = new int[M];
		isUsed = new int[N];
		
		nPm(0);
		
		//StringBuilder로 한 번에 출력.
		System.out.println(sb);
	}
}

//백트래킹 
//백트래킹의 시작. 기본 구조을 익혀놓으면 다른 문제에도 적용가능.
//ex) 9663 N-Queen, 1182 부분수열의 합  
//참조 -> 바킹독 블로그 - 백트래킹.
//https://blog.encrypted.gg/945 

//전역변수 c처럼 static 으로 클래스 영역에 선언.

//백준 8%에서 시간초과.
//java - println() 1000만 까지 자연수 찍는 속도 평균 30초.
//java - BufferedWriter 0.9초
//java - StringBuilder를 이용해 문자열 하나로 합친 뒤 println() 1.2초 -> 선택!
//참조 -> 백준 출력속도 비교 https://www.acmicpc.net/blog/view/57
