import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// 토마토
// 못생긴
//
public class Bj7569 {
    static class Tomato{
        int x;
        int y;
        int z;
        int day;

        public Tomato(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][][] box = new int [H][N][M];
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        int[] dh = {1,-1};

        Queue<Tomato> q = new ArrayDeque();

        // 안 익은 토마토 없는 지 체크용.
        int greenTomato = 0;

        for(int h=0;h<H;h++){
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++){
                    box[h][i][j] = Integer.parseInt(st.nextToken());
                    if(box[h][i][j] == 0) greenTomato = 1;
                    // 잘 익은 토마토 전부 큐에 넣는다.
                    if(box[h][i][j] == 1) q.add(new Tomato(i,j,h,0));
                }
            }
        }

        // 안 익은 토마토 없으면 바로 0 출력 후 종료.
        if(greenTomato == 0) System.out.println(0);
        else {
            // BFS
            while(!q.isEmpty()){
                Tomato now = q.poll();

                // 4방향 탐색.
                for(int d=0;d<4;d++){
                    int nx= now.x + dx[d];
                    int ny= now.y + dy[d];

                    if(nx<0||nx>=N||ny<0||ny>=M) continue;
                    // 탐색 중 안 익은 토마토가 있으면 익은 날짜를 입력하고 큐에 넣기.
                    if(box[now.z][nx][ny] == 0){
                        box[now.z][nx][ny] = now.day+1;
                        q.add(new Tomato(nx,ny,now.z,now.day+1));
                    }
                }

                // 2방향 탐색 (위층 아래층)
                for(int d=0;d<2;d++){
                    int nh = now.z + dh[d];

                    if(nh<0||nh>=H) continue;

                    // 탐색 중 안 익은 토마토가 있으면 익은 날짜를 입력하고 큐에 넣기.
                    if(box[nh][now.x][now.y] == 0){
                        box[nh][now.x][now.y] = now.day+1;
                        q.add(new Tomato(now.x,now.y,nh,now.day+1));
                    }
                }
            }

            int ans = 0;

            loop : for(int h=0;h<H;h++){
                for(int i=0;i<N;i++){
                    for(int j=0;j<M;j++){
                        // 안 익은 토마토 있으면 -1
                        if(box[h][i][j] == 0){
                            ans = -1;
                            break loop;
                        }
                        // 가장 큰 날짜 저장.
                        if(box[h][i][j] > ans) ans = box[h][i][j];
                    }
                }
            }

            // 출력
            System.out.println(ans);
        }
    }
}
