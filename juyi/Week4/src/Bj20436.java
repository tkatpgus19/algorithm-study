import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 *  Bj20436 : ZOAC3
 *  한글 자음 쪽 자판은 왼손, 모음 쪽 자판은 오른손
 *  한 번에 하나의 문자만 입력할 때 걸리는 시간?
 *  
 *  걸리는 시간 = 누를 때 1, 손가락 이동 시 abs(x1-x2) + abs(y1-y2)
 */
public class Bj20436 {

	static char[][] left = {{'q', 'w', 'e', 'r', 't'}, {'a', 's', 'd', 'f', 'g'}, {'z', 'x', 'c', 'v', '0'}};	// 왼손으로 입력할 수 있는 키보드 자판 배열 생성
	static char[][] right = {{'0', 'y', 'u', 'i', 'o', 'p'}, {'0', 'h', 'j', 'k', 'l', '0'}, {'b', 'n', 'm', '0', '0', '0'}};	// 오른손으로 입력할 수 있는 키보드 자판 배열 생성
	static char[] input, type;	// 입력할 문자열을 문자로 나누어 저장할 배열 input, 저장한 문자가 왼손인지 오른손인지 저장할 배열 type
	static int startLi, startLj, startRi, startRj;	// 왼손 처음 위치, 오른손 처음 위치
	static int time = 0;	// 걸리는 시간
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 BufferedReader 선언
		StringTokenizer st = new StringTokenizer(br.readLine());	// 한 줄 입력 받아 공백으로 구분하기

		String sL = st.nextToken();	// 분리된 문자열 저장 (왼손 처음 위치 문자)
		String sR = st.nextToken();	// 분리된 문자열 저장 (오른손 처음 위치 문자)
		input = br.readLine().toCharArray();	// 입력할 문자열을 char[]로 저장
		
		// 왼손 처음 위치 찾기
		left: for(int i = 0; i < 3; i++) {	// left 배열 크기만큼 반복	
			for(int j = 0; j < 5; j++) {	// left 배열 크기만큼 반복
				if(left[i][j] == sL.charAt(0)) {	// left 배열의 값과 왼손 처음 위치 문자가 같으면
					startLi = i;	// 인덱스 저장
					startLj = j;	// 인덱스 저장
					break left;	// 더 이상 반복문을 진행할 필요가 없으므로 탈출
				}
			}
		}
		
		// 오른손 처음 위치 찾기
		right: for(int i = 0; i < 3; i++) {	// right 배열 크기만큼 반복
			for(int j = 0; j < 6; j++) {	// right 배열 크기만큼 반복
				if(right[i][j] == sR.charAt(0)) {	// right 배열의 값과 오른손 처음 위치 문자가 같으면
					startRi = i;	// 인덱스 저장
					startRj = j;	// 인덱스 저장
					break right;	// 더 이상 반복문을 진행할 필요가 없으므로 탈출
				}
			}
		}
		
		type = new char[input.length];	// type 객체 생성 (안하면 NullPointerException 발생!!)
		
		for(int i = 0; i < input.length; i++) {	// 입력할 문자열만큼 반복
			left : for(int j = 0; j < 3; j++) {	// left 배열 크기만큼 반복
				for(int k = 0; k < 5; k++) {	// left 배열 크기만큼 반복
					if(left[j][k] == input[i]) {	// 같은 값이 있으면
						type[i] = 'l';	// 그 문자의 타입은 l
						break left;	// 더 이상 반복문을 진행할 필요가 없으므로 탈출
					}
				}
			}
			if(type[i] != 'l') type[i] = 'r';	// 그 문자의 타입이 l로 안바뀌었으면 왼손이 아니므로 r 저장
		}
		
		timecheck(0);	// 입력할 문자열의 첫 번째 문자부터 실행
		
		System.out.println(time);	// time 출력
	}
	
	private static void timecheck(int idx) {	// idx를 매개변수로 시간 체크하기
		if(idx == input.length) return;	// idx가 입력할 문자열의 크기와 같으면 모든 문자열 체크 완료 -> 메소드 종료
		if(type[idx] == 'l') {	// type이 왼쪽일 떄
			if(left[startLi][startLj] == input[idx]) {	// 현재 위치의 문자와 입력할 문자가 동일하면
				time++;	// 누르기
			}
			else {	// 현재 위치와 입력할 문자가 다르면
				for(int i = 0; i < 3; i++) {	// left 배열 크기 만큼 반복
					for(int j = 0; j < 5; j++) {	// left 배열 크기 만큼 반복
						if(left[i][j] == input[idx]) {	// 입력할 문자 찾기
							time += Math.abs(startLi - i) + Math.abs(startLj - j) + 1;	// 이동 시간 + 누르는 시간만큼 증가
							startLi = i;	// 현재 위치 변경
							startLj = j;	// 현재 위치 변경
						}
					}
				}
			}
		}
		else {	// type이 오른쪽일 때
			if(right[startRi][startRj] == input[idx]) {	// 현재 위치의 문자와 입력할 문자가 동일하면
				time++;	// 누르기
			}
			else {	// 현재 위치와 입력할 문자가 다르면
				for(int i = 0; i < 3; i++) {	// right 배열 크기 만큼 반복
					for(int j = 0; j < 6; j++) {	// right 배열 크기 만큼 반복
						if(right[i][j] == input[idx]) {	// 입력할 문자 찾기
							time += Math.abs(startRi - i) + Math.abs(startRj - j) + 1;	// 이동 시간 + 누르는 시간만큼 증가
							startRi = i;	// 현재 위치 변경
							startRj = j;	// 현재 위치 변경
						}
					}
				}
			}
		}
		
		timecheck(idx + 1);	// 다음 인덱스 진행
	}

}
