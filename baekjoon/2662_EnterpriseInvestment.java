// 기업투자
// https://www.acmicpc.net/problem/2662

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 각 기업별 투자 금액 대비 이익을 저장합니다.
        // cost[i][j] : j번 기업에 i금액을 투자하여 얻는 이익
        int[][] cost = new int[N+1][M+1];

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int j = 1 ; j <= M ; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[i][j] : 0부터 j번 기업까지 총 i금액을 투자하였을 때 얻을 수 있는 최대 이익
        int[][] dp = new int[N+1][M+1];
        // invest[i][j] : 총 i금액을 투자하였을 때 j번 기업에 투자한 금액
        int[][] investment = new int[N+1][M+1];

        for (int j = 1 ; j <= M ; j++) {
            for (int i = 0 ; i <= N ; i++) {
                for (int k = N-i ; k >= 0 ; k--) {
                    // 0부터 j-1번까지 기업들에 N-i 금액을 투자하여 얻은 이익과 j번 기업에 i금액을 투자하여 얻는 이익(cost[i][j])의 합을 비교하여 더 큰 값을 저장합니다.
                    // i : j번 기업에 투자하는 금액, k : 0부터 j-1번까지 기업들에 투자하는 금액
                    if (dp[i+k][j] < dp[k][j-1] + cost[i][j]) {
                        // 기존에 저장된 이익보다 더 큰 이익을 낼 수 있다면 새롭게 저장합니다.
                        dp[i+k][j] = dp[k][j-1] + cost[i][j];
                        // 더 큰 이익을 낼 수 있으므로 이때 j번 기업에 투자하는 금액 i를 저장합니다.
                        investment[i+k][j] = i;
                    }
                }
            }
        }

        // 투자 금액 N원으로 M개의 기업에서 낼 수 있는 최대이익을 출력합니다.
        System.out.println(dp[N][M]);

        // 기업별 투자 금액을 저장합니다.
        // enterprise[i] : i+1번 기업에 투자한 금액
        String[] enterprise = new String[M];

        while (M != 0) {
            // M번 기업에 투자한 금액은 investment[N][M]이므로 이를 저장합니다.
            enterprise[M-1] = Integer.toString(investment[N][M]);
            // M번 기업에 투자하고 남은 금액을 다시 N으로 저장합니다.
            N = N-investment[N][M];
            // M번에서부터 1번기업까지 역순으로 추적합니다.
            M--;
        }

        // 각 기업별 투자금액을 출력합니다.
        System.out.println(String.join(" ", enterprise));

    }
}

/*
입력받은 기업을 1번부터 M번으로 저장합니다.
i만큼의 금액을 가지고 1번부터 j번까지의 기업에 투자하여 최대로 얻는 이익을 dp[i][j]로 표현합니다.
초기에 가지고 있는 금액 n에서 i만큼을 빼면 남은 금액은 n-i금액입니다.
dp[i][j]가 최대가 되기 위해서는 j-1번째 기업까지 n-i 이하 금액으로 최대 이익 + j번째 기업에 i금액을 투자한 금액입니다.
즉, j번째 기업에 투자할 금액(i)은 0에서부터 N까지이며 j-1번째까지 기업들에 투자한 금액(k)은 N-i에서 0까지입니다.

문제에서 최대 이익뿐만 아니라 각 기업에 투자한 금액을 출력해야합니다.
최대 이익을 계산하며 투자금액 i+k에서 j번째 기업에는 i금액을 투자합니다.
investment[i][j]는 총 투자 금액이 i일때 j번째 기업에 투자하는 금액이라고 한다면
총 투자금액 N에서 investment[N][M]을 빼면 M번째 기업에서 투자하고 남은 나머지 금액(remain)을 알 수 있습니다.
investment[remain][M-1]은 M-1번째 기업에서 투자하고 남은 금액이 되며 최종적으로 investment[remain][0]이 될 때까지 역추적을 하면 됩니다.


 */