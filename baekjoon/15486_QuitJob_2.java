// 퇴사 2
// https://www.acmicpc.net/problem/15486

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 주어진 상담이 걸리는 기간과 상담시 받을 수 있는 금액을 저장합니다.
        // 배열의 크기가 n+2인 이유
        // 1. 배열의 시작을 0이 아닌 1부터 시작하므로 +1
        // 2. 퇴사일이 n+1일 이므로 +1
        // 퇴사일에 해당하는 상담은 상담기간0, 수익0입니다.
        int[] t = new int[n+2];
        int[] p = new int[n+2];


        for (int i = 1 ; i <= n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i] : i번째일에 최대로 받을 수 있는 수익
        int[] dp = new int[n+2];

        for (int i = 1 ; i <= n+1 ; i++) {
            // i번째일에 수익은 전날(i-1)보다 항상 같거나 크므로 전날 수익과 비교하여 큰 값을 저장합니다.
            dp[i] = Math.max(dp[i], dp[i-1]);

            // i번째일에 받을 수 있는 상담의 종료일이 퇴사전일 경우에만 상담을 할 수 있습니다.
            if(i+t[i]-1 <= n) {
                // 상담이 끝나야 금액을 받으므로 끝난 다음날(i+t[i]) 수익을 갱신하며 기존 수익과 비교하여 더 큰값을 저장합니다.
                dp[i+t[i]] = Math.max(dp[i+t[i]], dp[i] + p[i]);
            }
        }

        // n일째까지 상담을 마치고 n+1일째 최대 수익을 출력합니다.
        System.out.println(dp[n+1]);
    }
}