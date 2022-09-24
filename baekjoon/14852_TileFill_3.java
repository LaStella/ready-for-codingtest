// 타일 채우기 3
// https://www.acmicpc.net/problem/14852

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // dp[i] : 2xi크기의 벽을 타일로 채우는 경우의 수
        int[] dp = new int[N+1];

        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;

        for (int i = 3 ; i <= N ; i++) {
            dp[i] = dp[i-1]*2 + dp[i-2]*3;
        }
    }
}

/*
2xN크기의 벽에 타일을 채우는 경우는 다음과 같습니다.
1. N=1인 경우 채우는 경우의 수는 2입니다.
2. N=2인 경우 채우는 경우의 수는 7입니다.
3. N-1크기의 벽을 채우는 경우의 수에서 2x1크기의 벽을 채우는 경우의 수는 2입니다. dp[N-1]*2
4. N-2크기의 벽을 채우는 경우의 수에서 2x2크기의 벽을 채우는 경우의 수는 3입니다. (7-4(3에서 중복되는 4가지를 제외))
5. N-3크기의 벽을 채우는 경우의 수에서 2x3크기의 벽을 채우는 경우의 수는 2입니다. (3, 4에서 중복되는 경우를 제외) 


 */