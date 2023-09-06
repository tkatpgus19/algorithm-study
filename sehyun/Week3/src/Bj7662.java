import java.util.*;
import java.io.*;

/**
 * [7662번 이중 우선순위 큐]
 * 문제: 정수들과 명령이 주어질 때, 명령에 따라 최댓값, 최솟값을 제거해가며 마지막 큐에 남게 되는 정수들 중 최솟값과 최댓값을 출력하시오.
 * 해결: (초기 접근) 최댓값, 최솟값 추출을 위해 각각을 찾는 우선순위 큐 2개를 두고, 전체 정수 풀을 관리하는 해시 맵을 따로 두어 사용한다.
 *      (개선 버전) 굳이 우선순위 큐를 사용하지 않아도 맵 자료구조 중 TreeMap 은 입력받는 정수들을 모두 정렬해서 저장하므로
 *                 최댓값, 최솟값을 바로 제거할 수 있다(firstKey(), lastKey() 메소드).
 */
public class Bj7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        TreeMap<Integer, Integer> map = new TreeMap();
        int T = Integer.parseInt(br.readLine());

        for (int t=0; t<T; t++) {
            int k = Integer.parseInt(br.readLine());
            char command;
            int num;
            int targetNum;
            for (int i=0; i<k; i++) {
                st = new StringTokenizer(br.readLine());
                command = st.nextToken().charAt(0);
                num = Integer.parseInt(st.nextToken());

                // 입력일 때
                if (command == 'I') {
                    // getOrDefault() 메소드를 통해 키에 해당하는 값이 있든 없든 바로 값 저장
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else if (!map.isEmpty()) {
                    if (num == -1) {
                        // 가장 작은 값은 TreeMap의 가장 앞쪽에 존재하므로, 키값 추출 및 해당 값 제거
                        targetNum = map.firstKey();
                        // 해당 값이 한 개밖에 없었던 경우면 맵에서 제거해주고, 중복된 숫자가 있을 경우 개수를 줄여준다.
                        if (map.get(targetNum) == 1) {
                            map.remove(targetNum);
                        } else {
                            map.put(targetNum, map.get(targetNum) - 1);
                        }
                    } else {
                        targetNum = map.lastKey();
                        if (map.get(targetNum) == 1) {
                            map.remove(targetNum);
                        } else {
                            map.put(targetNum, map.get(targetNum) - 1);
                        }
                    }
                }
            }
            if (map.isEmpty()) {
                sb.append("EMPTY");
            } else {
                sb.append(map.lastKey()).append(' ').append(map.firstKey());
            }
            sb.append('\n');
            map.clear();
        }
        System.out.println(sb);

        // 메모리: 359572KB, 시간: 2620ms
        // 우선순위 큐 2개 + 해시맵 사용 시
        // 메모리: 474920KB, 시간: 4140ms
    }
}
