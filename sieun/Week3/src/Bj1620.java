/*
첫번째 풀이 - 실패 (시간초과)
아마 value값으로 key 값 찾을때 이중 for문사용해서 그런듯
*/

/*
두번째 풀이 - 성공
map 2개 써서 idx와 value를 바꿔서 key에 저장

*/


import java.io.*;
import java.util.*;


public class Bj1620 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String str;
		String find;
		
		Map<Integer,String> map1 = new HashMap<>();
		Map<String,Integer> map2 = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			str = bf.readLine();
			
			map1.put(i+1,str);
			map2.put(str,i+1);
		}
		
		for(int i =0; i<M; i++) {
			find = bf.readLine();
			if((find.charAt(0)>='A' && find.charAt(0)<='Z') || (find.charAt(0)>='a' && find.charAt(0)<='z')) {
				sb.append(map2.get(find));
				sb.append("\n");
			}
			else {
				sb.append(map1.get(Integer.parseInt(find)));
				sb.append("\n");
			}
			
		}
		System.out.println(sb);
	}
}