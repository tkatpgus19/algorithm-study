import java.io.*;
import java.util.*;

/**
 * [14502번 연구소]
 * 문제: 벽 3개를 세워 바이러스가 퍼졌을 때 감염되지 않는 지역의 최댓값을 구하시오.
 * 해결: 벽 3개를 세우는 모든 경우의 수를 구하고, 각 경우마다 바이러스를 퍼뜨리고, 안전지대를 체크해 최댓값을 찾는다.
 */
public class Bj14502 {
    static boolean[][] visited;
    static int[][] graph;
    // 모든 경우의 수를 테스트 해보기 위해 graph를 복제할 tmpGraph 선언(원본 유지 목적)
    static int[][] tmpGraph;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        tmpGraph = new int[N][M];
        visited = new boolean[N][M];

        // 그래프 초기화
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        buildWall(0);
        System.out.println(answer);

        // 메모리: 306900KB, 시간: 1388ms
    }

    // 벽 세우기
    static void buildWall(int cnt) {
        // 벽 3개를 다 세웠으면 바이러스 퍼뜨리기
        if(cnt == 3) {
            // 새로운 경우의 테스트를 위해 graph, visited 복사
            for(int i=0; i<visited.length; i++) {
                for(int j=0; j<visited[0].length; j++) {
                    tmpGraph[i][j] = graph[i][j];
                }
            }
            visited = new boolean[visited.length][visited[0].length];

            // 2(바이러스)를 찾을 때마다 bfs로 퍼뜨리기
            for(int i=0; i<visited.length; i++) {
                for(int j=0; j<visited[0].length; j++) {
                    if(tmpGraph[i][j] == 2) {
                        bfs(i, j);
                    }
                }
            }
            // 안전지대 최대 개수 갱신
            int tmp = countSafearea();
            if(tmp > answer) {
                answer = tmp;
            }
            return;
        }
        // 백트래킹으로 벽 세우기
        for(int i=0; i<visited.length; i++) {
            for(int j=0; j<visited[0].length; j++) {
                // 0(빈 공간)일 때 벽 세우기
                if(graph[i][j] == 0) {
                    graph[i][j] = 1;
                    buildWall(cnt+1);
                    graph[i][j] = 0;
                }
            }
        }
    }

    // 바이러스 퍼뜨리기
    static void bfs(int sx, int sy) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[] {sx, sy});
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while(!queue.isEmpty()) {
            int[] tmp = queue.removeFirst();
            int x = tmp[0];
            int y = tmp[1];

            for(int i=0; i<4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx<0 || ny<0 || nx>= visited.length || ny>=visited[0].length) {
                    continue;
                }
                if(!visited[nx][ny] && tmpGraph[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    tmpGraph[nx][ny] = 2;
                    queue.addLast(new int[] {nx, ny});
                }
            }
        }

    }

    // 안전지대 카운트하기
    static int countSafearea() {
        int cnt = 0;
        
        // 0(안전지대) 카운트
        for(int i=0; i<visited.length; i++) {
            for(int j=0; j<visited[0].length; j++) {
                if(tmpGraph[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}