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

        // cost[i][j] : i번 발전소로 j번 발전소를 키는 비용
        int[][] cost = new int[N][N];

        for (int i = 0 ;  i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 비용 저장 끝

        // 현재 켜져있는 발전소를 저장합니다.
        String[] power_plant = br.readLine().split("");

        // 현재까지 들어간 비용
        int now_cost = 0;
        // 현재 각 발전소의 상태 (0=꺼짐, 1=켜짐)
        int now_status = 0;

        for (int i = 0 ; i < N ; i++) {
            // i번 발전소가 켜져있다면 켜진 발전소의 개수를 늘리고 상태를 0에서 1로 바꿉니다.
            if (power_plant[i].equals("Y")) {
                now_status = now_status | (1 << i);
            }
        } // 발전소 정보 저장 끝

        int P = Integer.parseInt(br.readLine());

        // dp[i] : i개의 발전소가 켜져있으며 j상태의 발전소를 키는 비용
        int[] dp = new int[1<<N];

        // dp의 모든 값을 나올 수 없는 큰 값으로 초기화합니다.
        Arrays.fill(dp, Integer.MAX_VALUE);

        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(now_cost, now_status));

        int answer = -1;

        while(!q.isEmpty()) {
            Node p_node = q.poll();
            now_cost = p_node.cost;
            now_status = p_node.status;

            // 켜진 발전소의 개수가 P개 이상 있을 경우 종료합니다.
            // 발전소가 켜져있는 경우 1이 되므로 bitCount로 1의 개수를 셉니다.
            if (Integer.bitCount(now_status) >= P) {
                answer = now_cost;
                break;
            }

            // 켜진 발전소의 개수가 부족한 경우 발전소를 킵니다.
            for (int i = 0 ; i < N ; i++) {
                // i번 발전소가 고장나 있는 경우
                if ((now_status & (1<<i)) == 0) {
                    int min_cost = Integer.MAX_VALUE;
                    // 현재 상태에서 i번 발전소를 키는 가장 저렴한 비용을 계산합니다.
                    for (int j = 0 ; j < N ; j++) {
                        // i번 발전소가 고장나 있지 않은 경우(=켜져 있는 경우)
                        if ((now_status & (1<<j)) != 0) {
                            // 최저 비용을 저장합니다.
                            min_cost = Math.min(min_cost, cost[j][i]);
                        }
                    }

                    int next_status = now_status | (1<<i);
                    int next_cost = now_cost+min_cost;
                    // 이미 이전에 더 저렴한 비용으로 next_status에 해당하는 상태를 만들 수 있다면 계산할 필요가 없습니다.
                    if (dp[next_status] > next_cost) {
                        // i번 발전소를 킨 상태를 큐에 넣습니다.
                        dp[next_status] = next_cost;
                        q.add(new Node(next_cost, next_status));
                    }
                }
            }
        }

        System.out.println(answer);

    }


}

class Node implements Comparable<Node>{
    int cost;
    int status;

    public Node(int cost, int status) {
        this.cost = cost;
        this.status = status;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}

/*
 * 발전소가 켜진 상태와 꺼진 상태를 1(켜짐)과 0(꺼짐)으로 구분합니다.
 * i번 발전소가 켜져있다면 i번에 해당하는 비트의 숫자가 1이 되어야합니다.
 * ex. 발전소가 3개있으며 0번 발전소만 켜져있는 경우 001이며, 1, 2번 발전소만 켜져있는 경우 110입니다.
 * n번 발전소가 켜져있는지 &연산을 하면 알 수 있습니다.
 * status & (1<<n) == 0 : n번 발전소가 고장입니다.
 * ex.  status = 011, 2번 발전소를 확인
 *      011 & (1<<2) == 000 (고장)
 *      111 & (1<<2) == 100 (켜짐)
 * 
 * dp[status]에는 status를 만족하도록 발전소를 작동시 들어가는 최소 비용이 저장됩니다.
 * dp를 이용하여 최소 비용보다 비싼 비용으로 발전소를 작동하는 경우를 제외하여 불필요한 계산을 줄입니다.
 */