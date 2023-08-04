/*
첫번째 시도 - 성공
근데 너무 복잡한거 같아서 최적화 필요...
제대로 풀었는지 확신 X
*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Bj1966 {
	public static void main(String[] args) throws InterruptedException  {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		int max = 0; 
		int count =0;
		int find = 0;
		
		for(int i =0; i<T; i++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			Queue<Integer> data = new LinkedList<>();
			Queue<Integer> dataIdx = new LinkedList<>();
			
			for(int j=0; j<N; j++) {
				int temp = sc.nextInt();
				data.add(temp);
				dataIdx.add(j);
			}
			
			while(!data.isEmpty()) {
				//find max value
				 for(int j=0; j<data.size(); j++) {
					 if(data.peek() > max) {
						 max = data.peek();
					 }
					 data.add(data.peek());
					 data.remove();
				 }

				 //pop data
				 for(int j =0; j<N; j++) {
					 if(data.peek()!= max) {
						 data.add(data.peek());
						 data.remove();
						 dataIdx.add(dataIdx.peek());
						 dataIdx.remove();
					 }else {
						 if(dataIdx.peek() == M ) {
							 data.remove();
							 dataIdx.remove();
							 count ++;
							 find = count ; 
							 max = 0;
							 break ; 
							
						 }
						 else {
							 dataIdx.remove();
							 data.remove();
							 count ++;
							 max = 0;
							 break;
							
						 }
					 }
				 }
			}
			sb.append(find);
			sb.append("\n");
			count = 0;
			max =0;
		}
		System.out.println(sb);
		
	}
}