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


        // 대나무 숲의 각 칸을 방문하며 최대로 이동할 수 있는 칸의 수를 계산합니다.
        int max = 0;
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                max = Math.max(max, dfs(r, c));
            }
        }

        System.out.println(max);
    }

    // 판다가 (r, c)에서 출발하여 최대로 이동 가능한 칸의 수를 계산하는 함수입니다.
    private static int dfs(int r, int c) {
        // dp[r][c]가 초기값인 경우에만 계산을 합니다.
        // 이미 값이 저장되어있다면 이전에 계산을 하였으므로 다시 계산을 할 필요가 없습니다.
        if(dp[r][c] == 0) {
            dp[r][c] = 1;

            for(int i = 0 ; i < 4 ; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 대나무 숲을 벗어나거나 다음으로 이동할 위치의 대나무 양이 현재 위치보다 적다면 넘어갑니다.
                if(!isRange(nr, nc) || b[r][c] >= b[nr][nc]) continue;

                // 현재 위치(r, c)의 상, 하, 좌, 우에서 최대로 이동 가능한 칸의 수를 계산하여 +1을 하면 현재 위치로 오는 칸의 수가 됩니다.
                // 4방향에서 계산한 값 중 가장 큰 값에 +1한 것이 현재 위치에서 이동할 수 있는 최대 칸의 수가 됩니다.
                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
            }
        }

        return dp[r][c];
    }

    // 주어진 좌표가 대나무 숲의 범위를 벗어나는지 확인하는 함수입니다.
    private static boolean isRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}
