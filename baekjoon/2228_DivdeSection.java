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




    }
}

/*
1개 이상의 수를 연속해서 구간을 만들 수 있습니다.
ex. -1 3 1 2 4 -1
-1  ( 3 )  1  ( 2 4 ) -1
괄호안의 수는 구간이며 구간과 구간사이에는 붙어있지 않도록 간격이 존재해야합니다.
구간의 수는 m으로 주어지며 반드시 m개의 구간이 존재해야합니다.

dp[n][m]을 n개의 수들에서 m개의 구간으로 나누었을때 합의 최대값이라라 한다면
i번째 수에서 i번째 수를 구간에 포함하지 않는다면 최대값은 i-1번째 수까지의 구간합과 동일합니다.
dp[i][m] = dp[i-1][m]

i번째 수를 구간에 포함한다면 최대값은 

*/