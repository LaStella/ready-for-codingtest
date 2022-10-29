// 숫자구슬
// https://www.acmicpc.net/problem/2613

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] bead;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bead = new int[N];

        st = new StringTokenizer(br.readLine());

        // 그룹합 최댓값의 최솟값
        int left = 0;
        // 그룹합 최댓값의 최댓값
        int right = 0;

        // 각 구슬을 저장합니다.
        for (int i = 0 ; i < N ; i++) {
            bead[i] = Integer.parseInt(st.nextToken());
            // N개의 구슬을 N개의 그룹으로 나눌 경우 가장 큰 구슬이 그룹합 최댓값이 됩니다.
            // N개의 구슬을 1개의 그룹으로 나눌 경우 모든 구슬의 합이 그룹합 최댓값이 됩니다.
            // 따라서 그룹합 최댓값은의 범위는 다음과 같습니다.
            // left <= 그룹합 최댓값 <= right
            left = Math.max(left, bead[i]);
            right += bead[i];
        }

        // N개의 구슬을 M개의 그룹으로 나눌 때 그룹합 최댓값의 최솟값을 구합니다.
        int min = getMaxSum(left, right);

        System.out.println(min);

        StringBuilder sb = new StringBuilder();

        // 그룹의 구슬 합
        int sum = 0;
        // 그룹의 구슬 개수
        int count = 0;

        for (int i = 0 ; i < N ; i++) {
            sum += bead[i];

            // 그룹의 구슬 합이 최대값을 초과하는 경우
            if (sum > min) {
                // 하나의 그룹이 만들어지므로 만들어야하는 그룹의 수를 줄입니다.
                M--;
                // 그룹에 들어간 구슬의 개수를 출력합니다.
                sb.append(count+" ");
                // 새로운 그룹에 현재 구슬을 넣습니다.
                sum = bead[i];
                count = 1;
            }
            // 구슬 개수를 늘립니다.
            else count++;

            // 남은 그룹의 개수와 남은 구슬의 개수가 같은 경우
            if (M == N - i) {
                // 현재까지 구슬을 넣은 그룹을 하나의 그룹으로 만듭니다.
                M--;
                sb.append(count+" ");

                // 그룹에 최소 1개의 구슬이 필요하기 때문에 이후 남은 구슬들은 각각 하나의 그룹이 됩니다.
                while (M-- > 0) {
                    sb.append("1 ");
                }
                break;
            }
        }

        System.out.println(sb.toString());

    }

    // 이분탐색을 이용하여 만들 수 있는 그룹의 수가 M개 일 때 그룹의 최댓값이 최소인 값을 찾습니다.
    public static int getMaxSum(int left, int right) {
        int mid = 0;

        while(left <= right) {
            // 그룹합 최댓값
            mid = (left+right)/2;

            // 그룹의 구슬 합
            int sum = 0;
            // 그룹의 개수
            int count = 0;

            // 1번 구슬부터 그룹에 넣으며 최댓값인 mid를 초과하지 않도록 합니다.
            for (int i = 0 ; i < N ; i++) {
                sum += bead[i];
                if (sum > mid) {
                    // mid를 초과한다면 다음 그룹에 구슬을 넣으므로 그룹의 수를 증가시킵니다.
                    count++;
                    sum = bead[i];
                }
            }

            // 마지막 그룹이 그룹의 수에 포함되지 않았으므로 증가시킵니다.
            count++;
            // 그룹의 개수가 만들 수 있는 M개 보다 많은 경우
            // mid(그룹합 최댓값)가 작은 것이므로 더 큰 범위에서 다시 찾습니다.
            if (count > M) {
                left = mid+1;
            }
            // 그룹의 개수가 M개 이하인 경우
            // mid(그룹합 최댓값)가 큰 것이므로 더 작은 범위에서 다시 찾습니다.
            else {
                right = mid-1;
            }
        }

        return left;
    }
}

/*
116
125
134
143
152
161
215
224
...


1 1 6
2개의 구슬을 2개의 그룹으로 나누고, 6개의 구슬을 1개의 그룹으로 만듭니다.
dp[8][3] = Math.min(dp[2][2], sum[8]-sum[2])
dp[8][3] = Math.min(dp[3][2], sum[8]-sum[3])
...

dp[i][j] : i개의 구슬을 j개의 그룹으로 나눌 때 그룹의 최대값
------------------------------------------실패

그룹의 최대값을 이분탐색으로 찾습니다.
구슬을 m개의 그룹으로 나눌 경우 나올수 있는 최대값의 범위는 (가장 큰 구슬 ~ 모든 구슬의 합) 입니다.
m은 n이하의 자연수이므로 1~n까지의 범위를 가지므로 n개의 그룹으로 나눌 경우 가장 큰 구슬이 최대값이 되며 1개의 그룹으로 나눌 경우 모든 구슬의 합이 최대값이 됩니다.
left = 가장 큰 구슬
right = 모든 구슬의 합
mid = (left+right)/2

0번 구슬부터 N-1번 구슬까지 순서대로 그룹에 넣습니다.
그룹에 들어간 구슬의 합, 그룹합이 mid 보다 크다면 다음 그룹에 구슬을 이어서 넣습니다.
모든 구슬을 넣은 후 만들어진 그룹의 개수를 주어진 m과 비교합니다.
m보다 만들어진 그룹이 많다면 그룹합의 최대값(mid)가 작아서 그룹이 많이 만들어진 것이므로 mid보다 큰 범위에서 탐색을 다시합니다.
left = mid+1
m보다 만들어진 그룹이 적거나 같다면 그룹합의 최대값(mid)가 커서 그룹이 적게 만들어진 것이므로 mid보다 작은 범위에서 탐색을 다시합니다.
right = mid-1



18 18 18 18 12 5 18 18 18
M = 8



 */
