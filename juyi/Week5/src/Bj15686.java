import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  Bj15686 : 치킨 배달
 *  치킨집 중 M개를 고르고,
 *  도시의 치킨 거리(모든 집의 치킨 거리의 합)의 최소값 찾기
 *  
 */
public class Bj15686 {
	
	// 조합 내에서 사용하기 위해 멤버 변수로 선언
	static int N, M;	// 도시의 크기 N, 남길 치킨집 개수 M 
	static List<Data> chickenList;	// 치킨집의 인덱스를 저장할 리스트
	static List<Data> homeList;	// 집의 인덱스를 저장할 리스트
	static boolean[] selected;	// 선택된 치킨집을 저장할 배열
	static int min;	// 도시의 치킨 거리 최소값 저장할 변수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 BufferedReader
		
		StringTokenizer st = new StringTokenizer(br.readLine());	// 한 줄 입력 받고 공백으로 나누기
		N = Integer.parseInt(st.nextToken());	// N 입력 받기
		M = Integer.parseInt(st.nextToken());	// M 입력 받기
		
//		int[][] map = new int[N + 1][N + 1];	// 도시 배열 객체 생성
		chickenList = new ArrayList<Data>();	// 치킨집 인덱스 저장 리스트 객체 생성
		homeList = new ArrayList<Data>();	// 집 인덱스 저장 리스트 객체 생성
		for(int i = 1; i < N + 1; i++) {	// 도시 행 만큼 반복
			st = new StringTokenizer(br.readLine());	// 한 줄 입력 받고 공백으로 나누기
			for(int j = 1; j < N + 1; j++) {	// 도시 열 만큼 반복
				// 치킨집과 집의 인덱스만 찾아서 저장하면 되므로 map이 굳이 필요 없다
//				map[i][j] = Integer.parseInt(st.nextToken());	// map에 저장
//				if(map[i][j] == 1) homeList.add(new Data(i, j));
//				else if(map[i][j] == 2) chickenList.add(new Data(i, j));
				
				int temp = Integer.parseInt(st.nextToken());	// temp에 임시 저장
				if(temp == 1) homeList.add(new Data(i, j));	// temp가 1이면 homeList에 저장
				else if(temp == 2) chickenList.add(new Data(i, j));	// temp가 2이면 chickenList에 저장
			}
		}
		
		selected = new boolean[chickenList.size()];	// 선택된 치킨집을 저장하기 위해 selected 배열 생성
		min = Integer.MAX_VALUE;	// 최소값을 Integer의 MAX 값으로 초기화
		
		comb(0, 0);	// 조합 실행. 첫 실행이므로 idx 0, start 0
		
		System.out.println(min);	// 최소값 출력
	}
	
	private static void comb(int idx, int start) {	// 조합 함수 선언. 뽑은 개수 idx, 선택할 인덱스의 시작
		if(idx == M) {	// 기저 조건 : idx가 M이 될 때
			int[] s = new int[homeList.size()];	// 집의 치킨 거리의 최소값을 저장할 배열 생성. homeList의 size만큼.
			for(int i = 0; i < homeList.size(); i++) {	// homeList의 size만큼 반복
				s[i] = Integer.MAX_VALUE;	// 최소값을 비교하면서 저장해야 하므로 Integer의 MAX 값으로 초기화
			}
			for(int i = 0; i < chickenList.size(); i++) {	// chickenList의 size만큼 반복
				if(selected[i]) {	// 선택된 치킨집이면
					for(int j = 0; j < homeList.size(); j++) {	// homeList의 size만큼 반복하면서
						int temp = Math.abs(chickenList.get(i).i - homeList.get(j).i) + Math.abs(chickenList.get(i).j - homeList.get(j).j);	// 선택된 치킨집과의 치킨 거리 저장
						if(s[j] > temp) s[j] = temp;	// 현재 치킨 거리보다 작은 값이면 거리 변경
					}
				}
			}
			
			int sum = 0;	// 도시의 치킨 거리 저장할 변수 생성
			for(int i = 0; i < s.length; i++) {	// 거리 배열의 크기만큼 반복
				sum += s[i];	// 합하기
			}
			
			min = Math.min(min, sum);	// 최소값 저장
			return;	// 조합 함수 종료
		}
		
		// to do
		for(int i = start; i < chickenList.size(); i++) {	// start부터 chickenList의 size만큼 반복
			selected[i] = true;	// 선택
			comb(idx + 1, i + 1);	// 재귀 호출 - 다음 치킨집 선택하기
			selected[i] = false;	// 선택 되돌리기 (원상 복구)
		}

	}

	private static class Data {	// 집과 치킨집의 인덱스를 같이 관리하기 위해 클래스 생성
		int i;	// i 인덱스
		int j;	// j 인덱스
		
		public Data(int i, int j) {	// Data 생성자
			super();	// 부모 클래스 생성자 실행
			this.i = i;	// 파라미터 i를 멤버변수 i에 저장
			this.j = j;	// 파라미터 j를 멤버변수 j에 저장
		}
		
	}
}

