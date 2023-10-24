import java.util.*;
import java.io.*;

/**
 * [16954번: 움직이는 미로 탈출]
 * 문제: 8x8의 2차원 배열이 주어지고 위에서부터 벽이 시간에 따라 한칸씩 아래로 이동할 때, 벽을 피해 좌측 최하단에서 시작해 우측 최상단에 도달할 수 있는지를 구하시오.
 * 해결: 욱제를 퍼뜨려가는 동시에 욱제의 개수를 체크하고, 벽을 아래로 내려가며 탈출 여부를 파악한다. 욱제가 존재하고 벽이 모두 하단으로 사라졌으면 성공, 욱제가 모두 죽으면 실패이다. 벽을 아래로 내릴 때 기존 벽 위치의 마킹은 지우고 한 칸 아래에 새로 마킹을 해 벽을 내리되, 벽 간에 간섭이 생기지 않도록 신경써줘야 한다.
 */
public class Bj16954 {
    // 상, 하, 좌, 우, 대각선 방향 이동을 위한 dx dy 선언
    static int[] dx = {0, -1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, 0, -1, 1, -1, 1, -1, 1};
    
    // 욱제 개수를 체크하기 위한 변수 선언
    static int ws = 1;
    
    // 벽 좌표를 저장하기 위한 리스트 선언
    static List<int[]> walls = new ArrayList<>();
    static Deque<int[]> queue = new ArrayDeque<>();
    static char[][] graph = new char[8][8];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<8; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        // 벽 좌표를 찾아 리스트에 저장
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(graph[i][j] == '#') {
                    walls.add(new int[] {i, j});
                }
            }
        }

        int answer = 0;
        
        // 출발 위치 배열에 마킹 및 큐에 저장
        graph[7][0] = 'W';
        queue.addLast(new int[] {7, 0});

        List<int[]> tmp = new ArrayList<>();

        // 우측 상단에 도달할 때까지 반복
        while(true) {

            // 욱제가 모두 사라졌으면(벽에 맞아 죽었으면) 종료
            if(queue.isEmpty()) {
                break;
            }

            // 욱제가 가만히 있어 턴을 보내면 다시 탐색을 시작해야 하므로 임시 deque를 선언해 큐 값의 원본을 저장함
            Deque<int[]> test = new ArrayDeque<>();
            for(int[] q: queue) {
                test.add(q);
            }
            
            // 큐 값을 빼서 moveW를 수행해도 원본이 test에 저장되어 있으므로 두번씩 중복해서 탐색할 수 있음
            while(!test.isEmpty()) {
                test.removeFirst();
                moveW();
            }

            // 벽이 모두 사라졌고 욱제가 살아있으면 장애물이 있으므로 무조건 상단에 도달할 수 있음(성공)
            if(walls.isEmpty() && ws!=0) {
                answer = 1;
                break;
            }

            // 벽 아래로 한칸씩 이동
            for(int i=walls.size()-1; i>=0; i--) {
                int wx = walls.get(i)[0];
                int wy = walls.get(i)[1];

                // 벽이 배열 끝에 도달해 사라질 때, 그냥 현재 위치의 벽을 지우고 
                // 벽 리스트에서 빼기 위해 tmp 리스트에 추가(여기서 바로 빼버리면 다음 벽의 이동을 처리할 때 간섭이 생김)
                if(wx+1>7) {
                    graph[wx][wy] = '.';
                    tmp.add(walls.get(i));
                    continue;
                }

                // 아래로 벽을 이동시키기 전 현재 좌표의 벽을 지움
                graph[wx][wy] = '.';

                // 벽이 욱제위에 쌓일 때 욱제s--
                if(graph[wx+1][wy] == 'W') {
                    for(int[] q: queue) {
                        if(q[0] == wx+1 && q[1] == wy) {
                            queue.remove(q);
                            break;
                        }
                    }
                    ws--;
                }
                // 벽의 x값 갱신(한칸 내려간 좌표값)
                walls.get(i)[0] = wx+1;
                
                // 내려온 벽 설치
                graph[wx+1][wy] = '#';
            }

            // 욱제s가 모두 뒤졌을 때 종료
            if(ws == 0) {
                break;
            }
            // 1. 배열 끝에 도달해 사라진 벽들을
            // 2. 저장해둔 벽 리스트에서 제거
            for(int[] t: tmp) {
                walls.remove(t);
            }
        }

        System.out.println(answer);

        // 메모리: 14180KB, 시간: 124ms
    }
    
    // 욱제를 이동시키는 메소드
    static void moveW() {
        int[] tmp = queue.removeFirst();
        int x = tmp[0];
        int y = tmp[1];
        for(int i=0; i<9; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx<0 || ny<0 || nx>=8 || ny>=8) {
                continue;
            }
            // 빈 공간이면 W 마킹 후 이동하고 욱제s++
            if(graph[nx][ny] == '.') {
                graph[nx][ny] = 'W';
                ws++;
                queue.addLast(new int[] {nx, ny});
            }
            // 가만히 있는 경우면 그냥 큐에 추가 후 다음을 기약
            if(i==0) {
                queue.addLast(new int[] {nx,ny});
            }
        }
    }
}
