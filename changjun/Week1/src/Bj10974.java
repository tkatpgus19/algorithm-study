//BufferedReader, BufferedWriter,IOException,InputStreamReader,OutputStreamWriter 전부 java.io 
import java.io.*;
import java.util.StringTokenizer;


// 모든 순열 
public class Bj10974 {
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
		M = N;
		
		arr = new int[M];
		isUsed = new int[N];
		
		nPm(0);
		
		//StringBuilder로 한 번에 출력.
		System.out.println(sb);
	}
}

