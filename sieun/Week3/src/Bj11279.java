/*
첫번째 코드 = 성공
*/

import java.util.*;
import java.io.*;

public class Bj11279 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader (System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringBuffer sb = new StringBuffer();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		int N = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			pq.add(a);
			if(a==0) {
				System.out.println(pq.remove());
			}
			
		}
	}
}