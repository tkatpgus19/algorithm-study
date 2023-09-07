import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// 불!
// 지훈 복제본들과 불들은 매 초마다 전부 이동해야 한다.
// 따라서 지훈큐, 불큐, 지훈방문배열, 불방문배열을 따로 만들고.
// while(지훈 큐) 안에 while(지훈 큐), while(불 큐)를 만들어 임시 큐에 집어 넣어 모두 이동.

// 동일 시간에 지훈이랑 불이 겹치는 일을 막기 위해 불 큐 먼저 이동해준다.
// 가장자리이기만 하면 다음에 탈출 가능. 상하좌우 꼭지점일 필요 없다.
public class Bj4179 {
    static class Jihun{
        int x;
        int y;
        int time;

        public Jihun() {
            super();
        }

        public Jihun(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static class Fire{
        int x;
        int y;

        public Fire() {
            super();
        }

        public Fire(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];

        int[][] visitedJ = new int[R][C]; // 지훈 방문배열
        int[][] visitedF = new int[R][C]; // 불 방문배열

        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        Jihun jihun = new Jihun(); // 지훈이들
        Fire fire = new Fire(); // 불들

        Queue<Jihun> qJ = new ArrayDeque(); // 지훈 큐
        Queue<Fire> qF = new ArrayDeque(); // 불 큐

        // 입력
        for(int i=0;i<R;i++){
            char[] c =  br.readLine().toCharArray();
            for(int j=0;j<C;j++){
                map[i][j] = c[j];
                // 지훈이 위치 기억.
                if(map[i][j] == 'J'){
                    qJ.add(new Jihun(i,j,0));
                    visitedJ[i][j] = 1;
                }
                // 불 위치 기억
                if (map[i][j] == 'F') {
                    qF.add(new Fire(i,j));
                    visitedF[i][j] = 1;
                }
            }
        }

        int ans = -1;

        loop: while (!qJ.isEmpty()){
            Queue<Fire> qTmpF = new ArrayDeque(); // 불 임시 큐

            // 불 큐가 빌 때까지(모든 불) 이동
            while(!qF.isEmpty()){
                Fire nowF = qF.poll();

                // 상하좌우 탐색
                for(int d=0;d<4;d++){
                    int nxF = nowF.x + dx[d];
                    int nyF = nowF.y + dy[d];

                    if(nxF<0||nxF>=R||nyF<0||nyF>=C) continue;
                    if(map[nxF][nyF] == '#' || map[nxF][nyF] == 'F') continue;
                    if(visitedF[nxF][nyF] == 1) continue;

                    visitedF[nxF][nyF] = 1;
                    map[nxF][nyF] = 'F'; // 맵에 불 기록.
                    // 불 임시 큐에 넣어 준다.
                    qTmpF.add(new Fire(nxF,nyF));
                }
            }

            Queue<Jihun> qTmpJ = new ArrayDeque(); // 지훈 임시 큐

            // 지훈 큐가 빌 때까지(모든 복제 지훈이) 이동
            while(!qJ.isEmpty()){
                Jihun nowJ = qJ.poll();

                // 가장자리라면 다음 초에 탈출.
                if(nowJ.x == 0 || nowJ.x == R-1 || nowJ.y==0 || nowJ.y == C-1){
                    ans = nowJ.time+1;
                    break loop;
                }

                // 상하좌우 탐색
                for(int d=0;d<4;d++){
                    int nxJ = nowJ.x + dx[d];
                    int nyJ = nowJ.y + dy[d];

                    if(nxJ<0||nxJ>=R||nyJ<0||nyJ>=C) continue;
                    if(map[nxJ][nyJ] == '#' || map[nxJ][nyJ] == 'F') continue;
                    if(visitedJ[nxJ][nyJ] == 1) continue;

                    visitedJ[nxJ][nyJ] = 1;
                    // 지훈 임시 큐에 넣기.
                    qTmpJ.add(new Jihun(nxJ,nyJ, nowJ.time+1));
                }
            }

            // 모든 이동이 끝나면 임시 큐를 원래 큐로 전부 이동.
            while (!qTmpF.isEmpty()){
                qF.add(qTmpF.poll());

            }
            while (!qTmpJ.isEmpty()){
                qJ.add(qTmpJ.poll());
            }
        }

        if(ans != -1){
            System.out.println(ans);
        } else{
            System.out.println("IMPOSSIBLE");
        }

    }
}
