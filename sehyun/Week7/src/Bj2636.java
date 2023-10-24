import java.io.*;
import java.util.*;

/**
 * [2636번: 치즈]
 * 문제: 2차원 배열의 치즈 상태가 주어지고 매 시간마다 가장자리부터 녹기 시작할 때, 치즈가 모두 녹는데 걸린 시간과 그 직전의 치즈가 차지하는 칸의 개수를 구하시오.
 * 해결: bfs로 치즈의 가장자리만 녹여가며 해답을 구한다. graph값이 1(치즈)이면서 첫 방문일 때 0(녹이고)으로 바꿔주고 큐에 넣지 않고 탐색을 이어가는게 핵심이다. dfs 풀이가 더 간단할지도...
 */
public class Bj2636 {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] graph;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 치즈의 개수를 파악
        int tmp = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(graph[i][j] == 1){
                    tmp++;
                }
            }
        }

        // 녹기 직전의 치즈 개수를 저장하기 위한 발악
        int num = tmp;
        // 녹는데 걸린 시간(bfs를 돌린 횟수)저장
        int cnt = 0;
        
        // 모든 치즈가 녹을 때까지 녹이기 반복
        while(num != 0) {
            // 횟수 추가
            cnt++;
            
            // tmp에 이전 치즈 칸 수를 저장
            tmp = num;
            
            // bfs로 녹이고 치즈 칸 수 획득, 갱신
            num = bfs(N, M);
        }

        System.out.println(cnt);
        System.out.println(tmp);

        // 메모리: 15792KB, 시간: 160ms
    }

    // 치즈 녹이기
    static int bfs(int N, int M){
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{0, 0});

        // 계속해서 방문 여부가 초기화 되어야 함
        visited = new boolean[N][M];


        while(!queue.isEmpty()){
            int[] tmp = queue.removeFirst();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M){
                    continue;
                }
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    // 첫 방문인데 치즈가 아니면(빈공간) 큐에 추가
                    if(graph[nx][ny] != 1){
                        queue.addLast(new int[]{nx, ny});
                    }
                    // 첫 방문인데 치즈를 발견하면 녹이고(0) 큐에는 저장하지 않음
                    graph[nx][ny] = 0;
                }
            }
        }
        // 치즈 칸 수 저장
        int cnt = 0;

        // 치즈칸 수 체크
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(graph[i][j] == 1){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

// dfs 풀이(메모리: 16360KB, 시간: 156ms)
/*

import java.util.*;
import java.io.*;

public class Main {
    static int[][] graph;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        int count = 0;
        int prevCount = 0;
        while(true) {
            count = check(N, M);
            if(count == 0){
                break;
            }
            visited = new boolean[N][M];
            dfs(0, 0, N, M);
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(visited[i][j]){
                        graph[i][j] = 0;
                    }
                }
            }
            time++;
            prevCount = count;
        }
        System.out.println(time);
        System.out.println(prevCount);
    }

    static void dfs(int x, int y, int N, int M){
        if(x<0 || y<0 || x>=N || y>=M){
            return;
        }
        if(!visited[x][y]){
            visited[x][y] = true;
            if(graph[x][y] == 0) {
                dfs(x + 1, y, N, M);
                dfs(x - 1, y, N, M);
                dfs(x, y + 1, N, M);
                dfs(x, y - 1, N, M);
            }
        }
    }

    static int check(int N, int M){
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(graph[i][j] == 1){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
 */