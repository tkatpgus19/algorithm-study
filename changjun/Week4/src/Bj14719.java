import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 빗물
public class Bj14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken()); // 높이
        int W = Integer.parseInt(st.nextToken()); // 너비

        int[][] block = new int[H][W]; // 쿨하게 2차원 배열로 만듬. 최대 500*500 이니까.

        // 2차원 입력.
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<W;i++){
            int height = Integer.parseInt(st.nextToken());
            for(int j=0;j<H;j++){
                if(j<height){ // 높이 만큼 1 입력.
                    block[j][i] = 1;
                } else {
                    break;
                }
            }
        }

        int water = 0; // 물 찬 블록 수

        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                // 블록이 비어있으면.
                if(block[i][j] == 0){
                    int wall = 0; // 왼쪽, 오른쪽 벽 체크.
                    int front = j; // 왼쪽 벽 만나러 가는 친구
                    int back = j; // 오른쪽 벽 만나러 가는 친구
                    // 왼쪽으로 가서 벽을 만나면 체크
                    while(front>=0 && block[i][front]!=1){
                        front--;
                        if(front!=-1 && block[i][front] == 1)
                            wall++;
                    }
                    // 오른쪽으로 가서 벽을 만나면 체크
                    while(back<W && block[i][back]!=1){
                        back++;
                        if(back!=W && block[i][back] == 1)
                            wall++;
                    }
                    // 양쪽 벽이 막혀 있으면 물이 찬다!
                    if(wall==2) water++;
                }
            }
        }

        System.out.println(water);

    }
}
