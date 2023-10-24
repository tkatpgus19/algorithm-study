
/*
첫번째 제출 = 성공!
queue사용

배운것 : 간략한 queue사용 법
첫번째 queue참조 

queue.peek () -> 참조만 함
queue.poll () -> 참조하고 삭제


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj1158 {
	public static void main(String[] args) throws IOException {
		Queue<Integer> q = new LinkedList<>();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int ans[] = new int[N];
		int idx = 0;
		
		for(int i=1; i<=N; i++) {
			q.add(i);
		}
		
		sb.append("<");
		while(!q.isEmpty()) {
			for(int i=0; i<K-1; i++) {
				q.add(q.peek());
				q.remove();
			}
			ans[idx] = q.peek();
			q.remove();
			idx ++;
		}
		
		for(int i =0; i<ans.length; i++) {
			if(i != ans.length-1) {
				sb.append(ans[i]);
				sb.append(", ");
			}
			else {
				sb.append(ans[i]);
			}
		}
		sb.append(">");
		
		System.out.println(sb);
		

	}

}