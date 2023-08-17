import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/*
 *  Bj12933 : 오리
 *  quack 순서로 우는 오리가 최소 몇 마리인지 찾기
 */
public class Bj12933 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 StringTokenizer 선언
		
		String input = br.readLine();	// 녹음한 소리 입력 받기 input
		List<Character> list = new ArrayList<>();	// 오리가 최소 몇 마리인지 찾기 위한 ArrayList 선언
		
		int count = 0;	// 오리 마리 수
		for(int i = 0; i < input.length(); i++) {	// input의 길이만큼 반복
			switch (input.charAt(i)) {	// input의 하나의 문자 확인
			case 'q':	// q이면
				if(list.isEmpty() || !list.contains('k')) list.add('q');	// 리스트가 비어있으면 한 마리도 울지 않은 것, 또는 리스트에 k 값이 없으면 다 운 오리가 없는 것 -> 리스트에 q 추가
				else {	// 그렇지 않으면 다 운 오리가 있는 것
					for(int j = 0; j < list.size(); j++) {	// 리스트의 크기만큼 반복하면서
						if(list.get(j) == 'k') {	// k 찾기
							list.set(j, 'q');	// 그 위치를 q로 변경
							break;	// 리스트 내에 또 다른 k가 있을 때 그 위치는 변경하면 안되므로 탈출
						}
					}
				}
				break;	// case 탈출
			case 'u':	// u 이면
				if(list.isEmpty() || !list.contains('q')) {	// 리스트가 비어있거나 리스트에 q 값이 없으면 이상하게 우는 오리
					System.out.println(-1);	// -1 출력하고
					return;	// main 종료
				}
				else {	// 그렇지 않으면 q로 운 오리가 있음
					for(int j = 0; j < list.size(); j++) {	// 리스트의 크기만큼 반복하면서
						if(list.get(j) == 'q') {	// q 찾기
							list.set(j, 'u');	// 그 위치를 u로 변경
							break;	// 리스트 내에 또 다른 q가 있을 때 그 위치는 변경하면 안되므로 탈출 
						}
					}
				}
				break;	// case 탈출
			case 'a':	// a 이면
				if(list.isEmpty() || !list.contains('u')) {	// 리스트가 비어있거나 리스트에 u 값이 없으면 이상하게 우는 오리
					System.out.println(-1);	// -1 출력하고
					return;	// main 종료
				}
				else {	// 그렇지 않으면 u로 운 오리가 있음
					for(int j = 0; j < list.size(); j++) {	// 리스트의 크기만큼 반복하면서
						if(list.get(j) == 'u') {	// u 찾기
							list.set(j, 'a');	// 그 위치를 a로 변경
							break;	// 리스트 내에 또 다른 u가 있을 때 그 위치는 변경하면 안되므로 탈출
						}
					}
				}
				break;	// case 탈출
			case 'c':	// c 이면
				if(list.isEmpty() || !list.contains('a')) {	// 리스트가 비어있거나 리스트에 a 값이 없으면 이상하게 우는 오리
					System.out.println(-1);	// -1 출력하고
					return;	// main 종료
				}
				else {	// 그렇지 않으면 a로 운 오리가 있음
					for(int j = 0; j < list.size(); j++) {	// 리스트의 크기만큼 반복하면서
						if(list.get(j) == 'a') {	// a 찾기
							list.set(j, 'c');	// 그 위치를 c로 변경
							break;	// 리스트 내에 또 다른 a가 있을 때 그 위치는 변경하면 안되므로 탈출
						}
					}
				}
				break;	// case 탈출
			case 'k':	// c 이면
				if(list.isEmpty() || !list.contains('c')) {	// 리스트가 비어있거나 리스트에 c 값이 없으면 이상하게 우는 오리
					System.out.println(-1);	// -1 출력하고
					return;	// main 종료
				}
				else {	// 그렇지 않으면 a로 운 오리가 있음
					for(int j = 0; j < list.size(); j++) {	// 리스트의 크기만큼 반복하면서
						if(list.get(j) == 'c') {	// a 찾기
							list.set(j, 'k');	// 그 위치를 c로 변경
							break;	// 리스트 내에 또 다른 a가 있을 때 그 위치는 변경하면 안되므로 탈출
						}
					}
				}
				break;	// case 탈출
			}
		} // input 탐색 종료
		
		for(int i = 0; i < list.size(); i++) {	// 리스트의 크기만큼 반복하면서
			if(list.get(i) == 'k') count++;	// 리스트에 있는 k는 올바르게 운 오리들. count 증가
			else {
				count = -1;	// k가 아닌 값은 울다 말은 오리이므로 count = -1
				break;	// 반복문 탈출
			}
		}
		System.out.println(count);	// count 출력

	}

}
