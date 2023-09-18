/*두번째 코드 - 성공
82568kb	732ms */
import java.util.*;
import java.io.*;

public class Bj14940 {
	static int n , m, goalX, goalY ; //세로, 가로
	static int map[][], ans[][]; //map, 정답
	static Queue<int []> q = new LinkedList<>(); //bfs 할 큐
	static int [] dirX = {-1,0,1,0}; //사방 탐색할 x방향
	static int [] dirY = {0, -1, 0, 1}; //사방탐색할 y방향
	static boolean[][] visited; //방문
	
	static StringBuilder sb = new StringBuilder(); 
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken()); //세로 크기 입력
		m = Integer.parseInt(st.nextToken()); // 가로의 크기 입력
		
		map = new int [n][m];
		ans = new int [n][m];
		visited = new boolean [n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				ans[i][j] = -1;
			}
		}
		
		//입력 받음
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//goal 찾으면 초기 값으로 저장
				if(map[i][j] == 2) {
					q.add(new int[]{i, j});
					goalX = i; goalY = j;//goal 일경우 거리는 무조건 0
					ans[goalX][goalY] = 0;
					
				}
			}
		}
		
		//bfs 해서 탐색
		bfs();
		
		//출력
		ans[goalX][goalY] = 0;
		turnZero();
		print();
	}
	
	
	//bfs 메서드 
	private static void bfs() {
		
		//q가 안비어있으면 while문
		while(!q.isEmpty()) {
			int curPos[] = q.poll(); //현재 탐색하는 노드
			int x = curPos[0]; //현재 탐색하는 노드의 x
			int y = curPos[1]; //현재 탐색하는 노드의 y
			

			for(int i=0; i<4; i++) {
				//사방탐색해서 범위 체크 
				if(x+dirX[i]>=0 && x+dirX[i]<n && y+dirY[i]>=0 && y+dirY[i]<m &&!visited[x+dirX[i]][y+dirY[i]]) {
					//범위안에 들고 0이 아닐떄 -> 갈 수 있는 땅
					
					if(map[x+dirX[i]][y+dirY[i]]!=0) {
						//q에 추가 
						visited[x+dirX[i]][y+dirY[i]] = true; //방문처리
						q.add(new int[]{x+dirX[i], y+dirY[i]});
						//ans에 +1해서 넣어줌
						ans[x+dirX[i]][y+dirY[i]] = ans[x][y]+1;
					}
					//범위 안에 들고 0일때 -> 갈 수 없는 땅
					else {
						//답을 0으로 처리 
						ans[x+dirX[i]][y+dirY[i]] = 0;
					}
				}
			}
			
		}
	}
	
	//0처리
	private static void turnZero() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 0)
					ans[i][j] = 0;
			}
		}
	}
	
	//정답 출력하는 메서드
	private static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(ans[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
}