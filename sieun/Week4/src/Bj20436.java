/*
 첫번째 성공 
 * */

import java.util.Scanner;

public class Bj20436 {
	
	static Character[][] keyboard= {{'q','w','e','r','t','y','u','i','o','p'}
					,{'a','s','d','f','g','h','j','k','l','X'}
					,{'z','x','c','v','b','n','m','X','X','X'}};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		char sL=sc.next().charAt(0); 
		char sR=sc.next().charAt(0); 
		String str=sc.next(); 
		int time=0; // 총 걸리는 시간
		
		
		for(int i=0;i<str.length();i++) {
			char ch=str.charAt(i);
			
			
			if(ch=='y'||ch=='u'||ch=='i'||ch=='o'||ch=='p'
			||ch=='h'||ch=='j'||ch=='k'||ch=='l'
			||ch=='b'||ch=='n'||ch=='m'){
				
				String hand=findIndex(sR); // 오른손이 위치한 알파벳의 인덱스 구하기
				String move=findIndex(ch); // 이동해야하는 알파벳의 인덱스 구하기
				
				// findIndex는 문자열로 반환되기 때문에 int 형식으로 변환
				int x1y2=Integer.parseInt(hand);
				int x2y2=Integer.parseInt(move);
				
				
				time+=distacnce(x1y2/10,x1y2%10,x2y2/10,x2y2%10);
				
				sR=ch; // 오른손 위치 갱신
			}
			

			else {
				String hand=findIndex(sL);
				String move=findIndex(ch);
				
				int x1y2=Integer.parseInt(hand);
				int x2y2=Integer.parseInt(move);
				
				time+=distacnce(x1y2/10,x1y2%10,x2y2/10,x2y2%10);
				
				sL=ch; 
			}
			
			time+=1; 
		}
		
		System.out.println(time);
	}
	
	public static String findIndex(char c) {
		String result=""; 
		for(int i=0;i<3;i++) {
			for(int j=0;j<10;j++) {
				if(keyboard[i][j]==c) {
					result+=i;
					result+=j;
				}	
			}
		}
		return result;
	}
	
	public static int distacnce(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}
}