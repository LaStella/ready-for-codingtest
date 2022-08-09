// 앱
// https://www.acmicpc.net/problem/7579

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        // 각 앱당 최대 비용은 100이므로 최대로 나올 수 있는 비용은 n*100입니다.
        int max_cost = n*100;
        int m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n+1];
        int[] cost = new int[n+1];

        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for(int i = 1 ; i <= n ; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
        }

        // dp[i][j] : i번째까지 탐색하였을 때 비용 j로 확보할 수 있는 메모리의 최대 크기
        int[][] dp = new int[n+1][max_cost+1];

        // 최소 비용을 나올 수 없는 큰 값으로 초기화합니다.
        int min_cost = max_cost+1;

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 0 ; j <= max_cost ; j++) {
                // 비용 j가 i번 앱의 비용보다 커야 i번 앱의 메모리를 확보할 수 있습니다.
                if(cost[i] <= j) {
                    // i번 앱의 메모리와 남은 비용으로 구한 메모리의 합을 이전 탐색 최대 메모리 dp[i-1][j]와 비교하여 더 큰값을 저장합니다.
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]] + memory[i]);
                }

                // i번 앱의 메모리를 확보할 수 없다면 현재 확보 가능한 최대 크기의 메모리는 이전(i-1)까지 탐색하여 확보할 수 있는 최대 크기의 메모리가 됩니다.
                else {
                    dp[i][j] = dp[i-1][j];
                }

                // 확보한 메모리의 크기가 필요한 메모리의 크기(m)보다 크다면 비용의 최소값을 저장합니다.
                if(dp[i][j] >= m) {
                    min_cost = Math.min(min_cost, j);
                }
            }
        }

        System.out.println(min_cost);
    }
}