import java.util.*;
import java.io.*;

/**
 * [7569번: 토마토]
 * 문제: 3차원 배열의 토마토 상자와 그 안에 익은 토마토, 익지 않은 토마토가 주어질 때, 모든 토마토가 익을 때 까지 걸리는 시간을 구하시오.
 * 해결: bfs문제로 익은 토마토에서 시작해서 bfs로 주변 토마토들을 익힌다. 토마토는 하루에 사방으로 한칸씩만 익힐 수 있으므로 익는 시간은 곧 이미 익은 토마토와의 거리와 같다. 이에 모든 익지않은 토마토와 익은 토마토와의 거리를 모두 갱신하고, 최댓값을 출력한다.
 *      그냥 무작정 익은 토마토를 찾고 bfs를 돌리면 해답을 찾지 못하므로, 사전에 모든 익은 토마토의 좌표를 저장하고 해당 값들로만 bfs를 돌린다. 이후 새로 익게된 토마토들은 자연스럽게 queue의 뒤에 오게되며 후순위로 처리된다. 그리고 입력부터 토마토가 모두 익어있을 수 있음에 유의한다.
 */
public class Bj7569 {
    // 3차원 이동을 위한 dz값 추가
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    static int[][][] graph;

    // 익은 토마토와 익지 않은 토마토의 거리(토마토가 익는데 걸리는 시간)를 저장할 배열 선언
    static int[][][] path;
    static int M, N, H;
    static Deque<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        graph = new int[H][N][M];
        path = new int[H][N][M];

        // 이미 토마토가 다 익었는지를 파악하기 위한 변수
        int cnt = 0;
        for(int k=0; k<H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    graph[k][i][j] = Integer.parseInt(st.nextToken());

                    // 익은 토마토면 큐에 저장
                    if (graph[k][i][j] == 1) {
                        queue.addLast(new int[]{i, j, k});
                    }
                    // 익지않은 토마토 카운트(하나라도 있으면 토마토가 모두 익은 상태로 온게 아님)
                    else if (graph[k][i][j] == 0) {
                        cnt++;
                    }
                }
            }
        }

        // bfs 메소드 실행
        bfs();

        int answer = Integer.MIN_VALUE;

        // 루프 탈출을 위한 label
        findAnswer:
        for(int k=0; k<H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 익지않은 토마토인데 거리를 갱신하지 못했을 경우 모두 익히지 못한 것이므로 -1 출력
                    if (graph[k][i][j] == 0 && path[k][i][j] == 0) {
                        answer = -1;
                        break findAnswer;
                    }
                    // 가장 멀리있는 익은 토마토의 거리(익는데 걸린 시간)가 정답
                    answer = Math.max(answer, path[k][i][j]);
                }
            }
        }
        // 초기에 토마토가 모두 익은 상태로 입력되었을 때 0 출력
        if(cnt == 0){
            answer = 0;
        }
        System.out.println(answer);

        // 메모리: 110128KB, 시간: 740ms
    }

    static void bfs(){
        while(!queue.isEmpty()){
            int[] tmp = queue.removeFirst();
            int x = tmp[0];
            int y = tmp[1];
            int z = tmp[2];
            for(int i=0; i<6; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                int nz = z+dz[i];
                
                // 배열 범위를 벗어나거나 벽이면 continue
                if(nx<0 || ny<0 || nz<0 || nx>=N || ny>=M || nz>=H || graph[nz][nx][ny] == -1){
                    continue;
                }
                // 처음 탐색하는 곳이고 이미 익은 토마토가 아닐 때 거리값 갱신
                if(path[nz][nx][ny] == 0 && graph[nz][nx][ny] != 1) {
                    path[nz][nx][ny] = path[z][x][y] + 1;
                    queue.addLast(new int[]{nx, ny, nz});
                }
            }
        }
    }
}
