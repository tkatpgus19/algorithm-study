import java.io.*;
import java.util.*;

/**
 * [20546번 기적의 매매법]
 * 문제: 두가지 방법의 매매법이 주어질 때 최대의 이윤을 남기는 방법을 구하시오.
 * 해결: 주가가 연속으로 상승, 하락 하는지를 flag로 체크후 연산
 */
public class Bj20546{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] price = new int[14];

        // 시드머니, 일일 주가 입력
        int money = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<price.length; i++){
            price[i] = Integer.parseInt(st.nextToken());
        }

        // BNP 방식, TIMING 방식 총자산 계산
        int bnp = bnp(price, money);
        int timing = timing(price, money);

        if(bnp > timing){
            System.out.println("BNP");
        } else if(timing > bnp){
            System.out.println("TIMING");
        } else{
            System.out.println("SAMESAME");
        }

        // 메모리: 14108KB, 시간: 120ms
    }
    
    // BNP 방식
    static int bnp(int[] price, int money){
        int rest = money;
        int cnt = 0;
        for(int i=0; i<price.length; i++){
            // 자본이 남아있으면 추가로 매입
            if(rest >= price[i]){
                cnt += rest/price[i];
                rest -= rest/price[i]*price[i];
            }
        }
        return rest + cnt*price[price.length-1];
    }
    
    // TIMING 방식
    static int timing(int[] price, int money){
        int rest = money;
        int cnt = 0;
        int flag = 0;
        for(int i=0; i<price.length-1; i++){
            // 주가가 하락했을 때
            if(price[i] > price[i+1]){
                if(flag < 0){
                    flag = 0;
                }
                flag++;
                // flag가 3일 때(3일 연속 하락) 추가로 매입
                if(flag > 2 && rest >= price[i+1]){
                    cnt += rest/price[i+1];
                    rest -= rest/price[i+1]*price[i+1];
                }
            } 
            // 주가가 상승했을 때
            else if(price[i] < price[i+1]){
                if(flag > 0){
                    flag = 0;
                }
                flag--;
                // flag가 -3일 때(3일 연속 상승) 가진 주식 모두 매도
                if(flag < -2){
                    rest += cnt*price[i+1];
                    cnt = 0;
                }
            }
        }
        return rest + cnt*price[price.length-1];
    }
}