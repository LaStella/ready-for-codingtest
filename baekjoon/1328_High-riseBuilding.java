// 고층 건물
// https://www.acmicpc.net/problem/1699

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // dp[n][l][r] : 건물은 n개 있으며 왼쪽에서 l개 오른쪽에서 r개 보이는 경우의 수
        // int형 배열은 범위를 벗어나는 값이 나오므로 long형을 사용합니다.
        long[][][] dp = new long[N+1][N+1][N+1];

        dp[1][1][1] = 1;
        int mod = 1000000007;

        for (int n = 2 ; n <= N ; n++) {
            for (int l = 1 ; l <= L ; l++) {
                for (int r = 1 ; r <= R ; r++) {
                    dp[n][l][r] = (dp[n-1][l-1][r] + dp[n-1][l][r-1] + (dp[n-1][l][r] * (n-2))) % mod;
                }
            }
        }

        System.out.println(dp[N][L][R]);
    }
}

/*

1~n까지의 길이를 가진 n개의 빌딩이 존재합니다.
양 쪽 끝에서 볼 수 있는 마지막 빌딩은 n의 높이를 가진 빌딩이 됩니다.

1개의 빌딩이 존재할 때 양 끝에서 볼 수 있는 수는 1개이며 순서의 경우의수 또한 1개입니다.
2개의 빌딩이 주어진다면 기존의 1개의 빌딩에서 왼쪽 또는 오른쪽에 배치할 수 있습니다.
n개의 빌딩이 세워지며 왼쪽에서 보이는 빌딩의 수를 l, 오른쪽에서 보이는 빌딩의 수를 r이라고 한다면
dp[n][l][r]을 경우의 수로 정합니다.

dp[1][1][1] = 1

dp[n[[l][r]을 구하는 경우의 수는 3가지로 나뉩니다.
1. 왼쪽에서 보이는 빌딩의 수를 늘리는 것
dp[n-1][l-1][r]에서 왼쪽에서 보이도록 n번째 빌딩을 추가합니다. 이때 n번째 빌딩은 가장 작은 높이인 1이며 기존에 세워진 빌딩의 높이를 1씩 늘린다고 생각합니다.
2. 오른쪽에서 보이는 빌딩의 수를 늘리는 것
dp[n-1][l][r-1]에서 오른쪽에서 보이도록 n번째 빌딩을 추가합니다.
3. 왼쪽에서도 오른쪽에서도 보이지않게 중간에 늘리는 것
dp[n-1][l][r]에서 중간에 넣으므로 l값과 r값은 변동이 없습니다.
건물이 n-1개 있으므로 추가할 수 있는 자리는 n개 이며 여기서 양 끝은 건물이 보이므로 추가할 수 없는 자리입니다.
따라서 건물을 추가하는 방법은 n-2가지 존재합니다.
dp[n-1][l][r]*(n-2)

 */