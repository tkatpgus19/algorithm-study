import java.util.*;
import java.io.*;

public class Bj9655 {
	static int N;
	static String dp[] = new String[1004];

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());

		// 초기값 설정
		dp[1] = "SK";
		dp[3] = "SK";

		for (int i = 1; i < 1000; i++) {
			if (dp[i] == "CY") {
				dp[i + 1] = "SK";
				dp[i + 3] = "SK";
			} else if (dp[i] == "SK") {
				dp[i + 1] = "CY";
				dp[i + 3] = "CY";
			}
		}

		System.out.println(dp[N]);

	}

}
