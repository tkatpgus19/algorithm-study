import java.io.*;
import java.util.*;

public class Bj20546 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(bf.readLine());
		int money = Integer.parseInt(st.nextToken());
		int arr[] = new int[14];
		int JCount = 0, SCount = 0;
		int JMoney = money, SMoney = money;
		int JFinal = 0, SFinal = 0;
		
		st = new StringTokenizer(bf.readLine());
		
		for(int i=0; i<14; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		
		//준현이 계산하기
		for(int i=0; i<14; i++) {
			if(JMoney >= arr[i]) {
				JCount += JMoney/arr[i];
				JMoney = JMoney - JCount*arr[i];
			}
		}
		
		JFinal = JMoney + JCount*arr[13];
		
		//성민이 계산하기 
		int buyCheck = 0;
		int sellCheck = 0; 
		
		for(int i=1; i<14; i++) {
			if(arr[i-1] > arr[i]) {
				buyCheck += 1;
				sellCheck = 0;
			}
			else if(arr[i] > arr[i-1]) {
				sellCheck += 1;
				buyCheck = 0;
			}
			else {
				buyCheck = 0;
				sellCheck = 0;
			}
			if(buyCheck == 3) {
				if(SMoney >= arr[i]) {
					SCount += SMoney/arr[i];
					SMoney -=  SCount*arr[i];
					buyCheck = 1;
				}
			}
			else if(sellCheck ==3 ) {
				SMoney += SCount * arr[i];
				SCount = 0;
				sellCheck = 1;
			}
			
		}
		
		SFinal = SMoney + SCount*arr[13];
		System.out.println("SFinal : " + SFinal + " JFinal : " + JFinal);
		
		if(SFinal > JFinal)
			System.out.println("TIMING");
		else if(SFinal == JFinal )
			System.out.println("SAMESAME");
		else
			System.out.println("BNP");
	}
}