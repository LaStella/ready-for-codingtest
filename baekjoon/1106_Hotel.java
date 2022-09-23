// 호텔
// https://www.acmicpc.net/problem/1106

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] ad = new int[N][2];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            ad[i][0] = Integer.parseInt(st.nextToken());
            ad[i][1] = Integer.parseInt(st.nextToken());
        }

        // dp[i] : i명 늘리기 위해 필요한 최소 비용
        // 문제에서 주어진 조건은 1000명이하의 고객을 구하며 광고로 얻을 수 있는 고객의 수는 100명이하입니다.
        // 따라서 계산시 최대로 구하는 고객은 1100명까지 나올 수 있습니다.
        int[] dp = new int[1101];
        // 모든 최소 비용을 나올 수 없는 최대값으로 초기화합니다.
        Arrays.fill(dp, Integer.MAX_VALUE);

        int answer = -1;

        // 최저 비용을 구해야하므로 비용이 적을수록 우선순위가 높은 큐를 사용합니다.
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0));

        while(!q.isEmpty()) {
            Node p_node = q.poll();
            int now_cost = p_node.cost;
            int now_customer = p_node.customer;

            // 최소 C명이상 고객을 늘렸다면 현재 비용이 최저 비용입니다.
            if (now_customer >= C) {
                answer = now_cost;
                break;
            }

            // 각 광고별 늘어난 비용과 고객의 수를 계산합니다.
            for (int i = 0 ; i < N ; i++) {
                int next_cost = now_cost + ad[i][0];
                int next_customer = now_customer + ad[i][1];

                // 고객의 수를 늘리는데 들어가는 비용이 기존보다 저렴하다면 최저 비용을 변경하고 큐에 새로운 노드를 넣습니다.
                if (dp[next_customer] > next_cost) {
                    dp[next_customer] = next_cost;
                    q.add(new Node(next_cost, next_customer));
                }
            }
        }

        System.out.println(answer);
    }
}

class Node implements Comparable<Node> {
    int cost;
    int customer;
    public Node(int cost, int customer) {
        this.cost = cost;
        this.customer = customer;
    }

    // 비용이 적을수록 우선순위가 높으며 같은 비용이라면 고객의 수가 많을수록 높습니다.
    @Override
    public int compareTo(Node other) {
        if (this.cost == other.cost) {
            return other.customer - this.customer;
        }
        return this.cost - other.cost;
    }
}