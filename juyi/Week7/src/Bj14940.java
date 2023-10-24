import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj14940 {

	static int n, m;
	static int[][] map;
	static int[][] visited;
	static boolean[][] check;
	static Queue<int[]> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		check = new boolean[n][m];
		queue = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					queue.offer(new int[] {i, j});
					check[i][j] = true;
				}
			}
		}
		
		visited = new int[n][m];
		bfs();
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(visited[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	private static void bfs() {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = current[0] + dr[i];
				int nc = current[1] + dc[i];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				
				if(!check[nr][nc] && map[nr][nc] != 0) {
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = visited[current[0]][current[1]] + 1;
					check[nr][nc] = true;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!check[i][j] && map[i][j] == 1) {
					visited[i][j] = -1;
				}
			}
		}

	}

}
