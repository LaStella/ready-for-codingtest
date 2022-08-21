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

        int[][] map = new int[n][m];

        for (int r = 0 ; r < n ; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0 ; c < m ; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[r][c][0] : (r, c)에서 위에서 아래로 이동한 경우 최대 가치
        // dp[r][c][1] : (r, c)에서 왼쪽에서 오른쪽으로 이동한 경우 최대 가치
        // dp[r][c][2] : (r, c)에서 오른쪽에서 왼쪽으로 이동한 경우 최대 가치
        int[][][] dp = new int[n][m][3];

        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {

            }
        }



    }
}

/*
로봇은 왼쪽, 오른쪽, 아래로만 이동이 가능합니다.
위에서 아래로 이동한 경우 다시 위로갈 수 없으므로 문제가 없으나
왼쪽에서 오른쪽으로 이동한 경우와 오른쪽에서 왼쪽으로 이동한 경우 이미 방문한 지역은 다시 방문할 수 없는 조건이 있으므로 로봇이 이동한 방향을 고려해야합니다.
현재 위치를 (r, c)라고 할 때 (r, c)에서의 최대 가치는 (r-1, c)에서 아래로, (r, c-1)에서 오른쪽으로, (r, c+1)에서 왼쪽으로 이동하는 3가지 경우를 비교해야합니다.

이때 for문으로 사용하면 열(column)이 왼쪽에서 오른쪽으로 이동하는 계산을 하므로 오른쪽에서 왼쪽으로 이동하는 값을 계산하지 못합니다.
따라서 for문을 한번 더 사용하여 열의 양(+)방향과 열의 음(-)방향을 모두 계산해주어야합니다.



 */