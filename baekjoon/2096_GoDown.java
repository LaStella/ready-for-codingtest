// 내려가기
// https://www.acmicpc.net/problem/2096

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 주어진 게임판을 저장합니다.
        int[][] board = new int[n][3];

        for(int r = 0 ; r < n ; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < 3 ; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[r][c][0] : (r, c)에서 최고점수
        // dp[r][c][1] : (r, c)에서 최저점수
        int[][][] dp = new int[n][3][2];
        // 첫번째 줄은 게임판의 값으로 초기화합니다.
        for(int c = 0 ; c < 3 ; c++) {
            dp[0][c][0] = board[0][c];
            dp[0][c][1] = board[0][c];
        }

        for(int r = 1 ; r < n ; r++) {
            for(int c = 0 ; c < 3 ; c++) {
                // 현재행을 기준으로 바로 위의 행(r-1)에서 내려옵니다.
                // 위의 행에서 내려올 때는 인접한 열로 내려올 수 있으므로 c를 기준으로 c-1과 c+1로 이동이 가능합니다.
                // 범위를 벗어나는 c-1과 c+1은 계산하지 않습니다.
                
                // c번째 열과 c-1번째 열의 값을 비교합니다.
                int max1 = 0;
                int min1 = Integer.MAX_VALUE;
                if(c-1 >= 0) {
                    max1 = Math.max(dp[r-1][c-1][0] + board[r][c], dp[r-1][c][0] + board[r][c]);
                    min1 = Math.min(dp[r-1][c-1][1] + board[r][c], dp[r-1][c][1] + board[r][c]);
                }

                // c번째 열과 c+1번째 열의 값을 비교합니다.
                int max2 = 0;
                int min2 = Integer.MAX_VALUE;
                if(c+1 <= 2) {
                    max2 = Math.max(dp[r-1][c][0] + board[r][c], dp[r-1][c+1][0] + board[r][c]);
                    min2 = Math.min(dp[r-1][c][1] + board[r][c], dp[r-1][c+1][1] + board[r][c]);
                }

                // 위에서 구한 두 값을 비교하여 저장합니다.
                dp[r][c][0] = Math.max(max1, max2);
                dp[r][c][1] = Math.min(min1, min2);
            }
        }

        int max = Math.max(dp[n-1][0][0], Math.max(dp[n-1][1][0], dp[n-1][2][0]));
        int min = Math.min(dp[n-1][0][1], Math.min(dp[n-1][1][1], dp[n-1][2][1]));

        System.out.println(max+" "+min);
    }
}
