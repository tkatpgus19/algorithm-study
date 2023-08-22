 /*첫번째 코드 - 성공 
근데 이렇게 복잡하게 하는게 맞나...?
*/


import java.util.*;
import java.io.*;

public class Bj2578 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int numArr[][] = new int [5][5];
		int callArr[][] = new int [5][5];
		int num = 0;
		int count = 0;
		int findNum = 0;
		int countNum = 0;
		
		for(int i=0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j=0; j<5; j++) {
				numArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j =0; j<5; j++) {
				num = Integer.parseInt(st.nextToken());
				countNum ++;
				find(num,numArr);
				
				for(int k =0; k<5; k++) {
					if (checkWidth(numArr, k)) {
						count ++;
					}
				}
				for(int k =0; k<5; k++) {
					if (checkHeight(numArr, k)) {
						count ++;
					}
				}
				
				if (leftDiagonal(numArr)) {
					count ++;
				}
				
				if (rightDiagonal(numArr)) {
					count ++;
				}
				
				if(findNum == 0 && count >= 3) {
					findNum = countNum;
					break;
				}
				count = 0;
			}
		}
		
		System.out.println(findNum);
		
	}
	
	//찾아서 0으로 바꾸기
	static void find(int findNum, int numArr[][]) {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(findNum == numArr[i][j]) {
					numArr[i][j] = 0;
				}
			}
		}
	}
	
	//가로 한줄 체크
	static boolean checkWidth(int numArr[][], int num) {
		boolean check = true;
		for(int i =0; i<5; i++) {
			if(numArr[num][i]!=0) {
				check = false;
				break;
			}
		}
		return check;
	}
	
	//세로 한줄 체크
	static boolean checkHeight(int numArr[][], int num) {
		boolean check = true;
		for(int i =0; i<5; i++) {
			if(numArr[i][num]!=0) {
				check = false;
				break;
			}
		}
		return check;
	}
	
	//왼쪽 대각선 체크
	static boolean leftDiagonal(int numArr[][]) {
		boolean check = true;
		for(int i=0; i<5; i++) {
			if(numArr[i][i] != 0) {
				check = false;
				break;
			}
		}
		
		return check;
	}
	
	//오른쪽 대각선 체크
	static boolean rightDiagonal(int numArr[][]) {
		boolean check = true;
		for(int i=4; i>=0; i--) {
			if(numArr[i][4-i] != 0) {
				check = false;
				break;
			}
		}
		return check;
	}
}