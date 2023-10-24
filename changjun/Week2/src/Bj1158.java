import java.io.*;
import java.util.*;


public class Bj1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> s = new LinkedList<>();
		for(int i=0;i<N;i++) {
			s.add(i+1);
		}
		
		System.out.print("<");
		//
		while(!s.isEmpty()) {
			for(int i=0;i<K-1;i++) {
				s.add(s.poll()); //앞에서 빼서 뒤로 넣기 
			}
			System.out.printf("%d",s.poll());
			if(!s.isEmpty()) System.out.print(", ");
		}

		System.out.print(">");
		
	}
}
