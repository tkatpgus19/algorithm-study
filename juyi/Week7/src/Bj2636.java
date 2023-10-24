import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj2636 {

	static int N, M;
	static int[][] map;
	static int cheese, time;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cheese = 0;
		time = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) cheese++;
			}
		}
		
		int result = 0;
		while(cheese > 0) {
			result = cheese;
			bfs();
			time++;
		}
		
		System.out.println(time);
		System.out.println(result);
		
	}

	private static void bfs() {
		Queue<Data> queue = new ArrayDeque<>();
		
		boolean[][] visited = new boolean[N][M];
		queue.offer(new Data(0, 0));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Data current = queue.poll();
			
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			
			for(int i = 0; i < 4; i++) {
				int nr = current.i + dr[i];
				int nc = current.j + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if(visited[nr][nc]) continue;
				
				if(map[nr][nc] == 1) {
					cheese--;
					map[nr][nc] = 0;
					visited[nr][nc] = true;
				}
				else {
					queue.offer(new Data(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	static class Data{
		int i, j;

		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}
}
