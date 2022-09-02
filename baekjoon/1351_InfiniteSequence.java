// 무한 수열
// https://www.acmicpc.net/problem/1351

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Long, Long> dp;
    static int p;
    static int q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        // dp.get(i) : 수열의 i번째 값을 나타냅니다.
        dp = new HashMap<>();
        dp.put((long) 0, (long) 1);

        System.out.println(dfs(n));
    }

    
    // 수열의 i번에 해당하는 값을 리턴하는 함수입니다.
    public static long dfs(long i) {
        // 이미 값이 존재하는 경우 계산할 필요가 없으므로 값이 없는 경우에만 계산합니다.
        if (!dp.containsKey(i)) {
            // i번째 수열은 (i/p)번째 수열과 (i/q)번째 수열의 합입니다.
            dp.put(i, dfs(i/p) + dfs(i/q));
        }
    
        return dp.get(i);
    }
}

/*
dp배열을 만들어 풀려고 하였으나 주어진 n의 크기가 int형을 초과하는 범위입니다.
따라서 배열을 사용하여 해결하기 어렵다고 생각하여 map을 사용하였습니다.
 */