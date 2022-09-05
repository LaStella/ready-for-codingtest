// 발전소
// https://www.acmicpc.net/problem/1102

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N+1][N+1];

        for (int i = 0 ;  i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String[] power_plant = br.readLine().split("");
        int now_count = 0;
        int now_cost = 0;
        int now_status = 0;
        for (int i = 0 ; i < N ; i++) {
            if (power_plant[i].equals("Y")) {
                now_count++;
                now_status = now_status | (1 << i);
            }
        }

        int P = Integer.parseInt(br.readLine());

        // dp[i][j] : i개의 발전소가 켜져있으며 j상태의 발전소를 키는 비용
        int[][] dp = new int[N][1<<N];

        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(now_count, now_cost, now_status));
        int answer = 0;

        while(!q.isEmpty()) {
            Node p_node = q.poll();
            now_count = p_node.count;
            now_cost = p_node.cost;
            now_status = p_node.status;

            // 켜진 발전소의 개수가 P개 이상 있을 경우 종료합니다.
            if (now_count >= P) {
                answer = now_cost;
                break;
            }

            // 켜진 발전소의 개수가 부족한 경우 발전소를 킵니다.
            for (int i = 0 ; i < N ; i++) {
                // i번 발전소가 고장나 있는 경우
                if ((now_status & (1<<i)) == 0) {
                    int min_cost = 37;
                    // 현재 상태에서 i번 발전소를 키는 가장 저렴한 비용을 계산합니다.
                    for (int j = 0 ; j < N ; j++) {
                        if ((now_status & (1<<j)) == 1) {
                            min_cost = Math.min(min_cost, cost[j][i]);
                        }
                    }

                    if (min_cost != 37) {
                        // i번 발전소를 킨 상태를 큐에 넣습니다.
                        q.add(new Node(now_count+1, now_cost+min_cost, now_status | (1<<i) ));
                    }
                }
            }
        }

        if (answer == 0) {
            System.out.println(-1);
        }
        else {
            System.out.println(answer);
        }


    }


}

class Node implements Comparable<Node>{
    int count;
    int cost;
    int status;

    public Node(int count, int cost, int status) {
        this.count = count;
        this.cost = cost;
        this.status = status;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}
