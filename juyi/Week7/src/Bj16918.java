import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj16918 {

	static int R, C, N;
	static char[][] map;
	static Queue<Data> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		queue = new ArrayDeque<>();
		for(int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'O') queue.offer(new Data(i, j));
			}
		}

		if(N > 1) {
			switch(N % 4) {
			case 0:
			case 2:
				for(int i = 0; i < R; i++) {
					Arrays.fill(map[i], 'O');
				}
				break;
			case 1:
				bfs();
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						if(map[i][j] == 'O') queue.offer(new Data(i, j));
					}
				}
				bfs();
				break;
			case 3:
				bfs();
				break;
			}
		}

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void bfs() {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		for(int i = 0; i < R; i++) {
			Arrays.fill(map[i], 'O');
		}

		while(!queue.isEmpty()) {
			Data current = queue.poll();
			map[current.i][current.j] = '.';

			for(int i = 0; i < 4; i++) {
				int nr = current.i + dr[i];
				int nc = current.j + dc[i];

				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

				map[nr][nc] = '.';
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

		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + "]";
		}


	}

}
