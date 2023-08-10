import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 * 	Bj11279 : 최대 힙
 * 	연산의 개수 N
 * 	비어있는 배열에서 시작
 * 	x == 자연수 -> 배열에 x 추가
 *	x == 0 -> 배열에서 가장 큰 값 출력하고 배열에서 제거
 */

public class Bj11279 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 BufferedReader 선언
		int N = Integer.parseInt(br.readLine());	// 한 줄 입력 받아 N에 저장
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());	// 우선 순위가 있게하기 위해서 PriorityQueue 선언, 큰 값을 우선순위 높은 것으로 하기 위해 Collections.reverseOrder() 사용
		
		StringBuilder sb = new StringBuilder();	// 빠른 출력을 위한 StringBuilder 선언
		
		for(int i = 0; i < N; i++) {	// 연산의 개수만큼 반복. N번
			int x = Integer.parseInt(br.readLine());	// 정수 x 입력 받기
			if(x == 0) {	// 입력 받은 x가 0이라면 
				if(pq.isEmpty()) sb.append(0).append("\n");	// pq가 비어있으면 출력값은 0
				else {
					sb.append(pq.remove()).append("\n");	// pq가 비어있지 않으면 pq에서 우선 순위가 높은 값(가장 큰 값)을 반환하고 삭제.
				}
			}
			else pq.add(x);	// x가 0이 아니면 pq에 저장
		}
		
		System.out.println(sb);	// sb 출력
	}

}
