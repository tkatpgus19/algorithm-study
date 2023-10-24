import java.io.*;
import java.util.*;

/**
 * [17070번: 파이프 옮기기 1]
 * 문제: 2차원 배열안에서 움직이는 파이프의 상태가 주어질 때, 시작 위치에서 끝 위치로 이동할 수 있는 경로의 수를 구하시오.
 * 해결: 초기 파이프는 두 칸을 차지하므로 좌, 우의 좌표를 계속해서 갱신시키며 끝까지 이동시켜 경로를 찾는다. 파이프의 상태에 따라 움직임에 제약이 있으므로 매번 파이프가 어떤 상태인지 체크한다.
 */
public class Bj17070 {
    static int[][] graph;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 파이프 이동시키기
        move(0, 0, 0, 1, N);
        System.out.println(answer);

        // 메모리: 15904KB, 시간: 244ms
    }
    // 파이프가 어떤 상태인지 계속해서 체크 해야 함
    static int checkPosition(int x1, int y1, int x2, int y2) {

        // 삐딱스 모드니?
        if(x1==x2-1 && y1==y2-1) {
            return 0;
        }
        // 가로 모드니?
        if(y1==y2-1) {
            return 1;
        }
        // 세로 모드니?
        return 2;
    }

    // 파이프 상태에 따라 이동시키고
    // 이동했을 때 파이프 좌, 우 좌표를 갱신해야함
    static void move(int x1, int y1, int x2, int y2, int N) {
        // 파이프 상태 체크
        int status = checkPosition(x1, y1, x2, y2);

        // 파이프가 목적지에 도달했으면 경로에 추가
        if(x2==N-1 && y2==N-1) {
            answer++;
            return;
        }

        // 삐딱스 모드
        if(status == 0) {
            // 삐딱스 이동 가능
            if(x2+1<N && y2+1<N && graph[x2+1][y2]==0 && graph[x2][y2+1]==0 && graph[x2+1][y2+1]==0) {
                move(x1+1, y1+1, x2+1, y2+1, N);
            }
            // 가로 이동 가능
            if(y2+1<N && graph[x2][y2+1]==0) {
                move(x1+1, y1+1, x2, y2+1, N);
            }
            // 세로 이동 가능
            if(x2+1<N && graph[x2+1][y2]==0) {
                move(x1+1, y1+1, x2+1, y2, N);
            }
        }
        // 가로 모드
        else if(status == 1) {
            // 삐딱스 이동 가능
            if(x2+1<N && y2+1<N && graph[x2+1][y2]==0 && graph[x2][y2+1]==0 && graph[x2+1][y2+1]==0) {
                move(x1, y1+1, x2+1, y2+1, N);
            }
            // 가로 이동 가능
            if(y2+1<N && graph[x2][y2+1]==0) {
                move(x1, y1+1, x2, y2+1, N);
            }
        }
        // 세로 모드
        else {
            // 삐딱스 이동 가능
            if(x2+1<N && y2+1<N && graph[x2+1][y2]==0 && graph[x2][y2+1]==0 && graph[x2+1][y2+1]==0) {
                move(x1+1, y1, x2+1, y2+1, N);
            }
            // 세로 이동 가능
            if(x2+1<N && graph[x2+1][y2]==0) {
                move(x1+1, y1, x2+1, y2, N);
            }
        }
    }
}