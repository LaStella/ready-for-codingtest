// 숫자구슬
// https://www.acmicpc.net/problem/2613

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

        int[] marble = new int[N+1];

        st = new StringTokenizer(br.readLine());

        int left = 0;
        int right = 0;
        for (int i = 1 ; i <= N ; i++) {
            marble[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, marble[i]);
            right += marble[i];
        }

//        int[] sum = new int[N+1];
//
//        for (int i = 1 ; i <= N ; i++) {
//            sum[i] = sum[i-1] + marble[i];
//        }

        int mid;

        int[] p = new int[M+1];

        while(left <= right) {
            mid = (left+right)/2;

            for (int i = 1 ; i <= M ; i++) {
                int sum = 0;
                int index = 0;
                

            }
        }




        System.out.println("!");

    }
}

/*
116
125
134
143
152
161
215
224
...


1 1 6
2개의 구슬을 2개의 그룹으로 나누고, 6개의 구슬을 1개의 그룹으로 만듭니다.
dp[8][3] = Math.min(dp[2][2], sum[8]-sum[2])
dp[8][3] = Math.min(dp[3][2], sum[8]-sum[3])
...

dp[i][j] : i개의 구슬을 j개의 그룹으로 나눌 때 그룹의 최대값
------------------------------------------실패

그룹의 최대값을 이분탐색으로 찾습니다.
구슬을 m개의 그룹으로 나눌 경우 나올수 있는 최대값의 범위는 (가장 큰 구슬 ~ 모든 구슬의 합) 입니다.
m은 n이하의 자연수이므로 1~n까지의 범위를 가지므로 n개의 그룹으로 나눌 경우 가장 큰 구슬이 최대값이 되며 1개의 그룹으로 나눌 경우 모든 구슬의 합이 최대값이 됩니다.
left = 가장 큰 구슬
right = 모든 구슬의 합
mid = (left+right)/2

1번 그룹부터 m번 그룹까지 구슬을 넣습니다.





 */