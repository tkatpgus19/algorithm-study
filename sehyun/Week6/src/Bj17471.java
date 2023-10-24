import java.io.*;
import java.util.*;

/**
 * [17471번: 게리맨더링]
 * 문제: 그래프가 주어질 때, 특정 조건으로 두팀을 나누고 팀 간의 인구수 차의 최솟값을 구하시오.
 * 해결: 부분집합을 구해 팀을 나누고, 그래프를 탐색해 팀이 유효한지 체크하여 유효하다면 인구수 차를 구해 최솟값을 찾는다.
 */
public class Bj17471 {
    static int[] population;
    static boolean[][] graph;
    static boolean[] visited;

    // 부분집합을 구할 때 사용할 배열
    static boolean[] team;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        // 1번부터 주어지므로 N+1크기 배열을 선언해 초기화
        population = new int[N+1];
        graph = new boolean[N+1][N+1];
        team = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            population[i] = Integer.parseInt(st.nextToken());
        }
        
        // 그래프의 간선 입력
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for(int j=1; j<M+1; j++){
                graph[i][Integer.parseInt(st.nextToken())] = true;
            }
        }
        comb(1, N);
        
        // 최댓값 갱신이 되지 않았을 때(두 선거구로 나눌 수 없을 때) -1 출력
        if(answer == Integer.MAX_VALUE){
            answer = -1;
        }
        System.out.println(answer);

        // 메모리: 17088KB, 시간: 160ms
    }
    
    // 부분집합을 구해 팀의 유효성을 체크하고 인구수 차를 구하는 메소드
    static void comb(int depth, int N){
        // 팀이 결성 되었을 때
        if(depth == N+1){
            // 팀 상태 저장
            List<Integer> team1 = new ArrayList<>();
            List<Integer> team2 = new ArrayList<>();
            for(int i=1; i<N+1; i++){
                if(team[i]){
                    team1.add(i);
                }
                else{
                    team2.add(i);
                }
            }
            // 한쪽 팀에 모든 인원이 몰려있을 때(팀결성 실패) 종료
            if(team1.isEmpty() || team2.isEmpty()){
                return;
            }

            // 팀이 가능한지 체크
            visited = new boolean[N+1];
            if(bfs(team1) && bfs(team2)){
                // 가능하면 인원수 체크
                int result1 = 0;
                int result2 = 0;
                for(int i=1; i<N+1; i++){
                    if(team[i]){
                        result1 += population[i];
                    } else{
                        result2 += population[i];
                    }
                }
                // 최솟값 갱신
                answer = Math.min(answer, Math.abs(result1-result2));
            }
            return;
        }
        team[depth] = true;
        comb(depth+1, N);
        team[depth] = false;
        comb(depth+1, N);
    }

    // 팀의 유효성을 체크(그래프 탐색)하는 메소드
    static boolean bfs(List<Integer> teamList){
        Deque<Integer> queue = new ArrayDeque<>();
        // 팀에 존재하는 선거구만 저장하는 리스트 선언
        List<Integer> tmp = new ArrayList<>();
        
        tmp.add(teamList.get(0));
        queue.addLast(teamList.get(0));
        visited[teamList.get(0)] = true;

        while(!queue.isEmpty()){
            int node = queue.removeFirst();
            for(int i=1; i<graph.length; i++){
                // 만약 방문하지 않았고, 해당 팀에 존재하는 노드이면 리스트에 추가
                if(!visited[i] && graph[node][i]){
                    for(int t: teamList){
                        if(t == i){
                            visited[i] = true;
                            tmp.add(i);
                            queue.addLast(i);
                            break;
                        }
                    }
                }
            }
        }
        // 탐색하며 저장한 팀원들 수가 예상했던 팀원 수와 일치하면 유효하므로 true 반환
        return tmp.size() == teamList.size();
    }
}