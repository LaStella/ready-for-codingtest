// 게임
// https://www.acmicpc.net/problem/1103

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] board;
    static int[][] dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        // 주어진 직사각형 보드를 저장합니다. H는 -1로 저장합니다.
        for (int r = 0 ; r < n ; r++) {
            int c = 0;
            for (String s : br.readLine().split("")) {
                if(s.equals("H")) board[r][c++] = -1;
                else board[r][c++] = Integer.parseInt(s);
            }
        }

        // dp[r][c] : (r, c)에서 최대로 움직일 수 있는 횟수
        dp = new int[n][m];
        // 모든 값을 -1로 초기화합니다.
        for (int r = 0 ; r < n ; r++) {
            Arrays.fill(dp[r], -1);
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int row, int col) {
        // 주어진 좌표에 따른 dp값이 0인 경우 이전에 방문한 좌표로 이동 횟수가 계산되지 않은 좌표입니다.
        // 즉, 무한번 움직일 수 있는 사이클이 발생하였으므로 -1을 리턴합니다.
        if (dp[row][col] == 0) {
            return -1;
        }

        // 주어진 좌표에 따른 dp값이 초기값(-1)인 경우(첫 방문하는 좌표)에 계산을 합니다.
        // 초기값이 아닌 경우 이전에 이미 계산을 한 좌표로 계산한 dp값을 리턴합니다.
        else if (dp[row][col] == -1) {
            // dp값을 0으로 바꾸어 해당 좌표를 방문하였음을 표시합니다.
            dp[row][col] = 0;

            // 주어진 좌표를 기준으로 위, 아래, 왼쪽, 오른쪽 방향으로 x만큼(board[row][col]) 움직입니다.
            // 이때 각 방향에서 움직일 수 있는 최대 횟수를 max_count에 저장합니다.
            int max_count = 0;

            for (int i = 0 ; i < 4 ; i++) {
                int x = board[row][col];
                int nr = row + x * dr[i];
                int nc = col + x * dc[i];

                // 다음 좌표(nr, nc)가 보드를 벗어나거나 구멍인 경우 계산할 필요가 없으므로 넘어갑니다.
                if (!isRange(nr, nc)) continue;

                // 다음 좌표에서 이동할 수 있는 횟수를 계산합니다.
                int next_count = dfs(nr, nc);
                
                // -1인 경우 무한번 움직일 수 있는 사이클이므로 -1을 리턴합니다.
                if (next_count == -1) return -1;
                
                else max_count = Math.max(max_count, next_count);
            }

            // 현재 좌표에서 움직일 수 있는 최대 횟수는 위, 아래, 왼쪽, 오른쪽 중 가장 많이 움직일 수 있는 횟수에 +1(현재좌표에서 위, 아래, 왼쪽, 오른쪽 중 하나로 이동하는 경우) 한 것입니다.
            dp[row][col] = 1 + max_count;
        }

        return dp[row][col];
    }
    // 주어진 좌표가 범위 안이며 구멍이 아닌지 확인하는 함수입니다.
    public static boolean isRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < m && board[row][col] != -1;
    }
}