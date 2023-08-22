import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj2578 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final int SIZE = 5;

        int[][] bingo = new int[SIZE][SIZE];
        int[] call = new int[SIZE*SIZE];

        for(int i=0;i<SIZE;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<SIZE;j++){
                bingo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<SIZE;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<SIZE;j++){
                call[SIZE*i+j] = Integer.parseInt(st.nextToken());
            }
        }

        // 부른다
        for(int i=0;i<SIZE*SIZE;i++){
            int currentCall = call[i];

            // 부른 숫자 지운다
            loop: for(int j=0;j<SIZE;j++){
                for(int k=0;k<SIZE;k++){
                    if(bingo[j][k] == currentCall){
                        bingo[j][k] = 0;
                        break loop;
                    }
                }
            }

            // 빙고확인.
            int[] sumRow = new int[SIZE];
            int[] sumCol = new int[SIZE];
            int sumLeftX = 0;
            int sumRightX = 0;


            for(int j=0;j<SIZE;j++){
                for(int k=0;k<SIZE;k++){
                    sumRow[j] += bingo[j][k];
                    sumCol[k] += bingo[j][k];
                    if(j==k) sumLeftX+= bingo[j][k];
                    if(j+k==4) sumRightX+= bingo[j][k];
                }
            }

            int lineCount = 0;
            for(int j=0;j<SIZE;j++){
                if(sumRow[j] == 0){
                    lineCount++;
                }
                if(sumCol[j] == 0){
                    lineCount++;
                }
            }
            if(sumLeftX == 0) lineCount++;
            if(sumRightX == 0) lineCount++;

            if(lineCount>=3){
                System.out.println(i+1);
                break;
            }
        }
    }
}
