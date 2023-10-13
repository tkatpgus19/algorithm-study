import java.util.*;
import java.io.*;

public class Bj19637 {
	static int N, M;
	static int characterPower[];
	static int value[];
	static String name[];


	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException  {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken()); //칭호의 개수
		M = Integer.parseInt(st.nextToken()); //캐릭터의 수

		
		name = new String[N];
		value = new int[N];
		
		characterPower = new int [M];

		// 칭호 설정
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			name[i] = st.nextToken();
			value[i] = Integer.parseInt(st.nextToken());
		}

		// 캐릭터 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			characterPower[i] = Integer.parseInt(st.nextToken());
			//캐릭터마다 binary search 해서 string builder 에 append
			sb.append(name[Binary(N, characterPower[i])] + "\n");

		}
		//출력
		System.out.println(sb);

	}

	public static int Binary(int N, int characterPower) {
		int left = 0;
		int right = N;
		int ans = right;
	
		while (left <= right) {
			int mid = (left + right) / 2;
			//캐릭터가 value의 중간값 보다 작거나 같을 때 
			if (characterPower<=value[mid]) {
				ans = mid; //ans = mid 로 바꿔줌
				right = mid-1; //오른쪽 값을 mid -1 로 바꿔줌
			} 
			//캐릭터가 value의 중간값 보다 클때
			else {
				
				left = mid +1; //왼쪽 값을 start + 1로 바꿔줌
			}
			
		}
		return ans;
	}
	
	
}