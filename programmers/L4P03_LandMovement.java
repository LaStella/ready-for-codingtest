// 지형 이동
// https://programmers.co.kr/learn/courses/30/lessons/62050?language=java

import java.util.*;

class Solution {
    int n;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    public int solution(int[][] land, int height) {
        int answer = 0;
        n = land.length;
        
        boolean[][] visited = new boolean[n][n];
        
        answer = bfs(0, 0, visited, land, height);
        
        return answer;
    }
    
    public int bfs(int r, int c, boolean[][] visited, int[][] land, int height) {
        System.out.println("!");
        Queue<int[]> q = new LinkedList <>();
        q.add(new int[] {r, c});
        visited[r][c] = true;
        
        int result = 0;
        
        // 사다리 설치에 드는 최소 비용과 위치를 저장합니다.
        int min_cost = Integer.MAX_VALUE;
        int mr = 0, mc = 0;
        
        while(!q.isEmpty()) {
            int[] rc = q.poll();
            int pr = rc[0];
            int pc = rc[1];
            
            for(int i = 0 ; i < 4 ; i++) {
                int nr = pr+dr[i];
                int nc = pc+dc[i];
                
                if(!inRange(nr, nc) || visited[nr][nc]) continue;
                
                // 사다리를 설치하는 비용은 두 격자 칸의 높이차입니다.
                int cost = Math.abs(land[pr][pc] - land[nr][nc]);
                
                // height보다 작은 차이일 경우 사다리를 설치할 필요가 없습니다.
                if(cost <= height) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
                // 사다리를 설치해야 이동이 가능한 경우 설치 비용을 비교합니다.
                else {
                    if(cost < min_cost) {
                        System.out.println(cost);
                        System.out.println(nr);
                        System.out.println(nc);
                        min_cost = cost;
                        mr = nr;
                        mc = nc;
                    }
                }
            }
        }
        
        if(mr != 0 && mc != 0) {
            min_cost += bfs(mr, mc, visited, land, height);
        }
        
        return min_cost;
    }
    
    public boolean inRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}

/*
bfs로 상하좌우를 비교하며 방문
visited에 기록
height 범위 내의 이동이 가능한 영역을 모두 방문한다.
height 범위를 벗어난 이동이 필요한 경우 필요한 비용 중 최소 비용을 저장함
height 범위 내의 이동을 모두 마친 후 저장한 최소 비용을 더함

land의 모든 좌표를 방문 > height범위 이동 가능한 영역을 나눔 > 영역별 최소 비용 사다리 설치가 가능한 곳에 설치 (사다리가 중복 설치 되지않도록함)

------------------------------------------------
bfs를 재귀
bfs를 이용하여 상하좌우를 방문
height 범위 내의 이동이 가능한 영역을 모두 방문한다.
height 범위를 벗어난 이동이 필요한 경우 필요한 비용 중 최소 비용을 저장함
최소 비용으로 저장된 좌표를 다시 bfs


*/
