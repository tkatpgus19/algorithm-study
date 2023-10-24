import java.util.*;
import java.io.*;

/**
 * [1600번: 말이 되고픈 원숭이]
 * 문제: 말 모드로 이동할 수 있는 횟수 제한이 주어질 때, 좌측 상단에서 우측 하단까지 도달하는데 걸리는 최소의 이동 횟수를 구하시오.
 * 해결: 특정 좌표에 도달할 때 누적해서 몇번의 말 이동 찬스를 사용해서 도달했는지 체크하기 위해 path의 세번째 인덱스가 누적 사용한 chance인 공간에 거리값을 갱신해준다. 즉, 같은 좌표값이라도 각각 1번의 찬스를 써서 도달한 거리값, 2번의 찬스를, ... K번의 찬스를 써서 도달한 거리값이 저장되게 된다. 정답은 이들 중 최솟값.
 */
public class Bj1600 {

    // 원숭이 이동 dx,dy
    static int[] basicDx = {-1, 0, 1, 0};
    static int[] basicDy = {0, 1, 0, -1};

    // 말 이동 dx,dy
    static int[] advancedDx = {-2, -2, 1, -1, 2, 2, 1, -1};
    static int[] advancedDy = {1, -1, 2, 2, -1, 1, -2, -2};
    static int[][] graph;
    
    // 말모드로 이동한 거리값을 갱신하기 위해 3차원 배열 사용
    static int[][][] path;
    static int K, W, H;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        graph = new int[H][W];
        path = new int[H][W][K+1];

        for(int i=0; i<H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 배열 크기가 1x1이어서 이동을 못할 때, 바로 종료
        if(W*H==1) {
            System.out.println(0);
        }
        else {
            bfs();
            int answer = Integer.MAX_VALUE;
            
            // 0번~K번의 말이동 찬스를 써서 도달한 각각의 거리값 중 최솟값이 정답이 됨
            for(int i=0; i<K+1; i++) {
                if(path[H-1][W-1][i] != 0) {
                    answer = Math.min(answer, path[H-1][W-1][i]);
                }
            }

            if(answer != Integer.MAX_VALUE) {
                System.out.println(answer);
            }
            // 도달하지 못했을 때 -1 출력
            else {
                System.out.println(-1);
            }
        }

        // 메모리: 64684KB, 시간: 548ms
    }

    static void bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[] {0, 0, 0});
        while(!queue.isEmpty()) {
            int[] tmp = queue.removeFirst();
            int x = tmp[0];
            int y = tmp[1];
            int chance = tmp[2];

            // 목적지에 도달했을 떄 종료
            if(x==H-1 && y==W-1) {
                break;
            }
            
            // 먼저 원숭이 이동
            for(int i=0; i<4; i++) {
                int nx = x+basicDx[i];
                int ny = y+basicDy[i];
                if(nx<0 || ny<0 || nx>=H || ny>=W) {
                    continue;
                }
                // 빈 공간이고 "chance번의 말 이동 횟수를 사용해 처음으로 도달했을 때" 거리값 갱신
                if(graph[nx][ny] == 0 && path[nx][ny][chance] == 0) {
                    path[nx][ny][chance] = path[x][y][chance]+1;
                    queue.addLast(new int[] {nx, ny, chance});
                }
            }
            // 아직 말 이동 찬스가 남아있을 때 말 이동
            if(chance < K) {
                for(int i=0; i<8; i++) {
                    int nx = x+advancedDx[i];
                    int ny = y+advancedDy[i];
                    if(nx<0 || ny<0 || nx>=H || ny>=W) {
                        continue;
                    }
                    // 말 이동 chance를 사용해 도달했으므로 chance+1 번 배열에 거리값을 갱신해줘야 함
                    if(graph[nx][ny] == 0 && path[nx][ny][chance+1] == 0) {
                        path[nx][ny][chance+1] = path[x][y][chance]+1;
                        queue.addLast(new int[] {nx, ny, chance+1});
                    }
                }
            }
        }
    }
}
