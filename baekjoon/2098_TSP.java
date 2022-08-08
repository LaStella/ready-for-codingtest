// 외판원 순회
// https://www.acmicpc.net/problem/2098

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] dp;
    static int[][] w;
    static int INF = 16000001; // 도시는 최대 16개이며 행렬의 성분은 최대 1000000이므로 비용은 16*1000000을 넘길 수 없습니다.
    static int status_visit_all;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        // 주어진 도시간 비용을 저장합니다.
        w = new int[n][n];

        for(int r = 0 ; r < n ; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c = 0 ; c < n ; c++) {
                w[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[i][status] : i번 도시에서 방문한 다른 도시들의 정보가 status일 때 방문하지 않은 도시들을 방문한 뒤 시작 도시로 돌아오는 최소 비용
        // ex. dp[1][3] : 0번 도시(i)에서 0, 1번 도시만 방문한 경우(j) 앞으로 방문하지 않은 모든 도시를 방문한 뒤 시작 도시로 돌아오는 최소 비용
        // 위의 예시에서 status는 3으로 이진수 0011, 즉 0번도시와 1번도시를 방문한 상태입니다.
        status_visit_all = (1<<n)-1;
        dp = new int[n][status_visit_all];

        // 각 최소 비용을 -1로 초기화합니다.
        for(int i = 0 ; i < n ; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 1));
    }

    // 현재도시 i와 각 도시의 방문 정보 status가 주어질 때 앞으로 남은 모든 도시를 방문하여 시작 도시로 돌아가는 최소 비용을 리턴하는 함수입니다.
    public static int dfs(int i, int status) {
        // 모든 도시를 방문하였다면 시작 도시(0번)로 돌아가야 하므로 시작 도시로 가는 비용을 리턴합니다.
        if(status == status_visit_all) {
            // 시작 도시로 돌아갈 수 없는 경우 최대 비용을 리턴하여 최소 비용이 될 수 없게 합니다.
            if(w[i][0] == 0) {
                return INF;
            }
            return w[i][0];
        }

        // 현재 도시와 주어진 방문 정보를 통해 최소 비용을 계산한 적이 없는 경우에만 비용을 계산합니다.
        if(dp[i][status] == -1) {
            // 초기값으로는 충분히 큰 값으로 저장하여 최소 비용을 계산할 수 있도록 합니다.
            dp[i][status] = INF;
            // 다음 방문할 도시(j)에 따른 최소 비용을 계산합니다.
            for(int j = 0 ; j < n ; j++) {
                // 현재도시(i)에서 다음 도시(j)로 방문시 이전에 방문하지 않은 도시여야합니다.
                // 현재도시에서 다음 도시로 방문할 수 있어야 합니다. (w[i][j] != 0)
                if(w[i][j] == 0 || (status & (1<<j)) != 0) continue;

                dp[i][status] = Math.min(dp[i][status], dfs(j, (status | (1<<j))) + w[i][j]);
            }
        }

        return dp[i][status];
    }
}

/*
dp[i][status] : i번 도시에서 status에 따라 방문하지 않은 도시들을 방문한 뒤 시작 도시로 돌아오는 최소 비용
status : 각 도시의 방문 여부

dfs를 이용하여 각 도시를 방문하며 status가 모든 도시를 방문한 경우 종료
n = 4일 경우
각 도시는 방문했거나(1) 방문하지않은(0) 2가지 이므로 이진수로 0000으로 시작합니다.
모든 도시를 방문한 status는 1111입니다. 십진수로 15

어느 도시로 방문을 시작하여도 최종적으로 시작 도시로 돌아오므로 출발도시는 최소값에 영향을 주지 않습니다.
따라서 시작 도시를 0번 도시로 지정합니다.
(ex. 1>2>3>4>5>1 의 비용이 최소일 경우 2>3>4>5>1>2와 비용이 똑같습니다.)

ex. n = 4일 경우
출발도시를 0번째 도시, 0번째 도시를 방문하였으므로 status는 0001로 시작합니다.
0번째 도시에서 다음 도시로 이동한다면 status는 다음 도시에 따라 바뀝니다.
0>1, 0>2, 0>3으로 가는 세가지 경우가 존재하며 dp[0][status]는 저 세 경우 중 최소 비용이 저장되어야합니다.


 */