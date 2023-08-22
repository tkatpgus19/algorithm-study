import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 기적의 매매법
public class Bj20546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int DAY = 14; // 총 진행일
        int MONEY = Integer.parseInt(st.nextToken()); // 자금

        // 준현이 돈, 주식
        int jhMoney = MONEY;
        int jhStock = 0;
        // 성민이 돈, 주식
        int smMoney = MONEY;
        int smStock = 0;


        int[] stock = new int[DAY]; // 날짜별 주가

        // stock 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<DAY;i++){
            stock[i] = Integer.parseInt(st.nextToken());
        }

        // 준현 -> 여유되는대로 풀매수 법
        for(int day = 0; day<DAY;day++){
            int buy = jhMoney/stock[day];
            jhStock+=buy;
            jhMoney-=buy*stock[day];
        }

        // 성민 -> 흐름따라 풀매수 법
        int incCnt=0;
        int decCnt=0;
        for(int day = 1; day<DAY;day++){
            // 전날과 비교해서 증감따라서 세기
            if(stock[day-1]<stock[day]){
                incCnt++;
                decCnt = 0;
            } else if(stock[day-1] == stock[day]) {
                incCnt = 0;
                decCnt = 0;
            } else{
                decCnt++;
                incCnt = 0;
            }
            // 때가되면 풀 매수/매도
            if(decCnt>=3){
                int buy = smMoney/stock[day];
                smStock+=buy;
                smMoney-=buy*stock[day];
            } else if(incCnt>=3){
                smMoney+=smStock*stock[day];
                smStock = 0;
            }
        }

        // 마지막 날 가격 종합.
        int jhSum = jhMoney + jhStock * stock[DAY-1];
        int smSum = smMoney + smStock * stock[DAY-1];

        if(jhSum>smSum){
            System.out.println("BNP");
        } else if(jhSum == smSum){
            System.out.println("SAMESAME");
        } else{
            System.out.println("TIMING");
        }

    }
}
