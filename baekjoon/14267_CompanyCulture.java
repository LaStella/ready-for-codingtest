// 회사 문화
// https://www.acmicpc.net/problem/14267

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] list;
    static int[] value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // list[i] : i번 직원의 부하 리스트
        list = new ArrayList[n+1];
        for (int i = 1 ; i <= n ; i++) {
            list[i] = new ArrayList<>();
        }// 초기화 끝

        // 상하관계를 저장합니다.
        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 2 ; i <= n ; i++) {
            // i번 직원의 상사는 j이므로 list[j]에 i를 저장합니다.
            int j = Integer.parseInt(st.nextToken());
            list[j].add(i);
        }// 상하관계 저장 끝

        // value[i] : i번 직원의 칭찬 정도
        value = new int[n+1];

        // 주어진 칭찬을 저장합니다.
        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            // j번 직원에게 k만큼 칭찬하는 것을 저장합니다.
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            value[j] += k;
        }

        dfs(1);


        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i < n ; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        sb.append(value[n]);

        System.out.println(sb.toString());
        
    }

    // index번 직원이 자신의 부하에게 자신이 받은 칭찬(value) 만큼 값을 올려주는 함수입니다.
    static void dfs (int index) {
        for (int i : list[index]) {
            // index번 직원이 받은 칭찬은 value[index]이므로 부하인 i번 직원은 value[index]만큼 칭찬을 받습니다.
            value[i] += value[index];
            // 칭찬 받은 부하 직원에 대해서 다시 칭찬을 합니다.
            dfs(i);
        }
    }
}


/*


 */