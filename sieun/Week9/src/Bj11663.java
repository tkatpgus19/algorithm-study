import java.util.*;
import java.io.*;

public class Bj11663 {

	static int N, M;
	static int line[][];
	static int dot[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dot = new int[N];
		line = new int[M][2];

		// 점 좌표
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			dot[i] = Integer.parseInt(st.nextToken());
		}

		// 점들 정렬하기
		Arrays.sort(dot);

		// 선분 시작점 line : [i][0]
		// 선분 끝점 line : [i][1]
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			line[i][0] = Integer.parseInt(st.nextToken());
			line[i][1] = Integer.parseInt(st.nextToken());
			long ans = Binary(line[i][0], line[i][1]);
			sb.append(ans + "\n");
		}

		System.out.println(sb);
	}

	public static long Binary(int lineStart, int lineEnd) {
		long endIndex = 0, startIndex = 0;
		int left = 0;
		int right = dot.length;
		//System.out.println(lineStart + " " + lineEnd);
		
		//점이 선분을 벗어나면 0 처리
		if(dot[N-1]<lineStart ) return 0;
		if(dot[0] > lineEnd) return 0;

		//오른쪽 끝 index 찾기
		while (left <   right) {
			int mid = (left + right) / 2;

			if (dot[mid] > lineEnd) {
				right = mid - 1;
			} 
			else {
				left = mid + 1;
			}
		}
		endIndex = right;
		
		//System.out.println("endIndex: " + endIndex);
		
		left = 0;
		right = dot.length;
		
		//왼쪽 끝 index 찾기
		while (left < right) {
			int mid = (left + right) / 2;

			if (dot[mid] < lineStart) {
				left = mid + 1;
			} 
			else {
				right = mid - 1;
			}
		}
		
		startIndex = left;
		
		//System.out.println("startIndex: " + startIndex);
		
		//System.out.println("-------> ans : " + (endIndex-startIndex));
		return (endIndex-startIndex);
	}

}