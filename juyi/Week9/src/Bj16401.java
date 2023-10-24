import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Bj16401 : 과자 나눠주기
 * 조카 1명에게 줄 수 있는 막대 과자의 최대 길이 찾기
 */
public class Bj16401 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력
		StringTokenizer st = new StringTokenizer(br.readLine());	// 공백 나누기
		
		int M = Integer.parseInt(st.nextToken());	// 조카 명 수 입력 받기
		int N = Integer.parseInt(st.nextToken());	// 과자 개수 입력 받기
		
		int[] snack = new int[N];	// 과자 길이를 저장할 배열 선언
		st = new StringTokenizer(br.readLine());	// 공백 나누기
		for(int i = 0; i < N; i++) {	// 과자 개수만큼 반복
			snack[i] = Integer.parseInt(st.nextToken());	// 과자 길이 저장
		}
		
		Arrays.sort(snack);	// 오름차순 정렬
		
		int left = 1, right = snack[N-1];	// 왼쪽은 1부터, 오른쪽은 주어진 과자 길이의 최대값부터 시작
		while(left <= right) {	// left가 right보다 커질때까지 반복
			int mid = (left + right) / 2;	// 중간값 찾기
			int count = 0;	// 나눌 수 있는 과자 개수 저장할 변수
			for(int i = 0; i < N; i++) {	// 과자 개수만큼 반복
				count += snack[i] / mid;	// 주어진 과자 길이를 나눈 개수 구해서 더하기
			}
			
			if(count >= M) left = mid + 1;	// 계산된 개수가 주어진 조카 수보다 많으면 left를 mid 다음 값으로 변경해서 더 크게 과자 자름
			else right = mid - 1;	// 계산된 개수가 주어진 조카 수보다 적으면 right를 mid보다 하나 적은 값으로 변경해서 더 작게 과자 자름
		}
		
		System.out.println(right);	// while에서 탈출했을 때 right 값이 결과가 됨
	}
}
