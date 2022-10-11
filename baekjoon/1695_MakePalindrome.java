// 팰린드롬 만들기
// https://www.acmicpc.net/problem/1695

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] s;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 주어진 수열을 저장합니다.
        // 중복되는 계산을 거르기 위해 dp를 -1로 초기화합니다.
        s = new int[N];
        dp = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            s[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        } // 저장 및 초기화 끝

        System.out.println(getCount(0, N-1));

    }

    // 왼쪽 지점이 left, 오른쪽 지점이 right 일 때 수열을 팰린드롬으로 만들기위해 끼워 넣어야할 수를 구하는 함수입니다.
    public static int getCount (int left, int right) {
        // 이미 계산한 값은 결과를 리턴합니다.
        if (dp[left][right] != -1) return dp[left][right];

        // 양 끝에서 부터 시작한 두 지점이 만나거나 넘어가는 경우(left는 1씩 증가, right는 1씩 감소하므로)
        // 팰린드롬이 만들어진 것이므로 0을 리턴합니다.
        if (left > right || left == right) {
            return 0;
        }

        // 팰린드롬으로 만들어지지 않은 경우 주어진 두 지점의 수를 비교하여 같은지 확인합니다.
        if (s[left] == s[right]) {
            // 두 수가 같으면 다음 수에서 필요한 수를 구합니다.
            return dp[left][right] = getCount(left+1, right-1);
        }
        else {
            // 두 수가 다르면 왼쪽에 수를 넣는 경우와 오른쪽에 수를 넣는 경우 중 더 적게 수가 들어가는 쪽을 선택합니다.
            // 수를 하나 끼워넣으므로 +1을 합니다.
            return dp[left][right] = Math.min(getCount(left+1, right), getCount(left, right-1)) + 1;
        }
    }
}


/*

1 2 3 4 2
양 끝에서 부터 차례로 확인합니다.
1 != 2 이므로 팰린드롬으로 만들기 위해서는 양 끝 중 한 곳에 숫자를 끼워 넣어야합니다.
왼쪽에 끼워넣는 경우 2 1 2 3 4 2가 되며 다음으로 판별할 숫자는 1, 4가 됩니다.
오른쪽으로 끼워넣는 경우 1 2 3 4 2 1가 되며 다음으로 판별할 숫자는 2, 2가 됩니다.

수열의 왼쪽 끝에서 시작하는 지점을 left, 오른쪽 끝에서 시작하는 지점을 right 라고 할 때
left != right 인 경우 왼쪽에 끼워넣는 경우 get(left, right+1)와 오른쪽에 끼워넣는 경우 get(left+1, right) 중 더 작은 값에 +1(끼워넣으므로 카운트가 증가)을 한 값이 됩니다.

위의 예시에서 왼쪽으로 끼워넣는 경우 더 많은 숫자를 끼워야 함을 알 수 있습니다.
따라서 양쪽으로 넣는 경우 중 더 적게 숫자를 끼워 넣는 경우를 계산해야합니다.

중복되는 계산이 많으므로 메모이제이션을 이용하여 중복되는 계산을 제거합니다.

*/
