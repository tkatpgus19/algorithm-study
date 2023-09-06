import java.io.*;
import java.util.*;

/**
 * [17836번: 공주님을 구해라!]
 * 문제: 2차원의 미로가 주어지고 마법의 칼 그람의 좌표가 주어질 때, 공주님의 좌표까지 도달하는 데 걸리는 최소의 시간을 구하시오.
 * 해결: 먼저 그람을 먹지 않고 공주에게 도달하는 경로를 bfs로 구한다. 이후 갱신된 경로 배열을 참고해 그람을 먹을 수 있는지 파악하고, 먹을 수 있으면 그람을 먹고 나서 벽을 무시하는 루트로 공주에게 도달하는 경로를 구한다.
 */
public class Bj17836 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] graph;
    static int[][] path;
    static int N, M, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        path = new int[N][M];

        // 그람의 위치를 저장하는 배열 선언
        int[] gramPos = new int[2];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());

                // 그람위치 저장
                if(graph[i][j] == 2){
                    gramPos[0] = i;
                    gramPos[1] = j;
                }
            }
        }
        bfs(0, 0);

        int answer = -1;
        // 일반 탐색으로 도달할 수 없을 때(공주 거리값이 0일때) -1로 설정
        // 공주에게 도달할 수 있으면 해당 값을 일단 저장(정답 후보)
        if(path[N-1][M-1] != 0) {
            answer = path[N - 1][M - 1];
        }

        // 그람을 먹을 수 있을 때, 이전의 탐색결과를 초기화 하고 그람을 출발지로 벽을 무시하는 bfs 시작
        if(path[gramPos[0]][gramPos[1]] != 0){
            int tmp = path[gramPos[0]][gramPos[1]];
            path = new int[N][M];
            path[gramPos[0]][gramPos[1]] = tmp;

            bfs2(gramPos[0], gramPos[1]);
        }

        // 그람 루트 역시 공주에게 도달할 수 없으면 -1, 있으면 공주 거리값 과 일반 탐색 결과값 중 최솟값을 저장
        if(path[N-1][M-1] != 0){
            if(answer != -1) {
                answer = Math.min(answer, path[N - 1][M - 1]);
            }
            else{
                answer = path[N-1][M-1];
            }
        }
        
        // 찾은 최단 시간이 -1이거나 T초보다 크면 실패
        if(answer<0 || answer>T){
            System.out.println("Fail");
        }
        else{
            System.out.println(answer);
        }

        // 메모리: 15480KB, 시간: 180ms
    }

    // 일반 루트 탐색
    static void bfs(int sx, int sy){
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{sx, sy});
        while (!queue.isEmpty()) {
            int[] tmp = queue.removeFirst();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M){
                    continue;
                }
                // 처음 탐색하고 벽이 아닐 경우 거리값 갱신
                if (graph[nx][ny] != 1 && path[nx][ny] == 0) {
                    path[nx][ny] = path[x][y] + 1;
                    queue.addLast(new int[]{nx, ny});
                }
                // 두번째 탐색인데 이 루트가 더 빨랐다면 거리값 갱신
                else {
                    path[nx][ny] = Math.min(path[nx][ny], path[x][y] + 1);
                }

            }
        }
    }
    
    // 그람 루트 탐색(벽 무시 가능)
    static void bfs2(int sx, int sy){
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{sx, sy});
        while (!queue.isEmpty()) {
            int[] tmp = queue.removeFirst();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M){
                    continue;
                }
                // 벽이든 빈공간이든 상관없이 거리값을 갱신해가며 탐색
                if(path[nx][ny] == 0){
                    path[nx][ny] = path[x][y] + 1;
                    queue.addLast(new int[]{nx, ny});
                } else {
                    path[nx][ny] = Math.min(path[nx][ny], path[x][y] + 1);
                }

            }
        }
    }
}