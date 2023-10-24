/*
첫번째 풀이 - 실패
절대값이 같을 때 작은거 부터 출력하게 안함
*/



/*
두번째 풀이 - 성공
Math.abs (o1 ) - Math.abs(o2) 가 0일때 o1 - o2 return 하
배운거 : Priority Queue 에서 compare 사용해서 원하는대로 정렬하기
*/

import java.util.*;
import java.io.*;


public class Bj11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuffer sb = new StringBuffer();
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator <Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1)>Math.abs(o2)) {
					return 1;
				}else if(Math.abs(o1) == Math.abs(o2)) {
					return o1-o2;
				}else {
					return -1;
				}
			}
		}
	);	
		
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			int num = Integer.parseInt(st.nextToken());
			if(num == 0) {
				if(pq.isEmpty()) {
					sb.append(0);
					sb.append("\n");
				}
				else {
					sb.append(pq.poll());
					sb.append("\n");
				}
			}
			else {
				pq.add(num);
			}
		}
		
		System.out.println(sb);
	}
}