// 동전2
// https://www.acmicpc.net/problem/2294

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        int[] coin = new int[n];
        for(int i = 0 ; i < n ; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        // dp[i] : i원을 만드는 최소 동전의 개수
        int[] dp = new int[k+1];
        // k의 값이 최대 1만이므로 가치가 가장 작은 1원 동전으로 개수를 세는 경우 최대 1만이 됩니다.
        // 동전의 최대 개수보다 많은 개수로 초기화를 해줍니다.
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        for(int i = 0 ; i <= k ; i++) {
            for(int j = 0 ; j < n ; j++) {
                // j번째 동전의 가치가 i금액 보다 크다면 j번째 동전으로 구성할 수 없으므로 넘어갑니다.
                if(i < coin[j]) continue;
                // 각 동전들을 사용할 경우 필요한 동전의 개수를 비교하여 최소값을 저장합니다.
                // dp[i-coin[j]]+1 : i금액에서 j번째 동전을 사용하여 구성하므로 dp[i-coin[j]]는 남은 금액을 구성하는 최소의 개수이며, j번째 동전을 사용하므로 1을 더해줍니다.
                dp[i] = Math.min(dp[i], dp[i-coin[j]]+1);
            }
        }

        // 초기값인 10001인 경우 해당 금액을 주어진 동전으로 만들 수 없으므로 -1을 출력합니다.
        System.out.println(dp[k] == 10001 ? -1 : dp[k]);
    }
}
