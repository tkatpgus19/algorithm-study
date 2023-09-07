import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 봄버맨
//0 초기.
//1 아무것도 안 함.
//2 설치함.
//3 터짐.
//4 설치
//5 터짐..
// 반복.
public class Bj16918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        for(int i=0;i<R;i++){
            String s = br.readLine();
            for(int j=0;j<C;j++){
                int tmp = s.charAt(j);
                if(tmp == 'O') tmp = 0; // '0'을 0으로 바꿔서 저장.
                map[i][j] = tmp;
            }
        }
// 3 5 7 9 11 13
        // 짝수면 모두 폭탄으로 채워져 있으므로 2와 동일.
        if(N%2 == 0){
            N = 2;
        } else if(N>6) {
            // 홀수 일때 2가지 상황이 번갈아서 나온다.
            if((N/2)%2 == 1) N = 3;
            else N = 5;
        }

        for(int t=2;t<=N;t++){
            // 짝수일 때
            // 빈 곳 모두 폭탄으로 채움. 맵에 설치한 초 기록.
            if(t%2 == 0){
                for(int i=0;i<R;i++){
                    for(int j=0;j<C;j++){
                        if(map[i][j] == '.') map[i][j] = t;
                    }
                }
            }

            // 홀수일 때. 3초 전 설치한 폭탄 터트림.(맵에 설치한 초를 기록해두었기에 가능)
            if(t%2 == 1){
                for(int i=0;i<R;i++){
                    for(int j=0;j<C;j++){
                        if(map[i][j] + 3 == t){
                            map[i][j] = '.';
                            for(int d=0;d<4;d++){
                                int nx = i+dx[d];
                                int ny = j+dy[d];
                                if(nx<0||nx>=R||ny<0||ny>=C) continue;
                                if(map[nx][ny] == t - 3) continue;
                                map[i+dx[d]][j+dy[d]] = '.';
                            }
                        }
                    }
                }
            }
        }

        // '.'이 아니면 O 폭탄으로 바꿔서 출력.
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
               if(map[i][j]!='.') map[i][j] = 'O';
                System.out.printf("%c",map[i][j]);
            }
            System.out.println();
        }
    }
}
