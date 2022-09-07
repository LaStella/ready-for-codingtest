// 타일링
// https://coding-factory.tistory.com/604

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // dp[i] : 길이가 i 인 경우 타일을 채우는 방법의 수
        // 출력되는 방법의 수는 매우 크기때문에 숫자를 문자열 형태로 사용하는 BigInteger클래스를 사용합니다.
        BigInteger[] dp = new BigInteger[251];

        dp[0] = new BigInteger("1");
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("3");

        // 입력으로 주어지는 n은 최대 250이므로 250까지 계산합니다.
        for (int i = 3 ; i <= 250 ; i++) {
            // i길이에 타일을 놓는 방법은 i-1길이에서 타일 1개를 채우는 방법과 i-2길이에서 2길이 만큼 타일을 채우는(3가지) 방법이 있습니다.
            // 2길이 만큼 타일을 채우는 경우는 가로가로, 세로세로, 2x2타일로 채우는 3가지 방법으로 세로세로의 경우 i-1길이에서 채우는 방법과 중복됩니다.
            // 따라서 dp[i] = dp[i-1]*1 + dp[i-2]*(3-1) 이 됩니다.
            dp[i] = dp[i-1].add(dp[i-2].multiply(new BigInteger("2")));
        }

        String line;
        while ((line = br.readLine()) != null) {
            int n = Integer.parseInt(line);

            System.out.println(dp[n]);
        }
    }
}