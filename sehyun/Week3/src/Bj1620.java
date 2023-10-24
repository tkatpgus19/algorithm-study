import java.io.*;
import java.util.*;

/**
 * [1620번 나는야 포켓몬 마스터 이다솜]
 * 문제: 포켓몬 이름과 번호가 제공되고, 포켓몬 이름 혹은 번호가 주어질 때 각각 포켓몬 번호와 이름을 출력하시오.
 * 해결: 전형적인 map을 사용하는 문제지만, value 값이 주어졌을 때 key 값을 구해야 하는 경우가 있다.
 *      가장 간단한 방법은 key 값과 value 값이 뒤바뀐 set을 만들어 관리하는 것.
 *      이게 가능한 이유는 포켓몬의 번호 역시 고유해서 key 값으로 사용할 수 있기 때문.
 */
public class Bj1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 이름이 key인 map, 번호가 key인 map 각각 선언
        Map<String, String> mapByName = new HashMap<>();
        Map<String, String> mapById = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String str;
        for(int i=1; i<N+1; i++){
            str = br.readLine();
            mapByName.put(str, ""+i);
            mapById.put(""+i, str);
        }

        String tmp;
        for(int i=0; i<M; i++){
            str = br.readLine();
            tmp = mapByName.getOrDefault(str, "Nah");
            if(tmp.equals("Nah")){
                sb.append(mapById.get(str)).append('\n');
            } else{
                sb.append(tmp).append('\n');
            }
        }
        System.out.println(sb);

        // 메모리: 59488KB, 시간: 540ms
    }
}
