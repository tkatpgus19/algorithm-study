import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 *  Bj14889 : 스타트와 링크
 *  총 N명을 N/2명으로 이루어진 스타트 팀과 링크 팀으로 나누기
 *  스타트팀과 링크팀의 능력치 차이의 최소값 찾기
 */
public class Bj14889 {

	// 조합 함수에 이용하기 위해 static으로 선언
	static int N;	// 총 명수
	static int[][] map;	// 사람들의 능력치
	static boolean[] selected;	// 선택된 사람 저장할 배열
	static int min;	// 능력치 차이의 최소값
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 BufferedReader 선언
		
		N = Integer.parseInt(br.readLine());	// 총 명수 입력 받기
		
		map = new int[N + 1][N + 1];	// 사람이 1번부터이므로 N+1 크기로 map 2차원 배열 생성
		for(int i = 1; i < N + 1; i++) {	// map의 행 반복
			StringTokenizer st = new StringTokenizer(br.readLine());	// 한 줄 입력받고 공백으로 나누기
			for(int j = 1; j < N + 1; j++) {	// map의 열 반복
				map[i][j] = Integer.parseInt(st.nextToken());	// map에 저장
			}
		}

		selected = new boolean[N + 1];	// 1번부터이므로 N+1 크기로 selected 배열 생성
		min = Integer.MAX_VALUE;	// 최소값을 Integer의 MAX 값으로 초기화
		comb(0, 1);	// 조합 실행
		
		System.out.println(min);	// 최소값 출력
	}
	
	private static void comb(int idx, int start) {	// 조합 함수 선언. idx : 뽑은 수, start : 시작 인덱스
		if(idx == N / 2) {	// 기저조건 : 뽑은 수가 N/2명
			int sumS = 0, sumL = 0;	// 스타트팀의 능력치와 링크팀의 능력치를 저장할 변수 선언 
			for(int i = 1; i < N + 1; i++) {	// 1번부터 N번까지 선택된 사람 찾기
				for(int j = i + 1; j < N + 1; j++) {	// i + 1부터 N번까지 선택된 사람 찾기
					if(selected[i] && selected[j]) {	// i번쨰와 j번째가 둘 다 true이면 스타트팀
						sumS += map[i][j] + map[j][i];	// 스타트팀의 능력치 증가
					}
					else if(!selected[i] && !selected[j]) {	// i번째와 j번째가 둘 다 false이면 링크팀
						sumL += map[i][j] + map[j][i];	// 링크팀의 능력치 증가
					}
						
				}
			}
			
			min = Math.min(min, Math.abs(sumS - sumL));	// 최소값 저장
			return;	// 함수 종료
		}
		
		for(int i = start; i < N + 1; i++) {	// 조합이니까 start부터 N번까지 반복
			selected[i] = true;		// 선택
			comb(idx + 1, i + 1);	// 다음 선택하기
			selected[i] = false;	// 선택 취소 (원상복구)
		}
	}
}
