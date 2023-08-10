import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 생태학
// 인텔리제이는 EOF 처리가 안 됨.......
public class Bj4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String,Integer> tree = new HashMap<String,Integer>();
        String s = "";

        int cnt = 0;
        while((s = br.readLine())!=null){ // 인텔리제이는 EOF 처리가...
            if(tree.containsKey(s)){
            	// 만약 이미 있는 학명이면 빼서 카운트 1증가 후 다시 넣기 
                int tmp = tree.remove(s);
                tree.put(s,tmp+1);
            } else{
            	// 미등록 학명은 새로 넣기.
                tree.put(s,1);
            }
            cnt++;
        }
        ArrayList<String> al = new ArrayList(tree.keySet());
        Collections.sort(al); // 정렬 
        for(String treeName : al){
            System.out.printf("%s %.4f\n",treeName,(double)tree.get(treeName)/cnt*100);
        }
    }
}
