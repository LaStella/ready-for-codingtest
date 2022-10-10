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

        // 주어진 N개의 블록을 저장합니다.
        block = new int[N];
        int block_sum = 0;
        for (int i = 0 ; i < N ; i++) {
            block[i] = Integer.parseInt(st.nextToken());
            block_sum += block[i];
        }// 저장 끝

        // dp[i][j] : 0부터 i번 블록의 범위에서 두 탑의 높이차이가 j일 때 가능한 최대 높이
        dp = new int[N][block_sum+1];

        // -2로 초기화시켜줍니다.
        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(dp[i], -2);
        }

        int answer = dfs(0, 0);

        // 두 탑 모두 최소 1개의 블록을 쌓아야 하므로 가능한 최대 높이가 0인 경우 -1을 출력해야합니다.
        System.out.println(answer == 0 ? -1 : answer);
    }

    // depth번 블록차례에서 높이차이가 h일 때 가능한 최대 높이를 구하는 함수입니다.
    public static int dfs(int depth, int h) {
        // 더 이상 선택할 수 있는 블록이 없다면 높이차이가 0인지 확인합니다.
        // 높이차이가 0이어야 두 탑의 높이가 같으며 다른 경우 두 탑의 높이가 다르므로 -1을 리턴합니다.
        if (depth == N) {
            return h == 0 ? 0 : -1;
        }

        // 이미 계산한 값은 저장되어있는 값을 리턴합니다.
        if (dp[depth][h] != -2) return dp[depth][h];

        // depth번 블록을 사용하지 않고 다음 블록으로 넘어가는 경우
        dp[depth][h] = dfs(depth+1, h);

        // depth번 블록을 사용하여 높이차이를 늘리는 경우
        // depth+1번 블록에서 늘어난 높이차이(h + block[depth])를 가지고 쌓을 수 있는 높이를 계산합니다.
        // 어떻게 쌓아도 두 탑의 높이차이가 0이 될 수 없다면 위에서 -1이 리턴되므로 쌓을 수 없는 경우를 걸러낼 수 있습니다.
        int next_height = dfs(depth+1, h + block[depth]);
        if (next_height != -1) {
            dp[depth][h] = Math.max(dp[depth][h], next_height);
        }

        // depth번 블록을 사용하여 높이차이를 줄이는 경우
        // 높이차이(h)와 블록의 크기와 상관없이 두 값의 차이에 절대값이 다음 높이차이가 됩니다. (h = Math.abs(h-block[depth])
        // 단, 두 탑에 똑같이 쌓이는 블록의 높이는 다르므로 높이차이와 블록의 크기를 고려해야합니다.
        // ex1. h = 4, block[depth] = 2인 경우, 높이가 낮은 블록에 2를 더하므로 두 탑이 똑같이 쌓이는 블록은 2(block[depth])가 늘어납니다.
        // ex2. h = 4, block[depth] = 6인 경우, 높이가 낮은 블록에 6을 더하므로 두 탑이 똑같이 쌓이는 블록은 4(h)가 늘어납니다.
        next_height = dfs(depth+1, Math.abs(h - block[depth]));
        if (next_height != -1) {
            if (h > block[depth]) {
                dp[depth][h] = Math.max(dp[depth][h], block[depth] + next_height);
            }
            else {
                dp[depth][h] = Math.max(dp[depth][h], h + next_height);
            }
        }

        return dp[depth][h];
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
    줄이는 블록이 높이차이보다 큰 경우 (h < block[n])와 작은 경우로 나누어서 계산해야합니다.
    높이차이를 줄이는 것은 두 탑 중 낮은 탑에 블록을 쌓는 것으로 두 탑이 모두 가지고 있는 높이가 쌓을 수 있는 높이가 됩니다. (ex. tower1 = 2, tower2 = 4라고 할 때, 높이는 2입니다.)
        ex1. h = 4, block = 2 -> h = 4-2, 2 + dfs(depth+1, h)
        ex2. h = 4, block = 6 -> h = 6-4, 4 + dfs(depth+1, h)
4. 최종적으로 N-1번 블록까지 모두 처리한 경우(depth == N) 높이차이가 0이 아니라면 두 탑은 같은 높이가 아니므로 불가능한 답(-1)입니다.




 */