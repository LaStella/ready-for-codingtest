// 욕심쟁이 판다
// https://www.acmicpc.net/problem/1937

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int[][] b;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        // 주어진 대나무 숲(bamboo)을 저장합니다.
        b = new int[n][n];
        for(int r = 0 ; r < n ; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < n ; c++) {
                b[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[r][c] : (r, c)에서 출발하여 최대로 이동하는 칸의 수
        dp = new int[n][n];
        for(int[] arr : dp) {
            Arrays.fill(arr, 1);
        }

        int max = 0;
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                max = Math.max(max, dfs(r, c));
            }
        }

        System.out.println(max);
    }

    private static int dfs(int r, int c) {
        if(dp[r][c] == 1) {
            for(int i = 0 ; i < 4 ; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(!isRange(nr, nc) || b[r][c] < b[nr][nc]) continue;

                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
            }
        }

        return dp[r][c];
    }

    private static boolean isRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}
