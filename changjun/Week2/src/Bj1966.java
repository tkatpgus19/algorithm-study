import java.io.*;
import java.util.*;


public class Bj1966 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for(int t=0;t<T;t++) {
			Queue<Integer> s = new LinkedList<>();
			Queue<Integer> sIdx = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			
			for(int i=0;i<N;i++) {	
					s.add(Integer.parseInt(st.nextToken()));
					sIdx.add(i);
			}
			
			
			while(s.size()!=0) {
				int max = 0;
				
				//가장 큰 중요도 찾기 
				for(int i=0;i<s.size();i++) {
					if(s.peek() > max) {
						max = s.peek();
					}
					s.add(s.poll());
				}
				
				for(int i=0;i<s.size();i++) {
					//만약 max 값을 발견하면 빼기.
					if(s.peek()==max) {
						//뺀 값의 인덱스가 찾는 M이면 출력하고 종료.
						if(sIdx.peek() == M) {
							System.out.println(N-s.size()+1);
						}
						s.remove();
						sIdx.remove();
						break;
					}
					// 앞에서 빼서 뒤로 넣기.
					s.add(s.poll());
					sIdx.add(sIdx.poll());
				}
			}
		}
	}
}
