// 숫자카드
// https://www.acmicpc.net/problem/2591

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // i번째 숫자를 나타내기 위하여 맨 앞에 0을 붙여 길이를 1 늘립니다.
        String[] s = ("0"+br.readLine()).split("");

        // dp[i] : i번째 수까지 만드는 경우의 수
        int[] dp = new int[s.length+1];
        // 첫번째 수를 만드는 경우의 수는 1개뿐입니다.
        dp[1] = 1;
        // n : 이어지는 두 숫자를 int형으로 변환
        int n = Integer.parseInt(s[1]+s[2]);
        // 1~34까지 카드가 존재하며 이 중 두자리 수는 10~34입니다.
        // 10, 20, 30은 0카드가 존재하지 않으므로 만드는 경우의 수는 1개뿐입니다.
        if (11 <= n && n <= 34 && n != 20 && n != 30) dp[2] = 2;
        else dp[2] = 1;

        // 각 자리수에 따라 만드는 경우의 수를 계산합니다.
        for (int i = 3 ; i <= s.length-1 ; i++) {
            // i-1번째 수와 i번째 수를 붙여 만든 수를 n으로 저장합니다.
            n = Integer.parseInt(s[i-1]+s[i]);
            // 두 자리수의 n이 카드로 존재하는 수인 경우 두 자리수를 뺀 dp[i-2]에 n이 적힌 카드를 더해 만드는 경우의 수가 더해집니다.
            if (10 <= n && n <= 34) {
                dp[i] += dp[i-2];
            }
            // 한 자리수의 카드로 만드는 경우 i번째 수를 뺀 dp[i-1]에 i번째 수가 적힌 카드를 더해 만드는 경우의 수가 더해집니다.
            // i번째 수가 0이라면 0이 적힌 카드가 존재하지 않으므로 만들 수 없습니다.
            if (Integer.parseInt(s[i]) != 0) {
                dp[i] += dp[i-1];
            }
        }
        
        System.out.println(dp[s.length-1]);
    }
}

/*
문제발생1. 0을 고려하지 않아 만들 수 없는 경우의 수가 포함되는 경우가 발생하였습니다.
문제발생2. 문제발생1과 유사하며 10, 20, 30에서의 0을 고려하지 않아 문제가 발생하였습니다. 
 */