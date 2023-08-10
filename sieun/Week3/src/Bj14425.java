/*
첫번째 시도 -성공
SET CONTAINS 사용
*/

import java.util.*;
import java.io.*;


public class Bj14425 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();
		Set set = new HashSet();
		int count = 0;
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			String str = bf.readLine();
			set.add(str);
		}
		
		for(int i = 0; i<M; i++) {
			String find = bf.readLine();
			if(set.contains(find)) {
				count ++;
			}
		}
		
		System.out.println(count);
	}
}