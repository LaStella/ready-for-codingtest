// 카드게임
// https://www.acmicpc.net/problem/10835

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] left;
    static int[] right;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        left = new int[n+1];
        right = new int[n+1];

        // 주어진 왼쪽 카드 더미와 오른쪽 카드 더미를 저장합니다.
        String[] left_card = br.readLine().split(" ");
        String[] right_card = br.readLine().split(" ");

        for (int i = 1 ; i <= n ; i++) {
            left[i] = Integer.parseInt(left_card[i-1]);
            right[i] = Integer.parseInt(right_card[i-1]);
        }
        // 저장 끝

        // dp[i][j] : 왼쪽 카드 더미에서 i번째, 오른쪽 카드 더미에서 j번째 일 때 얻을 수 있는 최고 점수
        dp = new int[n+1][n+1];

        // 모든 dp를 -1로 초기화 하여 계산하지 않은 값임을 표시합니다.
        for (int i = 1 ; i <= n ; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(1, 1));
    }

    // 왼쪽 카드 더미에서 left_index번째, 오른쪽 카드 더미에서 right_index번째 일 때 얻을 수 있는 최고 점수를 리턴하는 함수입니다.
    public static int dfs(int left_index, int right_index) {
        // 어느 쪽 더미든 남은 카드가 없다면 종료됩니다. 게임이 종료되므로 얻을 수 있는 점수는 없습니다.
        if (left_index == n+1 || right_index == n+1) {
            return 0;
        }

        // 이미 계산한 카드 더미 순서짝(left_index, right_index)은 다시 계산할 필요가 없으며, 계산하지 않은 카드 순서짝만 계산합니다.
        if (dp[left_index][right_index] == -1) {
            dp[left_index][right_index] = 0;
            // 규칙1에 의해 왼쪽 카드만 버리는 경우 얻을 수 있는 최대 점수입니다.
            int rule_1 = dfs(left_index+1, right_index);
            // 규칙1에 의해 양쪽 카드 모두 버리는 경우 얻을 수 있는 최대 점수입니다.
            int rule_2 = dfs(left_index+1, right_index+1);

            // 두 경우 중 큰 값이 저장됩니다.
            dp[left_index][right_index] = Math.max(rule_1, rule_2);

            // 규칙2에 의해 오른쪽 카드가 왼쪽 카드보다 작아 오른쪽 카드만 버리는 경우 얻을 수 있는 최대 점수입니다.
            if (left[left_index] > right[right_index]) {
                int rule_3 = dfs(left_index, right_index+1) + right[right_index];

                dp[left_index][right_index] = Math.max(dp[left_index][right_index], rule_3);
            }
        }

        return dp[left_index][right_index];
    }
}

/*
게임이 끝나는 순간은 둘 중 하나라도 카드가 더 이상 없어야합니다.
양쪽 모두 n장의 카드가 있으므로 마지막 카드는 n번째로 n+1번째에 접근할 경우 게임이 종료됩니다.

왼쪽 카드와 오른쪽 카드가 주어진 상황에서 규칙에 따라 행할 수 있는 행위는 3가지입니다.
1. 왼쪽 카드를 버립니다. 점수는 얻지 못합니다.
2. 양쪽 카드를 버립니다. 점수는 얻지 못합니다.
3. 오른쪽 카드를 버립니다. 단, 오른쪽 카드가 왼쪽 카드보다 작아야 하며 점수를 오른쪽 카드만큼 얻습니다.

왼쪽 카드가 i번째, 오른쪽 카드가 j번째 일때 얻을 수 있는 최대 점수를 dp[i][j]로 표현합니다.

 */