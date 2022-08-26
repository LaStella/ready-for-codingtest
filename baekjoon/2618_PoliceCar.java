// 경찰차
// https://www.acmicpc.net/problem/2618

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int w;
    static int[][] dp;
    static int[] work_row;
    static int[] work_col;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        w = Integer.parseInt(br.readLine());

        work_row = new int[w+1];
        work_col = new int[w+1];

        // 각 사건의 row와 column을 저장합니다.
        for (int i = 1 ; i <= w ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            work_row[i] = Integer.parseInt(st.nextToken());
            work_col[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j] : 경찰차1이 i번 사건의 위치에, 경찰차2가 j번 사건의 위치에 있을 때 남은 사건을 처리하는데 필요한 최소 이동 거리
        // ex. dp[1][2] : 경찰차1이 1번 사건의 위치에, 경찰차2가 2번 사건의 위치에 있을 때 남은 사건(3번~w번)을 처리하는데 필요한 최소 이동 거리
        dp = new int[w+1][w+1];

        System.out.println(dfs(1, 0, 0));

        int car1_index = 0;
        int car2_index = 0;

        for (int i = 1 ; i <= w ; i++) {
            // 최소 이동 거리에서 경찰차 1이 i번 사건으로 이동하는 거리를 뺀 나머지 거리가 경찰차1이 i사건 위치에 잇을 때 최소 거리와 같다면 i번 사건은 경찰차1이 맡았음을 알 수 있씁니다.
            // 즉, (i번 ~ w번 사건)최소 이동 거리 - 경찰차1 이동거리 == (i+1번 ~ w번 사건)최소 이동 거리
            if ((dp[car1_index][car2_index] - getDistance(car1_index, i)) == dp[i][car2_index]) {
                car1_index = i;
                System.out.println("1");
            }

            else {
                car2_index = i;
                System.out.println("2");
            }
        }
    }

    // work_num번 사건을 두 경찰차가 각각 맡아 남은 사건을 모두 처리하는데 필요한 거리를 계산하는 함수입니다.
    public static int dfs(int work_num, int car1, int car2) {
        // 마지막 사건을 처리하여 더이상 사건이 없으면 이동하지 않으므로 0을 리턴합니다.
        if (work_num > w) {
            return 0;
        }

        // 이전에 계산하지 않은 경우에만 계산합니다.
        if (dp[car1][car2] == 0) {
            // 경찰차1이 사건을 맡는 경우 이동하게 되는 최소 거리를 계산합니다.
            // 경찰차1의 현재위치에서 사건 work_num의 위치로 이동하는 거리와 남은 사건(work_num+1 ~ w)을 처리하는데 필요한 최소 거리의 합을 구합니다.
            int car1_take = getDistance(car1, work_num) + dfs(work_num+1, work_num, car2);
            // 위와 마찬가지이며 경찰차2가 사건을 맡는 경우입니다.
            int car2_take = getDistance(work_num, car2) + dfs(work_num+1, car1, work_num);

            // 위의 두 경우 중 최소 거리를 저장합니다.
            dp[car1][car2] = Math.min(car1_take, car2_take);
        }

        return dp[car1][car2];
    }

    // 두 지점 i와  j사이의 거리를 계산합니다.
    // 단 경찰차1이 움직일 경우 i가 경찰차1의 현재 위치이며, 경찰차2가 움직일 경우 j가 경찰차2의 위치입니다.
    public static int getDistance(int i, int j) {
        // 경찰차1이 시작위치(1, 1)인 경우
        if (i == 0) {
            return Math.abs(1 - work_row[j]) + Math.abs(1 - work_col[j]);
        }

        // 경찰차2가 시작위치(n, n)인 경우
        if (j == 0) {
            return Math.abs(n - work_row[i]) + Math.abs(n - work_col[i]);
        }

        return Math.abs(work_row[i] - work_row[j]) + Math.abs(work_col[i] - work_col[j]);
    }
}

/*
i번 사건에 대해서 두 경찰차가 각각 맡는 경우를 dp[car1][car2]으로 나타냅니다.
dp[0][0] : 각 경찰차가 출발위치에서 모든 사건을 처리하는데 필요한 최소 이동 거리
dp[1][0] : 1번 경찰차가 1번 사건을 맡는 경우 남은 최소 이동 거리
dp[0][2] : 2번 경찰차가 2번 사건을 맡는 경우 남은 최소 이동 거리

dp배열에 이동한 최소 거리를 저장하는게 아닌 아동후 남은 최소 이동거리를 저장하는 이유는 이후에 사건을 처리한 경찰차를 출력할때 추적에 용이하기 때문입니다.
dp[0][0] 에는 최소 이동 거리가 저장되므로 두 경찰차가 1번사건을 맡는 경우는 dp[1][0]과 dp[0][1]로 나뉘어집니다.
사건의 각 좌표를 알고 있으므로 두 경찰차의 위치를 사건 번호로 나타낼 때
dp[0][0] - (1번 경찰차가 1번 사건으로 가는 이동 거리) == dp[1][0] 일 경우 첫번째 사건은 1번 경찰차가 맡았음을 알 수 있습니다.

car1 = 0;
car2 = 0;

 */