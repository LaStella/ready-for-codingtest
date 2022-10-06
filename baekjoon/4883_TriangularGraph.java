// 삼각 그래프
// https://www.acmicpc.net/problem/4883

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N;
        int testcase = 0;

        // 여러개의 테스트 케이스가 주어지며 마지막에는 0이 입력되므로 주어진 입력이 0이 아니면 반복합니다.
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            testcase++;

            // 주어지는 N개 행의 그래프를 저장합니다.
            int[][] graph = new int[N+1][4];

            StringTokenizer st;
            for (int i = 1 ; i <= N ; i++) {
                st = new StringTokenizer(br.readLine());

                graph[i][1] = Integer.parseInt(st.nextToken());
                graph[i][2] = Integer.parseInt(st.nextToken());
                graph[i][3] = Integer.parseInt(st.nextToken());
            }

            // dp[i][j] : 가장 위쪽 가운데 정점에서 i번 행 j번 열로 이동하는 최소 비용
            // 문제에서 주어지는 그래프는 N행 3열 형식이지만 계산을 편리하게 하기 위해 범위를 벗어나는 좌, 우 열(0, 4번 열)을 추가한 5열 형식으로 만듭니다. 
            int[][] dp = new int[N+1][5];

            // 답으로 나올 수 없는 충분히 큰 비용으로 초기화합니다.
            for (int i = 0 ; i <= N ; i++) {
                Arrays.fill(dp[i], 1000000);
            }

            // 첫번째 행의 첫번째 열은 시작할 수 없는 위치이므로 graph[1][1]을 가져올 필요가 없습니다.
            // 첫번째 열의 2번째 열(가운데)에서 시작하므로 dp[1][3]은 2번째 열과 3번째 열의 합이 됩니다.
            dp[1][2] = graph[1][2];
            dp[1][3] = graph[1][2] + graph[1][3];

            // r : row, c : column
            // (1, 2)에서 시작하여 (r, c)로 이동하는 최소 비용을 계산합니다.
            for (int r = 2 ; r <= N ; r++) {
                for (int c = 1 ; c <= 3 ; c++) {
                    // (r, c)로 이동 가능한 출발지는 다음과 같습니다.
                    // 1. (r-1, c-1)
                    // 2. (r-1, c)
                    // 3. (r-1, c+1)
                    // 4. (r, c-1)
                    // 4가지 출발지 중 최소 비용을 찾아 (r, c)에서의 비용을 합하면 (r, c)로 이동하는 최소 비용이 나옵니다.
                    // 1번째 열과 3번째 열에서 범위를 벗어나는 출발지가 존재하지만(ex. c-1 == 0, c+1 == N+1) 범위를 벗어나는 dp값에는 초기화한 값이 저장되어있으므로 계산에 영향을 주지 않습니다. 
                    int min = Math.min(Math.min(dp[r-1][c-1], dp[r-1][c]), Math.min(dp[r-1][c+1], dp[r][c-1]));
                    dp[r][c] = min + graph[r][c];
                }
            }

            System.out.println(testcase+". "+dp[N][2]);
        }
    }
}