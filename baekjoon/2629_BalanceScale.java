// 양팔저울
// https://www.acmicpc.net/problem/2629

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static Set<Integer> set = new HashSet<>();
    static int[] w;
    static boolean[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        // 주어진 수열을 저장합니다.
        w = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < n ; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        // 추의 최대 무게는 500이므로 만들 수 있는 최대 무게는 500*n 입니다.
        dp = new boolean[n][500*n+1];

        dfs(0, 0);

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < t ; i++) {
            // 추로 만들 수 있는 무게라면 Y를 아니라면 N을 출력합니다.
            if(set.contains(Integer.parseInt(st.nextToken()))) {
                sb.append("Y ");
            }
            else sb.append("N ");
        }

        System.out.println(sb.toString());
    }

    // 현재 무게 v에 i번째 추로 만들 수 있는 무게를 저장하는 함수입니다.
    public static void dfs(int i, int v) {
        // 마지막 추까지 계산을 하였거나 이미 계산을 한 무게는 리턴합니다.
        if(i == n || dp[i][v]) return;

        // 중복 계산을 막기위해 방문배열을 사용합니다.
        dp[i][v] = true;

        // 현재 무게 v에 i번째 추를 더하거나 빼거나, 더하지도 빼지도 않는 3가지 경우로 나누어 저장합니다.
        set.add(Math.abs(v-w[i]));
        dfs(i+1, Math.abs(v-w[i]));

        set.add(v);
        dfs(i+1, v);

        set.add(v+w[i]);
        dfs(i+1, v+w[i]);
    }
}