// ATM
// https://www.acmicpc.net/problem/4013

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, start;
    static int visitCount = 0, sccCount = 0;
    static int[] visitOrder, sccNum, sccAtm, dp;
    static ArrayList<Integer>[] edges;
    static ArrayList<Integer>[] scc_edges;
    static boolean[] finished;
    static Stack<Integer> st = new Stack<>(); // SCC탐색시 방문한 교차로(정점)를 저장하는 스택

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N+1]; // edges[i] : i번 교차로에서 연결된 도로들
        // N개의 교차로(정점)를 생성합니다. (0번째는 더미)
        for (int i = 0 ; i <= N ; i++) {
            edges[i] = new ArrayList<>();
        }

        // M개의 도로를 입력받아 저장합니다.
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int edge_start = Integer.parseInt(st.nextToken());
            int edge_end = Integer.parseInt(st.nextToken());

            // 시작 교차로에서 끝 교차로로 가는 도로를 추가하여 두 교차로를 이어줍니다.
            edges[edge_start].add(edge_end);
        }


        visitOrder = new int[N+1]; // visitOrder[i] : i번 교차로의 방문 순서
        finished = new boolean[N+1]; // finished[i] : i번 교차로의 SCC 탐색 종료 여부
        sccNum = new int[N+1]; // sccNum[i] : i번 교차로의 SCC그룹 번호

        // 모든 교차로(정점)에 대해 DFS를 수행하여 SCC를 찾습니다. (타잔 알고리즘)
        for (int i = 1 ; i <= N ; i++) {
            if (!finished[i]) dfs(i); // 이미 SCC그룹을 찾은 교차로는 찾을 SCC를 탐색할 필요가 없습니다.
        }

        makeSccGraph(); // SCC그룹(정점)간 도로(간선)을 연결하여 그래프를 형성합니다.

        sccAtm = new int[sccCount]; // sccAtm[i] : i번 SCC그룹에 있는 ATM의 금액 총합
        // 각 교차로의 ATM에 들어있는 돈을 SCC그룹에 따라 하나의 ATM으로 더합니다.
        for (int i = 1 ; i <= N ; i++) {
            // i번 교차로가 속한 SCC그룹의 ATM에
            sccAtm[sccNum[i]] += Integer.parseInt(br.readLine());
        }

        st = new StringTokenizer(br.readLine());

        // 시작 교차로와 레스토랑의 개수를 입력받습니다.
        start = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        // dp[i] : i번 scc그룹에서 얻을 수 있는 최대 금액
        dp = new int[sccCount];

        bfs();

        // scc그룹에서 레스토랑이 있는 지점 중 최대 금액을 탐색합니다.
        int answer = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < P ; i++) {
            answer = Math.max(answer, dp[sccNum[Integer.parseInt(st.nextToken())]]);
        }

        System.out.println(answer);
    }

    // DFS알고리즘을 이용하여 SCC를 찾는 함수입니다.
    // 자신의 부모 교차로(정점)을 리턴합니다.
    public static int dfs(int u) {
        // u번 교차로를 방문한 순서를 저장합니다.
        visitOrder[u] = visitCount++;
        // 스택에 방문한 교차로의 번호를 저장합니다. (방문 순서를 저장하는것이 아닙니다.)
        st.push(u);

        // 부모는 자기 자신의 방문 순서로 초기화합니다.
        int parent = visitOrder[u];
        // v : u교차로와 연결된 이웃 교차로
        for (Integer v : edges[u]) {
            // 방문하지 않은 이웃 교차로
            // 방문 순서가 빠를 수록 상위 교차로입니다.
            // 이웃 교차로의 부모가 현재 부모보다 작다면 현재 교차로의 부모를 이웃 교차로의 부모로 변경합니다.
            if (visitOrder[v] == 0) parent = Math.min(parent, dfs(v));
            // 방문했으나 dfs함수가 처리중인 교차로
            else if (!finished[v]) parent = Math.min(parent, visitOrder[v]);
        }

        // 부모가 자기 자신인 경우
        if (parent == visitOrder[u]) {
            // 스택에서 자기 자신이 나올때(pop == u)까지 뽑아 하나의 scc로 묶습니다.
            // 각 교차로에 대해서 같은 scc그룹이라면 같은 sccNum을 가지게 됩니다.
            while(true) {
                int pop = st.pop();
                sccNum[pop] = sccCount;
                finished[pop] = true;
                if (pop == u) break;
            }
            sccCount++;
        }

        return parent;
    }

    // 각 SCC그룹을 하나의 교차로(정점)으로 보고 도로(간선)를 연결해 그래프를 만드는 함수입니다.
    public static void makeSccGraph() {
        scc_edges = new ArrayList[sccCount];
        // 각 SCC그룹을 정점으로 만듭니다.
        for (int i = 0 ; i < sccCount; i++) {
            scc_edges[i] = new ArrayList<>();
        }

        for (int i = 1 ; i <= N ; i++) {
            // 각 교차로(i)에서 연결된 도로(edges[i])로 이어진 교차로(j)를 찾습니다.
            for (Integer j : edges[i]) {
                /*
                    도로로 연결된 두 교차로(i, j)가 같은 SCC그룹이 아니라면
                    i교차로가 속한 SCC 그룹에서 j교차로가 속한 SCC그룹으로 간선으로 이어집니다.
                 */
                if (sccNum[i] != sccNum[j]) scc_edges[sccNum[i]].add(sccNum[j]);
            }
        }
    }

    // scc그룹을 완전탐색하여 각 그룹에서 얻을 수 있는 최대 금액을 계산합니다.
    // 타잔 알고리즘을 이용하여 SCC를 찾았기 때문에 현재 그래프에서 사이클은 존재하지 않습니다.
    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(sccNum[start]);
        dp[sccNum[start]] = sccAtm[sccNum[start]];

        while (!q.isEmpty()) {
            int nowNum = q.poll();

            for (Integer nextNum : scc_edges[nowNum]) {
                // nextNum번 SCC그룹에서 얻을 수 있는 최대 금액을 계산합니다.
                if (dp[nextNum] < dp[nowNum] + sccAtm[nextNum]) {
                    dp[nextNum] = dp[nowNum] + sccAtm[nextNum];
                    q.add(nextNum);
                }
            }
        }
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



문제발생. 타잔 알고리즘을 잘못이해하여 방문 순서를 고려하지 않은 SCC탐색을 실행하였습니다.
 */

