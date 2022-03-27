// 섬 연결하기
// https://programmers.co.kr/learn/courses/30/lessons/42861?language=java

import java.util.*;

class Solution {
    int answer;
    public int solution(int n, int[][] costs) {
        answer = Integer.MAX_VALUE;
        // 두 섬 사이의 다리 여부를 저장할 배열입니다.
        boolean[][] bridge = new boolean[n][n];
        // 건설할 다리의 방문 여부를 저장할 배열입니다.
        boolean[] visited = new boolean[costs.length];
        
        comb(0, 0, costs, bridge, visited);
        
        return answer;
    }
    
    // 두 섬 사이에 건설할 다리를 costs에서 뽑는 조합을 찾는 함수입니다.
    public void comb(int result, int depth, int[][] costs, boolean[][] bridge, boolean[] visited) {
        // 찾은 조합 중 모든 섬이 서로 통행 가능한 경우 건설 비용을 비교하여 최소 비용을 저장합니다.
        if(check(bridge)) {
            answer = Math.min(answer, result);
        }
        // 건설 가능한 모든 다리를 보았을 경우 종료합니다.
        else if(depth == costs.length) {
            return;
        }
        // 건설 가능한 다리의 조합을 찾습니다.
        else {
            int u = costs[depth][0];
            int v = costs[depth][1];
            
            visited[depth] = true;
            bridge[u][v] = true;
            bridge[v][u] = true;
            // depth번에 해당하는 다리를 건설하는 경우
            comb(result+costs[depth][2], depth+1, costs, bridge, visited);
            visited[depth] = false;
            bridge[u][v] = false;
            bridge[v][u] = false;
            // depth번에 해당하는 다리를 건설하지 않는 경우
            comb(result, depth+1, costs, bridge, visited);
        }
    }
    
    // 모든 섬이 서로 통행 가능한지 확인하는 함수입니다.
    public boolean check(boolean[][] bridge) {
        int n = bridge.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        // 0번 섬을 q에 넣습니다. (아무 섬을 넣어도 상관없습니다)
        q.add(0);
        while(!q.isEmpty()) {
            int p_index = q.poll();
            // 뽑힌 섬(p_index번 섬)의 방문여부를 저장합니다.
            visited[p_index] = true;
            // 뽑힌 섬과 연결된 섬이고 아직 방문하지 않은 섬이면 q에 넣습니다.
            for(int i = 0 ; i < n ; i++) {
                if(bridge[p_index][i] == true && !visited[i]) {
                    q.add(i);
                }
            }
        }
        
        // 모든 섬을 방문한 경우 true를 리턴하며 방문하지 못한 섬이 있다면 false리턴합니다.
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i]) return false;
        }
        
        return true;
    }
    
}


/*
조합으로 다리를 선택
하나의 정점을 방문해 모든 정점으로 연결되는지 확인
해당 정점으로 연결이 되어있다면 모든 정점으로 이동이 가능한것을 의미하므로

*/