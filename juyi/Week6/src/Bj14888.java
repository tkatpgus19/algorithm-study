
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 *  2023-08-29
 *  Bj14888 : 연산자 끼워넣기
 *  N개의 수, N-1개의 연산자
 *  만들수 있는 식의 결과가 최대인 것과 최소인 것 구하기
 */
public class Bj14888 {

	// 다른 메소드에서 사용하기 위해 멤버 변수로 선언
	static int N, nums[], opers[];	// 주어진 수의 개수 N, 주어진 수 저장 배열, 연산자의 개수 저장 배열
	static int max, min;	// 최대값, 최소값 변수
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력
		
		N = Integer.parseInt(br.readLine());	// N 입력 받기
		
		nums = new int[N];	// 주어진 수 저장 배열 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine());	// 한 줄 입력받고 공백으로 나누기
		for(int i = 0; i < N; i++) {	// N번 반복
			nums[i] = Integer.parseInt(st.nextToken());	// 주어진 수 저장
		}
		
		opers = new int[4];	// 연산자의 개수 저장 배열 객체 생성
		st = new StringTokenizer(br.readLine());	// 한 줄 입력받고 공백으로 나누기
		for(int i = 0; i < 4; i++) {	// 4번 반복 (연산자의 종류)
			opers[i] = Integer.parseInt(st.nextToken());	// 연산자 개수 입력 받기
		}
		
		max = Integer.MIN_VALUE;	// max를 MIN_VALUE로 초기화
		min = Integer.MAX_VALUE;	// min을 MAX_VALUE로 초기화
		setOper(0, new int[N - 1]);	// setOper 메소드 실행
		
		System.out.println(max);	// max 출력
		System.out.println(min);	// min 출력
	}
	
	private static void setOper(int idx, int[] selectedOper) {	// 연산자 설정 메소드. idx : 뽑은 연산자의 수, selectedOper : 선택된 연산자를 저장할 배열
		if(idx == N - 1) {	// N-1개 연산자 뽑았을 때 기저조건
			max = Integer.max(max, calc(selectedOper));	// 계산 메소드 실행하고 그 값과 max 비교하여 큰 값 저장
			min = Integer.min(min, calc(selectedOper));	// 계산 메소드 실행하고 그 값과 min 비교하여 작은 값 저장
			return;	// setOper 메소드 종료
		}
		
		for(int i = 0; i < 4; i++) {	// 4번 반복 (연산자의 종류)
			if(opers[i] == 0) continue;	// 해당 연산자의 개수가 0이면 다음 반복으로 가기
			selectedOper[idx] = i;	// 해당 연산자 선택
			opers[i]--;	// 선택한 연산자의 수 감소
			setOper(idx + 1, selectedOper);	// 다음 연산자 선택하기
			opers[i]++;	// 선택한 연산자의 수 증가 (원상복구)
		}
	}
	
	private static int calc(int[] selectedOper) {	// 계산 메소드. selectedOper를 매개변수로 받음
		int result = nums[0];	// 0번째 수로 result 초기화
		for(int i = 0; i < N - 1; i++) {	// N-1번 반복
			switch(selectedOper[i]) {	// 선택된 연산자 확인
			case 0:	// 0이면 +
				result += nums[i + 1];	// result에 다음 숫자 더하기
				break;	// 0번 끝
			case 1:	// 1이면 -
				result -= nums[i + 1];	// result에 다음 숫자 빼기
				break;	// 1번 끝
			case 2:	// 2이면 *
				result *= nums[i + 1];	// result에 다음 숫자 곱하기
				break;	// 2번 끝
			case 3:	// 3이면 /
				result /= nums[i + 1];	// result에 다음 숫자 나누기
				break;	// 3번 끝
			}
		}
		
		return result;	// 결과 리턴

	}

}
