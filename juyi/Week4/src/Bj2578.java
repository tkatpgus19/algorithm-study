import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 *  Bj2578 : 빙고
 *  사회자가 몇 번째 수를 부른 후 선이 3개 이상 그어지지 찾기
 *  
 *  처음엔 너무 어렵게 생각해서 어려웠는데 풀고나니 쉬웠다
 */
public class Bj2578 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 BufferedReader 선언
		
		String[][] map = new String[5][5];	// 철수의 빙고판
		int[] row = new int[5];	// 사회자가 부른 수의 위치(행)에 따라 개수 세기 위한 배열
		int[] col = new int[5];	// 사회자가 부른 수의 위치(열)에 따라 개수 세기 위한 배열
		int left = 0;	// 사회자가 부른 수의 위치(왼쪽 대각선)에 따라 개수 세기 위한 변수
		int right = 0;	// 사회자가 부른 수의 위치(오른쪽 대각선)에 따라 개수 세기 위한 변수
		
		for(int i = 0; i < 5; i++) {	// 철수 빙고판 행의 크기만큼 반복
			map[i] = br.readLine().split(" ");	// split을 이용하여 공백으로 분리하여 입력 받기
		}
		
		int count = 0, bingo = 0;	// 사회자가 부른 수의 개수를 세기 위한 count, 그어지는 선의 개수를 세기 위한 bingo
		bingo : for(int i = 0; i < 5; i++) {	// 사회자의 빙고판 행 만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());	// 한 줄 입력 받아서 공백으로 분리
			for(int j = 0; j < 5; j++) {	// 사회자의 빙고판 열 만큼 반복
				String select = st.nextToken();	// 분리된 수 입력 받기
				count++;	// 수 하나 불렀으므로 1 증가
				
				// 철수 빙고판 비교
				map : for(int r = 0; r < 5; r++) {	// 철수 빙고판 행 만큼 반복	
					for(int c = 0; c < 5; c++) {	// 철수 빙고판 열 만큼 반복 
						if(map[r][c].equals(select)) {	// 사회자가 부른 수 찾기
							if(r == c) {	// 행 값과 열 값이 같으면 왼쪽 대각선에 위치한 수
								left++;	// left 증가
								if(left == 5) bingo++;	// left가 5개가 되면 왼쪽 대각선 1개 긋기. bingo 증가
							}
							if(r + c == 4) {	// 행 값 + 열 값이 4이면 오른쪽 대각선에 위치한 수
								right++;	// right 증가
								if(right == 5) bingo++;	// right가 5개가 되면 오른쪽 대각선 1개 긋기. bingo 증가
							}
							row[r]++;	// 수가 위치한 행의 개수 배열 증가
							if(row[r] == 5) bingo++;	// 수가 위치한 행의 개수 배열 값이 5이면 가로선 긋기. bingo 증가
							col[c]++;	// 수가 위치한 열의 개수 배열 증가
							if(col[c] == 5) bingo++;	// 수가 위치한 열의 개수 배열 값이 5이면 세로선 긋기. bingo 증가
							if(bingo >= 3) break bingo;	// bingo가 3 이상이 되면 더 이상 사회자의 빙고판을 확인할 필요 x -> bingo 탈출
							else break map;	// 아직 3이 안되었고 사회자가 부른 값은 찾았으므로 map 반복문 탈출하고 다음 사회자가 부르는 수 찾으러 가기
						}
					}
				}
			}
		}
		
		System.out.println(count);	// count 출력
	}

}
