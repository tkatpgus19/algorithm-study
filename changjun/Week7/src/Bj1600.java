import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// 말이 되고픈 원숭이.
public class Bj1600 {
    static class Monkey{
        int x;
        int y;
        int move; // 이동거리
        int horse; // 말처럼 이동할 수 있는 횟수

        public Monkey(int x, int y, int move, int horse) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.horse = horse;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] map = new int[H][W];
        // 3차원 방문 배열. 말처럼 이동한 횟수에 기반함.
        int[][][] visitedMove = new int[H][W][K+1];

        // 상하좌우
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        // 말처럼 이동.
        int[] hx = {1,2,2,1,-1,-2,-2,-1};
        int[] hy = {2,1,-1,-2,-2,-1,1,2};

        // 입력
        for(int i=0;i<H;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<W;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 상태 입력.
        Queue<Monkey> q = new ArrayDeque();
        q.add(new Monkey(0,0,0,K));
        for(int i= K-1;i>=0;i--){
            visitedMove[0][0][i] = 1;
        }

        int ans = -1;

        // BFS
        while(!q.isEmpty()){
            Monkey m = q.poll();

            // 도착하면 종료.
            if(m.x == H-1 && m.y == W-1){
                ans = m.move;
                break;
            }

            // 상하좌우 이동.
            for(int d=0;d<4;d++){
                int nx = m.x+dx[d];
                int ny = m.y+dy[d];

                if(nx<0||nx>=H||ny<0||ny>=W) continue;
                if(map[nx][ny] == 1) continue;
                if(visitedMove[nx][ny][m.horse] == 1) continue;

                //visitedMove[nx][ny][m.horse] = 1;

                // 더 낮은 말 횟수를 가진 방문은 의미 없음. 인데 위에 주석이 더 빠름..
                for(int i= m.horse;i>=0;i--){
                    visitedMove[nx][ny][i] = 1;
                }
                // 이동거리만 증가시켜 큐에 넣음.
                q.add(new Monkey(nx,ny,m.move+1,m.horse));
            }

            // 말처럼 이동.
            if(m.horse>0){
                for(int d=0;d<8;d++){
                    int nx = m.x+hx[d];
                    int ny = m.y+hy[d];

                    if(nx<0||nx>=H||ny<0||ny>=W) continue;
                    if(map[nx][ny] == 1) continue;
                    // 다음 지점을 갈 때 말처럼 이동횟수가 하나 주니까 -1 한채로 체크해야함. 중요.
                    if(visitedMove[nx][ny][m.horse-1] == 1) continue;

                    //visitedMove[nx][ny][m.horse-1] = 1;
                    // 위와 동일. 이것도 -1한 채로 방문 체크.
                    for(int i= m.horse-1;i>=0;i--){
                        visitedMove[nx][ny][i] = 1;
                    }
                    q.add(new Monkey(nx,ny,m.move+1,m.horse-1));
                }
            }
        }

        System.out.println(ans);
    }
}
