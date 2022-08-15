// 사회망 서비스(SNS)
// https://www.acmicpc.net/problem/2533

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Integer>[] edges;
    static int[][] dp;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        // 주어진 간선(엣지)을 저장합니다.
        edges = new ArrayList[n+1];

        for (int i = 1 ; i <= n ; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < n-1 ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 간선을 양방향으로 저장합니다.
            edges[u].add(v);
            edges[v].add(u);
        }

        // dp[i][0] : i번 노드가 얼리 어댑터가 아닌 경우 필요한 최소 얼리 어댑터의 수
        // dp[i][1] : i번 노드가 얼리 어댑터인 경우 필요한 최소 얼리 어댑터의 수
        dp = new int[n+1][2];

        // 각 노드가 얼리 어댑터 인경우 필요한 어댑터 수는 자기 자신 1개가 있으므로 1로 초기화합니다.
        for (int i = 1 ; i <= n ; i++) {
            dp[i][1] = 1;
        }

        visited = new boolean[n+1];

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    // 주어진 i번 노드에서 얼리 어댑터인 경우와 아닌 경우에 필요한 최소 얼리 어댑터의 수를 계산합니다.
    public static void dfs(int i) {
        visited[i] = true;

        for (int child : edges[i]) {
            if(!visited[child]) {
                dfs(child);
                // i번 노드가 얼리 어댑터가 아니라면 모든 자식이 얼리 어댑터이어야 합니다.
                // 따라서 자식이 얼리 어댑터인 경우 필요한 최소 얼리 어댑터의 수들을 더합니다.
                dp[i][0] += dp[child][1];

                // i번 노드가 얼리 어댑터라면 자식의 최소 얼리 어댑터의 수를 더하면 됩니다.
                dp[i][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}

/*
주어진 노드 n개
n개의 노드 중 아무 노드를 루트노드로 설정합니다. 사이클이 없는 경우만 주어지므로 어느 노드를 루트로 하든 결과는 변하지않습니다.
주어진 간선을 이용하여 트리형태가 완성되며 각 노드는 얼리 어댑터가 될 수도 아닐수도 있습니다.
부모노드와 자식노드로 나누어서 보면 부모노드가 얼리어댑터일 경우 자식노드는 얼리 어댑터가 될 수도 아닐 수도있습니다.
반대로 부모노드가 얼리어댑터가 아닐 경우 자식노드들은 반드시 얼리 어댑터이어야합니다.

부모노드가 필요한 얼리 어댑터의 수는 자식노드에서 필요한 얼리 어댑터의 수들의 합입니다.

dp[i][0] : i번 노드가 얼리 어댑터가 아닌 경우 필요한 최소 얼리 어댑터의 수
dp[i][1] : i번 노드가 얼리 어댑터인 경우 필요한 최소 얼리 어댑터의 수

dp[i][0] = dp[i의 자식들][1]의 합
dp[i][1] = dp[i의 자식들][0] dp[i의 자식들][1] 중 작은 값의 합
 */