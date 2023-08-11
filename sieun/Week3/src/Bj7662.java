import java.io.*;
import java.util.*;


public class Bj7662 {
    public static void main(String args[]) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        for(int i=0; i<T; i++){
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int N = Integer.parseInt(br.readLine());
            
            for(int j=0; j<N; j++){
            	
                String input[] = br.readLine().split(" ");
                String str = input[0];
                
                int num = Integer.parseInt(input[1]);
                
                //D일때  
                if(str.equals("D")){
                	//비었으면 건뛰  
                    if(tm.isEmpty()) continue;
                    
                    //1일 때 큰수 삭제  
                    else if(num==1){      // 큰 수를 삭제하는 경우
                        int minNum = tm.lastKey();    // 제일 작은 수를 가져오고
                        int cntNum = tm.get(minNum);  // 그 수가 몇개 있는지 확인하여
                        if(cntNum == 1) tm.remove(minNum);  // 1개만 있으면 그냥 지우고
                        else tm.put(minNum, cntNum-1);      // 그보다 많으면 숫자를 하나 줄여준다.
                    }
                    //-1일떄 작은 수 삭
                    else{              
                        int minNum = tm.firstKey();
                        int cntNum = tm.get(minNum);
                        if(cntNum == 1) tm.remove(minNum);
                        else tm.put(minNum, cntNum-1);
                    }
                }
                //I 일 때 -> 입력함 
                else{
                    tm.put(num, tm.getOrDefault(num, 0)+1);   // 현재 수를 입력하고, 없으면 생성, 있으면 cnt늘리기
                }
            }
            
            
            if(tm.isEmpty()) System.out.println("EMPTY");
            else System.out.println(tm.lastKey() + " " + tm.firstKey());
        }

    }
}