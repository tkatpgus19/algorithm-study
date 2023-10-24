import java.util.*;
import java.io.*;

/**
 * [21939번 문제 추천 시스템 Version 1]
 * 문제: 문제번호와 난이도가 주어질 때, 특정 연산을 수행할 때 결과를 출력하시오.
 * 해결: 어렵다기보다는 생각을 코드로 옮기기 어려운 문제.
 *      recommend 명령으로 난도+문제번호의 최댓+최솟값을 출력해야 하므로
 *      1. 난도를 "key"값으로, 해당 난도의 문제 번호들을 정렬한 상태로 저장하는 TreeSet이 "value"로 구성된
 *      2. 난도를 기준으로 정렬된 TreeMap을 구현하여 해결한다.
 *      그리고 solved 명령을 통해 문제번호만으로 문제를 제거해야 하므로
 *      단순히 문제번호를 "key"값으로, 난도를 "value"로 갖는 TreeMap(or HashMap) 역시 사용한다.
 */
public class Bj21939 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // <Key, Value> = <난도, 문제번호를 담는 TreeSet>
        // 문제별 난도를 알아오기 위해 단순 Map 역시 선언(dictionary 역할)
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        TreeMap<Integer, Integer> dict = new TreeMap<>();

        int N = Integer.parseInt(br.readLine());

        int num, diff;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            diff = Integer.parseInt(st.nextToken());

            // 해당 난도의 문제가 없으면(null) TreeSet 생성 + 값 추가 후 추가
            if(map.getOrDefault(diff, null) == null) {
                map.put(diff, new TreeSet<>());
            }
            map.get(diff).add(num);
            dict.put(num, diff);

        }
        int M = Integer.parseInt(br.readLine());

        String command;
        int tmp;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            command = st.nextToken();

            if(command.equals("add")) {
                // 처음 입력과 동일한 로직
                num = Integer.parseInt(st.nextToken());
                diff = Integer.parseInt(st.nextToken());
                if(map.getOrDefault(diff, null) == null) {
                    map.put(diff, new TreeSet<>());
                }
                map.get(diff).add(num);
                dict.put(num, diff);
            } else if(command.equals("recommend")) {
                num = Integer.parseInt(st.nextToken());
                if(!map.isEmpty()) {
                    if(num == 1) {
                        // 가장 어려운 난이도의 가장 높은 번호 출력
                        sb.append(map.get(map.lastKey()).last());
                    } else {
                        // 가장 쉬운 난이도의 가장 낮은 번호 출력
                        sb.append(map.get(map.firstKey()).first());
                    }
                    sb.append('\n');
                }
            } else {
                // 단순 dict 맵에서 해당 번호의 난도를 가져와 이 난도에서 해당 번호 문제를 제거
                // 제거 후 해당 난도의 문제들이 없다면(비어있다면) 해당 난도 제거
                num = Integer.parseInt(st.nextToken());
                map.get(dict.get(num)).remove(num);
                if(map.get(dict.get(num)).isEmpty()) {
                    map.remove(dict.get(num));
                }
            }
        }
        System.out.println(sb);

        // 메모리: 52612KB, 시간: 644ms
        // 이상하게 HashMap 사용시 시간이 더 오래걸림(712ms)
    }
}
