import java.util.*;
import java.io.*;

public class Bj7569 {
	static int M,N,H;
	static StringBuilder sb = new StringBuilder ();
	static int map[][][]; //토마토 상태 저장하는 곳
	static boolean check = true; //처음에 다 익어 있ㄴ느지 확인
	static Queue <int []> q = new LinkedList<>(); //idx 저장하는 곳
	static int[] dirZ = {-1,0,1,0,0,0};
	static int[] dirX = {0,-1,0,1,0,0};
	static int[] dirY = {0,0,0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
	//변수 선언
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int [H][N][M];

		
		//한단 상태 입력 // 1: 익음, 0: 안익음, -1 : 없음
		for(int i = 0; i<H; i++) {
			for(int j =0; j<N; j++) {
				st = new StringTokenizer(bf.readLine());
				for(int k=0; k<M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if(map[i][j][k] == 0 ) 
						check = false;
					else if(map[i][j][k]==1) {
							q.add(new int[] {i,j,k});	//q에 익어있는 토마토 좌표 입력
					}
				}
			}
		}
		
		if(check) {
			System.out.println(0); //처음부터 모두 익어있는 상태 = 0 출력
			return;
		}
		
		
		//bfs로 탐색한다
		bfs();
		
		//안익은거 있는지 체크 -> -1 출력
		if(!isAllRipe()) {
			System.out.println(-1);
			return;
		}
		
		//다 익었으면 시간 출력
		System.out.println(findTime()-1);
		
		
	}
	
	//bfs 탐색
	static void bfs() {
		while(!q.isEmpty()&&!isAllRipe()) { //queue가 empty 하거나 전부 익었을 때까지 while문 돈다. 
			int curPos[] = q.poll();
			int curZ = curPos[0];
			int curX = curPos[1];
			int curY = curPos[2];
			
			for(int i = 0; i<6; i++) { //6방향으로 범위 체크
				int nextZ = curZ+ dirZ[i];
				int nextX = curX+ dirX[i];
				int nextY = curY+ dirY[i];
				//범위 & 안익었으면 
				if(nextZ>=0 && nextZ<H && nextX >= 0 && nextX<N && nextY >=0 && nextY <M && map[nextZ][nextX][nextY]== 0) {
					q.add(new int [] {nextZ, nextX, nextY}); //q에 다음에 탐색할 곳 추가
					map[nextZ][nextX][nextY] = map[curZ][curX][curY]+1; //익음 처리 -> 방문 처리 // 시간 더해줘서 익음 처리 해줌
				}
			}
		}
		
	}

	//다 익었는지 체크하는 메서드
	private static boolean isAllRipe() {
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k = 0; k<M; k++) {
					if(map[i][j][k] == 0) {
						return false; //하나라도 안익은거 있으면 return false
					}
				}
			}
		}
		return true; //익거나 빈곳만 있으면 return true
	}
	
	//시간 찾음
	private static int findTime() {
		int  max = Integer.MIN_VALUE;
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k = 0; k<M; k++) {
					max = Math.max(max, map[i][j][k]);
				}
			}
		}
		
		return max;
		
	}
}
