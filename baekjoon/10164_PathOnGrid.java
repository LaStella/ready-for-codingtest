// 격자상의 경로
// https://www.acmicpc.net/problem/10164

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
        int k = Integer.parseInt(st.nextToken());

        if(k != 0) {
            // 주어진 k의 열과 행을 계산합니다.
            int k_r = (k-1) / m;
            int k_c = (k-1) % m;

            // k를 반드시 지나야 하므로 k로 가는 경로의 수를 구합니다.
            int route_k = getRouteCount(k_r, k_c);
            // k에서 도착지로 가는 경로의 수를 구합니다.
            int route_end = getRouteCount(n - k_r - 1, m - k_c - 1);

            // 출발지에서 k를 지나 도착지로 가는 경로의 수는 위의 두 경로의 수를 곱한것과 같습니다. 
            System.out.println(route_k * route_end);
        }
        else {
            System.out.println(getRouteCount(n-1, m-1));
        }



    }

    // 크기가 (row+1) * (col+1)인 격자의 출발지(0, 0)에서 도착지(row, col)로 이동하는 최단 경로의 수를 리턴하는 함수입니다.
    public static int getRouteCount (int row, int col) {
        // 주어진 조건은 격자칸의 인접한 오른쪽 또는 아래 칸으로만 이동할 수 있습니다.
        // 따라서 현재 좌표(r, c)로 오는 경로는 (r-1, c)에서 아래로 이동하는 경로와 (r, c-1)에서 오른쪽으로 이동하는 경로의 합입니다.
        int[] dr = {-1, 0};
        int[] dc = {0, -1};
        int[][] dp = new int[row+1][col+1];
        dp[0][0] = 1;

        for(int r = 0 ; r <= row ; r++) {
            for(int c = 0 ; c <= col ; c++) {
                for(int i = 0 ; i < 2 ; i++) {
                    int nr = r+dr[i];
                    int nc = c+dc[i];

                    // 범위를 벗어나는 좌표는 컨티뉴합니다.
                    if(nr < 0 || nc < 0) continue;

                    dp[r][c] += dp[nr][nc];
                }
            }
        }

        return dp[row][col];
    }
}