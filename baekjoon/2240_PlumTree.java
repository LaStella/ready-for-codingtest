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
            tree_index = Integer.parseInt(br.readLine());

            for (int j = 0 ; j <= w ; j++) {
                // 자두가 움직이지 않은 경우
                if (j == 0) {
                    // 자두의 위치는 1번 나무에서 시작하므로 1번 나무에서 떨어지는 자두만 받을 수 있습니다.
                    dp[i][j] = tree_index == 1 ? dp[i-1][j]+1 : dp[i-1][j];
                }

                else {
                    // 자두의 위치는 움직인 횟수가 홀수면 2번나무, 짝수면 1번나무입니다.
                    jadu_index = j % 2 == 0 ? 1 : 2;

                    // 자두의 위치와 자두가 떨어질 나무의 번호가 같아야 자두를 받을 수 있습니다.
                    if (jadu_index == tree_index) {
                        // 이전 시간(i-1)에 자두의 현재 위치에서 자두를 +1한 경우와 반대 나무에 위치하여 자두를 못받은 경우를 비교하여 더 큰 값을 저장합니다.
                        dp[i][j] = Math.max(dp[i-1][j]+1, dp[i-1][j-1]);
                    }
                    // 자두의 위치와 자두가 떨어질 나무의 번호가 다르다면 반대편에 위치하였을 경우 자두를 받을 수 있습니다.
                    else {
                        // 위와 마찬가지로 이전 시간(i-1)에 자두가 반대 나무에 있을 경우 +1과 자두의 현재 위치에서 자두를 못받은 경우를 비교합니다.   
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]+1);
                    }
                }
            }
        }

        int max = 0;
        // 끝나는 시간은 t시간으므로 자두가 움직인 횟수에 따라 최대값을 찾습니다.
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