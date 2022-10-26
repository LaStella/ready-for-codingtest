// 성냥개비
// https://www.acmicpc.net/problem/3687

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        int[] n = new int[t+1];
        int max_n = 0;

        // 주어지는 테스트케이스의 n을 저장합니다.
        for (int i = 1 ; i <= t ; i++) {
            n[i] = Integer.parseInt(br.readLine());
            max_n = Math.max(max_n, n[i]);
        }// n 저장 끝

        // dp[i] : 성냥개비가 i개일 때 만들 수 있는 가장 작은 수
        long[] dp = new long[max_n+1];
        
        // 100개의 성냥개비가 주어졌을 때 최소 자리수는 14자리입니다. (100/7 = 14)
        // 따라서 나올 수 없는 충분히 큰 수로 16자리 이상의 수를 채워넣습니다.
        Arrays.fill(dp, Long.parseLong("1000000000000000"));
        
        // 각 성냥개비로 만들 수 있는 가장 작은 수를 초기값으로 저장합니다.
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;

        // 성냥개비가 6개로 0과 6을 만들 수 있으며 0은 앞으로 올 수 없으므로 dp[6] = 6을 저장합니다.
        // 뒤에 붙는 수는 0이 가능하므로 가장 작은 수인 0을 저장합니다. add[6] = "0";
        String[] add = {"0", "0", "1", "7", "4", "2", "0", "8"};

        for (int i = 8 ; i <= max_n ; i++) {
            for (int j = 2 ; j <= 7 ; j++) {
                // 성냥개비는 2~7개로 한 수를 만들 수 있습니다.
                // 각 수를 뒤에 붙이며 남은 성냥개비(i-j)로 만들 수 있는 가장 적은 수와 붙였을 때 최소값을 저장합니다.
                // dp[i] = dp[i-j] + add[j]
                dp[i] = Math.min(dp[i], Long.parseLong(dp[i-j] + add[j]));
            }
        }

        // 출력을 저장합니다.
        StringBuilder sb = new StringBuilder();

        for (int i = 1 ; i <= t ; i++) {
            // 가장 작은 수를 출력합니다.
            sb.append(dp[n[i]]+" ");

            // 가장 큰 수를 출력합니다.
            // 가장 큰 수는 주어진 성냥 n이 짝수인가 홀수인가에 따라 다릅니다.
            // 자리수를 최대한 늘리는 것이 가장 큰 수가 됩니다.
            if (n[i] % 2 == 0) {
                // n이 짝수인 경우 : 1을 만드는데 필요한 성냥은 2개이므로 n/2개의 1을 만들 수 있습니다.
                sb.append("1".repeat(n[i]/2) + "\n");
            }
            else {
                // n이 홀수인 경우 : 7을 만드는데 필요한 성냥은 3개이며, 남은 n-3개의 성냥은 짝수가 되므로 (n-3)/2개의 1을 만들 수 있습니다.
                sb.append("7" + "1".repeat((n[i]-3)/2) + "\n");
            }
        }

        System.out.println(sb.toString());

    }
}

/*
주어진 성냥개비로 만드는 가장 큰 수는 2개의 성냥개비로 1을 만들어 자리수를 늘리는 것입니다.
즉 가장 큰 수는 주어진 성냥개비가 홀수인가 짝수인가에 따라 정해집니다.
주어진 성냥개비가 n개 라고 할 때,
홀수 : 7 + 1.repeat((n-3)/2)
짝수 : 1.repeat(n/2)

가장 작은 수의 경우 dp[n]을 n개의 성냥개비가 주어질 때 만들 수 있는 가장 작은 수라고 합니다.
dp[2] = 1
dp[3] = 7
dp[4] = 4
dp[5] = 2
dp[6] = 6
dp[7] = 8
dp[8] = dp[2]+dp[6] = 10

dp[n] = dp[n-i] + dp[i] 중 최소값

ex. dp[15] = dp[13] + dp[2]
           = dp[12] + dp[3]

dp[6]의 경우 최소는 0 이지만 앞에 0을 사용할 수 없는 조건이 존재합니다.
ex. dp[8]를 구하는 경우 dp[6] + dp[2]와 dp[2] + dp[6]은 각각 61과 10으로 다릅니다.
따라서 뒤에 붙이는 dp를 dp[6] = 0인 새로운 배열을 만듭니다.
dp[6] + add[2]

 */
