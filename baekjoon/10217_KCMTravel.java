// KCM Travel
// https://www.acmicpc.net/problem/10217

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // dp[i][j] : 1번 도시에서 i번 도시에 j원 비용을 사용하여 도착하는 최소 시간
            int[][] dp = new int[n+1][m+1];

            // ticket[i] : i번 도시에서 출발하는 티켓 리스트
            List<int[]>[] ticket = new ArrayList[n+1];

            for (int i = 1; i <= n ; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
                ticket[i] = new ArrayList<>();
            }

            // 티켓 정보 저장
            for (int i = 1 ; i <= k ; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                ticket[u].add(new int[] {v, c, d});
            }

            // 가장 짧은 소요시간을 출력하므로 시간을 기준으로 우선순위를 정합니다.
            Queue<Node> q = new PriorityQueue<>();
            q.add(new Node(1, 0, 0));
            int answer = -1;

            while(!q.isEmpty()) {
                Node p_node = q.poll();
                int now_index = p_node.index;
                int now_cost = p_node.cost;
                int now_time = p_node.time;

                // 목적지인 n번 도시에 도착한 경우 종료합니다.
                if (now_index == n) {
                    answer = now_time;
                    break;
                }

                // 현재 도시(now_index)에서 출발하는 티켓을 확인합니다.
                for (int[] tk : ticket[now_index]) {
                    // tk[0] : 도착 도시, tk[1] : 비용, tk[2] : 시간
                    int next_index = tk[0];
                    int next_cost = now_cost + tk[1];
                    int next_time = now_time + tk[2];

                    // 다음 도시로 가는 비용이 m원을 초과하는 경우 넘어갑니다.
                    if (next_cost > m) continue;

                    // 같은 비용으로 더 짧은 시간에 도착할 수 있는 경우 넘어갑니다.
                    // next_cost 비용과 같은 비용으로 더 짧은 시간에 도착한다면 계산할 필요가 없습니다.
                    // dp[i][j] : 1번 도시에서 i번 도시에 j원 비용을 사용하여 도착하는 최소 시간
                    if (dp[next_index][next_cost] <= next_time) continue;

                    // next_cost 비용 이상의 금액으로 next_index번 도시에 도착하는 시간을 next_time으로 저장합니다.
                    // 비용이 많이 들며 시간이 적게 드는 경우라면 계산할 필요가 있으나 비용과 시간 모두 많이 드는 경우는 불필요한 계산입니다.
                    for (int j = next_cost ; j <= m ; j++) {
                        // 더 많은 비용으로 더 짧은 시간을 갈 수 있는 경우 이후의 금액은 계산할 필요가 없으므로 중단합니다.
                        if (dp[next_index][j] <= next_time) break;
                        dp[next_index][j] = next_time;
                    }
                    
                    // 다음 도시로 이동한 정보를 큐에 넣습니다.
                    q.add(new Node(next_index, next_time, next_cost));
                }
            }

            if (answer != -1) sb.append(answer);
            else sb.append("Poor KCM");
            
            sb.append("\n");

        }

        System.out.println(sb.toString());

    }
}

class Node implements Comparable<Node> {
    int index;
    int time;
    int cost;

    public Node(int index, int time, int cost) {
        this.index = index;
        this.time = time;
        this.cost = cost;
    }

    // 시간을 기준으로 비교하며 같은 시간이 걸리는 경우 비용을 비교합니다.
    @Override
    public int compareTo(Node other) {
        if (this.time == other.time) {
            return this.cost - other.cost;
        }
        return this.time - other.time;
    }
}

/*
출발 도시 1에서 도착 도시 n까지 걸리는 최소 시간을 구해야합니다.
주어진 티켓 정보를 이용하여 시간을 기준으로 우선순위큐를 이용하는 다익스트라 알고리즘을 사용합니다.
출발 도시 1에서 이동하여 도착하는 다음 도시에 대한 정보를 노드로 표현하면 다음과 같습니다.
new Node (도시 번호, 시간, 비용)

즉, 출발 도시 new Node(1, 0, 0)에서 시작하여 도착 도시 Node(n, ?, ?)을 구하는 것으로 시간에 따른 우선순위큐를 이용합니다.
큐에서 적은 시간순으로 뽑으므로 뽑은 노드에서 연결된 다른 도시으로 비용을 계산합니다.
비용이 주어진 지원 비용(m)을 넘으면 큐에 넣을 수 없으며 지원 비용보다 작은 비용이 든다면 큐에 넣을 수 있습니다.

큐에 넣을때 이미 방문한 도시도 넣을 수 있는 문제가 있습니다.
    > 방문한 도시을 다시 방문하는 경우 시간만 늘어나므로 시간을 기준으로 뽑는 우선순위큐에서는 오류가 발생하지않습니다.

비용과 시간에 따른 문제
1 > 2 > 4 > 5
1 > 3 > 4 > 5
1 > 2 : cost=20 time=10
1 > 3 : cost=10 time=20
2 > 4 == 3 > 4
4 > 5로 가는 비용과 시간에 따라 위의 두 경로 중 최소 시간이 결정됩니다.
이는 4번 도시에 도착했을때의 비용과 시간에 관계가 있습니다.
따라서 각 도시에 도착하는 비용과 시간을 다음과 같이 표현합니다.
dp[i][j] : i번 도시에 j비용으로 도착하는 최소 시간
따라서 dp[4][10] = 20 이며 dp[4][20] = 10이 됩니다.
dp[4][11~19]는 20의 비용을 보다 적으므로 최소 시간은 20이며
dp[4][21~]은 20의 비용보다 많으므로 최소 시간은 10입니다.
 */