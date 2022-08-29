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

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[][] cost = new int[n+1][n+1];
            int[][] dist = new int[n+1][n+1];

            // 티켓 정보 저장
            for (int i = 1 ; i <= k ; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                cost[u][v] = Integer.parseInt(st.nextToken());
                dist[u][v] = Integer.parseInt(st.nextToken());
            }

            Queue<Node> q = new PriorityQueue<>();
            q.add(new Node(1, 0, 0));


        }

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

    @Override
    public int compareTo(Node other) {
        return this.time - other.time;
    }
}

/*
출발공항 1에서 도착공항 n까지 걸리는 최소 시간을 구해야합니다.
주어진 티켓 정보를 이용하여 시간을 기준으로 우선순위큐를 이용하는 다익스트라 알고리즘을 사용합니다.
출발공항 1에서 이동하여 도착하는 다음 공항에 대한 정보를 노드로 표현하면 다음과 같습니다.
new Node (공항번호, 시간, 비용)

즉, 출발공항 new Node(1, 0, 0)에서 시작하여 도착공항 Node(n, ?, ?)을 구하는 것으로 시간에 따른 우선순위큐를 이용합니다.
큐에서 적은 시간순으로 뽑으므로 뽑은 노드에서 연결된 다른 공항으로 비용을 계산합니다.
비용이 주어진 지원 비용(m)을 넘으면 큐에 넣을 수 없으며 지원 비용보다 작은 비용이 든다면 큐에 넣을 수 있습니다.

큐에 넣을때 이미 방문한 공항도 넣을 수 있는 문제가 있습니다.




 */