import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj15787 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int [][] train = new int [N+1][21];
		int count = 0;
		
		for(int i =0; i<M; i++) {
			st = new StringTokenizer(bf.readLine());
			int ins = Integer.parseInt(st.nextToken());
			int x, k;
			
			if(ins == 1 ) {
				x = Integer.parseInt(st.nextToken());
				k = Integer.parseInt(st.nextToken());
				train[x][k] = 1;
			} 
			else if(ins == 2) {
				x = Integer.parseInt(st.nextToken());
				k = Integer.parseInt(st.nextToken());
				train[x][k] = 0;
			}
			
			else if(ins ==3){
				x = Integer.parseInt(st.nextToken());
				for(int j = 20; j>0; j--) {
					train[x][j] = train[x][j-1];	
				}
			}
			else if(ins ==4){
				x = Integer.parseInt(st.nextToken());
				for(int j = 0; j<20; j++) {
					train[x][j] = train[x][j+1];	
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(!isCheck(i, train)){
				count ++;
			}
		}
		
		
		System.out.println(count);

	}

	private static boolean isCheck(int trainNum, int [][] train) {
		
		if(trainNum ==1) return false;
		
		else {
			for(int i = 1; i< trainNum; i++) {
				if(Arrays.equals(train[trainNum], train[i])) {
					return true;
				}
			}
		}
		
		return false;
	}
}