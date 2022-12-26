// 숫자
// https://www.acmicpc.net/problem/1131

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] dp = new long[1000001];
        Arrays.fill(dp, -1);
        boolean[] visited = new boolean[1000001];

        long answer = 0;

        for (int i = a ; i <= b ; i++) {
            answer += getMinS(i, k, dp);
        }

        System.out.println(answer);
    }

    // 수열의 최소값을 구하는 함수입니다.
    private static long getMinS(int numb, int k, long[] dp) {
        Stack<Integer> stack = new Stack<>();
        stack.add(numb);
        // a부터 수열을 구합니다. a, S(a), S(S(a)), ...
        while (true) {
            int s = getS(numb, k);

            //
            if (dp[s] == -1 && stack.contains(s)) {

            }

            // 이미 구한적 있는 숫자면 최소
            if (dp[s] != -1) {

            }


            stack.add(s);



        }
    }

    // 각 자리수를 K제곱 한 후 합을 구하는 함수 S입니다.
    private static int getS(int a, int k) {
        int answer = 0;

        while (a > 0) {
            answer += Math.pow(a % 10, k);
            a /= 10;
        }

        if (answer > 1000000) return getS(answer, k);

        return answer;
    }
}


/*
수열은 N부터 시작하여 이전 수에 대해서 S함수 값을 구합니다.
S값을 계산하였을 때 이미 이전에 구한 값이라면 반복되므로 종료합니다.
    ex) 4, 16, 37, 58, 89, 145, 42, 20, 4 종료
수열에서 나온 값 중 최소값을 찾아 합을 구합니다.

메모리 초과
--------------------------------------------------
한 숫자 수열에서 반복되는 내에서는 모두 같은 최소값을 가지게됩니다.
    ex) 2: 2, 4, 16, 37, 58, 89, 145, 42, 20, 4...
        3: 3, 9, 81, 65, 61, 37, 58, 89, 145, 42, 20, 4, 16, 37...
        4: 4, 16, 37, 58, 89, 145, 42, 20, 4...
        위의 경우 3는 최소값이 3이며 4는 최소값이 4입니다.
        이때 숫자 3에서의 수열을 보면 37 이후로 반복되는 사이클이 존재합니다.
        사이클 내에서의 최소값을 찾아 사이클에 속하는 숫자들의 최소값을 저장합니다.
        61부터 3까지 남은 숫자에 대해서 최소값을 갱신해가며 저장합니다.
            ex) dp[61] = Math.min(61, 4) -> dp[65] = Math.min(65, 4) -> dp[81] = Math.min(81, 4) ...
                dp[3] = Math.min(3, 4) 
각 수열의 숫자에 대해서 최소값을 저장하는 dp배열을 만듭니다.
dp[] : i숫자에 대한 수열의 최소값
Sk(i)를 한 수를 미리 구하여 저장합니다.
    이때 Sk(i) 는 1000000 보다 클 수 있기때문에 수가 큰 경우 한번더 S함수를 구합니다.


 */