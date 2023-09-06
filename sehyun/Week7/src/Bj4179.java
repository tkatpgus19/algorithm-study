import java.io.*;
import java.util.*;

/**
 * [4179번: 불!]
 * 문제: 2차원의 배열과 불, 지훈이의 위치가 주어지고 매시간마다 불이 번질 때, 지훈이가 배열 밖으로 탈출하는데 걸리는 가장 빠른 시간을 구하시오.
 * 해결: 큐에 지훈 좌표를 먼저 넣고 뒤에 불 좌표를 넣어 bfs를 통해 graph에 퍼뜨린다. graph에 지훈이가 모두 사라지면 탈출 실패, 지훈이가 모두 사라지기 전에 배열 가장자리에 다다랐을 때 탈출 성공. 포인트는 항상 지훈이가 먼저 이동하고 이후 불이 퍼지도록 하는 것이다.
 */
public class Bj4179 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static char[][] graph;
    
    // 최단 거리 저장을 위한 배열 선언
    static int[][] time;
    static int answer = 0;
    static int R, C;
    static Deque<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new char[R][C];
        time = new int[R][C];

        for(int i=0; i<R; i++){
            graph[i] = br.readLine().toCharArray();
        }

        // 지훈 좌표, 불 좌표를 큐에 추가
        // 항상 지훈이가 최우선적으로 이동해야 하므로 큐(덱)의 앞에 저장, 불 좌표는 뒤에 추가해 후순위로 둠
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(graph[i][j] == 'J'){
                    queue.addFirst(new int[]{i,j});
                }
                else if(graph[i][j] == 'F'){
                    queue.addLast(new int[]{i,j});
                }
            }
        }
        if(!bfs()){
            System.out.println("IMPOSSIBLE");
        }
        else{
            System.out.println(answer);
        }

        // 메모리: 51088KB, 시간: 572ms
    }

    // 지훈과 불의 이동시작
    static boolean bfs(){
        // 지훈s 개수 카운트
        int jCnt = 1;
        
        while(!queue.isEmpty()){
            int[] tmp = queue.removeFirst();
            int x = tmp[0];
            int y = tmp[1];

            // 지훈이(J)가 좌표 상에서 모두 불(F)에 잠식되었을 때 탈출 후 false 반환(탈출 실패)
            if(jCnt == 0){
                break;
            }
            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                // J가 배열의 끝에 다다랐을 때 정답 갱신 후 true 반환(탈출 성공)
                if((nx == -1 || ny == -1 || nx == R || ny == C) && graph[x][y] == 'J'){
                    answer = time[x][y]+1;
                    return true;
                }
                // 벽이거나 불일 경우 continue
                if(nx<0 || ny<0 || nx>=R || ny >= C || graph[nx][ny] == '#' || graph[nx][ny] == 'F'){
                    continue;
                }
                // graph[x][y]가 지훈일때 -> 지훈 이동하기(J 퍼뜨리기)
                if(graph[x][y] == 'J'){
                    // 빈 공간이면 거리값 갱신 후 J 퍼뜨리기
                    // 지훈이 하나 더 추가되므로 jCnt++
                    if(graph[nx][ny] == '.'){
                        time[nx][ny] = time[x][y]+1;
                        graph[nx][ny] = 'J';
                        jCnt++;
                        queue.addLast(new int[]{nx, ny});
                    }
                }
                // graph[x][y]가 불일때 -> 불 퍼뜨리기
                else if(graph[x][y] == 'F'){
                    if(graph[nx][ny] != '#'){
                        // 벽이 아니고 지훈이를 만났을 때 지훈이 하나를 죽임(jCnt--)
                        // 불(F) 퍼뜨리기
                        if(graph[nx][ny] == 'J'){
                            jCnt--;
                        }
                        graph[nx][ny] = 'F';
                        queue.addLast(new int[]{nx, ny});
                    }
                }
            }
        }
        // 탈출 실패
        return false;
    }
}