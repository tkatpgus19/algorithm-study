import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 *  Bj17471 : 게리맨더링
 *  두 개의 선거구로 나누는데 각 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 함.
 *  선거구는 구역을 적어도 하나 포함해야 함. -> 공집합은 x
 *  
 *  두 개로 나누는 건 부분집합 이용.
 *  인접 체크는 BFS 이용.
 */
public class Bj17471 {

	// 메소드에서 사용하기 위해 멤버 변수로 선언
	static int N;	// 구역의 개수
	static int[] people;	// 구역의 인구수 배열
	static List<Integer>[] list;	// 인접 리스트
	static int[] selected;	// 선택된 구역 확인 배열
	static boolean[] visited;	// bfs에서 사용할 방문 체크 배열
	static int min;	// 최소값
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력
		N = Integer.parseInt(br.readLine());	// N 입력 받기
		
		people = new int[N + 1];	// 인구수 저장 배열 생성
		
		StringTokenizer st = new StringTokenizer(br.readLine());	// 한 줄 입력 받고 공백으로 나누기
		for(int i = 1; i < N + 1; i++) {
			people[i] = Integer.parseInt(st.nextToken());	// 인구수 저장
		}
		
		list = new ArrayList[N + 1];	// 인접리스트 배열 생성
		for(int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();	// 인접리스트 객체 생성
		}
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());	// 한 줄 입력 받고 공백으로 나누기
			int size = Integer.parseInt(st.nextToken());	// 인접한 구역의 수 저장
			for(int j = 0; j < size; j++) {	// 인접 구역 수만큼 반복
				list[i].add(Integer.parseInt(st.nextToken()));	// 인접 정점 저장
			}
		}
		
		selected = new int[N + 1];	// 선택된 구역 배열 생성
		min = Integer.MAX_VALUE;	// MAX로 초기화
		
		powerSet(0);	// 메소드 실행
		
		if(min == Integer.MAX_VALUE) min = -1;	// 실행 후에 min값이 변경되지 않았으면 두 선거구로 나눌 수 없는 경우 -> min = -1
		System.out.println(min);	// min 출력

	}
	
	private static void powerSet(int idx) {
		if(idx == N + 1) {	// 기저조건. 뽑은 수가 N + 1일 때
			int size1 = 0, size2 = 0, sum1 = 0, sum2 = 0;	// 선거구1의 사이즈, 인구수 합계, 선거구2의 사이즈, 인구수 합계
			int start1 = 1, start2 = 1;	// bfs로 넘겨줄 선거구 1인 지역, 선거구 2인 지역
			for(int i = 1; i < N + 1; i++) {
				if(selected[i] == 1) {	// 선거구 1인 경우
					size1++;	// 선거구1 사이즈 증가
					sum1 += people[i];	// 선거구 1에 포함된 지역의 인구수 증가
					start1 = i;	// 선거구 1인 지역 변경
				}
				else {	// 선거구 2인 경우
					size2++;	// 선거구2 사이즈 증가
					sum2 += people[i]; // 선거구 2에 포함된 지역의 인구수 증가
					start2 = i;	// 선거구 2인 지역 변경
				}
			}
			
			boolean check1 = false, check2 = false;	// 선거구 2개로 나눌 수 있는지 없는지 확인할 boolean 변수
			
			if(size1 > 0 && size1 < N && size2 > 0 && size2 < N) {	// 두 선거구는 하나의 지역을 포함해야하므로 사이즈 확인하기
				check1 = bfs(start1, 1, size1);	// 선거구1 bfs로 인접한지 확인
				check2 = bfs(start2, 2, size2);	// 선거구2 bfs로 인접한지 확인
			}

			if(check1 && check2) {	// check1과 check2가 모두 true이면
				min = Math.min(min, Math.abs(sum1 - sum2));	// 최소값 변경
			}
			
			return;	// powerSet 종료
		}
		
		// 부분집합 만들기
		selected[idx] = 1;
		powerSet(idx + 1);
		selected[idx] = 2;
		powerSet(idx + 1);
	}
	
	private static boolean bfs(int select, int type, int size) {	// bfs select : 선택된 지역, type : 선거구1인지 2인지, size : 선거구 1 사이즈 또는 선거구 2 사이즈
		Queue<Integer> queue = new ArrayDeque<Integer>();	// 큐 생성
		
		visited = new boolean[N + 1];	// 방문체크 배열 생성
		queue.add(select);	// 큐에 select 넣기
		visited[select] = true;	// select 방문 처리
		
		int count = 0;	// 해당 선거구로 선택된 지역이 인접한지 아닌지를 체크하기 위해 큐에서 나온 횟수를 셈
		while(!queue.isEmpty()) {	 // 큐가 비어있지 않으면
			int current = queue.poll();	// 빼고
			count++;	// count 증가
			for(int i = 0; i < list[current].size(); i++) {	// 인접리스트 사이즈만큼
				if(!visited[list[current].get(i)] && selected[list[current].get(i)] == type) {	// 방문하지 않고 선택된 선거구가 같으면
					queue.add(list[current].get(i));	// 큐에 넣기
					visited[list[current].get(i)] = true;	// 방문 체크
				}
			}
		}
		
		if(count == size) return true;	// 큐에서 나온 횟수와 선거구의 사이즈가 동일하면 인접한 지역들로 이루어진 것! true 리턴
		
		return false;	// 위에서 리턴 안됐으면 false 리턴

	}

}
