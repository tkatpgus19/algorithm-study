import java.util.*;
import java.io.*;

/**
 * [16918번: 봄버맨]
 * 문제: 특정 루틴으로 폭탄을 놓고 터뜨리고를 반복하는 상황에 2차원 배열의 폭탄이 놓인 격자판과 N초가 주어질 때, N 초뒤의 격자판의 상태를 출력하시오.
 * 해결: 1초 뒤의 그림을 직접 그려가며 중복되는 패턴을 파악하고 이를 구현한다. 분기는 크게 N=1 일때, N%4 == 0, 2 일때, N%4 == 1일때, N%4 == 3일때로 나뉜다.
 */
public class Bj16918 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] graph;
    static int R, C, N;

    // 폭탄 위치를 저장해 일정 초 이후 터뜨리기 위해 폭탄 좌표를 저장하는 리스트 선언
    static List<int[]> bombs = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new char[R][C];

        for(int i=0; i<R; i++){
            graph[i] = br.readLine().toCharArray();
        }
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                // 초기 폭탄의 좌표를 저장
                if(graph[i][j] == 'O'){
                    bombs.add(new int[]{i, j});
                }
            }
        }

        // 1초뒤(변한게 없음)면 그대로 출력
        if(N == 1){
            copyArray();
        }
        else {
            switch (N%4) {
                // graph의 모든 칸을 폭탄으로 채운 상태
                case 0:
                case 2:
                    fillAllField();
                    copyArray();
                    break;
                // 초기 폭탄 상태에서
                case 1:
                    // 1. 모든 칸을 폭탄으로 채우고
                    fillAllField();
                    // 2. 초기 저장된 폭탄들만 터뜨림(1초의 폭탄들)
                    boom();
                    // 3. 초기 폭탄이 터질 때 영향을 받지 않은(터지지 않은) 다른 폭탄들을 찾아 리스트에 저장하고
                    findBombs();
                    // 4. 다시 모든 칸을 폭탄으로 채움
                    fillAllField();
                    // 5. 폭탄을 터뜨리고 결과를 저장
                    boom();
                    copyArray();
                    break;
                // 모든 칸을 폭탄으로 채우고, 초기 저장된 폭탄들만 터뜨리고 결과를 저장
                case 3:
                    fillAllField();
                    boom();
                    copyArray();
                    break;
            }
        }

        System.out.println(sb);

        // 메모리: 16148KB, 시간: 160ms
    }

    // graph 배열을 StringBuilder에 그대로 저장하는 메소드
    static void copyArray(){
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                sb.append(graph[i][j]);
            }
            sb.append('\n');
        }
    }
    
    // graph 배열을 모두 폭탄으로 채우는 메소드
    static void fillAllField(){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                graph[i][j] = 'O';
            }
        }
    }
    
    // 터질(3초가 지난)폭탄을 터뜨리는 메소드
    static void boom(){
        for(int[] bomb: bombs){
            int x = bomb[0];
            int y = bomb[1];
            graph[x][y] = '.';
            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx<0 || ny<0 || nx>=R || ny>=C){
                    continue;
                }
                graph[nx][ny] = '.';
            }
        }
        // 다음 터질 폭탄 좌표를 담기위해 폭탄 리스트 초기화
        bombs.clear();
    }

    // 다음 터뜨릴 폭탄의 좌표를 찾아 저장하는 메소드
    static void findBombs(){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(graph[i][j] == 'O'){
                    bombs.add(new int[]{i, j});
                }
            }
        }
    }
}
