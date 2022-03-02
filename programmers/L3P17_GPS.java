// GPS
// https://programmers.co.kr/learn/courses/30/lessons/1837?language=java

import java.util.*;

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;
        // 거점의 번호가 1부터 시작하므로 n+1의 범위를 가집니다. (0번은 사용하지 않습니다.)
        boolean[][] edges = new boolean[n+1][n+1];
        
        // 거점과 거점사이의 도로의 존재여부를 저장합니다.
        for(int[] edge : edge_list) {
            edges[edge[0]][edge[1]] = true;
            edges[edge[1]][edge[0]] = true;
        }
        
        // 한 거점에서 머무를 수도 있습니다.
        for(int i = 1 ; i < n+1 ; i++) {
            edges[i][i] = true;
        }
        
        // 시간에 따라 거점으로의 최소 오류 횟수를 저장합니다.
        // ex) result[t][n] = t시간에 n거점으로 이동할 경우 최소 오류 횟수
        int[][] result = new int[k][n+1];
        // gps_log의 최대 개수는 100이며 가능한 최대 오류 횟수는 출발지와 도착지를 제외한 98입니다.
        // 가능한 최대 오류 횟수를 넘어서는 값으로 초기화합니다.
        for(int[] arr : result) {
            Arrays.fill(arr, 99);
        }
        // 시작 거점과 도착 거점은 바뀔 수 없으며, gps_log[0]이 출발지 이므로 출발지의 오류 횟수를 0으로 초기화합니다.
        result[0][gps_log[0]] = 0;
        // 여기서 t는 시간을 의미하나 gps_log가 0부터 시작하므로 t=0부터 시작합니다.
        for(int t = 1 ; t < k ; t++) {
            // t시간에 i거점으로 이동하는 최소 오류를 계산합니다. 
            for(int i = 1 ; i <= n ; i++) {
                // 모든 거점 중에서
                for(int j = 1 ; j <= n ; j++) {
                    // i거점과 연결되어 있는 거점이라면 t-1시간의 j거점 중 최소 오류 횟수를 가져옵니다.
                    if(edges[i][j]) {
                        result[t][i] = Math.min(result[t][i], result[t-1][j]);
                    }
                }
                // i거점이 t시간의 gps_log와 일치하지 않으면 오류 수정으로 1을 더합니다.
                if(gps_log[t] != i) {
                    result[t][i]++;
                }
            }
        }
        
        // gps_log의 마지막 거점이 도착지로 출발지로부터 해당 도착지까지 오류 횟수를 가져옵니다.
        // 오류 횟수가 최대 오류 횟수(98)같거나 작으면 answer이 됩니다.
        if(result[k-1][gps_log[k-1]] < 99) {
            answer = result[k-1][gps_log[k-1]];
        }
        // 최대 오류 횟수(98)보다 많다면 올바른 경로로 수정이 불가능하므로 -1을 리턴합니다.
        else {
            answer = -1;
        }
        
        return answer;
    }
}

/*
출발지 부터 도착지 까지 오류 처리 횟수가 최소
1부터 k번째 경로 까지
t=i 일때 거점n으로의 최소 오류 경로 = t=i-1일때 거점n과 연결된 거점 중 최소오류 + (log와 거점n이 일치하지 않을경우 +1)
시간 거점
    0   1   2   3   4   5   6   7
t=0 99  0   99  99  99  99  99  99
t=1 99  1   0   1   100 100 100 100
t=2 99  1   1   0   1   2   100 100
t=3 99  1   1   0   1   1   2   3
t=4 99  1   1   1   1   1   1   2
t=5 99  2   2   2   2   2   2   1

*/