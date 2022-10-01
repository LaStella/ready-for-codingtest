// 도로 포장
// https://www.acmicpc.net/problem/1162

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
        int K = Integer.parseInt(st.nextToken());

        // 주어지는 M개의 도로 정보를 저장합니다.
        // road[i] : i번 도시에서 이용 가능한 도로들
        ArrayList<Node>[] road = new ArrayList[N+1];
        for (int i = 1 ; i <= N ; i++) {
            road[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 도로는 양방향으로 저장합니다.
            // u > v 로 이동하는 도로이며 비용은 c입니다.
            road[u].add(new Node(v, c));
            road[v].add(new Node(u, c));
        }

        // dp[i][j] : j개의 도로를 포장하였을 때 1번 도시에서 i번 도시로 이동하는 최소 비용
        long[][] dp = new long[N+1][K+1];
        for (int i = 1 ; i <= N ; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        dp[1][0] = 0;

        Queue<Node> q = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
        q.add(new Node(1, 0, 0));

        while (!q.isEmpty()) {
            Node p_node = q.poll();
            int now_index = p_node.index;
            int now_count = p_node.count;
            long now_cost = p_node.cost;

            // now_index 도시로 가는 현재 비용이 최저비용이 아닌 경우 넘어갑니다.
            // 자세한 설명은 맨 밑 주석(문제발생2) 참조
            if (now_cost > dp[now_index][now_count]) {
                continue;
            }

            for (Node next : road[now_index]) {
                // 도로를 포장하는 경우
                // 도로를 이동하는 비용이 없으므로 next.index 도시로 이동하는 비용은 now_index 도시까지 이동해온 비용이 그대로 유지됩니다.
                // next.index 마을로 이동하는 기존 비용보다 저렴한 경우에만 이동합니다.
                // 도로의 포장횟수가 k를 넘지 않아야합니다.
                if (now_count < K && dp[next.index][now_count+1] > dp[now_index][now_count]) {
                    dp[next.index][now_count+1] = dp[now_index][now_count];
                    q.add(new Node(next.index, now_count+1, dp[next.index][now_count+1]));
                }

                // 도로를 포장하지 않는 경우
                // 도로를 이동하는 비용이 더해지므로 next.index 도시로 이동하는 비용은 now_index 도시까지 이동해온 비용에 next.index 도시로 이동하는 도로의 비용이 추가됩니다.
                // next.index 마을로 이동하는 기존 비용보다 저렴한 경우에만 이동합니다.
                if (dp[next.index][now_count] > dp[now_index][now_count] + next.cost) {
                    dp[next.index][now_count] = dp[now_index][now_count] + next.cost;
                    q.add(new Node(next.index, now_count, dp[next.index][now_count]));
                }
            }
        }

        System.out.println(Arrays.stream(dp[N]).min().getAsLong());

    }
}

class Node{
    int index;
    int count;
    long cost;

    public Node(int index, int count, long cost) {
        this.index = index;
        this.count = count;
        this.cost = cost;
    }

    public Node(int index, long cost) {
        this.index = index;
        this.cost = cost;
    }
}

/*
다익스트라와 우선순위큐블 이욯하는 유리해보입니다.
1. 큐에 저장될 Node에는 현재 위치, 현재 포장한 도로의 개수, 현재 비용이 저장됩니다.
2. 우선순위큐를 이용하여 비용이 적게 드는 순으로 뽑히도록 합니다.
3. 현재 위치에서 연결된 도로를 이용하여 다음 위치로 이동합니다.
4. 출발지인 1번 도시에서 목적지인 N번 도시로 이동해야합니다.
5. dp[n][k] : n번 도시로 k개의 도로가 포장되었을때 가는 최소 비용이라고 정의합니다.
6. 이동한 정보를 도로를 포장하는 경우와 포장하지 않은 경우로 나누어서 큐에 넣습니다.
7. 도로를 포장하는 경우 도로의 비용이 0으로 변하므로 현재 위치에서 다음 위치로 이동하는 비용이 저렴해지는 경우에만 가능합니다.
dp[next_index][now_count+1] > dp[now_index][now_count]

문제발생1.
    Hashmap[]으로 도로를 저장하는 경우 문제 발생
    ArrayList[]에 도로를 저장하여 해결하였습니다.

문제발생2.
    시간이 초과되는 문제
    for문 안에서 도로를 포장하는 경우와 포장하지 않는 경우 2가지 모두 고려하여 큐에 넣도록 하였으며 이때 최소값을 계속해서 갱신하게됩니다.
    문제는 for문에서 도로를 사용하는 순서에 따라 최소값이 뒤에서 갱신되는 경우입니다.
    ex. 1 > 3 > 5 > 6(목적지)로 가는 경우와 1 > 4 > 5 > 6로 가는 경우가 있다고 가정하는 경우
    5번 도시에 도착하는 경우에서 문제가 발생합니다. 같은 횟수로 도로를 포장했다고 가정하면 1 > 3 > 5로 가는 비용이 먼저 계산되며 dp[5][count]값이 갱신됩니다.
    이후 1 > 4 > 5로 가는 비용이 계산되어 dp[5][count]값이 갱신되는데 이때 1 > 3 > 5로 가는 비용보다 저렴한 경우 먼저 계산되어 큐에 들어간 1 > 3 > 5로 가는 불필요한 계산을 하게됩니다.
    따라서, 큐에서 뽑은 노드가 최저비용으로 도착하는 비용인지 다시 확인해야합니다.

문제발생3.
    int형 초과문제
    long형으로 고쳐 초과하여 틀린 답이 나오는 문제를 해결하였습니다.

문제발생4.
    dp[N][K]가 최소값이 아닌 경우가 존재합니다.
    N번 도시에 도착하는 dp[N]에서 최소값을 찾아 출력하여 해결하였습니다.

 */
