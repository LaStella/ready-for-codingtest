// 가장 큰 정사각형
// https://www.acmicpc.net/problem/1915

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // dp[r][c] : (r, c) 에서 이루어지는 사각형의 최대 길이
        int[][] dp = new int[n][m];

        // 주어진 배열을 저장합니다.
        for(int r = 0 ; r < n ; r++) {
            String s = br.readLine();
            for(int c = 0 ; c < m ; c++) {
                dp[r][c] = s.charAt(c) - '0';
            }
        }

        int max = 0;
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < m ; c++) {
                // 1로 이루어진 정사각형을 구해야하므로 현재 좌표가 1인 경우에만 확인합니다.
                if(dp[r][c] == 1) {
                    // 현재 좌표를 기준으로 왼, 위, 왼위 3지점을 확인해야 하므로 row와 column의 범위는 1 이상이어야 합니다.
                    if(r >= 1 && c >= 1) {
                        // 왼, 위, 왼위 3지점의 각 사각형의 길이 중 최소 길이를 가져옵니다.
                        // 최소 길이에 +1 한 길이가 현재 좌표에서의 사각형의 최대 길이가 됩니다.
                        dp[r][c] = Math.min(dp[r-1][c-1], Math.min(dp[r-1][c], dp[r][c-1])) + 1;
                    }
                    // 최대 사각형의 길이를 저장합니다.
                    max = Math.max(max, dp[r][c]);
                }
            }
        }

        System.out.println(max*max);
    }
}
