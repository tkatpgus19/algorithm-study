/*첫번째 시도  - 성공*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Bj2346 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Deque<Integer> deque = new ArrayDeque<>(); //덱 선언 : 자료형은 Wrapper class로 
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        

		int N = Integer.parseInt(bf.readLine()); //N 입력 받기
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		
		//N개의 종이 입력 받아서 DEQUE에 추가
		int[] arr = new int[N];  //Paper의 정보가 들어가있음
		int paper ;
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			deque.add(i);	// 풍선 번호를 저장함 
		}
		
		while(deque.size() > 1) {
			System.out.print(deque.getFirst()+1 + " "); //터트릴 풍선 출력
			paper = arr[deque.getFirst()]; //안에 있는 번호 확인
			deque.removeFirst(); //풍선 터트리기
//번호가 양수일 경우 : 앞에서 부터 탐색
			if(paper > 0 ) {
				for(int i=0; i<paper-1; i++) {
					deque.addLast(deque.getFirst());
					
					deque.removeFirst();
				}
			}
//번호가 음수일 경우 : 뒤에서 부터 탐색
			else {
				for(int i=0; i<Math.abs(paper); i++) {
					deque.addFirst(deque.getLast());
					
					deque.removeLast();
				}
			}
		}
		System.out.print(deque.getFirst()+1 );
	}
}


/*
배운 것 : java 로 한줄에 n개의 데이터 입력 받기
StringTokenizer로 한줄 입력 받은 다음에 
For문 돌려서 nextTokem으로 받아온다. 

DEQUE 간략한 사용 법
*/