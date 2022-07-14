// 계단 오르기
// https://www.acmicpc.net/problem/2579

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());

        int[] stair = new int[length+2];
        // 각 계단의 점수를 저장합니다. stair[i] : i번째 계단의 점수
        for(int i = 1 ; i < length+1 ; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        // dp[i][0] : i번째 계단을 1칸 밟고 올라오는 최대 점수
        // dp[i][1] : i번째 계단을 2칸 밟고 올라오는 최대 점수
        int[][] dp = new int[length+1][2];
        dp[1][0] = stair[1];
        dp[1][1] = stair[1];
        for(int i = 2 ; i < length+1 ; i++) {
            // 1칸 밟고 올라오는 최대 점수는 i-1번째 계단을 2칸 밟고 올라온 점수에서 현재 계단의 점수를 더합니다.
            // 계단을 연속해서 3칸 밟을 수 없기 때문에 i-1번째 계단을 올라올땐 2칸을 밟고 올라온 계단이어야만 합니다.
            dp[i][0] = dp[i-1][1] + stair[i];
            // 2칸 밟고 올라오는 최대 점수는 i-2번째 계단의 최대 점수에서 현재 계단의 점수를 더합니다.
            dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + stair[i];
        }
        System.out.println(Math.max(dp[length][0], dp[length][1]));


        /*  두번째 풀이  - dp를 1차원 배열
        // dp[i] : i번째 계단에서 최대 점수
        int[] dp = new int[length+2];
        dp[1] = stair[1];
        dp[2] = stair[1]+stair[2];
        for(int i = 3 ; i < length+1 ; i++) {
            // 연속해서 계단을 3개 밟을 수 없습니다.
            // 현재 위치의 계단으로 오는 방법은 두 가지 입니다.
            // 1. 현재 계단보다 3칸 아래 계단에서 2칸 > 1칸을 밟아올라오는 경우
            // 2. 현재 계단보다 2칸 아래 계단에서 2칸을 밟아올라오는 경우
            dp[i]= Math.max(dp[i-3]+stair[i-1]+stair[i], dp[i-2]+stair[i]);
        }

        System.out.println(dp[length]);
         */
    }
}