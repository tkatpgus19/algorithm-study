import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj10974 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[] visited = new boolean[N];
		int[] arr = new int[N];
		int[] output = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		DFS(arr, output, visited, 0, N);
		
	}
	public static void DFS(int[] arr, int[] output, boolean[] visited, int depth, int n) {
		if(depth == n) {
			for(int i = 0; i < n; i++) {
				System.out.print(output[i] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				output[depth] = arr[i];
				DFS(arr, output, visited, depth + 1, n);
				visited[i] = false;
			}
		}
	}

}