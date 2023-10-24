import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj2615 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] t = new int[19][19]; // 바둑판
        int[][] s = {{-1,1},{0,1},{1,1},{1,0}}; // 4방위 탐색

        int winnerColor = 0; // 이긴 색.
        int winX = -1;
        int winY = -1;

        // 바둑판 입력.
        for(int i=0;i<19;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<19;j++) {
                t[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //가로,세로,대각선 중 가장 왼쪽을 먼저 발견하기 위해 위에서 아래로 탐색
        loop: for(int y=0;y<19;y++) {
            for(int x=0;x<19;x++) {
                //자리에 아무돌도 없으면 바로 continue;
                if(t[x][y] == 0) {
                    continue;
                }

                //돌이 놓여 있으면 색 확인.
                int color = t[x][y];

                //4방위탐색
                for(int i=0;i<4;i++) {
                    int tx = x; // 현재 x
                    int ty = y; // 현재 y
                    int cnt = 1; // 자기자신도 check

                    int txR = tx-s[i][0]; // 탐색할 반대편 x
                    int tyR = ty-s[i][1]; // 탐색할 반대편 y

                    // 바둑판 안의
                     if(txR>=0&&txR<19&&tyR>=0&&tyR<19) {
                        // 탐색할 반대쪽 돌이 색이 같다면 제외
                        // 6목에 포함된 5목 같은 케이스 제외하기 위함.
                        if(t[txR][tyR] == color){
                            continue;
                        }
                    }

                     // 6목 찾기 위해 6번 까지 탐색
                    for(int j=0;j<6;j++) {
                        tx+=s[i][0]; // 탐색할 x
                        ty+=s[i][1]; // 탐색할 y
                        // 바둑판 밖으로 나가면 종료.
                        if(tx<0||tx>=19||ty<0||ty>=19) {
                            break;
                        }
                        // 탐색한 방향의 색이 맞지 않으면 종료.
                        if(t[tx][ty] != color) {
                            break;
                        } else {
                            cnt++;
                        }
                    }
                    //cnt==6 인 조건 거르기
                    if(cnt==5) {
                        winnerColor = color;
                        winX = x+1;
                        winY = y+1;
                        break loop;
                    }
                }
            }
        }

        // 출력 잘 하기.
        System.out.printf("%d\n",winnerColor);
        if(winnerColor!=0){
            System.out.printf("%d %d",winX,winY);
        }
    }
}

