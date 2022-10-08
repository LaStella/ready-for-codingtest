// 같은 탑
// https://www.acmicpc.net/problem/1126

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] block;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        block = new int[N];
        int block_sum = 0;
        for (int i = 0 ; i < N ; i++) {
            block[i] = Integer.parseInt(st.nextToken());
            block_sum += block[i];
        }

        dp = new int[N][block_sum+1];

        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(dp[i], -2);
        }
    }

    public static int dfs(int depth, int h) {
        if (dp[depth][h] != -2) return dp[depth][h];

        if (depth == N) {
            // 차이가 0인 경우
            return h == 0 ? 0 : -1;
        }
        
        // depth번 블록을 사용하지 않고 다음 블록으로 넘어가는 경우
        dp[depth][h] = dfs(depth+1, h);
        
        // depth번 블록을 사용하여 높이차이를 늘리는 경우
        // depth+1번 블록에서 늘어난 높이차이(h + block[depth])를 가지고 쌓을 수 있는 높이를 계산합니다.
        // 어떻게 쌓아도 두 탑의 높이차이가 0이 될 수 없느다면 위에서 -1이 리턴되므로 쌓을 수 없는 경우를 걸러낼 수 있습니다.
        int next_height = dfs(depth+1, h + block[depth]);
        if (next_height != -1) {
            dp[depth][h] = Math.max(dp[depth][h], block[depth] + next_height);
        }
        

    }
}


/*
각 블록에 대해서 선택하는 경우 선택 안하는 경우로 나누어서 계산해야합니다. (dfs)
N은 최대 50이므로 dfs에서 재귀호출시 stack overflow가 발생할 수 있습니다.
따라서 메모이제이션을 사용하여 반복되는 계산을 줄입니다.
dp[i][j] : 0~i번째 블록의 범위에서 두 탑의 높이 차이가 j일 때 가능한 최대 높이

두 탑의 높이 차이를 이용하여 메모이제이션을 사용하는 이유는 두 탑의 높이는 각각 최대 500000이므로 두 탑의 높이를 모두 저장하기에는 메모리가 부족합니다.

임의의 블록(block[n])을 한 탑에 쌓으면 다른 탑과의 높이 차이(h)가 생깁니다.
높이 차이는 다음과 같습니다.
기존의 높이 차이가 h라고 할 때,
1. 블록을 사용하지 않아 다음 블록에서 현재의 높이차이(h)를 유지하는 경우
2. 블록을 사용하여 두 탑의 높이차이를 늘리는 경우
3. 블록을 사용하여 두 탑의 높이차이를 줄이는 경우

0번 블록부터 N-1번 블록까지 가능한 경우를 계산합니다.
ex.
1. n번 블록을 사용하지 않는 경우
    n+1번 블록에서 가능한 최대 높이를 계산합니다.
2. n번 블록을 사용하여 높이차이를 늘리는 경우
    높이차이는 n번블록만큼 늘어나므로 h += block[n]이 됩니다.
3. n번 블록을 사용하여 높이차이를 줄이는 경우
    높이차이는 n번블록만큼 줄어들어 h -= block[n]이 됩니다.
4. 최종적으로 N-1번 블록까지 모두 처리한 경우(depth == N) 높이차이가 0이 아니라면 두 탑은 같은 높이가 아니므로 불가능한 답(-1)입니다.





 */