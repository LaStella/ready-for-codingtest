// 성냥개비
// https://www.acmicpc.net/problem/3687

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

    }
}

/*
주어진 성냥개비로 만드는 가장 큰 수는 2개의 성냥개비로 1을 만들어 자리수를 늘리는 것입니다.
즉 가장 큰 수는 주어진 성냥개비가 홀수인가 짝수인가에 따라 정해집니다.
주어진 성냥개비가 n개 라고 할 때,
홀수 : 7 + 1.repeat(n-3)
짝수 : 1.repeat(n/2)

가장 작은 수의 경우 dp[n]을 n개의 성냥개비가 주어질 때 만들 수 있는 가장 작은 수라고 합니다.
dp[2] = 1
dp[3] = 7
dp[4] = 4
dp[5] = 2
dp[6] = 0
dp[7] = 8
dp[8] = dp[2]+dp[6] = 10


 */