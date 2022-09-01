// Dance Dance Revolution
// https://www.acmicpc.net/problem/2342

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] inst;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지시의 개수 n과 n개의 지시 방향이 들어있는 inst배열
        n = st.countTokens();
        inst = new int[n];
        for (int i = 1 ; i < n; i++) {
            inst[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j][k] : 왼발이 j, 오른발이 k에 있을 때 남은 지시 사항(i ~ n)을 만족하는데 필요한 최소의 힘
        dp = new int[n+1][5][5];

        System.out.println(dfs(1, 0, 0));
    }

    // index번 지시에서 각 발의 위치에 따라 필요한 최소 힘을 리턴하는 함수입니다.
    public static int dfs(int index, int left, int right) {
        // 마지막 지시사항인 경우 필요한 힘이 더이상 없으므로 0을 리턴합니다.
        if(index == n) return 0;

        // dp에는 최소 힘이 저장되므로 이미 구한 경우 중복계산할 필요가 없습니다.
        if(dp[index][left][right] == -1) {
            // 왼발이 움직이는 경우 필요한 힘은 다음 지시에서 이동한 왼발과 이동하지않은 오른발의 위치에 따른 최소힘과 왼발이 현재 지시에 따라 이동하는데 들어가는 힘의 합입니다.
            int left_move = dfs(index+1, inst[index], right) + getPower(left, inst[index]);
            // 오른발이 움직이는 경우이며 계산 방법은 위와 같습니다.
            int right_move = dfs(index+1, left, inst[index]) + getPower(right, inst[index]);

            // 두 값 중 더 적은 힘이 저장됩니다.
            dp[index][left][right] = Math.min(left_move, right_move);
        }

        return dp[index][left][right];
    }

    // start지점에서 end지점으로 이동하는데 필요한 힘을 리턴하는 함수입니다.
    public static int getPower(int start, int end) {
        // 출발 지점이 0이라면 어느 지점으로 이동하든 2의 힘이 필요합니다.
        if (start == 0) return 2;

        // 두 지점(start, end)의 절대값 차이
        int d = Math.abs(start-end);
        // 같은 지점으로의 이동은 1의 힘이 필요합니다.
        if (d == 0) return 1;
        // 출발 지점의 인접한 지점으로 이동은 3의 힘이 필요합니다.
        if (d == 1 || d == 3) return 3;
        // 출발 지점과 반대 지점으로 이동은 4의 힘이 필요합니다.
        return 4;
    }
}

/*
왼발과 오른발의 위치에 따라 최소의 힘이 달라집니다.
dp[i][j][k]를 i번 지시사항에서 왼발이 j번 오른발이 k번에 위치할때 최소의 힘으로 표현합니다.

위, 왼, 아래, 오른 순으로 1,2,3,4이므로
현재위치와 반대위치의 차이의 크기는 2입니다. (1-3, 2-4)
양 옆일 경우 차이의 크기는 1 또는 3입니다. (1-2, 1-4...)




 */