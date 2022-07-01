// 트리 트리오 중간값
// https://programmers.co.kr/learn/courses/30/lessons/68937?language=java

import java.util.*;

class Solution {
    public int solution(int n, int[][] edges) {
        int answer = 0;
        // 0번이 아닌 1번 부터 시작하므로 n+1개의 리스트를 만듭니다.
        ArrayList<Integer>[] edge_list = new ArrayList[n+1];
        
        for(int i = 1; i < n+1 ; i++) {
            edge_list[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges) {
            edge_list[edge[0]].add(edge[1]);
            edge_list[edge[1]].add(edge[0]);
        }
        
        
        // 임의의 정점(여기선 1)에서 가장 먼 정점을 찾아 a로 저장합니다.
        int[] dist = bfs(1, edge_list, n);
        int a = 1;
        for(int i = 1 ; i < n+1 ; i++) {
            if(dist[a] < dist[i]) a = i;
        }
        
        // a에서 가장 먼 정점 b를 찾습니다.
        dist = bfs(a, edge_list, n);
        int b = a;
        int count = 1;
        for(int i = 1 ; i < n+1 ; i++) {
            if(dist[b] < dist[i]) {
                b = i;
                count = 1;
            }
            else if(dist[b] == dist[i]) {
                count++;
            }
        }
        // 가장 먼 정점이 2개 이상이라면 두 정점이 b, c가 됩니다.
        // 따라서 중간값은 a에서 b또는 c까지의 거리가 됩니다. (a~b, b~c, c~a에서 a~b와 a~c는 거리가 같으므로 중간값이 됩니다.)
        if(count >= 2) return dist[b];
        
        // b에서 가장 먼 정점 c를 찾습니다.
        dist = bfs(b, edge_list, n);
        int c = b;
        count = 1;
        for(int i = 1 ; i < n+1 ; i++) {
            if(dist[c] < dist[i]) {
                b = i;
                count = 1;
            }
            else if(dist[c] == dist[i]) {
                count++;
            }
        }
        // b를 찾을때와 마찬가지로 2개 이상이라면 두 정점이 c, a가 되어 중간값은 b~c와 b~a는 거리가 같아 중간값이 됩니다.
        if(count >= 2) return dist[c];
        // 가장 먼 정점이 하나라면 a와 b가 서로 가장 먼 정점이므로 a와 b 사이의 정점을 c로 고르게됩니다.
        // a와 b사이에 c정점을 고른 다면 중간값은 a와 b사이의 거리-1이 됩니다.
        // ex. 예제 1번에서 4번 정점과 연결된 5번정점이 있다고 할때, a와 b는 각각 1, 5가 됩니다.
        // 1~5번 정점까지의 거리는 4이며 1~5번 사이의 정점을 c로 골라야 하며 중간값이 가장 크게 고른다면 2번 또는 4번 정점을 고르게됩니다.
        // 1~2의 거리는 1, 2~5의 거리는 3, 또는 1~4의 거리는 3, 4~5의 거리는 1
        // 1~3, 3~5의 거리는 2가 되므로 중간값이 2가 되어 위의 두 경우보다 작습니다.
        answer = dist[b]-1;
        
        return answer;
    }
    
    // start번 정점에서 나머지 정점들 사이의 거리를 구하는 함수입니다.
    public int[] bfs(int start, ArrayList<Integer>[] edge_list, int n) {
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        
        while(!q.isEmpty()) {
            int p_index = q.poll();
            for(int n_index : edge_list[p_index]) {
                if(visited[n_index]) continue;
                visited[n_index] = true;
                dist[n_index] = dist[p_index]+1;
                q.add(n_index);
            }
        }
        return dist;
    }
}

/*
주어진 정점 n개에서 3개의 정점을 선택하며, 각 정점간 거리가 길수록 함수값은 커집니다.
1. 주어진 정점중 트리의 끝에 있는 임의의 정점을 선택해야합니다.
    ex) 예제1번의 경우 1, 4 번 정점이 트리의 끝자락에 있습니다.
    > 따라서 임의의 정점(여기선 1로 지정)에서 가장 먼 정점을 찾아 a정점으로 정합니다.
2. 1에서 찾은 a정점에서 거리가 가장 먼 정점(b)을 찾습니다.
    > 가장 먼 정점의 개수가 1개라면 찾은 정점 b로 부터 가장 먼 정점을 다시 탐색합니다.
    > 가장 먼 정점의 개수가 2개 이상이라면 a와 찾은 정점 중 두 정점(b, c)를 고르면 됩니다.
        > a~b사이의 거리 = a~c사이의 거리
        > 따라서 중간값은 a~b사이의 거리가 됩니다.
3. 2에서 찾은 b정점에서 거리가 가장 먼 정점(c)를 찾습니다.
    > 가장 먼 정점이 1개라면 a와 b가 서로 가장 먼 정점을 가리키는 것이므로, 둘 사이의 정점이 c가 됩니다.
        > a~b사이의 거리가 최대값, a~c, c~b거리가 각각 중간값, 최소값가 됩니다.
        > 따라서 c가 a에 가까울 수록 또는 c가 b에 가까울 수록 중간값이 커집니다. (a~b사이의 거리를 d라 하면 a~c = 1, c~b = d-1 또는 a~c = d-1, c~b = 1)
        > 따라서 중간값은 a~b사이의 거리 -1 이 됩니다.
    > 가장 먼 정점이 2개 이상이라면 b정점을 기준으로 c, a 두 정점을 고르면 됩니다.
        > b~c사이의 거리 = b~a사이의 거리
        > 따라서 중간값은 b~c사이의 거리가 됩니다.

*/