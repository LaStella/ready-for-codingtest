// 박성원
// https://www.acmicpc.net/problem/1086

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int[][] arr;
    static long[][] dp;
    static int[][] mod;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 집합의 수를 저장합니다.
        // 각 수는 한 문자씩 쪼개어 저장합니다. ex. 101 -> arr[0][0] = 1, arr[0][1] = 0, arr[0][2] = 1
        arr = new int[N][];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        } // 집합의 수 저장 끝

        K = Integer.parseInt(br.readLine());

        // 나올 수 있는 모든 경우의 수(q)를 계산합니다.
        // 집합의 수는 N개 이므로 나올 수 있는 조합은 N! 개입니다.
        long q = 1L;

        for (int i = 2 ; i <= N ; i++) {
            q *= (long)i;
        }

        // dp[i][j] : 앞숫자의 나머지가 i이며 j상태인 경우, K로 나눈 나머지가 0인 경우의 수
        // bitmask : 집합의 있는 N개의 수를 사용한 상태를 나타냅니다. 0 : 사용안함, 1 : 사용함 ex. 10110 : 5개(0~4번)의 수 중 1, 2, 4번 수를 사용한 상태
        dp = new long[K][1<<N];

        // dpMod[i][j] : 앞숫자의 나머지가 i일 때, j를 K로 나눈 나머지
        mod = new int[K][N];

        // 나올 수 없는 값(-1)으로 초기화합니다.
        for (int i = 0 ; i < K ; i++) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(mod[i], -1);
        }

        // N개의 수에서 만든 순열이 K로 나누어 떨어지는 경우의 수(p)를 계산합니다.
        long p = getDp(0, 0, 0);

        if (p == 0) {
            q = 1;
        }
        else {
            long gcd = gcd(p, q);
            p /= gcd;
            q /= gcd;
        }

        System.out.println(p + "/" + q);
    }

    // 앞 숫자의 나머지 m이 주어질 때 n번째 수를 K로 나눈 나머지를 구하는 함수입니다.
    public static int getMod (int m, int n) {
        if (mod[m][n] != -1) {
            return mod[m][n];
        }

        int now_mod = m;

        for (int i = 0 ; i < arr[n].length ; i++) {
            now_mod = (now_mod*10 + arr[n][i]) % K;
        }

        return mod[m][n] = now_mod;
    }

    // 앞 숫자의 나머지 m, 현재 사용한 집합의 수 depth, 집합의 수의 사용 여부 bitmask가 주어질 때, K로 나누어 떨어지는 경우의 수를 구하는 함수입니다.
    public static long getDp (int m, int depth, int bitmask) {
        if (dp[m][bitmask] != -1) {
            return dp[m][bitmask];
        }

        if (depth == N) {
            // 나머지가 0이면 가능한 경우의 수이므로 1을, 나머지가 0이 아니면 0을 저장하고 리턴합니다.
            return dp[m][bitmask] = (m == 0 ? 1l : 0);
        }

        long sum = 0;
        for (int i = 0 ; i < N ; i++) {
            // 이미 사용한 수는 넘어갑니다.
            if (((1<<i) & bitmask) != 0) {
                continue;
            }

            sum += getDp(getMod(m, i), depth+1, (1<<i) | bitmask);
        }

        return dp[m][bitmask] = sum;
    }

    // 두 수의 최대 공약수를 구하는 함수입니다. (호제법 알고리즘)
    public static long gcd(long a, long b) {
        long r = a % b;

        if (r == 0) {
            return b;
        }
        else {
            return gcd(b, r);
        }
    }
}

/*
1.
    우선 주어진 수들을 가지고 하나의 수를 만든다면 너무 큰 수가 나오므로 나머지 계산하는데 어려움이 있습니다.
    크기가 큰 수에서 나머지를 구하는 방법은 다음과 같습니다.
    ex. 5221의 나머지 17 연산의 경우
    1. 5 % 17 = 5 / 다음 문자로 이월
    2. 52 % 17 = 1 / 다음 문자로 이월
    3. 12 % 17 = 12 / 다음 문자로 이월
    4. 121 % 17 = 2 / 다음 문자가 없으므로 나머지는 2

    이전 수에서 구한 나머지를 다음 수에 붙여 나머지를 계산합니다.
    마지막 수까지 과정을 반복하면 최종적으로 남는 나머지를 구할 수 있습니다.

2.
    주어진 수들로 하나의 수를 만드는 순열을 구해야합니다.
    순열을 구하는 방법은 dfs를 이용합니다.
    0부터 N-1번까지의 수들을 비트마스크를 이용하여 사용 여부를 저장합니다.
    1번에서 길이가 긴 수에 대해서 나머지를 구하는 방법이 있으므로 전체의 수가 필요하지 않으며, K로 나눈 나머지만 있으면 됩니다.
    앞의 수를 K로 나눈 나머지 m과 집합의 수를 사용 여부 bitmask에 따라 만들어 질 수 있는 순열 중 K로 나누어 떨어지는 경우를 dp[m][bitmask]에 저장합니다.
    앞의 수를 나눈 나머지에 따라 집합의 수에서 K로 나눈 나머지가 다르며 같은 나머지 계산이 여러번 행해질 수 있는 문제가 있습니다.
    ex. 집합의 수 {1, 2, 3, 322, 521} K = 4일 때 (1 322 521 2 3) 과 (2 521 322 1 3)에서 중복되는 나머지 계산이 존재합니다.
    1 321 % 4
    2 521 % 4 = 1 -> 1 322 % 4
    중복되는 계산을 줄이기 위해 mod[m][n]에 저장합니다.
    mod[m][n] : 앞에서 계산되고 남은 나머지가 m일 때 n번 수와 붙여 K로 나눈 나머지


3.
    마지막으로 p / q 꼴로 출력하는데 이는 gcd알고리즘을 이용하여 최대 공약수로 나누면 됩니다.
 */
