// 문자판
// https://www.acmicpc.net/problem/2186

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static char[][] board;
    static char[] target;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 주어지는 문자판을 저장합니다.
        board = new char[N][M];

        for (int i = 0 ; i < N ; i++) {
            board[i] = br.readLine().toCharArray();
        }// 문자판 저장 끝

        // 주어지는 목표 단어를 저장합니다.
        target = br.readLine().toCharArray();

        // dp[d][r][c] : 목표 단어의 d번째 문자부터 시작하여 (r, c)에서 목표 단어를 만들 수 있는 경우의 수
        dp = new int[target.length][N][M];

        // 모든 값을 나올 수 없는 -1로 초기화합니다.
        for (int i = 0 ; i < target.length ; i++) {
            for (int j = 0 ; j < N ; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int answer = 0;

        for (int r = 0 ; r < N ; r++) {
            for (int c = 0 ; c < M ; c++) {
                answer += dfs(0, r, c);
            }
        }

        System.out.println(answer);
    }

    // dfs알고리즘을 이용하여 주어진 좌표와 depth(목표 단어의 몇번째 문자인지)로 목표 단어를 만들 수 있는 경우의 수를 계산하는 함수입니다.
    public static int dfs(int depth, int r, int c) {
        // 목표 단어의 마지막 문자까지 도달했다면 목표 단어를 만들 수 있으므로 1을 리턴합니다.
        if (depth == target.length-1) {
            return dp[depth][r][c] = 1;
        }

        // 이미 계산한 깊이와 좌표값이라면 계산된 값을 리턴합니다.
        if (dp[depth][r][c] != -1) {
            return dp[depth][r][c];
        }

        // 위의 두 경우가 아니라면 계산을 시작합니다.
        // 처음 만들 수 있는 경우의 수는 0으로 초기화합니다.
        dp[depth][r][c] = 0;

        // 현재 깊이(depth)에 해당하는 문자가 문자판의 좌표(r, c)와 일치한다면 다음 문자(depth+1)를 확인합니다.
        if (target[depth] == board[r][c]) {
            // 문자판의 상, 하, 좌, 우로 이동이 가능하므로 4번 반복합니다.
            for (int i = 0 ; i < 4 ; i++) {
                // 한번에 이동할 수 있는 거리는 1이상 K이하 입니다.
                for (int k = 1 ; k <= K ; k++) {
                    int nr = r + k*dr[i];
                    int nc = c + k*dc[i];
                    // 이동한 좌표가 문자판의 범위를 벗어나는 좌표라면 넘어갑니다.
                    if (!inRange(nr, nc)) continue;
                    // 이동한 좌표의 문자가 다음 문자(depth+1)와 일치하는 경우 다음 문자에서 dfs를 이용해 경우의 수를 계산합니다.
                    if (target[depth+1] == board[nr][nc]) {
                        // 다음 문자에서 dfs를 할 수 있는 모든 경우의 수는 곧 현재 문자에서 시작된 경우의 수 이므로 모두 더해야합니다.
                        dp[depth][r][c] += dfs(depth+1, nr, nc);
                    }
                }
            }
        }

        return dp[depth][r][c];
    }

    // 주어지는 좌표가 광산의 범위를 벗어나는지 확인하는 함수입니다.
    public static boolean inRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}

/*

 */