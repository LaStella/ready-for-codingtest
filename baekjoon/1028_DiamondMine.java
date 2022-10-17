// 다이아몬드 광산
// https://www.acmicpc.net/problem/1028

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R;
    static int C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[][] board = new int[R][C];

        // 주어지는 광산의 정보를 저장합니다.
        for (int r = 0 ; r < R ; r++) {
            String[] s = br.readLine().split("");
            for (int c = 0 ; c < C ; c++) {
                board[r][c] = Integer.parseInt(s[c]);
            }
        }// 저장 끝

        // dp[r][c][0] : (r, c)에서 왼쪽위 대각선에서 내려오는 다이아몬드의 길이
        // dp[r][c][1] : (r, c)에서 오른쪽위 대각선에서 내려오는 다이아몬드의 길이
        int[][][] dp = new int[R][C][2];

        // dr,dc : 왼쪽위와 오른쪽 위로 가는 가중치를 나타냅니다.
        int[] dr = {-1, -1};
        int[] dc = {-1, 1};

        int answer = 0;

        for (int r = 0 ; r < R ; r++) {
            for (int c = 0 ; c < C ; c++) {
                // 현재 좌표가 0이라면 다이아가 존재하지 않으므로 넘어갑니다.
                if (board[r][c] == 0) continue;

                // 왼쪽위와 오른쪽위에서 내려오는 다이아몬드의 길이를 저장합니다.
                for (int i = 0 ; i < 2; i++) {
                    // i = 0 일 때 왼쪽위의 좌표를, i = 1일 때 오른쪽위의 좌표를 가져옵니다.
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    // 범위를 벗어나지않는 좌표라면 이어지는 다이아몬드의 길이를 가져옵니다.
                    if (inRange(nr, nc)) dp[r][c][i] = dp[nr][nc][i];
                    // 현재 좌표 또한 1(board[r][c] = 1)이므로 다이아몬드의 길이를 늘려줍니다.
                    dp[r][c][i]++;
                }

                // 왼쪽위와 오른쪽위로 이어지는 다이아몬드의 길이가 현재 최대값 answer 보다 길다면 다이아몬드의 모양이 맞는지 확인합니다.
                if (dp[r][c][0] > answer && dp[r][c][1] > answer) {
                    // 다이아몬드의 크기는 두 길이 중 짧은 길이부터 시작합니다. (긴 길이는 한쪽이 짧아서 다이아몬드의 형상을 만들 수 없습니다.)
                    int size = Math.min(dp[r][c][0], dp[r][c][1]);

                    // 각 대각선으로 이어지는 왼쪽위 꼭지점(dp[r-s][c-s])과 오른쪽위 꼭지점(dp[r-s][c+s])을 찾습니다.
                    // 왼쪽위 꼭지점에서는 오른쪽위 대각선(dp[r-s][c-s][1])으로, 오른쪽위 꼭지점에서는 왼쪽위 대각선(dp[r-s][c+s][0])으로 이어져야 다이아몬드의 형상이 만들어집니다.
                    // 따라서 두 대각선의 길이가 다이아몬드의 크기(s+1)보다 크거나 같아야 합니다.
                    // answer보다 작은 크기의 다이아는 확인할 필요가 없습니다.
                    for (int s = size-1 ; s >= answer ; s--) {
                        if (dp[r-s][c-s][1] >= s+1 && dp[r-s][c+s][0] >= s+1) {
                            answer = Math.max(answer, s+1);
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    // 주어지는 좌표가 광산의 범위를 벗어나는지 확인하는 함수입니다.
    public static boolean inRange (int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}

/*
01100
01011
11111
01111
11111

다이아몬드의 형태는 좌, 우 대각선방향으로 이어집니다.
따라서 2번째 행에서부터 윗행으로 대각선방향에 1이 존재하는지에 따라 다이아의 존재를 알 수 있습니다.
dp[i][j][0] : i행 j열에서 왼쪽 대각선으로 이어지는 다이아
dp[i][j][1] : i행 j열에서 오른쪽 대각선으로 이어지는 다이아

 */