// 로봇 조종하기
// https://www.acmicpc.net/problem/2169

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

        int[][] map = new int[n+1][m+1];

        for (int r = 1 ; r <= n ; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1 ; c <= m ; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[r][c][0] : (r, c)에서 (위->아래) 이동한 경우 최대 가치
        // dp[r][c][1] : (r, c)에서 (왼->오른) 이동한 경우 최대 가치
        // dp[r][c][2] : (r, c)에서 (오른->왼) 이동한 경우 최대 가치
        int[][][] dp = new int[n+1][m+1][3];

        // 탐사 가치는 음수가 존재하므로 모든 가치를 나올 수 없는 최저값으로 초기화합니다.
        for (int r = 1 ; r <= n ; r++) {
            for (int c = 1 ; c <= m ; c++) {
                Arrays.fill(dp[r][c], n*m*(-101));
            }
        }

        // 시작 지점(1, 1)의 가치를 초기화합니다.
        Arrays.fill(dp[1][1], map[1][1]);

        // 같은 행 내에서 왼쪽과 오른쪽 이동을 같이 할 수 없습니다. (이미 탐사한 지역은 탐사하지 않으므로)
        for(int r = 1 ; r <= n ; r++) {
            // 로봇의 (왼->오른)이동과 (위->아래)이동을 계산합니다.
            // (위->아래)이동의 경우 이전 행의 모든 이동 값 중 최대 값을 고르는 것이므로 열의 진행 방향에 영향을 받지 않습니다.
            for(int c = 1 ; c <= m ; c++) {
                // (r-1, c) -> (r, c)
                // (위->아래) 이동하여 (r, c)에 도착하는 최대 가치는 (r-1, c)에서의 최대 가치 + (r, c)의 가치입니다.
                // 첫 번째 행은 윗 행이 존재하지 않으므로 계산하지 않습니다.
                if (r > 1) dp[r][c][0] = Math.max(dp[r-1][c][0], Math.max(dp[r-1][c][1], dp[r-1][c][2])) + map[r][c];
                // (r, c-1) -> (r, c)
                // (왼->오른) 이동하여 (r, c)에 도착하는 최대 가치는 (r, c-1)의 (왼->오른)이동 값(dp[r][c-1][1])과 (위->아래)이동 값(dp[r][c-1][2]) 중 더 큰 값 + (r, c)의 가치입니다.
                // 첫 번째 열은 왼쪽 열이 존재하지 않으므로 계산하지 않습니다.
                if (c > 1) dp[r][c][1] = Math.max(dp[r][c-1][0], dp[r][c-1][1]) + map[r][c];
            }

            for (int c = m-1 ; c >= 1 ; c--) {
                // (r, c+1) -> (r, c)
                // (오른->왼) 이동하여 (r, c)에 도착하는 최대 가치는 (r, c+1)의 (왼->오른)이동 값(dp[r][c+1][1])과 (위->아래)이동 값(dp[r][c+1][2]) 중 더 큰 값 + (r, c)의 가치입니다.
                // 마지막 열은 오른쪽 열이 존재하지 않으므로 계산하지않습니다.
                if (r > 1) dp[r][c][2] = Math.max(dp[r][c+1][0], dp[r][c+1][2]) + map[r][c];
            }
        }

        // 마지막 도착지 (n, m)으로 (왼->오른)이동과 (위->아래)이동만 가능합니다.
        // 따라서 두 이동의 값을 비교한 최대 가치를 출력합니다.
        System.out.println(Math.max(dp[n][m][0], dp[n][m][1]));
    }
}

/*
로봇은 왼쪽, 오른쪽, 아래로만 이동이 가능합니다.
위에서 아래로 이동한 경우 다시 위로갈 수 없으므로 문제가 없으나
왼쪽에서 오른쪽으로 이동한 경우와 오른쪽에서 왼쪽으로 이동한 경우 이미 방문한 지역은 다시 방문할 수 없는 조건이 있으므로 로봇이 이동한 방향을 고려해야합니다.
현재 위치를 (r, c)라고 할 때 (r, c)에서의 최대 가치는 (r-1, c)에서 아래로, (r, c-1)에서 오른쪽으로, (r, c+1)에서 왼쪽으로 이동하는 3가지 경우를 비교해야합니다.

이때 for문으로 사용하면 열(column)이 왼쪽에서 오른쪽으로 이동하는 계산을 하므로 오른쪽에서 왼쪽으로 이동하는 값을 계산하지 못합니다.
따라서 for문을 한번 더 사용하여 열의 양(+)방향과 열의 음(-)방향을 모두 계산해주어야합니다.

첫 행은 윗 행이 없으므로 위에서 내려오는 계산을 할 수 없으며, 마찬가지로 첫 열 또한 왼쪽 열이 없으므로 계산할 수 없습니다.

탐사 지역의 가치는 음수도 존재하므로 dp배열의 모든 값은 나올 수 없는 최저 값으로 초기화해 주어야합니다.
0을 쓰게될 경우 없는 0을 최대치로 계산하는 문제가 있습니다.
 */