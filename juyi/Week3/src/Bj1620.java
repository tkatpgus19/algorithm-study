import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*	
 * 	Bj1620 : 나는야 포켓몬 마스터 이다솜
 * 	N개의 포켓몬 개수, M개의 문제 개수 (1 <= N, M <= 100,000)
 *	번호 입력 -> 이름 출력
 *	이름 입력 -> 번호 출력
 */

public class Bj1620 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 BufferedReader br 선언
		StringTokenizer st = new StringTokenizer(br.readLine());	// 공백으로 구분하기 위해 StringTokenizer st 선언
		int N = Integer.parseInt(st.nextToken());	// N 입력 받기
		int M = Integer.parseInt(st.nextToken());	// M 입력 받기
		
		Map<Integer, String> mapName = new HashMap<>();	// 번호 기준으로 이름을 저장하기 위해 Map<Integer, String> map 선언
		Map<String, Integer> mapNum = new HashMap<>();	// 이름 기준으로 번호를 저장하기 위해 Map<String, Integer> map 선언
		
		for(int i = 0; i < N; i++) {	// 포켓몬의 개수만큼 반복 N번
			String name = br.readLine();	// 이름 입력 받기
			mapName.put(i + 1, name);	// mapName에 번호와 이름 저장
			mapNum.put(name, i + 1);	// mapNum에 이름과 번호 저장
		}
		
		StringBuilder sb = new StringBuilder();	// 빠른 출력을 위한 StringBuilder sb 선언
		
		for(int i = 0; i < M; i++) {	// 문제 수 만큼 반복 M번
			String question = br.readLine();	// 문제 입력 받기
			
			// 문자열이 숫자가 아니면 숫자형으로 형 변환 했을 때 예외가 발생하는 것을 이용
			try{
				int q = Integer.parseInt(question);	// question을 Integer로 변환. 문자열이 숫자가 아니면 NumberFormatException 발생
				sb.append(mapName.get(q)).append("\n");	// 예외가 발생하지 않았으므로 주어진 문제는 숫자이니까 sb에 mapName의 value값(name)을 저장
			} catch (NumberFormatException e) {		// NumberFormatException 발생. -> question이 문자형이라는 의미
				sb.append(mapNum.get(question)).append("\n");	// 예외가 발생했으므로 주어진 문제는 문자열(이름). -> sb에 mapNum의 value값(번호) 저장
				
				// 아래 코드로 진행 시 시간 초과
//				for(int j = 0; j < mapName.size(); j++) {	// map.size()만큼 반복
//					if(mapName.get(j + 1).equals(question)) {	// question과 동일한 이름을 찾았다면
//						sb.append(j + 1).append("\n");		// sb에 map의 key값(번호)을 저장
//						break;								// 값을 찾았으니 탈출
//					}
//				}
			}
		}
		
		System.out.println(sb);	// sb 출력
	}

}
