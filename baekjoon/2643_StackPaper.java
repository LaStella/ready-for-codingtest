// 색종이 올려 놓기
// https://www.acmicpc.net/problem/2643

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // dp[i] : i번 색종이 위에 놓을 수 있는 색종이의 수
        int[] dp = new int[N];

        // 주어진 N개의 색종이를 너비 순으로 저장합니다.
        Queue<Paper> q = new PriorityQueue<>();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 주어진 두 값 중 큰 값을 너비로 사용합니다.
            if (a > b) q.add(new Paper(a, b));
            else q.add(new Paper(b, a));
        }
        Paper[] p = new Paper[N];
        int index = 0;
        while (!q.isEmpty()) {
            p[index++] = q.poll();
        }// 색종이 너비순 저장 끝

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < i ; j++) {
                // j번 색종이 위에 i번 색종이를 놓을 수 있는 경우
                if (p[i].w >= p[j].w && p[i].h >= p[j].h) {
                    // j번 색종이 위에 놓을 수 있는 색종이의 수를 i번 색종이 위에 놓을 수 있습니다.
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            // i번 색종이도 놓는 개수에 포함해야 하므로 +1합니다.
            dp[i]++;
        }

        int answer = 0;

        for (int i = 0 ; i < N ; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);

    }
}

class Paper implements Comparable<Paper> {
    int w;
    int h;

    public Paper(int w, int h) {
        this.w = w;
        this.h = h;
    }


    @Override
    public int compareTo(Paper o) {
        if (this.w == o.w) {
            return this.h - o.h;
        }
        return this.w - o.w;
    }
}