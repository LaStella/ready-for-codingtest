// 사전
// https://www.acmicpc.net/problem/1256

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // dp[i][j] : a를 i개, z를 j개를 가지고 만들 수 있는 문자열의 개수
        dp = new int[n+1][m+1];

        // k가 사전에 수록되어 있는 문자열의 개수보다 크다면 -1을 출력합니다.
        if(dfs(n, m) < k) System.out.println(-1);

        else {
            getString(n, m, k);
            System.out.println(sb.toString());
        }

    }

    // a문자의 개수와 z문자의 개수로 만들 수 있는 문자열의 개수를 계산하는 함수입니다.
    private static int dfs(int a, int z){
        if(a == 0 || z == 0) return dp[a][z] = 1;
        else if(dp[a][z] != 0) return dp[a][z];
        // 여기서 k의 최대값 +1 과 비교하는 이유는 k의 최대값을 넘어가는 k가 주어지지 않으므로 그 이상의 문자열의 개수는 계산할 필요가 없기때문입니다.
        // 사전에 수록되는 문자열의 개수는 k의 최대값을 초과하고도 int형으로 표현할 수 없을 정도로 큰 수(200!/100!*100!)가 나옵니다.
        else return dp[a][z] = Math.min(dfs(a-1, z) + dfs(a, z-1), 1000000001);
    }

    // a문자의 개수와 z문자의 개수로 만들 수 있는 문자열 중 k번째 문자열을 만드는 함수입니다.
    private static void getString(int a, int z, int k){
        // 사용할 a 또는 z가 더이상 존재하지 않으므로 남은 z 또는 a문자를 붙입니다.
        if(a == 0){
            sb.append("z".repeat(z));
            return;
        }

        if(z == 0){
            sb.append("a".repeat(a));
            return;
        }

        // a를 첫문자로 만드는 문자열의 개수 안에 있는 경우
        if(k <= dp[a-1][z]){
            sb.append("a");
            getString(a-1, z, k);
        }

        // z를 첫문자로 만드는 문자열의 개수 안에 있는 경우
        else{
            sb.append("z");
            getString(a, z-1, k-dp[a-1][z]);
        }
    }
}

/*
dp[i][j]

dp[i][j] = dp[i-1][j] + dp[i][j-1]
a에 a를 i-1개 z를 j개를 가지고 만든 문자열을 더하는 경우
z에 a를 i개 z를 j-1개를 가지고 만든 문자열을 더하는 경우
위 두 경우의 합이 a를 i개, z를 j개 가지고 만들 수 있는 문자열의 개수

ex. n = 1, m = 1
dp[1][1] = dp[0][1] + dp[1][0]
a + z = az
z + a = za
2가지
i또는 j가 0인 경우 만들 수 있는 문자열은 1가지다.

문자열을 만드는 방법
ex. n = 2, m = 2, k = 2
첫번째 문자가 a인지 확인하려면 a에 나머지 문자열을 붙이는 문자열의 개수를 확인합니다.
dp[n-1][m] = dp[1][2] = 3
a로 시작하는 문자열의 개수는 3개이므로 k보다 큽니다.
따라서 첫번째 문자는 a로 시작함을 알 수 있습니다.

두번째 문자 역시 위와 마찬가지로 계산합니다.
n = 1, m = 2, k = 2
dp[1-1][2] = dp[0][2] = 1
두번째 문자가 a인 문자열의 개수는 1개로 k보다 작습니다.
따라서 두번째 문자는 z이며 k-dp[0][2]를 합니다.
여기서 k-dp[0][2]를 하는 이유는 주어진 문자의 개수를 통해 만들 수 있는 문자열 중 k번째를 찾기 때문입니다.
두번째 문자가 a인 경우는 1개이며 이 문자열이 곧 첫번째 문자열입니다. 하지만 첫번째 문자열이 찾는 k번째 문자열이 아니므로 사전에서 제외한다면
두번째 문자가 a인 문자열들의 개수만큼(여기선 1개지만) 사전에서 순서가 앞으로 당겨지게됩니다. 따라서 k번째에서 두번째 문자가 a인 문자열의 개수(dp[0][2])를 빼줍니다.

세번째 문자
n = 1, m = 1, k = 1
dp[1-1][1] = dp[0][1] = 1
k와 같으므로 세번째 문자는 a입니다.

네번째 문자
n = 0, m = 1, k = 1
a가 더이상 존재하지 않으므로 남은 z를 더합니다.
 */