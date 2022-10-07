// 같은 탑
// https://www.acmicpc.net/problem/1126

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] block;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        block = new int[N];
        int block_sum = 0;
        for (int i = 0 ; i < N ; i++) {
            block[i] = Integer.parseInt(st.nextToken());
            block_sum += block[i];
        }

        dp = new int[N][block_sum+1];

        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    public static void dfs(int depth, int h) {
        if (dp[depth][h] != -1) return;

    }
}


/*
각 블록에 대해서 선택하는 경우 선택 안하는 경우로 나누어서 계산해야합니다. (dfs)
N은 최대 50이므로 dfs에서 재귀호출시 stack overflow가 발생할 수 있습니다.
따라서 메모이제이션을 사용하여 반복되는 계산을 줄입니다.
dp[i][j] : 0~i번째 블록의 범위에서 두 탑의 높이 차이가 j일 때 가능한 최대 높이


 */