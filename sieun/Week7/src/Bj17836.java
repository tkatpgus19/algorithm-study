import java.io.*;
import java.util.*;

public class Bj17836 {
	static int N, M, T;
	static int map[][];
	static int time[][];
	static Queue<int []> q= new LinkedList<>();
	static int dirX[] = {-1,0,1,0};
	static int dirY[] = {0,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int [N][M];
		time = new int [N][M];
		q.add(new int[] {0,0,0}); //용사 좌표 + 검 가지고 있는지 아닌지 
		map[0][0] = -1;
		
		
		
		
		//map 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = bfs(); //bfs 로 탐색하기 
		
		if(T<ans) System.out.println("Fail"); //초과하면 Fail 출력
		else System.out.println(ans); //아니면 시간 출력
		
	}
	
	static private int bfs() {
		int ansTime = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			//현재 좌표 입력 받기
			int[] curpos = q.poll();
			int curX = curpos[0];
			int curY = curpos[1];
			int swordFind = curpos[2];
			
			
			//4방향 탐색 
			for(int i=0; i<4; i++) {
				int nextX = curX + dirX[i];
				int nextY = curY + dirY[i];
				//검을 가지고 있지 않을 때 
				if(swordFind== 0) {
					if(nextX >=0 && nextX < N && nextY >= 0 && nextY <M && (map[nextX][nextY] == 0 || map[nextX][nextY] == 2)) {
						//다음 map 이 공주가 있는 맵일 경우 현재 값에서 최소값 찾음
						if(nextX == N-1 && nextY == M-1) {
							ansTime = Math.min(ansTime,time[curX][curY] +1) ;
						}
						else {
							if(map[nextX][nextY] == 2) {
								q.add(new int[] {nextX, nextY,1});	
							}
							else {
								q.add(new int[] {nextX, nextY,0});	
							}
							time[nextX][nextY] = time[curX][curY] +1;
							map[nextX][nextY] = -1; // 방문처리 : -1 로 바꿔줌
						}
					}
				}
				//검을 가지고 있을 때 -> 다 지나갈 수 있음
				else {
					if(nextX >=0 && nextX < N && nextY >= 0 && nextY <M && map[nextX][nextY] != -2) {
						//다음 map 이 공주가 있는 맵일 경우 현재 값에서 최소값 찾음
						if(nextX == N-1 && nextY == M-1) {
							ansTime = Math.min(ansTime,time[curX][curY] +1) ;
						}
						else {
							q.add(new int[] {nextX, nextY,1});
							time[nextX][nextY] = time[curX][curY] +1;
							map[nextX][nextY] = -2; // 방문처리 : -1 로 바꿔줌
						}
					}
				}
			}
		}
		
		return ansTime;
	}
}
