// 매출 하락 최소화
// https://programmers.co.kr/learn/courses/30/lessons/72416?language=java

import java.util.*;

class Solution {
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        
        int n = sales.length;
        // 각 직원의 매출 하락 비용을 저장하는 배열입니다.
        // cost[i][0] : i번 직원이 불참시 매출 하락 비용
        // cost[i][1] : i번 직원이 참석시 매출 하락 비용
        int[][] cost = new int[n][2];
        
        // 서로 연결된 링크를 리스트로 저장합니다.
        // link_list[i] : i번 노드와 연결된 자식이 담긴 리스트
        // link_list[i].get(j) : i번 노드의 j번째 자식
        ArrayList<Integer>[] link_list = new ArrayList[n];
        for(int i = 0 ; i < n ; i++) {
            link_list[i] = new ArrayList<>();
        }
        
        // 1번 노드를 0번으로 저장하므로 모든 노드에 -1을 합니다.
        for(int[] link : links) {
            link_list[link[0]-1].add(link[1]-1);
        }
        
        dfs(0, cost, sales, link_list);
        
        // 1번 직원의 참석시와 불참시 매출 하락 비용 중 더 적은 비용이 답입니다.
        answer = Math.min(cost[0][0], cost[0][1]);
        
        return answer;
    }
    
    // 주어진 트리를 후위 순회하며 매출 하락 비용(cost)를 계산하는 함수입니다.
    public void dfs(int index, int[][] cost, int[] sales, ArrayList<Integer>[] link_list) {
        // index번 직원이 불참시 비용입니다.
        cost[index][0] = 0;
        // index번 직원이 참석시 비용입니다.
        cost[index][1] = sales[index];
        
        // 팀원이 없으면 index번 직원은 팀장이 아니므로 리턴합니다.
        if(link_list[index].isEmpty()) {
            return;
        }
        
        int min_child_cost = Integer.MAX_VALUE;
        // 팀원의 불참시, 참석시 매출 하락 비용 중 더 적은 것을 index번 직원의 매출 하락 비용에 더해야합니다.
        for(int child_index : link_list[index]) {
            dfs(child_index, cost, sales, link_list);
            
            // 팀원이 불참시 매출 하락이 더 적은 경우입니다.
            if(cost[child_index][0] < cost[child_index][1]) {
                cost[index][0] += cost[child_index][0];
                cost[index][1] += cost[child_index][0];
                // index번 팀장의 모든 팀원이 불참시 매출 하락이 최소일 수 있습니다.
                // 한 팀에 반드시 1명은 참석해야 하므로, 팀원의 불참시 비용과 참석시 비용의 차이가 가장 적은 팀원을 워크숍에 참석시킵니다.
                // 다른 팀원이 워크숍에 참석한다면(min_child_cost == 0) 계산할 필요가 없습니다.
                if(min_child_cost != 0) {
                    min_child_cost = Math.min(min_child_cost, cost[child_index][1] - cost[child_index][0]);    
                }
            }
            // 팀원이 참석시 매출 하락이 더 적은 경우입니다.
            else {
                cost[index][0] += cost[child_index][1];
                cost[index][1] += cost[child_index][1];
                // index번 팀장의 팀은 워크숍에 참석하는 팀원이 있으므로 매출 하락 차이가 가장 적은 팀원을 찾을 필요가 없습니다.
                min_child_cost = 0;
            }
        }
        
        cost[index][0] += min_child_cost;
    }
}

/*
dfs를 이용하여 루트에서 자식으로 내려갑니다.
팀장인 노드는 서브트리를 가집니다.

각 노드별로 해당 노드를 선택할 때와 선택하지 않을 때를 나누어 값을 저장합니다.


예제1의 D조직을 예시로
(워크숍 불참시, 참석시)
6번  (0, 14) / 8번  (0, 16) / 2번  (0, 17)
10번 (6,8,2번 노드 중 참석시 최저값, 17)

C조직에서
4번 (0, 18) / 10번 (6,8,2번 노드 중 참석시 최저값, 17)
5번 (4, 10번 노드 중 참석시 최저값, 19)

문제 발생 > 팀원이 참석시 최저값이 팀장의 참석시 값보다 큰 경우 
--------------------------------------------------------------
각 노드에 자식이 존재한다면 팀장입니다.
팀장은 팀원(자식)의 모든 최저 매출 하락 비용을 더해야합니다.
각 직원은 매출 하락 비용을 참석시와 불참시 두 비용을 저장합니다.

C조직에서
5번의 참석시 매출 하락 비용은 5번의 비용 + 4번의 최저 매출 하락 비용 + 10번의 최저 매출 하락 비용
5번의 불참시 매출 하락 비용은 5번의 비용 + 4번의 최저 매출 하락 비용 + 10번의 최저 매출 하락 비용
    > 단, 4번과 10번의 최저 매출 하락 비용이 4번과 10번 둘 모두 불참시라면, 둘 중 하나는 참석해야합니다.
    > 따라서 4번과 10번 각각 참석시와 불참시의 차이가 가장 적은 직원이 참석합니다.(cost[i][1] - cost[i][0]이 가장 적은 직원)


*/