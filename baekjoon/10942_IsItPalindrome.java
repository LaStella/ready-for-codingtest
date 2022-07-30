// 팰린드롬?
// https://www.acmicpc.net/problem/10942

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 주어진 수열(sequence)을 저장합니다.
        int[] s = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= n ; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j] : i부터 j까지의 수가 팰린드롬 수인지 여부
        boolean[][] dp = new boolean[n+1][n+1];

        // 길이가 1이라면 팰린드롬의 수입니다.
        for(int i = 1 ; i <= n ; i++) {
            dp[i][i] = true;
        }

        // 길이가 2라면 두 수가 같아야 팰린드롬의 수입니다.
        for(int i = 1 ; i <= n-1 ; i++) {
            if(s[i] == s[i+1]) dp[i][i+1] = true;
        }

        // 길이가 3이상이라면 다음의 조건을 만족할 경우 팰린드롬의 수입니다.
        //  1. 양 끝의 수가 같아야 합니다.
        //  2. 양 끝 수를 제외한 나머지 수가 팰린드롬의 수 입니다.
        // ex) 1 2 3 2 1 에서 양 끝은 1로 같으며 2 3 2 는 팰린드롬의 수 입니다.
        // 부분 수열의 길이가 짧은 것부터 팰린드롬의 수인지 판별하여야 긴 수열을 판별할 수 있습니다.
        // 위에서 길이가 2인 수열까지 했으므로 길이가 3인 수열부터 팰린드롬의 수인지 확인합니다.
        // i+w : 부분 수열의 길이
        for(int w = 2 ; w < n ; w++) {
            for(int i = 1 ; i <= n-w ; i++) {
                if (s[i] == s[i + w] && dp[i + 1][i + w - 1]) {
                    dp[i][i + w] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        // 출력을 반복문 안에 쓸 경우 여러번 출력하는 속도로 인하여 시간초과가 발생합니다.
        // 이를 해결하기위해 StringBuilder 객체를 이용하여 출력할 내용을 저장한 후 한번에 출력합니다.
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(dp[start][end]) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.println(sb);
    }
}
