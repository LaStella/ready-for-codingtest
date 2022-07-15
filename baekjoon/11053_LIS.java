// 가장 긴 증가하는 부분 수열(Longest Increasing Subsequence)
// https://www.acmicpc.net/problem/11053

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 수열 A를 arr배열에 저장합니다.
        int[] arr = new int[n+1];
        int index = 1;
        for(String s : br.readLine().split(" ")) {
            arr[index++] = Integer.parseInt(s);
        }

        // dp[i] : i번째 수에서 가장 긴 부분 수열의 길이
        int[] dp = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            // 각 i번째 수에 대해서 길이 1의 부분수열(자기 자신)이 반드시 존재합니다.
            dp[i] = 1;
            for(int j = 1 ; j < i ; j++) {
                // 수열 A에서 i번째 수 보다 j번째 수가 작다면 증가하는 수열이 될 수 있습니다.
                if(arr[i] > arr[j]) {
                    // 더 긴 부분 수열의 길이를 저장합니다.
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        // 가장 긴 부분수열의 길이를 탐색합니다.
        int max_length = 0;
        for(int length : dp) {
            max_length = Math.max(max_length, length);
        }
        System.out.println(max_length);
    }
}