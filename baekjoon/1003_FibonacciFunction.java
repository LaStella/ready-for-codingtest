// 피보나치 함수
// https://www.acmicpc.net/problem/1003

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());
        br.mark(length*4);
        int[][] answer = new int[length][2];


        // 입력받은 n 중 가장 큰 n을 찾습니다.
        int max_n = 0;
        for(int i = 0 ; i < length ; i++) {
            max_n = Math.max(max_n, Integer.parseInt(br.readLine()));
        }
        br.reset();

        // 가장 큰 n까지 0과 1의 호출 횟수를 계산합니다.
        // dp[n][0or1] : n에 대하여 0 또는 1을 호출한 횟수를 뜻합니다.
        // max_n이 0일 수 있으므로 max_n+2만큼 크기를 지정해줍니다.
        int[][] dp = new int[max_n+2][2];
        dp[0][0] = 1;
        dp[1][1] = 1;
        for(int i = 2 ; i <= max_n ; i++) {
            dp[i][0] = dp[i-1][0]+dp[i-2][0];
            dp[i][1] = dp[i-1][1]+dp[i-2][1];
        }

        for(int i = 0 ; i < length ; i++) {
            int n = Integer.parseInt(br.readLine());

            System.out.println(dp[n][0]+" "+dp[n][1]);
        }
    }
}