import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 *  Bj14502 : 연구소
 *  0 = 빈칸, 1 = 벽, 2 = 바이러스
 *  바이러스는 상하좌우로 퍼짐
 *  벽 3개를 세우고 바이러스가 안퍼진 안전공간의 최대 개수 찾기
 *  
 *  조합으로 벽 3개 세우는 위치 찾기
 *  bfs로 바이러스 퍼지게 하고 남은 빈칸 개수 세기
 */
public class Bj14502 {

	// 다른 메소드에서 사용하기 위해 멤버 변수로 선언
	static int N, M;	// 연구소 지도 크기
	static String[][] map;	// 연구소의 지도 저장할 배열 선언
	static int max;	// 안전 공간의 최대 개수 저장할 변수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력 BufferedReader
		StringTokenizer st = new StringTokenizer(br.readLine());	// 한 줄 입력 받고 공백으로 나누기
		
		N = Integer.parseInt(st.nextToken());	// N 입력 받기
		M = Integer.parseInt(st.nextToken());	// M 입력 받기

		map = new String[N][M];	// map 배열 객체 생성
		for(int i = 0; i < N; i++) {	// map의 행 크기만큼 반복
			map[i] = br.readLine().split(" ");	// 한 줄 입력 받고 공백으로 나누어서 map의 i행에 저장
		}
		
		max = Integer.MIN_VALUE;	// 최대 개수 저장할 max 변수를 Integer의 최소값으로 초기화
		
		comb(0, 0);	// 조합 실행
		
		System.out.println(max);	// max 값 출력
		
	}
	private static void comb(int cnt, int start) {	// 조합 함수 선언. 뽑은 개수 cnt, 시작 위치 start
		if(cnt == 3) {	// 기저 조건 : cnt가 3
			String[][] virusMap = new String[N][M];	// bfs로 새로 벽이 생긴 map을 넘겨주기 위해 복사용 배열 생성
			for(int i = 0; i < N; i++) {	// map의 행만큼 반복
				virusMap[i] = Arrays.copyOf(map[i], map[i].length);	// 복사하기
			}
			max = Math.max(max, bfs(virusMap));	// bfs로 virusMap을 넘겨주고 받은 리턴값과 현재 max 값 비교하여 max 값 변경
			return;	// 조합 함수 종료
		}
		
		// 2차원 배열 조합 뽑기
		for(int i = start; i < N * M; i++) {	// start부터 N*M까지 반복
			int r = i / M;	// i를 열 크기로 나누면 몫이 행 인덱스 의미
			int c = i % M;	// i를 열 크기로 나눈 나머지는 열 인덱스 의미
			
			if(map[r][c].equals("0")) {	// map[r][c]가 0이면
				map[r][c] = "1";	// 1로 변경
				comb(cnt + 1, i + 1);	// 다음꺼 뽑으러 가기
				map[r][c] = "0";	// 0으로 변경 (원상복구)
			}
		}
	
	}
	
	private static int bfs(String[][] VirusMap) {	// bfs 함수 선언. 매개변수로 map 받기
		Queue<Data> queue = new ArrayDeque<Data>();	// 큐 생성
		
		for(int i = 0; i < N; i++) {	// virusMap의 행만큼 반복
			for(int j = 0; j < M; j++) {	// virusMap의 열만큼 반복
				if(VirusMap[i][j].equals("2")) {	// virusMap[i][j] 값이 2이면
					queue.offer(new Data(i, j));	// 큐에 넣기
				}
			}
		}
		
		while(!queue.isEmpty()) {	// 큐가 비어있지 않으면
			Data current = queue.poll();	// 큐 값 뽑기
			
			int[] dr = {-1, 1, 0, 0};	// 4방향 탐색 (상하좌우)
			int[] dc = {0, 0, -1, 1};	// 4방향 탐색 (상하좌우)
			
			for(int i = 0; i < 4; i++) {	// 4방향 반복
				int nr = current.i + dr[i];	// 탐색할 행 인덱스
				int nc = current.j + dc[i];	// 탐색할 열 인덱스
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && VirusMap[nr][nc].equals("0")) {	// nr, nc가 범위 안쪽이고 virusMap의 값이 0이면
					VirusMap[nr][nc] = "2";	// 바이러스 퍼짐
					queue.offer(new Data(nr, nc));	// 큐에 넣기
				}
			}
		}
		
		int count = 0;	// 빈칸을 셀 변수 선언
		for(int i = 0; i < N; i++) {	// virusMap의 행만큼 반복
			for(int j = 0; j < M; j++) {	// virusMap의 열만큼 반복
				if(VirusMap[i][j].equals("0")) count++;	// 0 개수 세기
			}
		}
		
		return count;	// count 리턴
		
	}
	
	static class Data {	// map의 인덱스를 같이 관리하기 위해 클래스 생성
		int i;	// 행 인덱스
		int j;	// 열 인덱스
		
		public Data(int i, int j) {	// 생성자
			super();
			this.i = i;
			this.j = j;
		}
		
	}

}
