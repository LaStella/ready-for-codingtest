// 사탕 가게
// https://www.acmicpc.net/problem/4781

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            // m은 소수점 둘째자리를 가진 실수형태로 주어집니다.
            // 실수 자료형인 double에 100을 곱하여 int형으로 변환할 경우 Rounding Error가 발생하여 정확하지않은 값으로 변환되는 문제가 있습니다.
            // double형 데이터를 BigDecimal형으로 바꾸어 100을 곱한 후 이를 int형으로 변환합니다.
            int m = BigDecimal.valueOf(Double.parseDouble(st.nextToken())).multiply(new BigDecimal(100)).intValue();

            // n과 m의 입력이 0이라면 중단합니다.
            if (n == 0 && m == 0) break;

            // v[i] : i번 사탕의 칼로리
            // c[i] : i번 사탕의 비용
            int[] v = new int[n+1];
            int[] c = new int[n+1];

            for (int i = 1 ; i <= n ; i++) {
                st = new StringTokenizer(br.readLine());
                v[i] = Integer.parseInt(st.nextToken());
                c[i] = BigDecimal.valueOf(Double.parseDouble(st.nextToken())).multiply(new BigDecimal(100)).intValue();
            }

            // dp[i] : i금액으로 만들 수 있는 최대 칼로리
            int[] dp = new int[m+1];

            // 각 사탕에 대해 금액별로 최대 칼로리를 계산합니다.
            for (int i = 1 ; i <= n ; i++) {
                for (int j = 1 ; j <= m ; j++) {
                    // 금액 j가 사탕보다 많거나 같은 경우 사탕을 구매할 수 있습니다.
                    // i번 사탕의 칼로리와 남은 금액으로 만들 수 있는 최대 칼로리의 합이 j금액으로 만들 수 있는 칼로리가 됩니다.
                    // 같은 금액이라도 만들 수 있는 칼로리는 다르므로 최대값을 저장합니다.
                   if (c[i] <= j) {
                        dp[j] = Math.max(dp[j], dp[j-c[i]] + v[i]);
                    }
                }
            }

            System.out.println(dp[m]);
        }
    }
}

/*
배낭문제와 동일하게 풀 수 있습니다.
배낭문제와 다른 점은 물건의 중복 선택이 가능하다는 점입니다.
dp[i] : i금액으로 살 수 있는 사탕의 최대 칼로리
금액을 1부터 소지금액인 m까지 늘리며 칼로리의 최대값을 계산합니다.

1번~n번 사탕까지 존재하므로 2중 for문을 사용하여 사탕과 금액에 대해서 최대 칼로리를 계산합니다.
사탕의 종류 i, 금액을 j라고 한다면 i번 사탕의 비용이 j보다 적은 경우에 구매할 수 있습니다.
이때 남은 금액(j-cost[i])으로 살 수 있는 최대 칼로리와 i번 사탕의 칼로리의 합이 j금액으로 살 수 있는 칼로리가 됩니다.
dp[j] = dp[j-cost[i]] + value[i]

문제발생. 입력으로 주어지는 실수에 대해서 *100을 하여 int형으로 계산하였으나 실수표현의 부정확성때문에 발생하는 오류(Rounding Error or Round-off Error)가 발생하였습니다.
        BigDecimal을 사용하여 문제를 해결하였습니다.
 */