
/*
첫번째 코드 : 성공
*/

import java.util.*;
import java.io.*;

public class Bj16918 {
	
	static int R,C,N;
	static char map[][];
	static Queue<int []> bomb = new LinkedList<>(); 
	static int [] dirX = {-1,0,1,0}; 
	static int [] dirY = {0, -1, 0, 1}; 
	static StringBuilder sb = new StringBuilder ();
	
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
	
		//map 입력 받기
		
		for(int i=0; i<R; i++) {
			String str = bf.readLine();
			for(int j =0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'O')
					bomb.add(new int[] {i,j});
			}
		}
		

		
		//시간 흐름
		for(int i=2;i<=N; i++) {
			if(i%2 == 0) {
				//폭탄 전부 채우기
				allBomb();
			}
			else {
				//폭탄 터트리기
				BombBomb();
				//터트리고 남은 폭탄 위치 queue에 추가
				findBomb();
			}
			
		}
		
	
		//출력하기
		print();
	}
	
	//전부 톡탄으로 채우는 메서드 
	static void allBomb() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = 'O';
			}
		}
	}
	
	//폭탄 터트리는 메서드
	static void BombBomb() {
		while(!bomb.isEmpty()) {
			int curPos[] = bomb.poll(); //현재 폭탄 
			int x = curPos[0]; //현재 폭탄의 x
			int y = curPos[1]; //현재 폭탄의 y
			map[x][y] = '.'; //자기 자신 터트림
			
			for(int i=0; i<4; i++) {
				//사방탐색해서 범위 체크 
				if(x+dirX[i]>=0 && x+dirX[i]<R && y+dirY[i]>=0 && y+dirY[i]<C ) {
					//범위안에 들때
					map[x+dirX[i]][y+dirY[i]] = '.';
				}
			}
		}
	}
	
	//폭탄위치 찾아서 queue에 추가하는 메서드
	static void findBomb() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'O') {
					bomb.add(new int[] {i,j});
				}
			}
		}
	}
	
	//정답 출력하는 메서드
	private static void print() {
		sb.setLength(0);
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}