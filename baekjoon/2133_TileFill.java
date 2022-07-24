// 타일 채우기
// https://www.acmicpc.net/problem/2133

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n % 2 == 0) {
            int[] dp = new int[n+1];
            dp[0] = 1;
            dp[2] = 3;
            for(int i = 4 ; i < n+1 ; i = i+2) {
                dp[i] = dp[i-2] * dp[2];
                for(int j = i-2 ; j >= 0 ; j -= 2) {
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