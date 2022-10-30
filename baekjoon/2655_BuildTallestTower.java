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

        Queue<Brick> q = new PriorityQueue<>();

        StringTokenizer st;
        for (int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            q.add(new Brick(a, h, w));
        }

        while (!q.isEmpty()) {
            System.out.println(q.poll().a);
        }


    }
    static class Brick implements Comparable<Brick> {
        int a, h, w;
        public Brick (int a, int h, int w) {
            this.a = a;
            this.h = h;
            this.w = w;
        }

        @Override
        public int compareTo(Brick o) {
            return this.a - o.a;
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
