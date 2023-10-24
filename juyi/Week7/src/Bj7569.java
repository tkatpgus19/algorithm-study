import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj7569 {

	static int M, N, H;
	static int[][][] map;
	static int[][][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[N][M][H];
		visited = new int[N][M][H];
		int sum = 0, count = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					map[j][k][i] = Integer.parseInt(st.nextToken());
					if(map[j][k][i] == 1) sum++;
					if(map[j][k][i] == -1) {
						visited[j][k][i] = -1;
						count++;
					}
				}
			}
		}
		
		if(sum == N * M * H - count) {
			System.out.println(0);
			return;
		}
		
		bfs();
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < H; k++) {
					if(visited[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
					if(max < visited[i][j][k]) max = visited[i][j][k];
				}
			}
		}
		
		System.out.println(max -1);
		
	}
	
	private static void bfs() {
		Queue<Data> queue = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < H; k++) {
					if(map[i][j][k] == 1) {
						queue.offer(new Data(i, j, k));
						visited[i][j][k] = 1;
					}
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Data current = queue.poll();
			
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			int[] dk = {-1, 1};
			
			for(int i = 0; i < 2; i++) {
				int nk = current.k + dk[i];
				
				if(nk < 0 || nk >= H) continue;
				
				if(visited[current.i][current.j][nk] == 0) {
					queue.add(new Data(current.i, current.j, nk));
					visited[current.i][current.j][nk] = visited[current.i][current.j][current.k] + 1;
				}
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = current.i + dr[i];
				int nc = current.j + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if(visited[nr][nc][current.k] == 0) {
					queue.add(new Data(nr, nc, current.k));
					visited[nr][nc][current.k] = visited[current.i][current.j][current.k] + 1;
				}
				
			}
			
		}
		
	}

	static class Data{
		int i, j, k;

		public Data(int i, int j, int k) {
			super();
			this.i = i;
			this.j = j;
			this.k = k;
		}
	}
}
