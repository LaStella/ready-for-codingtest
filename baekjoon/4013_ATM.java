// ATM
// https://www.acmicpc.net/problem/4013

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] atm;
    static boolean[] restaurant;
    static ArrayList<ArrayList<Integer>> edge_list = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> scc_edge_list = new ArrayList<>();
    static int[] sccNum;
    static int sccIndex = 0;
    static int[] sccAtm;
    static boolean[] visited; // 방문한 교차로(정점)을 나타내는 배열
    static boolean[] finishied; // SCC탐색이 완료된 교차로를 나타내는 배열
    static Stack<Integer> st = new Stack<>(); // SCC탐색시 방문한 교차로(정점)를 저장하는 스택
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // N개의 교차로를 생성합니다. (0번째는 더미)
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
        for (int i = 1 ; i <= N ; i++) {
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


        visited = new boolean[N+1];
        finishied = new boolean[N+1];
        sccNum = new int[N+1];

        // 모든 교차로(정점)에 대해 DFS를 수행하여 SCC를 찾습니다. (타잔 알고리즘)
        for (int i = 1 ; i <= N ; i++) {
            if (!visited[i]) dfs(i);
        }

        makeSccEdgeList();

        dp = new int[sccIndex];



        System.out.println("!"); // breakpoint
    }

    // DFS알고리즘을 이용하여 SCC를 찾는 함수입니다.
    // 자신의 부모 교차로(정점)을 리턴합니다.
    public static int dfs(int u) {
        visited[u] = true;
        st.push(u);

        int parent = u;
        for (int i = 0 ; i < edge_list.get(u).size() ; i++) {
            // v : u교차로와 연결된 이웃 교차로
            int v = edge_list.get(u).get(i);
            // 방문하지 않은 이웃 교차로
            if (!visited[v]) parent = Math.min(parent, dfs(v));
            // 방문했으나 dfs함수가 처리중인 교차로
            else if (!finishied[v]) parent = Math.min(parent, v);
        }

        // 부모가 자기 자신인 경우
        if (parent == u) {
            // 스택에서 자기 자신이 나올때까지 뽑아 하나의 scc로 묶습니다.
            // 각 교차로에 대해서 같은 scc그룹이라면 같은 sccNum을 가지게 됩니다.
            while(true) {
                int intersection = st.pop();
                sccNum[intersection] = sccIndex;
                finishied[intersection] = true;
                if (intersection == u) break;
            }
            sccIndex++;
        }

        return parent;
    }

    // 각 scc그룹을 하나의 교차로(정점)으로 보고 도로(간선)를 만드는 함수입니다.
    public static void makeSccEdgeList() {
        sccAtm = new int[sccIndex];
        for (int i = 0 ; i < sccIndex ; i++) {
            scc_edge_list.add(new ArrayList<>());
        }

        for (int i = 1 ; i <= N ; i++) {
            // 같은 scc에 속한 교차로의 모든 atm 금액을 합하여 sccAtm에 저장합니다.
            sccAtm[sccNum[i]] += atm[i];
            // 각 교차로에 대해 연결된 도로를 통해 이어진 교차로를 찾습니다.
            for (Integer j : edge_list.get(i)) {
                // 도로로 연결된 두 교차로(i, j)가 하나의 scc그룹이 아니라면 두 교차로는 각자가 속한 scc를 이어주는 교차로가 됩니다.
                if (sccNum[i] != sccNum[j]) scc_edge_list.get(sccNum[i]).add(sccNum[j]);
            }
        }
    }

    public static void bfs() {

    }
}


/*
오랜시간 고민했으나 해결방법을 찾지못하여 참고
강한 결합 요소 (Strongly Connected Componet) 추출 알고리즘을 알게되었습니다.
--------------------------------------

 */

/*
주어진 교차로(정점)와 도로(간선)을 이용하여 SCC를 찾습니다.
같은 SCC그룹에 속하는 교차로는 서로 도달이 가능합니다.
찾은 SCC그룹을 각각 하나의 교차로(정점)으로 보고 도로를 연결합니다.
서로 다른 SCC그룹에 속한 두 교차로에 연결된 도로가 있다면 두 SCC그룹 사이에 간선이 존재합니다.

각 SCC그룹을 하나의 교차로로 보기때문에 SCC그룹내의 모든 ATM에 들어있는 돈을 합하여 sccATM에 저장합니다.

출발 교차로가 속한 SCC그룹에서 출발하여 이동가능한 모든 SCC그룹으로 이동하며 누적된 ATM금액을 dp에 저장합니다.
이때 이동 가능한 모든 SCC그룹으로 완전탐색을 합니다. (BFS를 이용)
dp[i] : i번 SCC그룹에서 얻을 수 있는 최대 금액


 */

