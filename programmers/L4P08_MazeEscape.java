// 미로 탈출
// https://programmers.co.kr/learn/courses/30/lessons/81304?language=java

import java.util.*;

class Solution {
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = Integer.MAX_VALUE;
        
        // 출발지에서 각 정점으로 가는 최소 시간입니다. ex. dist[2] = start에서 2번 정점으로 가는 최소 시간
        // HashMap<String, int[]> dist_map = new HashMap<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        int[][] route = new int[n+1][n+1];
        int[][] reverse_route = new int[n+1][n+1];
        
        // 주어지는 통로를 정순과 역순으로 저장합니다.
        // 트랩의 발동 여부를 저장하는 상태에 따라 정순과 역순 통로를 사용하기 때문입니다.
        for(int[] road : roads) {
            if(route[road[0]][road[1]] == 0) {
                route[road[0]][road[1]] = road[2];
            }
            else {
                route[road[0]][road[1]] = Math.min(route[road[0]][road[1]], road[2]);
            }
            if(reverse_route[road[1]][road[0]] == 0) {
                reverse_route[road[1]][road[0]] = road[2];
            }
            else {
                reverse_route[road[1]][road[0]] = Math.min(reverse_route[road[1]][road[0]], road[2]);
            }
        }
        
        // 트랩이 있는 정점을 저장합니다.
        // 각 트랩마다 번호를 부여합니다. (ex. traps[2, 3] 인 경우 2,3번 정점에 각각 0,1번 트랩을 부여)
        HashMap<Integer, Integer> trapMap = new HashMap<>();
        for(int i = 0 ; i < traps.length ; i++) {
            trapMap.put(traps[i], i);
        }
        
        // 다익스트라 알고리즘
        // 누적 시간이 적은 순으로 정렬하므로 우선순위큐를 사용합니다.
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0, "0".repeat(traps.length)));
        
        while(!q.isEmpty()) {
            Node p_node = q.poll();
            int p_index = p_node.index;
            int p_time = p_node.time;
            String p_status = p_node.status;
            
            // 현재 정점이 도착 정점이라면 중단합니다.
            if(p_index == end) {
                break;
            }
            
            // 현재 정점이 트랩인지 아닌지 확인합니다.
            // 트랩이라면 상태를 변경해줍니다.
            boolean trapActivated = false;
            if(trapMap.containsKey(p_index)) {
                int trap_index = trapMap.get(p_index);
                if(p_status.charAt(trap_index) == '0') {
                    StringBuilder sb = new StringBuilder(p_status);
                    sb.setCharAt(trap_index, '1');
                    p_status = sb.toString();
                    trapActivated = true;
                }
                else {
                    StringBuilder sb = new StringBuilder(p_status);
                    sb.setCharAt(trap_index, '0');
                    p_status = sb.toString();
                }
            }
            
            // 정순 방향 통로
            // 현재 정점에서 출발하는 모든 통로를 사용합니다.
            // 도착지가 트랩 정점이라면 트랩 발동 여부에 따라 통로의 사용을 결정합니다.
            for(int i = 1 ; i < n+1 ; i++) {
                if(route[p_index][i] != 0) {
                    // 도착지가 트랩 정점인 경우
                    if(trapMap.containsKey(i)) {
                        int i_trap_index = trapMap.get(i);
                        if((p_status.charAt(i_trap_index) == '0' && !trapActivated) || (p_status.charAt(i_trap_index) == '1' && trapActivated)) {
                            // int[] dist = dist_map.getOrDefault(p_status, basic_dist.clone());
                            if(dist[i] > p_time + route[p_index][i]) {
                                dist[i] = p_time + route[p_index][i];
                                // dist_map.put(p_status, dist);
                                q.add(new Node(i, dist[i], p_status));
                            }
                            else {
                                q.add(new Node(i, p_time + route[p_index][i], p_status));
                            }
                        }
                    }
                    // 도착지가 일반 정점인 경우
                    else {
                        if(trapActivated) {
                            continue;
                        }
                        else {
                            // int[] dist = dist_map.getOrDefault(p_status, basic_dist.clone());
                            if(dist[i] > p_time + route[p_index][i]) {
                                dist[i] = p_time + route[p_index][i];
                                // dist_map.put(p_status, dist);
                                q.add(new Node(i, dist[i], p_status));
                            }
                            else {
                                q.add(new Node(i, p_time + route[p_index][i], p_status));
                            }
                        }
                    }
                }
            }
            
            // 역순 방향 통로
            for(int i = 1 ; i < n+1 ; i++) {
                if(reverse_route[p_index][i] != 0) {     
                    if(trapMap.containsKey(i)) {
                        int i_trap_index = trapMap.get(i);
                        if((p_status.charAt(i_trap_index) == '0' && trapActivated) || (p_status.charAt(i_trap_index) == '1' && !trapActivated)) {
                            // int[] dist = dist_map.getOrDefault(p_status, basic_dist.clone());
                            if(dist[i] > p_time + reverse_route[p_index][i]) {
                                dist[i] = p_time + reverse_route[p_index][i];
                                // dist_map.put(p_status, dist);
                                q.add(new Node(i, dist[i], p_status));
                            }
                            else {
                                q.add(new Node(i, p_time + reverse_route[p_index][i], p_status));
                            }
                        }
                    }
                    else {
                        if(!trapActivated) {
                            continue;
                        }
                        else {
                            // int[] dist = dist_map.getOrDefault(p_status, basic_dist.clone());
                            if(dist[i] > p_time + reverse_route[p_index][i]) {
                                dist[i] = p_time + reverse_route[p_index][i];
                                // dist_map.put(p_status, dist);
                                q.add(new Node(i, dist[i], p_status));
                            }
                            else {
                                q.add(new Node(i, p_time + reverse_route[p_index][i], p_status));
                            }
                        }
                    }
                }
            }
        }
        
        answer = dist[end];
        // for(String key : dist_map.keySet()) {
        //     answer = Math.min(answer, dist_map.get(key)[end]);
        // }
        
        
        return answer;
    }
    
    class Node implements Comparable<Node> {
        int index;
        int time;
        String status;
        public Node(int index, int time, String status) {
            this.index = index;
            this.time = time;
            this.status = status;
        }
        
        @Override
        public int compareTo(Node other) {
            return this.time - other.time;
        }
    }
}
/*
1.
2차 배열에 모든 경로를 저장
int[][] route, route[a][b]는 a에서 b로 연결되는 경로 

trap을 밟으면 route[a][b] 를 route[b][a]로 전환
route[b][a]가 존재할 경우 둘의 소모값만 바꿔준다.
int temp = route[a][b];
route[a][b] = route[b][a];
route[b][a] = temp;

bfs를 이용하여 탐색
문제발생> 트랩에 의해 바뀌는 간선때문에 bfs를 이용할 수 없습니다.
------------------------------
2.
다익스트라 이용
출발 정점에서 연결된 정점을 방문
> 방문 시 출발지에서 목적지 정점까지 걸리는 시간이 작은 값을 저장, int[] dist배열 이용, dist[2] = 출발지에서 2번 정점까지 걸리는 시간
> 누적 시간은 이동한 정점에 저장합니다. Node 객체 이용
> 누적 시간이 가장 적은 정점을 뽑아 다음 정점으로 이동합니다.
> 정점이 end에 도달하면 dist[end]와 비교하여 최소값을 저장합니다.
> trap의 발동 여부를 항상 저장하여 다음 정점으로 정보를 넘겨줍니다.
    > ex. 입출력예2번에서 2번 트랩을 밟을 경우, 1번으로 이동하는 경우와 3번으로 이동하는 경우가 존재, 3번으로 이동할 경우 트랩이 발동하며, 1번으로 이동한 경우 트랩은 발동하지 않습니다. 서로 다른 경우이므로 트랩의 발동 유무는 공유하지 않아야합니다.
    > 트랩은 최대 10개 이므로 각 트랩에 순서대로 번호를 부여하여 0,1로 작동 여부를 저장합니다. (ex. trap이 10개, 1,2번 트랩 발동인 경우 trap_status = 1100000000)
문제발생
dist[i]는 출발지에서 i정점까지 최소 시간을 기록하는 배열
허나 문제의 예2번에서 1>2>3>2로 순으로 진행될 경우, 마지막 2번 정점을 방문시 기존 2번 정점 방문시보다 시간이 더 크므로 넘어갈 수 없습니다.
dist에 저장하지 않고 q에 정점을 넣는 것으로 해결, 하지만 시간이 늘어나게됩니다.

테스트케이스7 실패
서로 다른 두 방 사이에 직접 연결된 길이 여러개 존재할 수도 있습니다. 라는 조건에서 방향만 다른길이 존재할 것으로생각하였으나, 같은 방향길도 여러개 존재할 수도 있으므로
기존에 저장된 route라면 최소값이 저장되도록 변경하여 해결하였습니다.

*/