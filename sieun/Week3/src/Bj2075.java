/*
첫번째 코드 - 성공
*/

import java.util.*;
import java.io.*;


public class Bj2075 {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0; i<N*N; i++) {
			int a= sc.nextInt();
			pq.add(a);
		}
		for(int i=0; i<N-1; i++) {
			pq.remove();
		}
		System.out.println(pq.remove());
	}
}