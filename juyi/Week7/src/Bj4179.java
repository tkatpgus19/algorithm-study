import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj4179 {

	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Data> queueJ, queueF;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		queueJ = new ArrayDeque<>();
		queueF = new ArrayDeque<>();
		for(int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'J') {
					queueJ.offer(new Data(i, j));
					map[i][j] = '.';
				}
				else if(map[i][j] == 'F') queueF.offer(new Data(i, j));
			}
		}
		
		visited = new boolean[R][C];
		int result = bfs();
		
		System.out.println(result < 0? "IMPOSSIBLE":result);
	
	}
	
	private static int bfs() {
		int time = 0;
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		while(!queueJ.isEmpty()) {
			time++;
			int sizeF = queueF.size();
			while(sizeF-- > 0) {
				Data current = queueF.poll();
				
				for(int i = 0; i < 4; i++) {
					int nr = current.i + dr[i];
					int nc = current.j + dc[i];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					
					if(visited[nr][nc]) continue;
					
					if(map[nr][nc] == '.') {
						queueF.offer(new Data(nr, nc));
						visited[nr][nc] = true;
					}
					
				}
			}
			
			int sizeJ = queueJ.size();
			while(sizeJ-- > 0) {
				Data current = queueJ.poll();
				if(current.i == 0 || current.i == R-1 || current.j == 0 || current.j == C-1) return time;
				
				for(int i = 0; i < 4; i++) {
					int nr = current.i + dr[i];
					int nc = current.j + dc[i];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					
					if(visited[nr][nc]) continue;
					
					if(map[nr][nc] == '.') {
						queueJ.offer(new Data(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}
		return -1;
	}

	static class Data{
		int i, j;

		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + "]";
		}
		
	}
}
