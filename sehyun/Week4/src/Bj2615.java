import java.io.*;
import java.util.*;

/**
 * [2615번 오목]
 * 문제: 오목판이 주어질 때, 승패와 이를 결정지은 오목돌 5개 중 가장 왼쪽에 존재하는 돌의 좌표를 출력하시오. (단, 6개 이상 연속해서 놓인 돌은 승리로 간주하지 않는다)
 * 해결: 돌 하나하나 가로, 세로, 좌 대각, 우 대각을 체크하되, visited로 중복 연산을 막는다. 가로, 세로, 좌 대각은 탐색을 시작한 시점의 돌이 가장 왼쪽에 존재하므로 그대로 출력하되, 우 대각 탐색은 값을 계속 갱신해줘야 한다.
 */
public class Bj2615 {
    static StringBuilder sb = new StringBuilder();
    static int[][] graph = new int[19][19];

    // 각 돌의 가로, 세로, 좌 대각, 우 대각 체크 여부를 기록하기 위한 배열
    static boolean[][][] visited = new boolean[19][19][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 오목판 입력
        for(int i=0; i<19; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<19; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 오목판을 탐색하며 0이아닌 돌(1, 2)을 만날 때 승패 검사
        for(int i=0; i<19; i++) {
            for(int j=0; j<19; j++) {
                // 세로줄 체크 (세로줄 체크를 해보지 않았을 때만 메소드 호출)
                if(graph[i][j] != 0 && !visited[i][j][0]) {
                    if(checkCol(i, j)) {
                        System.out.println(sb);
                        return;
                    }
                }
                // 가로줄 체크
                if(graph[j][i] != 0 && !visited[j][i][1]) {
                    if(checkRow(i, j)) {
                        System.out.println(sb);
                        return;
                    }
                }
                // 좌 대각 체크
                if(graph[i][j] != 0 && !visited[i][j][2]) {
                    if(checkLeftC(i, j)) {
                        System.out.println(sb);
                        return;
                    }
                }
                // 우 대각 체크
                if(graph[i][j] != 0 && !visited[i][j][3]) {
                    if(checkRightC(i, j)) {
                        System.out.println(sb);
                        return;
                    }
                }
            }
        }
        // 위 아무 조건도 걸리지 않았을 때(무승부)
        System.out.println(0);

        // 메모리: 14292KB, 시간: 140ms
    }

    // 세로줄 체크
    static boolean checkCol(int i, int j) {

        // 돌이 1(흑)인지 0(백)인지 저장
        int state = graph[i][j];
        int check = 0;

        // i번째 부터 19번째 요소까지 같은 돌이 연속해서 있는지 체크
        for(int idx=i; idx<19; idx++) {

            // 같은 돌이 6개 이상일 때(승리 아님) 탈출
            if(check > 5) {
                break;
            }
            // 같은 돌일 때 카운트하고 방문여부 갱신
            if(graph[idx][j] == state) {
                check++;
                visited[idx][j][0] = true;
            }
            // 하나라도 다른 돌 혹은 공백이 껴있을 때 탈출
            else {
                break;
            }
        }
        // 같은 돌이 5개 연속으로 존재할 때
        if(check == 5) {
            sb.append(state).append('\n').append(i+1).append(' ').append(j+1);
            return true;
        }
        return false;
    }

    // 가로줄 체크
    static boolean checkRow(int i, int j) {
        int state = graph[j][i];
        int check = 0;

        for(int idx=i; idx<19; idx++) {
            if(check > 5) {
                break;
            }
            if(graph[j][idx] == state) {
                check++;
                visited[j][idx][1] = true;
            } else {
                break;
            }
        }
        if(check == 5) {
            sb.append(state).append('\n').append(j+1).append(' ').append(i+1);
            return true;
        }
        return false;
    }

    // 좌 대각 체크
    static boolean checkLeftC(int i, int j) {
        int state = graph[i][j];
        int idx = 0;
        int check = 0;

        while(true) {
            // 인덱스 범위 탈출에 유의해야 함
            if(i+idx > 18 || j+idx > 18 || check > 5) {
                break;
            }
            if(graph[i+idx][j+idx] == state) {
                check++;
                visited[i+idx][j+idx][2] = true;
            } else {
                break;
            }
            idx++;
        }
        if(check == 5) {
            sb.append(state).append('\n').append(i+1).append(' ').append(j+1);
            return true;
        }
        return false;
    }

    // 우 대각 체크
    static boolean checkRightC(int i, int j) {
        int state = graph[i][j];
        int idx = 0;
        int check = 0;

        // 가장 왼쪽 돌의 좌표 저장을 위한 변수
        int aI = i;
        int aJ = j;

        while(true) {
            if(i+idx > 18 || j-idx < 0 || check > 5) {
                break;
            }
            if(graph[i+idx][j-idx] == state) {
                check++;
                visited[i+idx][j-idx][3] = true;
                
                // 돌의 좌표 갱신
                aI = i+idx;
                aJ = j-idx;
            } else {
                break;
            }
            idx++;
        }
        if(check == 5) {
            sb.append(state).append('\n').append(aI+1).append(' ').append(aJ+1);
            return true;
        }
        return false;
    }
}