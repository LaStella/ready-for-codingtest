// 전시장
// https://www.acmicpc.net/problem/2515

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        Queue<Paint> q = new PriorityQueue<>();

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            q.add(new Paint(h, c);
        }

        Paint[] p = new Paint[N+1];

        for (int i = 1 ; i <= N ; i++) {
            p[i] = q.poll();
        }

        int[] dp = new int[N+1];

        for (int i = 1 ; i <= N ; i++) {
            dp[i] = dp[i-1];
            for (int j = 1 ; j < i ; j++) {
                if (p[i].height - p[j].height >= S) {
                    dp[i] = Math.max(dp[i], dp[j] + p[i].cost);    
                }
            }
        }
    }
}

class Paint implements Comparable<Paint> {
    int height, cost;

    public Paint(int h, int c) {
        this.height = h;
        this.cost = c;
    }

    @Override
    public int compareTo(Paint o) {
        return this.height - o.height;
    }
}

/*
그림의 높이가 20000000까지 주어지므로 높이와 비용에 따른 dp배열을 만들기는 어렵습니다.
주어지는 그림은 최대 300000개이므로 0~i번째 그림까지 최대 가격 합을 구하는게 좋아보입니다.
그림들을 높이순으로 정렬하여 i번째 그림을 선택할 때 높이 차이가 s 이상이 되는지 확인할 수 있도록 합니다.

 */