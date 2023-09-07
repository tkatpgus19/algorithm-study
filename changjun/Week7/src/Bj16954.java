import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// 움직이는 미로 탈출

// 벽은 맨 아래로 가면 맨 위로 가는 게 아니라 사 라 진 다.
// 벽이 아래로 이동하므로 처음 큐에 넣을 때 아래서 부터 넣어야 겹치지 않는다.
// 0,0 시작 7,7 끝이 아니라 7,0 시작 0,7 끝.
public class Bj16954 {
    static class Character{
        int x;
        int y;
        int time; // 시간

        public Character(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static class Wall{
        int x;
        int y;

        public Wall(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int map[][] = new int[8][8];
        // 8방향 + 제자리 탐색.
        int[] dx = {0,1,0,-1,1,-1,1,-1,0};
        int[] dy = {1,0,-1,0,1,-1,-1,1,0};

        Queue<Character> qCharacter = new ArrayDeque<>();
        Queue<Wall> qWall = new ArrayDeque();

        //입력
        for(int i=0;i<8;i++){
            char[] input = br.readLine().toCharArray();
            for(int j=0;j<8;j++){
                map[i][j] = input[j];

            }
        }

        // 벽을 아래서 부터 큐에 넣기.
        for(int i=7;i>=0;i--){
            for(int j=0;j<8;j++){
                if(map[i][j] == '#'){
                    qWall.add(new Wall(i,j));
                }
            }
        }

        // 초기 위치 좌하단.
        qCharacter.add(new Character(7,0,0));

        int ans = 0;

        loop: while(!qCharacter.isEmpty()){
            // 캐릭터 먼저 이동.
            Queue<Character> qCharacterTmp = new ArrayDeque<>();
            while(!qCharacter.isEmpty()){
                Character nc = qCharacter.poll();

                // 우상단 도착하면 종료.
                if(nc.x == 0 && nc.y == 7){
                    ans = 1;
                    break loop;
                }

                // 8방향 + 제자리 탐색, BFS
                for(int d=0;d<9;d++){
                    int nx = nc.x+dx[d];
                    int ny = nc.y+dy[d];

                    if(nx<0||nx>=8||ny<0||ny>=8) continue;

                    // 가려는 위치에 벽이 있거나 그 바로 위에 있다면(다음에 벽이 내려오므로) 안 됨.
                    if(map[nx][ny] =='#') continue;
                    if(nx!=0 && map[nx-1][ny] =='#') continue;

                    qCharacterTmp.add(new Character(nx,ny,nc.time+1));
                }
            }

            // 벽 이동.
            Queue<Wall> qWallTmp = new ArrayDeque<>();

            while(!qWall.isEmpty()){
                Wall nc = qWall.poll();
                // 현재 자리 벽 지운 뒤.
                map[nc.x][nc.y] = '.';
                // 맨 밑이 아니면 이동 후 큐에 다시 넣기.
                if(nc.x != 7) {
                    qWallTmp.add(new Wall(nc.x+1, nc.y));
                    map[nc.x+1][nc.y] = '#';
                }
            }

            // 임시큐 친구들 전부 다시 원래 큐로 이동.
            while(!qCharacterTmp.isEmpty()){
                qCharacter.add(qCharacterTmp.poll());
            }
            while(!qWallTmp.isEmpty()){
                qWall.add(qWallTmp.poll());
            }

        }

        System.out.println(ans);
    }
}
