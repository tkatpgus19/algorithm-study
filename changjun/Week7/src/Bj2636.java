import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// 치----즈
// for -> while (BFS)
// 맵에 각 치즈의 녹은 순간 기록.
// 0과 1과 겹치지 않으려고 2부터. 마지막에 1 빼준다.
public class Bj2636 {
    static class Cheese {
        int x;
        int y;
        int time; // 녹은 시간.

        public Cheese(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시간. 2부터 시작. 맵의 0,1과 겹치면 안 되니까. 나중에 1빼면 됨.
        for (int t = 2; ; t++) {
            Queue<Cheese> q = new ArrayDeque();
            int[][] visited = new int[N][M];

            // 0,0 부터 퍼져나간다.
            q.add(new Cheese(0, 0, t));
            visited[0][0] = 1;

            // BFS
            while (!q.isEmpty()) {
                Cheese n = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = n.x + dx[d];
                    int ny = n.y + dy[d];

                    // 맵 밖으로 나가면 건너 뜀.
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                    // 이번 시간에 방문한 곳은 건너 뜀.
                    if(visited[nx][ny] == 1) continue;

                    visited[nx][ny] = 1;
                    // 치즈면 녹은 시간을 적는다. 아니면 q에 넣는다.
                    if (map[nx][ny] == 1) {
                        map[nx][ny] = t;
                    } else {
                        q.add(new Cheese(nx, ny, n.time + 1));
                    }
                }
            }

            int finish = 1;
            loop: for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 안 녹은 치즈가 있다면 끝나지 않은 것..
                    if (map[i][j] == 1){
                        finish = 0;
                        break;
                    }
                }
            }

            // 끝났으면 맵에 마지막 초가 기록 된 곳(마지막에 녹은 치즈들)을 찾아 모두 더한다.
            if (finish == 1) {
                int cnt = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (map[i][j] == t){
                            cnt++;
                        }
                    }
                }
                System.out.println(t-1); // 2부터 시작했으므로 1빼서 출력.
                System.out.println(cnt); // 마지막에 녹은 치즈 개수 출력.
                break;
            }
        }

    }
}
