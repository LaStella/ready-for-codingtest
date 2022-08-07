// 외판원 순회
// https://www.acmicpc.net/problem/2098

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 주어진 게임판을 저장합니다.
        int[][] w = new int[n][n];

        for(int r = 0 ; r < n ; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < n ; c++) {
                w[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][1<<n];

    }
}

/*
dp[i][status] : i번째 도시를 방문할때 status에 따른 최소값
status : 각 도시의 방문 여부

dfs를 이용하여 각 도시를 방문하며 status가 모든 도시를 방문한 경우 종료

 */