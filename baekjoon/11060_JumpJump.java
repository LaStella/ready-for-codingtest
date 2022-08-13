// 점프 점프
// https://www.acmicpc.net/problem/11060

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] a = new int[n+1];

        for(int i = 1 ; i <= n ; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i] : i번째 칸에 도착하는 최소 점프 횟수
        int[] dp = new int[n+1];
        // 점프의 횟수를 나올 수 없는 큰 횟수로 초기화합니다. (n칸의 미로이므로 n번 점프할 수 없습니다.)
        Arrays.fill(dp, n);
        // 출발 지점에서의 점프 횟수는 0번입니다.
        dp[1] = 0;

        for(int i = 1 ; i < n ; i++) {
            // i번째 칸에 도착할 수 없으므로 제외합니다.
            if(dp[i] == n) continue;
            for(int j = a[i] ; j > 0 ; j--) {
                // 미로의 끝을 넘어가므로 제외합니다.
                if(i+j > n) continue;
                // 발판 이하의 숫자(j)만큼 i에서 이동하여 도착한 i+j번째 칸(dp[i+j]에 최소 점프 횟수를 저장합니다.
                dp[i+j] = Math.min(dp[i+j], dp[i] + 1);
            }
        }

        // 미로의 끝(n)에서 최소 점프 횟수가 초기값일 경우 도착할 수 없으므로 -1을 리턴합니다.
        System.out.println(dp[n] == n ? -1 : dp[n]);
    }
}