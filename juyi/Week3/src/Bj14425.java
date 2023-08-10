import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 *	Bj14425 : 문자열 집합
 *	M개의 문자열 중에 몇 개가 집합 S(N개의 문자열)에 포함되어 있는지 찾기
 *
 *	-> 교집합 사용하려고 했으나 중복 처리 x
 */

public class Bj14425 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 BufferedReader 선언
		StringTokenizer st = new StringTokenizer(br.readLine());	// 공백으로 입력값을 나누기 위한 StringTokenizer 선언
		int N = Integer.parseInt(st.nextToken());	// 나누어진 입력 값 N에 저장
		int M = Integer.parseInt(st.nextToken());	// 나누어진 입력 값 M에 저장
		
		Set<String> setS = new HashSet<>();	// 나무의 이름을 저장할 Set 생성
//		Set<String> setM = new HashSet<>();	// 교집합을 이용했을 때 M개의 문자열을 저장하기 위한 Set 생성
		
		for(int i = 0; i < N; i++) {	// N번 반복
			String temp = br.readLine();	// 한 줄 입력 받기
			setS.add(temp);	// 입력값 setS에 저장
		}
		
		int count = 0;	// 포함되어 있는 개수를 저장할 변수 count
		for(int i = 0; i < M; i++) {	// M번 반복
			if(setS.contains(br.readLine())) count++;	// setS에 입력값이 포함되어 있으면 count 증가
		}
		
		// 교집합으로 풀면 중복되는 것을 처리할 수 x
//		Set<String> tmp = new HashSet<>(setS);	// 교집합을 저장할 set 생성
//		for(int i = 0; i < M; i++) {	// M번 반복
//			String temp = br.readLine();	// 한 줄 입력 받기
//			setM.add(temp);	// 입력값 setM에 저장
//		}
//		tmp.retainAll(setM);	// 교집합 처리
		
		System.out.println(count);	// count 출력
		
	}
	
}
