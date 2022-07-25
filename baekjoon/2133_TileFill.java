// 타일 채우기
// https://www.acmicpc.net/problem/2133

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 타일의 크기가 1*2 또는 2*1이므로 3*n에서 n이 짝수인 경우만 타일을 빈틈없이 채울 수 있습니다.
        if(n % 2 == 0) {
            // dp[i] : 3*i크기의 타일을 배치하는 경우의 수
            int[] dp = new int[n+1];
            dp[2] = 3;
            // 짝수만 계산하므로 i는 2씩 증가합니다.
            for(int i = 4 ; i <= n ; i += 2) {
                // i칸의 타일을 배치하는 경우의 수는 i-2칸을 배치하는 경우의 수와 남은 2칸을 배치하는 경우의 수를 곱한 결과입니다.
                // 타일을 가로로 배치하는 경우 새롭게 만들 수 있는 타일의 모양이 i가 증가할때마다 2개씩 생기므로 2를 더해줍니다.
                dp[i] = dp[i-2] * dp[2] + 2;
                
                // 새롭게 만들 수 있는 타일이 i크기마다 2개씩 존재하는데 이를 뒤에 배치하는 경우의 수를 더해주어야 합니다.
                // 예를 들어 i=8일때, 6칸으로 만들 수 있는 새로운 형태의 타일을 뒤에 배치한 경우 남은 2칸은 dp[2]이므로 dp[2]*2가 됩니다.
                // 마찬가지로 4칸으로 만들 수 있는 새로운 형태의 타일을 뒤에 배치한 경우 남은 4칸은 dp[4]이므로 dp[4]*2를 더해주어야합니다.
                for(int j = i-4 ; j > 0 ; j -= 2) {
                    dp[i] += (dp[j] * 2);
                }
            }

            System.out.println(dp[n]);
        }
        else {
            System.out.println(0);;
        }

    }
}

/*
3*n에서 n이 짝수여야 타일을 빈틈없이 채울 수 있습니다. (2*1 또는 1*2 크기의 타일로만 채워야 합니다)
dp[2] = 3;
dp[4] = dp[2] * dp[2] + 2 (4칸으로만 만들 수 있는 새로운 타일 유형)
dp[6] = dp[4] * dp[2] + dp[2] * 2(4칸으로만 만들 수 있는 새로운 타일 유형) + 2 (6칸으로만 만들 수 있는 새로운 타일 유형)
dp[8] = dp[6] * dp[2] + dp[4] * 2(4칸으로만 만들 수 있는 새로운 타일 유형)
n이 늘어날때마다 타일을 가로로 배치하여 만들 수 있는 새로운 형태의 타일 모양이 2가지씩 존재합니다. 이하 이 타일을 s[n]이라 칭합니다.
n칸을 배치하는 타일의 개수가 dp[n]이라 할때, dp[n-2]*dp[2]에 n까지 발생하는 s[n]을 뒤에 배치하는 경우를 더해주어야합니다.
예를 들어 dp[6]에서 4칸을 배치하는 경우의 수인 dp[4]에 남은 두칸을 채우는 dp[2]를 곱하여 만들 수 있는 6칸 형태의 경우의 수가 존재합니다.
하지만 이 경우의 수에는 s[4]를 뒤에 배치하는 경우는 존재하지 않습니다. 따라서 이를 더해주어야합니다.
dp[n] = dp[n-2]*dp[2] + dp[n-4]*2 + dp[n-6]*2 + ... + dp[2]*2 + 2

 */