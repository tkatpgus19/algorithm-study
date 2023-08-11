import java.util.*;
import java.io.*;

/**
 * [4358번 생태학]
 * 문제: 나무 종이 주어질 때, 각 종이 차지하는 비율을 출력하시오.
 * 해결: HashMap 을 이용해 입력받은 종의 개수를 각각 기록하고, 정렬 후 비율을 계산해 같이 출력한다.
 *      TreeMap 을 이용하면 정렬 없이 바로 출력이 가능하지만 입력을 받을 때 마다 정렬이 수행되므로 시간이 오래 걸린다.
 */
public class Bj4358 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Map<String, Float> map = new HashMap<>();

        float total = 0;
        while(true){
            String str = br.readLine();
            if(str == null){
                break;
            } else{
                map.put(str, map.getOrDefault(str, 0.0f)+1.0f);
            }
            total++;
        }

        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        for(String key: list){
            sb.append(key).append(' ').append(String.format("%.4f\n", map.get(key)/total*100));
        }

        System.out.println(sb);

        // 메모리: 103396KB, 시간: 732ms
        // TreeMap 사용시
        // 메모리: 105172KB, 시간: 1156ms
    }
}
