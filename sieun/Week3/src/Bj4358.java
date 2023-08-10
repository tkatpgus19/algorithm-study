/*
첫번째 제출 = 성공
배운 것 : hashset 정렬해서 출력하기
*/

import java.util.*;
import java.io.*;

public class Bj4358 {
	public static void main(String[] args) throws IOException {
		Map<String, Integer> treeMap = new HashMap<>();
		BufferedReader bf = new BufferedReader (new InputStreamReader(System.in));
		String name = bf.readLine();
		int count = 1;
		
		while(true) {
			if(treeMap.containsKey(name))
				treeMap.replace(name, treeMap.get(name)+1);
			else
				treeMap.put(name, 1);
			name = bf.readLine();
		
			if(name == null || name.length() == 0) 
				break;
			else
				count ++;
		}
		
		List<String> keyList = new ArrayList<>(treeMap.keySet());
		keyList.sort((s1,s2) -> s1.compareTo(s2));
		for(String key : keyList) {
			
			System.out.printf("%s %.4f\n",key,(double)treeMap.get(key)/count*100);
		}
		
	}
}