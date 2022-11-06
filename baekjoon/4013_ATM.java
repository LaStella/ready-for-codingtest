// ATM
// https://www.acmicpc.net/problem/4013

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] atm;
    static boolean[] restaurant;
    static ArrayList<ArrayList<Integer>> edge_list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // N개의 교차로를 생성합니다.
        for (int i = 0 ; i <= N ; i++) {
            edge_list.add(new ArrayList<>());
        }

        // M개의 도로를 입력받아 저장합니다.
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int edge_start = Integer.parseInt(st.nextToken());
            int edge_end = Integer.parseInt(st.nextToken());

            // 시작 교차로에서 끝 교차로로 가는 도로를 추가하여 두 교차로를 이어줍니다.
            edge_list.get(edge_start).add(edge_end);
        }

        atm = new int[N+1];

        // 각 ATM에 들어있는 돈을 저장합니다.
        for (int i = 1 ; i < N ; i++) {
            atm[i] = Integer.parseInt(br.readLine());
        }

        st = new StringTokenizer(br.readLine());

        // 시작 교차로와 레스토랑의 개수를 입력받습니다.
        int start = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        restaurant = new boolean[N+1];

        // 레스토랑이 있는 교차로는 true를 저장합니다.
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < P ; i++) {
            restaurant[Integer.parseInt(st.nextToken())] = true;
        }


    }
}

/*
오랜시간 고민했으나 해결방법을 찾지못하여 참고
강한 결합 요소 (Strongly Connected Componet) 추출 알고리즘을 알게되었습니다.
--------------------------------------

 */