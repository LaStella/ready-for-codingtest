// 다이아몬드 광산
// https://www.acmicpc.net/problem/1028

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] board = new int[R][C];







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