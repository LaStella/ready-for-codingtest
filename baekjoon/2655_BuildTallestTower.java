// 가장높은탑쌓기
// https://www.acmicpc.net/problem/2655

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 벽돌을 저장할 리스트입니다.
        List<Brick> bricks = new ArrayList<>();

        bricks.add(new Brick(0, 0, 0, 0));

        StringTokenizer st;
        for (int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            bricks.add(new Brick(i, a, h, w));
        }

        // 넓이순으로 벽돌을 정렬합니다. (오름차순)
        Collections.sort(bricks);

        // dp[i] : i번 벽돌부터 쌓을 때 최대 높이
        int[] dp = new int[n+1];

        for (int i = 1 ; i <= n ; i++) {
            for (int j = 0 ; j < i ; j++) {
                // j < i 일 때, 오름차순으로 정렬된 i번 벽돌은 j번 벽돌보다 넓이가 넓습니다.
                // 넓이와 무게 모두 커야하므로 i번 벽돌이 j번 벽돌보다 무거운 경우 i번 벽돌 위에 j번 벽돌을 놓을 수 있습니다.
                if (bricks.get(i).weight > bricks.get(j).weight) {
                    // j번 벽돌부터 쌓은 최대 높이 + i번 벽돌의 높이 
                    dp[i] = Math.max(dp[i], dp[j] + bricks.get(i).height);
                }
            }
        }

        // 최대 높이와 최대 높이의 벽돌 번호를 찾습니다.
        int max_height = 0;
        int max_id = 0;

        for (int i = 1 ; i <= n ; i++) {
            if (max_height < dp[i]) {
                max_height = dp[i];
                max_id = i;
            }
        }

        // 정렬된 벽돌 번호가 아닌 입력된 벽돌 번호가 저장될 리스트입니다.
        List<Integer> id_list = new ArrayList<>();

        // 최대 높이에서 dp를 탐색하여 역순으로 벽돌을 찾습니다.
        // dp[i]는 i번 벽돌부터 쌓은 최대 높이이므로 dp[i] == 최대 높이 인 경우 i번 벽돌부터 쌓아올린 탑입니다.
        // 최대 높이에서 i번 벽돌의 높이를 차감한 높이를 다시 dp에서 탐색합니다.
        // i번 벽돌위에 존재하는 벽돌이므로 dp[i-1]부터 탐색하면 됩니다.
        for (int i = max_id ; i > 0 ; i--) {
            // i번 벽돌에서 최대 높이가 max_height라면 i번 벽돌을 쌓아 올린 타워입니다.
            if (max_height == dp[i]) {
                // max_height에서 i번 벽돌의 높이를 차감하여 다음 최대 높이를 탐색합니다.
                max_height -= bricks.get(i).height;
                // 찾은 벽돌은 id_list에 저장합니다.
                id_list.add(bricks.get(i).id);
            }
        }

        // 벽돌의 개수를 출력합니다.
        StringBuilder sb = new StringBuilder();
        sb.append(id_list.size()+"\n");

        // 벽돌을 쌓은 순서를 출력합니다.
        for (int i = id_list.size()-1 ; i >= 0 ; i--) {
            sb.append(id_list.get(i)+"\n");
        }

        System.out.println(sb.toString());
    }
    static class Brick implements Comparable<Brick> {
        // index, area, height, weight
        // 번호, 넓이, 높이, 무게
        int id, area, height, weight;

        public Brick (int id, int area, int height, int weight) {
            this.id = id;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Brick o) {
            // 오름차순
            return this.area - o.area;
        }
    }
}

/*
i번 벽돌 위에 놓을 수 있는 최대 높이를 계산해야합니다.
dp[i] : i번 벽돌위에 놓을 수 있는 최대 높이

각 벽돌들은 넓이, 높이, 무게가 존재하며 넓이와 무게는 밑에 있는 벽돌일수록 넓고 무거워야 합니다.
벽돌을 넓이 순으로 정렬합니다.(오름차순)
이중 for을 사용하여 dp[i]를 계산합니다.
    j < i일 때 i번 벽돌위에 j번 벽돌을 올려놓을 수 있는 넓이 조건을 충족합니다.(오름차순이므로)
    i번 벽돌위에 j번 벽돌을 올려놓기 위해서는 i번 벽돌의 무게가 j번 벽돌의 무게보다 많아야 합니다.
    넓이와 무게 조건을 모두 충족한다면 i번 위에 j번 벽돌을 올려놓을 수 있으며 j번 벽돌에서 쌓을 수 있는 최대 높이 + i번 벽돌의 높이 가 dp[i]가 됩니다.
    if(brick[i].weight > brick[j].weight) dp[i] = dp[j] + brick[i].height



 */