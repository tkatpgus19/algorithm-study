import java.io.*;

/**
 * [9655번: 돌 게임]
 * 문제: 턴을 번갈아가며 돌을 1개 혹은 3개씩 가져갈 때 마지막 돌을 가져가는 사람을 구하시오.
 * 해결: 패턴을 구해 정답을 구한다.
 */
public class Bj9655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if(N%2 == 0){
            System.out.println("CY");
        }
        else{
            System.out.println("SK");
        }

        // 메모리: 14448KB, 시간: 128ms
    }

}