import java.util.*;
import java.io.*;

/**
 * [14940번: 쉬운 최단거리]
 * 문제: 2차원 배열의 벽(0), 빈 공간(1), 시작위치(2)가 주어질 때, 시작위치와 모든 빈 공간 사이의 거리를 출력하는 문제.
 * 해결: 전형적인 bfs 문제로 해당 공간까지 도달하는데 걸린 횟수를 path 배열에 갱신해간다. 탐색을 마치고 빈 공간(1)인데도 거리 값이 갱신되지 않았으면 도달하지 못한 곳이므로 -1을 저장해 출력한다.
 */
public class Bj14940 {
    static int[][] graph;
    // 거리를 기록하기 위한 배열 선언
    static int[][] path;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        path = new int[n][m];

        // 시작위치(2)를 입력과 동시에 받기 위한 좌표 변수들
        int startX = -1;
        int startY = -1;

        // 그래프 입력
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                
                // 이전까지 시작위치를 찾지 못했고, 지금 찾았을 때 할당
                if(startX<0 && graph[i][j] == 2){
                    startX = i;
                    startY = j;
                }
            }
        }
        
        // bfs 실행
        bfs(startX, startY);

        // StringBuilder에 2차원 배열 값 저장
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                // 벽이 아닌데도 도달하지 못했을 때 -1 저장
                if(graph[i][j] == 1 && path[i][j] == 0){
                    sb.append(-1);
                }
                else{
                    sb.append(path[i][j]);
                }
                sb.append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);

        // 메모리: 46056KB, 시간: 644ms
    }

    static void bfs(int sx, int sy){
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{sx, sy});
        while(!queue.isEmpty()){
            int[] tmp = queue.removeFirst();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx<0 || ny<0 || nx>=n || ny>=m){
                    continue;
                }
                // 벽이 아니고 처음 방문하는 곳(거리가 초기값)이면 이전 경로값+1으로 갱신
                if(graph[nx][ny] == 1 && path[nx][ny] == 0){
                    path[nx][ny] = path[x][y] + 1;
                    queue.addLast(new int[]{nx, ny});
                }
            }
        }
    }
}
