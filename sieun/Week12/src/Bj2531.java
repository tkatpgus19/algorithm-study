import java.util.*;
import java.io.*;

public class Bj2531 {
	static int N, d, k, c;
	static int dish[];
	static int eat[];
	static int cnt = 0, ans = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		// 입력 시작 ======================================================================
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken()); // 접시의 수
		d = Integer.parseInt(st.nextToken()); // 초밥의 가짓 수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		dish = new int[N]; // 접시위에 있는 초밥
		eat = new int[d + 1]; // 내가 먹은 초밥 종류

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			dish[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 끝 ======================================================================

		// 처음 k개 먹음
		for (int i = 0; i < k; i++) {
			if(eat[dish[i]] == 0)
				cnt ++;
			eat[dish[i]]++;
		}



		// sliding window 알고리즘
		for (int i = 0; i < N; i++) {
			
			//print();
			//System.out.println("cnt : " + cnt );
			if(cnt >= ans) {
				if(eat[c] == 0)
					ans = cnt + 1;
				else
					ans = cnt;
			}

			int j = (i + k) % N; // end 값 (인덱스 초과할 떄의 처리)
			
			int lastfish = dish[i];
			int curfish = dish[j];
			
			eat[lastfish] --;
			if(eat[lastfish] == 0)
				cnt --;
			
			if(eat[curfish] == 0)
				cnt ++;
			eat[curfish] ++;

		}

		System.out.println(ans);
	}

	// 디버깅용
	public static void print() {
		for (int i = 1; i <= d; i++)
			System.out.print(eat[i] + " ");
		System.out.println();
	}

}
