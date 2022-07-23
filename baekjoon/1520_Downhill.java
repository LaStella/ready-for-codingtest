// 내리막 길
// https://www.acmicpc.net/problem/1520

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int row;
    private static int col;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[][] map;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        row = Integer.parseInt(s[0]);
        col = Integer.parseInt(s[1]);

        map = new int[row][col];
        // dp[i][j] : (i, j)에서 목적지(row-1, col-1)까지 가는 경로의 수
        // dp[i][j] == 0 : 이전에 방문한 좌표이나 목적지로 가는 경로가 없습니다.
        // dp[i][j] == -1 : 이전에 방문한 적이 없는 좌표로 해당 좌표에서 목적지로 가는 경로를 탐색할 필요가 있습니다.
        dp = new int[row][col];
        // 입력받은 지도를 저장합니다.
        for(int r = 0 ; r < row ; r++) {
            s = br.readLine().split(" ");
            for(int c = 0 ; c < col ; c++) {
                map[r][c] = Integer.parseInt(s[c]);
                // dp배열을 -1로 초기화 해줍니다.
                dp[r][c] = -1;
            }
        }
        
        // (0, 0)에서 시작하므로 (0, 0)에서 탐색한 경로의 수를 출력합니다.
        System.out.println(dfs(0, 0));
    }

    // 주어진 좌표를 기준으로 상, 하, 좌, 우로 탐색하는 함수입니다.
    // 주어진 좌표가 목적지라면 탐색을 중단합니다.
    private static int dfs(int r, int c) {
        // 목적지에 도착 1을 리턴합니다.
        if(r == row-1 && c == col-1) {
            return 1;
        }

        // 목적지에 도착하지 않았으나 현재 좌표가 이전에 방문한 좌표라면 현재 좌표의 경로의 수를 리턴합니다.
        // 경로가 없을 경우 밑에서 0으로 초기화하였으므로 0을 리턴하게됩니다.
        if(dp[r][c] != -1) {
            return dp[r][c];
        }
        else {
            // 경로가 없을 수 있으므로 0으로 초기화 합니다.
            dp[r][c] = 0;
            // 입력받은 좌표에서 상, 하, 좌, 우로 탐색합니다.
            for(int i = 0 ; i < 4 ; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 지도의 범위를 벗어나는 경우는 제외합니다.
                if(!isRange(nr, nc)) {
                    continue;
                }
                // 새로운 좌표가 현재 좌표보다 높이가 낮으면 새로운 좌표를 기준으로 다시 탐색합니다.
                // 탐색으로 나온 리턴값을 더하여 경로의 개수를 계산합니다.
                if(map[nr][nc] < map[r][c]) {
                    dp[r][c] += dfs(nr, nc);
                }
            }
        }

        return dp[r][c];
    }

    // 지도의 범위를 벗어나는지 확인하는 함수입니다.
    private static boolean isRange(int r, int c) {
        return 0 <= r && r < row && 0 <= c && c < col;
    }
}