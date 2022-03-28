// 섬 연결하기
// https://programmers.co.kr/learn/courses/30/lessons/42861?language=java

// 핵심 : 크루스칼 알고리즘(Kruskal Algorithm)사용

import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int[] parent = new int[n];
        int bridge_count = 0;
        
        // 건설할 다리를 비용순으로 정렬합니다.
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        // 모든 섬들의 부모를 자신으로 초기화합니다. (섬의 집합을 만드는 과정입니다.)
        for(int i = 0 ; i < n ; i++) {
            parent[i] = i;
        }
        
        // 비용이 작은 다리부터 건설할지 확인합니다.
        for(int[] cost : costs) {
            // 다리가 건설되는 두 섬(u, v)의 부모섬(섬 집합의 꼭대기)을 찾습니다.
            int u_parent = find(cost[0], parent);
            int v_parent = find(cost[1], parent);
            
            // 두 섬의 부모가 서로 같은 경우 통행이 가능하므로 건설할 필요가없습니다.
            // 두 섬의 부모가 서로 다른 경우 다리를 건설하여 두 섬 집합을 이어줍니다.
            if(u_parent != v_parent) {
                answer += cost[2];
                union(u_parent, v_parent, parent);
                // 모든 섬이 서로 통행하는데 필요한 다리는 n-1개이므로 필요한 다리가 모두 건설되면 중단합니다.
                if(++bridge_count == n-1) break;
            }
        }
        
        return answer;
    }
    
    // 두 집합의 섬을 하나의 집합으로 합치는 함수입니다.
    public void union(int u, int v, int[] parent) {
        // 두 부모섬 중 번호가 작은 섬을 부모로 합니다. (부모섬을 정하는 규칙이므로 번호가 큰 섬을 부모로 하여도 상관없습니다.)
        if(u > v) {
            parent[u] = v;
        }
        else {
            parent[v] = u;
        }
    }
    
    // 주어지는 index번 섬이 속한 집합의 부모섬을 찾는 함수입니다.
    public int find(int index, int[] parent) {
        if(parent[index] == index) {
            return index;
        }
        else {
            return find(parent[index], parent);
        }
    }
}

/*
크루스칼 이용
*/