// 구간 나누기
// https://www.acmicpc.net/problem/2228

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

        int min_value = -32768*N-1;
        // 입력으로 주어지는 배열을 저장합니다.
        int[] arr = new int[N+1];
        // sum[i] : 1번째 수부터 i번째 수까지의 합
        int[] sum = new int[N+1];

        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum[i] = sum[i-1] + arr[i];
        }

        // dp[i][j] = i번째 수까지 j개의 구간으로 나눈 최대합
        int[][] dp = new int[N+1][M+1];

        // 1개 이상의 구간을 사용하는 모든 dp값을 나올 수 없는 최저값으로 초기화합니다.
        for (int i = 0 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                dp[i][j] = min_value;
            }
        }

        // 1개의구간 1개의 수는 첫번째 수가 최대합이 됩니다.
        dp[1][1] = arr[1];

        for (int i = 2 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                // 1. i번째 수를 선택하지 않는 경우 i-1번째 수에서의 최대값과 동일합니다.
                dp[i][j] = dp[i-1][j];

                // 2. i번째 수를 선택하는 경우
                // 2-1. 선택하는 구간이 1개라면 누적합이 가장 높은 구간을 선택하면 됩니다.
                // ex. -1, 3, 1, 2, 4, -1 이 주어진다면 누적합이 가장 높은 구간은 3, 1, 2, 4이 됩니다. 
                if (j == 1) {
                    for (int k = 0 ; k <= i-1 ; k++) {
                        dp[i][j] = Math.max(dp[i][j], sum[i]-sum[k]);
                    }
                }

                // 2-2. i번째 수는 j번 구간에 포함되어야 하며 j번 구간의 범위를 알 수 없습니다.
                // 따라서 다음 두 값의 합이 최대가 되는 경우를 찾아야합니다.
                // k번 수(1<= k <= i-2)까지 j-1개의 구간으로 나눈 구간합(dp[k][j-1])
                // k+2번 수부터 i까지의 누적합(sum[i] - sum[k+1])
                // k+2번 수부터 누적합을 구하는 이유는 서로 다른 두 구간은 겹치거나 인접해서는 안되므로 1개의 수를 띄워야합니다. 따라서 k+1번 수가 띄우는 수가 됩니다. 
                else {
                    for (int k = 1 ; k <= i-2 ; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[k][j-1] + (sum[i] - sum[k+1]));
                    }
                }
            }
        }


        System.out.println(dp[N][M]);
    }
}

/*
1개 이상의 수를 연속해서 구간을 만들 수 있습니다.
ex. -1 3 1 2 4 -1
-1  ( 3 )  1  ( 2 4 ) -1
괄호안의 수는 구간이며 구간과 구간사이에는 붙어있지 않도록 간격이 존재해야합니다.
구간의 수는 m으로 주어지며 반드시 m개의 구간이 존재해야합니다.

dp[n][m]을 n개의 수들에서 m개의 구간으로 나누었을때 합의 최대값이라라 한다면
i번째 수에서 i번째 수를 m번 구간에 포함하지 않는다면 최대값은 i-1번째 수까지의 구간합과 동일합니다.
dp[i][m] = dp[i-1][m]

i번째 수를 m번 구간에 포함한다면 m번 구간의 범위를 알 수 없으므로 0부터 i-2번까지의 수(j)가 m-1번 구간까지의 최대값이라고 했을 때
구간 m에 포함된 수는 j+2번째 수부터 i번째 수가 됩니다. j+1번째 수부터 구간 m으로 시작할 수 없는 이유는 구간과 구간은 붙어있을 수 없기때문입니다.
따라서, dp[i][m] = Math.max(dp[i][m], dp[j][m-1] + (sum[i] - sum[j+1])) 이 됩니다. (sum[i] : 0부터 i번까지 수들의 합)
*/