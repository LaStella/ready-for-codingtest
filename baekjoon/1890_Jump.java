// 점프
// https://www.acmicpc.net/problem/1890

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int[] dr = {1, 0};
    private static int[] dc = {0, 1};
    private static int[][] board;
    private static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        // 게임판을 저장합니다.
        board = new int[n][n];

        for(int r = 0 ; r < n ; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < n ; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[r][c] : (r, c)에서 목적지에 도착하는 경로의 수
        // int형 배열을 사용하면 경로의 수가 int형 범위를 초과할 수 있으므로 long형으로 생성합니다.
        dp = new long[n][n];
        for(long[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        System.out.println(dfs(0, 0));
    }

    private static long dfs(int r, int c) {
        // 목적지에 도착 1을 리턴합니다.
        if(r == n-1 && c == n-1) {
            return 1;
        }

        // 목적지에 도착하지 않았으나 현재 좌표가 이전에 방문한 좌표라면 현재 좌표의 경로의 수를 리턴합니다.
        // 경로가 없을 경우 밑에서 0으로 초기화하였으므로 0을 리턴하게됩니다.
        if(dp[r][c] != -1) {
            return dp[r][c];
        }
        else {
            // 목적지에 도착하는 경로가 없을 수 있으므로 0으로 초기화 합니다.
            dp[r][c] = 0;
            // 입력받은 좌표에서 하, 우로 탐색합니다.
            for(int i = 0 ; i < 2 ; i++) {
                // 게임판의 수 만큼 이동할 수 있으므로 이동 방향에 따라 수를 곱해줍니다.
                int nr = r + dr[i] * board[r][c];
                int nc = c + dc[i] * board[r][c];
                // 게임판의 범위를 벗어나는 경우는 제외합니다.
                if(!isRange(nr, nc)) {
                    continue;
                }
                // 새로운 좌표를 기준으로 다시 탐색합니다.
                // 탐색으로 나온 리턴값을 더하여 경로의 개수를 계산합니다.
                dp[r][c] += dfs(nr, nc);
            }
        }

        return dp[r][c];
    }

    // 게임판의 범위를 벗어나는지 확인하는 함수입니다.
    private static boolean isRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}
