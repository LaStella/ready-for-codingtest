// 동굴 탐험
// https://programmers.co.kr/learn/courses/30/lessons/67260?language=java

import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        boolean[] visited = new boolean[n];
        
        // 통로를 리스트에 저장합니다.
        // route.get(i)는 i번 방과 연결된 모든 통로가 저장됩니다.
        ArrayList<ArrayList<Integer>> route = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            route.add(new ArrayList<>());
        }
        for(int[] p : path) {
            route.get(p[0]).add(p[1]);
            route.get(p[1]).add(p[0]);
        }
        
        // 모든 순서를 맵으로 저장합니다. (key : 방 번호, value : key를 들어가기 위해 먼저 방문해야하는 방번호)
        HashMap<Integer, Integer> order_map = new HashMap<>();
        for(int[] o : order) {
            order_map.put(o[1], o[0]);
        }
        
        // 큐에는 현재 방문 가능한 방 번호가 들어갑니다.
        Queue<Integer> q = new LinkedList<>();
        visited[0] = true;
        q.add(0);
        // 큐에 있는 방을 순회한 횟수를 저장합니다.
        int count = 0;
        
        while(!q.isEmpty()) {
            // count가 큐의 크기와 같다면 한바퀴를 순회하였으므로 탐험이 불가능한 상태입니다.
            if(count == q.size()) {
                return false;
            }
            int p_index = q.poll();
            
            // 입장 순서가 존재하는 방인지 확인합니다.
            if(order_map.containsKey(p_index)) {
                if(!visited[order_map.get(p_index)]) {
                    q.add(p_index);
                    count++;
                    continue;
                }
            }
            
            // 입장 순서가 없거나, 입장 순서를 충족시키는 방이라면 방문처리를 하고 순회 횟수를 초기화합니다.
            count = 0;
            visited[p_index] = true;
            
            // 현재 방과 연결된 다른 방들을 큐에 넣습니다.
            for(int i : route.get(p_index)) {
                // 이미 방문한 방은 넣지않습니다.
                if(!visited[i]) {
                    q.add(i);
                }
            }
        }
        
        return answer;
    }
}

/*
order를 map에 저장합니다.

예제1번을 예시로
0번 방으로 시작
0번방을 방문할 수 있는지 확인합니다.
방문할 수 있다면 0번과 연결된 모든 방을 큐에 넣습니다. (1,3,7)
방문할 수 없다면 0번을 다시 큐에 넣습니다.

문제발생> 방과 방 사이의 통로들을 2차 boolean배열 형태로 저장하였으나, n의 크기가 커짐에 따라 메모리가 초과되는 문제가 발생하였습니다.
이를 해결하기 위해 각 통로들을 ArrayList를 이용하여 저장하였습니다.
문제발생> 마지막 효율성 테스트에서 시간초과가 발생하였습니다. 입장 순서가 존재하는 방인 경우 먼저 방문해야 하는 방을 방문하지 않은 경우 다시 큐에 넣게되는데,
이때 먼저 방문해야 하는 방을 방문하여도 큐에서 해당 방의 차례가 오기까지 한참 걸릴 수 있는 문제가 있습니다.
*/