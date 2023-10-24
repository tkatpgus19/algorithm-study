import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// ZOAC 3
// 자판 자/모음 꼼꼼이 살펴야 함.
public class Bj20436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] left = {
                {'q','w','e','r','t',0},
                {'a','s','d','f','g',0},
                {'z','x','c','v',0,0},
        };
        int[][] right = {
                {0,'y','u','i','o','p'},
                {0,'h','j','k','l',0},
                {'b','n','m',0,0,0}, // b도 오른손......
        };

        // 시작 위치 입력
        int startLeft = st.nextToken().charAt(0);
        int[] currentLeft = new int[2];
        int startRight =  st.nextToken().charAt(0);
        int[] currentRight = new int[2];

        for(int j=0;j<3;j++){
            for(int k=0;k<6;k++){
                if(left[j][k] == startLeft){
                    currentLeft[0] = j;
                    currentLeft[1] = k;
                }
                if(right[j][k] == startRight){
                    currentRight[0] = j;
                    currentRight[1] = k;
                }
            }
        }


        char[] word = br.readLine().toCharArray();
        int len = word.length;
        int time = 0;


        for(int i=0;i<len;i++){
            char currentChar = word[i]; // 현재 알파벳

            for(int j=0;j<3;j++){
                for(int k=0;k<6;k++){
                    // 왼손이 현재 알파벳과 일치하면
                    if(left[j][k] == currentChar){
                        time+=(Math.abs(currentLeft[0]-j)+Math.abs(currentLeft[1]-k)); // 이동 거리만큼 시간 더해주고
                        // 손 위치 이동시키고
                        currentLeft[0] =j;
                        currentLeft[1] =k;
                        time++; // 꾸욱 (1초)
                    }
                    // 오른손이 현재 알파벳과 일치하면
                    else if(right[j][k] == currentChar){
                        time+=(Math.abs(currentRight[0]-j)+Math.abs(currentRight[1]-k)); // 이동 거리만큼 시간 더해주고
                        // 손 위치 이동시키고
                        currentRight[0] =j;
                        currentRight[1] =k;
                        time++; // 꾸욱 (1초)
                    }
                }
            }
        }

        System.out.println(time); // 총 걸린 시간 출력.
    }
}
