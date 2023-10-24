import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 *	Bj4358 : 생태학
 *	한 줄에 하나의 나무 종 이름 (<= 30글자)
 *	입력은 최대 10,000개, 나무는 최대 1,000,000그루
 *	이름 사전순 출력, 그 종이 차지하는 비율 소수점 4째 자리 반올림
 *
 *	입력의 끝이 주어지지 않는다.
 */
public class Bj4358 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 빠른 입력을 위한 BufferedReader 선언
		
		String name = new String();	// 나무의 이름을 저장할 변수 선언
		Map<String, Integer> names = new HashMap<String, Integer>();	// 이름(key)과 같은 이름의 개수(value)를 함께 관리하기 위해 Map 사용
		
		int size = 0;	// 전체 입력값의 크기 확인을 위한 size 변수 선언
		
		// BufferedReader는 입력 받을 값이 없을 때 null을 반환. 이를 이용하여 반복해서 입력 받기
		while((name = br.readLine()) != null) {	// 한 줄을 입력 받아 name에 저장하고 그 값이 null이 아니면 반복
			if(names.containsKey(name)) {	// names의 키 값 중 name이 있다면
				names.put(name, names.get(name) + 1);	// 이전의 value(개수)(names.get(name))에 1을 더함. Map은 같은 키 값을 저장하면 마지막으로 저장한 value로 덮어씌움. 
			}
			else names.put(name, 1);	// names의 키 값 중 name이 없다면 value(개수)를 1로 저장
			size++;	// 1개 입력 받았으므로 1 증가
		}
		List<String> keySet = new ArrayList<>(names.keySet());	// 키 값을 기준으로 정렬하기 위해 List 사용. keySet()은 Map의 키 값만 가져옴
		Collections.sort(keySet);	// 오름차순 정렬
		
		for(int i = 0; i < keySet.size(); i++) {	// keySet의 사이즈만큼 반복
			System.out.printf("%s %.4f\n", keySet.get(i), names.get(keySet.get(i)) / (double)size * 100);	// 출력
		}

	}

}
