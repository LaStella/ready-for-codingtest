// 기타리스트
// https://www.acmicpc.net/problem/1495

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] v = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= n ; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }


        int[] dp = new int[n+1];
        dp[0] = s;

        for(int i = 1 ; i <= n ; i++) {
            if(isRange(dp[i-1] + v[i]))
            dp[i] = Math.max()
        }



    }

    public static boolean isRange(int value) {
        return 0 <= value && value <= m;
    }
}

/*

5 10 30

10 5 3 4 20
10+10+5+3-4 = 24
10+10+5-3+4 = 26


 */