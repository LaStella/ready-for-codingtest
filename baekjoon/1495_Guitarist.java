// 기타리스트
// https://www.acmicpc.net/problem/1495

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] v = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= n ; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i] : i크기의 볼륨에서의 연주 순서
        int[] dp = new int[m+1];
        // 연주 순서를 -1로 초기화합니다.
        Arrays.fill(dp, -1);
        // s크기의 볼륨에서는 0으로 초기화합니다.
        dp[s] = 0;

        for(int i = 1 ; i <= n ; i++) {
            // i번째 곡을 연주한 뒤 변경된 볼륨들을 저장할 큐입니다.
            Queue<Integer> q = new LinkedList<>();
            for(int j = 0 ; j <= m ; j++) {
                // 이전 연주(i-1번)에서 나온 볼륨을 찾아 계산합니다.
                if(dp[j] == i-1) {
                    int v1 = j + v[i];
                    int v2 = j - v[i];
                    if(isRange(v1)) q.add(v1);
                    if(isRange(v2)) q.add(v2);
                }
            }

            // 계산된 볼륨 크기를 i번 순서의 볼륨으로 저장합니다.
            // 위의 반복문내에서 저장할 경우 다음과 같은 문제가 발생할 수 있습니다.
            // ex. v1 = 10, i = 3일때 기존 dp[10] = 2이 존재할 경우, dp[10] = 3으로 덮어쓰게되어 dp[10]의 계산을 못하는 문제가 발생합니다.
            // 이를 방지하기위해 변경된 볼륨을 큐에 저장한 후 반복문이 종료된 뒤 변경된 볼륨에 연주 순서를 저장합니다.
            while(!q.isEmpty()) {
                dp[q.poll()] = i;
            }
        }

        int max_volume = -1;
        // n번 순서에서 최대 크기의 볼륨을 찾습니다.
        for(int i = m ; i >= 0 ; i--) {
            if(dp[i] == n) {
                max_volume = i;
                // 볼륨의 크기 i가 최대에서부터 탐색하므로 n번 연주순서의 볼륨 크기를 찾으면 종료합니다.
                break;
            }
        }

        System.out.println(max_volume);
    }

    // 주어진 볼륨이 허용 범위내의 볼륨인지 확인하는 함수입니다.
    public static boolean isRange(int volume) {
        return 0 <= volume && volume <= m;
    }
}

/*

5 10 30

10 5 3 4 20
10+10+5+3-4 = 24
10+10+5-3+4 = 26


 */