import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1158 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 키보드로부터 입력 받기 위한 BufferedReader 선언
		StringTokenizer st = new StringTokenizer(br.readLine());	// 공백 구분 입력을 위한 StringTokenizer 선언
		int n = Integer.parseInt(st.nextToken());					// 입력 받은 명 수를 int로 변환하여 n에 저장
		int k = Integer.parseInt(st.nextToken());					// 입력 받은 K를 int로 변환하여 k에 저장
		
		int[] people = new int[n + 1];	// 사람을 저장할 배열 선언. 1부터 사용 -> n+1 크기
		int[] newPeople = new int[n];	// 새로운 사람 배열 선언. 0부터 사용 -> n 크기
		
		for(int i = 1; i < n + 1; i++) {	// 1부터 n + 1까지 반복
			people[i] = i;					// people 배열에 순서대로 저장
		}
		
		int idx = k, npidx = 0;	// 첫 번째로 제거할 사람(k) 인덱스를 idx, 새로운 사람 배열을 저장할 인덱스를 npidx로 선언
		newPeople[npidx] = people[k];	// 새로운 사람 배열의 0번째로 people[k]를 저장
		people[k] = 0;			// 저장 후에는 0으로 초기화
		npidx++;				// newPeople의 다음 저장을 위해 npidx 증가
		int count = 0;			// k 체크를 위한 count 변수 선언
		int check = 1;			// while 탈출을 위해 check 변수 선언. 이미 k번째는 진행했으므로 1로 초기화.
		
		while(true) {			// while 반복
			if(check == n) break;	// check가 n이 되면 주어졌던 people이 다 제거된 것. 그래서 탈출.
			idx++;					// people의 다음 인덱스 확인을 위해 idx 증가
			if(idx > n) {			// idx가 n보다 크면 1로 돌아가야하므로 %연산 이용.
				idx = idx % n;		// idx가 n보다 크면 1로 돌아가야하므로 %연산 이용.
			}
			
			if(people[idx] == 0) continue;	// people[idx]가 0이면 이미 제거된 것이므로 continue.
			count++;						// people의 다음 인덱스로 넘어갔으므로 count 증가
			if(count == k) {				// count가 k가 되면 제거 진행
				newPeople[npidx] = people[idx];	// newPeople[npidx]에 people[idx] 대입
				people[idx] = 0;				// people[idx]는 제거되었으므로 0으로 초기화
				count = 0;						// k번째가 제거되고 다음 k번째를 찾기위해 count를 0으로 초기화
				npidx++;						// newPeople의 다음 인덱스에 값을 저장해야하므로 npidx 증가
				check++;						// people[idx]가 제거되었으므로 check 증가
			}
		}
		
		StringBuilder sb = new StringBuilder();	// 빠른 출력을 위한 StringBuilder 선언
		sb.append("<");							// 출력 양식 추가
		for(int i = 0; i < n; i++) {			// newPeople의 0번째부터 n-1번째까지 출력 반복
			sb.append(newPeople[i]);			// newPeople의 0번째부터 n-1번째까지 출력 반복
			if(i == n - 1) sb.append(">");		// 마지막 출력이라면 출력 양식 지켜서 마무리
			else sb.append(", ");				// 마지막 출력이 아니면 ", " 출력 양식 지키기
		}
		System.out.println(sb);					// 저장해놓은 StringBuilder 출력
	}

}
