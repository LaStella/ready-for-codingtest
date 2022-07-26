// ACM Craft
// https://www.acmicpc.net/problem/1005

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 건물의 건설 시간을 저장합니다.
            // time[i] : i번 건물을 짓는데 필요한 시간
            int[] time = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1 ; i <= n ; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            // 건설 규칙을 저장합니다.
            // rule[i] : i번 건물을 선행으로 요구하는 건물 리스트
            List<Integer>[] rule = new ArrayList[n+1];
            
            // count[i] : i번 건물을 짓는데 필요한 선행 건물의 개수
            int[] count = new int[n+1];
            for(int i = 1 ; i <= n ; i++) {
                rule[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < k ; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                rule[x].add(y);
                count[y]++;
            }

            // dp[i] : i번 건물을 짓는데 필요한 시간
            int[] dp = new int[n+1];

            // 건설이 가능한 건물을 저장할 큐입니다.
            Queue<Integer> q = new LinkedList<Integer>();
            for(int i = 1 ; i <= n ; i++) {
                dp[i] = time[i];

                // 필요한 선행 건물이 없다면 큐에 넣습니다.
                if(count[i] == 0){
                    q.add(i);
                }
            }

            while(!q.isEmpty()) {
                // node : 건설가능한 건물
                int node = q.poll();

                // node번 건물을 선행으로 하는 건물에 필요시간을 계산합니다.
                for(int i : rule[node]) {
                    // 선행건물인 node가 지어져야 i번 건물이 건설 가능합니다.
                    // 따라서 시간이 더 큰 값을 저장해야합니다.
                    dp[i] = Math.max(dp[i], dp[node] + time[i]);

                    // i번 건물은 선행으로 필요한 node가 완성되므로 필요 건물의 수를 감소시킵니다.
                    // 필요한 건물의 수가 없다면 건섫이 가능하므로 큐에 넣습니다.
                    if(--count[i] == 0) {
                        q.add(i);
                    }
                }
            }

            // 목표 건물
            int target = Integer.parseInt(br.readLine());

            System.out.println(dp[target]);
        }
    }
}

/*
dp[i] : i번 건물을 지을때 필요한 시간
 */
