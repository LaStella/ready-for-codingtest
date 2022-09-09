// 기업투자
// https://www.acmicpc.net/problem/2662

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] cost = new int[N+1][M+1];

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int j = 1 ; j <= M ; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        System.out.println("!");

    }
}

/*
입력받은 기업을 1번부터 M번으로 저장합니다.
i만큼의 금액을 가지고 1번부터 j번까지의 기업에 투자하여 최대로 얻는 이익을 dp[i][j]로 표현합니다.
초기에 가지고 있는 금액 n에서 i만큼을 빼면 남은 금액은 n-i금액입니다.
dp[n-j][i-1]

 */