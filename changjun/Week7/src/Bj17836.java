import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// 공주님을 구해라! (석방하라..)
// 벽 뿌수기 문제와 동일.
// 방문체크를 검 있을 때 없을 때 따로 하면 됨.
public class Bj17836 {
    static class Zelda{
        int x;
        int y;
        int sword;
        int time;

        public Zelda(int x, int y, int sword, int time) {
            this.x = x;
            this.y = y;
            this.sword = sword;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] visited = new int[N][M]; // 일반 방문 체크 배열
        int[][] visitedSword = new int[N][M]; // 검을 가진 방문 체크 배열.
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        // 입력
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Zelda> q = new ArrayDeque<>();

        // 초기 위치 큐에 넣기.
        q.add(new Zelda(0,0,0,0));
        visited[0][0] = 1;

        int ans = -1;

        // BFS
        while(!q.isEmpty()){
            Zelda z = q.poll();

            // 도착하면 종료.
            if(z.x == N-1 && z.y == M-1){
                ans = z.time;
                break;
            }

            // 검 발견.
            if(map[z.x][z.y] == 2){
                visitedSword[z.x][z.y] = 1;
                z.sword = 1;
            }

            // 4방향 탐색
            for(int d=0;d<4;d++){
                int nx = z.x+dx[d];
                int ny = z.y+dy[d];

                if(nx<0||nx>=N||ny<0||ny>=M) continue;

                // 벽이고 검이 없으면 다음으로.
                if(map[nx][ny] == 1){
                    if(z.sword == 0) continue;
                }

                if(z.sword == 0){
                    // 검이 없는 채로 방문 했으면 다음으로
                    if(visited[nx][ny] == 1) continue;
                    // 방문 체크 후 큐에 넣기.
                    q.add(new Zelda(nx,ny,z.sword,z.time+1));
                    visited[nx][ny] = 1;
                } else{
                    // 검이 있는 채로 방문 했다면 다음으로.
                    if(visitedSword[nx][ny] == 1) continue;
                    // 방문 체크 후 큐에 넣기.
                    q.add(new Zelda(nx,ny,z.sword,z.time+1));
                    visitedSword[nx][ny] = 1;
                }
            }
        }

        // 도착지점에 못 도달 했거나 시간이 넘쳤다면 Fail
        if(ans == -1 || ans > T){
            System.out.println("Fail");
        } else{
            // 제때 도착했으면 시간 출력.
            System.out.println(ans);
        }

    }
}
