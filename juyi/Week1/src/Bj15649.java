import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj15649 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[N];
		int[] arr = new int[N];
		int[] output = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		DFS(arr, output, visited, 0, N, M);
		
	}
	public static void DFS(int[] arr, int[] output, boolean[] visited, int depth, int n, int m) {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				System.out.print(output[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				output[depth] = arr[i];
				DFS(arr, output, visited, depth + 1, n, m);
				visited[i] = false;
			}
		}
	}
}
