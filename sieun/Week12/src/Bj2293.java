import java.util.*;
import java.io.*;

public class Bj2293 {
	static int n;
	static int k;
	static int coin[], count[];
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken()); //동전 종
		k = Integer.parseInt(st.nextToken()); //원하는 
		
		coin = new int [n];
		count = new int [n];
		
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(bf.readLine());
			coin[i]= Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(coin);
		
		twoPointer();
		
		
		System.out.println(cnt);
	
	}
	
	private static void twoPointer() {
		
		int start  = 0, end  = 0 ; 
		
		while(end < n) {
			System.out.println("start : " +start  + " end : " + end);
			int sum = 0;
			
			for(int i = start; i <=end; i++) {
				sum += coin[i] * count[i];	
			}
			
			System.out.println("sum : " + sum);
			
			if(sum < k) {
				count[end]++;
			}
				
			if(sum > k) {
				if(count[start] > 0 ) {
					System.out.println("start : " +start );
					count[start] --;
				}
					
				else
					start ++;
			}
				
			if(sum ==k) {
				cnt ++;
				end ++;
				count[start] ++;
			}
			
			print();
		}
	}
	
	
	private static void print() {
		for(int i=0; i<n; i++) {
			System.out.print(count[i] + " ");
		}
		System.out.println();
	}
}
