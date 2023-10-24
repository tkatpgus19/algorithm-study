import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/*
 *  Bj15787 : 기차가 어둠을 헤치고 은하수를
 *  기차에 사람을 태우거나 내리거나 뒤로 이동, 앞으로 이동
 *  승객이 똑같이 앉아있는 기차는 은하수 건널 수 x
 *  은하수 건널 수 있는 기차의 개수?
 */
public class Bj15787 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 BufferedReader 선언
		StringTokenizer st = new StringTokenizer(br.readLine());	// 공백으로 구분하여 입력받기 위해 StringTokenizer 선언
		
		int N = Integer.parseInt(st.nextToken());	// 기차의 개수 입력 받기 N
		int M = Integer.parseInt(st.nextToken());	// 명령 개수 입력 받기 M
		
		int[] flag = new int[N + 1];	// 1번 기차부터 있으므로 N+1로 선언
		for(int i = 0; i < M; i++) {	// 명령 개수만큼 반복 M번
			st = new StringTokenizer(br.readLine());	// 한 줄 입력 받고 공백으로 분리
			String m = st.nextToken();	// 명령 저장
			int idx = Integer.parseInt(st.nextToken());	// 기차 번호 저장
			int seat;	// 좌석 번호
			
			switch (m) {
			case "1":	// 사람 태우기
				seat = Integer.parseInt(st.nextToken());	// 좌석 번호 입력 받기
				flag[idx] |= 1 << (20 - seat);	// 비트마스킹 이용 -> 좌석 번호를 1로 변경
				break;
			case "2":	// 사람 내리기
				seat = Integer.parseInt(st.nextToken());	// 좌석 번호 입력 받기
				flag[idx] &= ~(1 << (20 - seat));	// 비트마스킹 이용 -> 좌석 번호를 0으로 변경
				break;
			case "3":	// 뒤로 밀기
				flag[idx] >>= 1;	// 비트마스킹 이용 -> 오른쪽으로 쉬프트
				// int형이라 32bit이므로 오른쪽으로 이동해도 20번째에는 0이 채워지니까 0으로 변경할 필요x
				//flag[idx] &= ~(1 << 19);	// 새로 채워지는 비트는 전 비트의 최상단 비트이므로 0으로 변경
				break;
			case "4":	// 앞으로 밀기
				flag[idx] <<= 1;	// 비트마스킹 이용 -> 왼쪽으로 쉬프트
				flag[idx] &= ~(1 << 20);	// 좌석 개수가 20개이므로 왼쪽으로 이동한 비트가 20을 넘어가면 0으로 변경
				break;
			}
		}
		
		Set<Integer> train = new HashSet<>();	// 중복 제거를 위해 Set 사용
		for(int i = 1; i < N + 1; i++) {	// 기차 개수만큼 반복 N번
			train.add(flag[i]);	// Set에 저장
		}
		
		System.out.println(train.size());	// Set 크기 출력
	}

}
