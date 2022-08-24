// 자두나무
// https://www.acmicpc.net/problem/2240

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][] dp = new int[t+1][w+1];

        int tree_index = 0;
        int jadu_index = 0;
        for (int i = 1 ; i <= t ; i++) {
            tree_index = Integer.parseInt(st.nextToken());

            for (int j = 0 ; j <= w ; j++) {
                jadu_index = j % 2 == 0 ? 1 : 2;
                if(jadu_index == tree_index) {
                    dp[i][j] = Math.max(dp[i-1][j]+1, dp[i-1][j-1]);
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]+1);
                }
            }
        }

        int max = 0;
        for (int i = 0 ; i <= w ; i++) {
            max = Math.max(max, dp[t][w]);
        }

        System.out.println(max);
    }
}

/*
두 개의 나무에서 자두가 열리며 시작 위치는 1번 나무입니다.
따라서 홀수번 움직일 경우 자두의 위치는 2번 나무이며 짝수번 움직일 경우 자두의 위치는 1번 나무입니다.

dp[i][j] : i시간에 j번 움직이며 받은 자두의 최대 개수

i시간에 해당하는 나무아래에 자두가 위치해야 자두를 받을 수 있으며 j번 움직인 횟수에 따라 자두의 위치를 알 수 있습니다.
tree_index : i시간에 해당하는 나무의 번호
j%2 == 0 ? location = 1 : location = 2 : 자두의 위치

자두의 현재 위치와 i시간에 떨어지는 나무의 번호가 같다면 i-1시간에 자두가 현재 위치와 동일한 경우+1과 반대 위치인 경우 중 더 큰값을 저장합니다.
dp[i][j] = Math.max(dp[i-1][j]+1, dp[i-1][j-1])

자두의 현재 위치와 i시간에 떨어지는 나무의 번호가 다르다면 i-1시간에 자두가 현재 위치와 동일한 경우와 반대 위치인 경우+1 중 더 큰값을 저장합니다.
dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]+1)



 */