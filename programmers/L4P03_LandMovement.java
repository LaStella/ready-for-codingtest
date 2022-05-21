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
        
        int[][] land_group = new int[n][n];
        
        
        int group_numb = 0;
        
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                // 아직 그룹화되지 않은 지형이라면 그룹화를 합니다.
                if(land_group[r][c] == 0) {
                    bfs(r, c, land_group, land, height, ++group_numb);
                }
            }
        }
        
        List<Edge> edge_list = new ArrayList<>();
        
        findEdges(land_group, land, edge_list);
        
        System.out.println(edge_list);
        // 출력
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                System.out.printf("%3d", land_group[r][c]);
            }
            System.out.println();
        }
        
        return answer;
    }
    
    
    // (r, c)에서 부터 상, 하, 좌, 우로 높이차가 height 이하인 격자를 탐색하여 그룹화하는 함수입니다.
    public void bfs(int r, int c, int[][] land_group, int[][] land, int height, int group_numb) {
        Queue<int[]> q = new LinkedList <>();
        q.add(new int[] {r, c});
        land_group[r][c] = group_numb;
        
        while(!q.isEmpty()) {
            int[] location = q.poll();
            int pr = location[0];
            int pc = location[1];
            
            // 큐에서 뽑은 위치를 기준으로 상, 하, 좌, 우를 나타내는 새로운 좌표(nr, nc)를 만들어 탐색합니다.
            for(int i = 0 ; i < 4 ; i++) {
                int nr = pr+dr[i];
                int nc = pc+dc[i];
                
                // 새로운 좌표가 격자 범위를 벗어나거나 이미 방문한 격자는 넘어갑니다.
                if(!inRange(nr, nc) || land_group[nr][nc] != 0) continue;
                
                // 두 격자 칸의 높이차가 height보다 작으면 탐색할 수 있습니다.
                if(Math.abs(land[pr][pc] - land[nr][nc]) <= height) {
                    land_group[nr][nc] = group_numb;
                    q.add(new int[] {nr, nc});
                }
            }
        }
    }
    
    // 모든 격자칸의 상, 하, 좌, 우를 비교하여 다른 그룹이라면 간선리스트에 간선을 추가하는 함수입니다.
    public void findEdges(int[][] land_group, int[][] land, List<Edge> edge_list) {
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < n ; c++) {
                for(int i = 0 ; i < 4 ; i++) {
                    int nr = r+dr[i];
                    int nc = c+dc[i];
                    // 새로운 좌표(nr, nc)가 격자 범위를 벗어나거나 이미 방문한 격자는 넘어갑니다.
                    if(!inRange(nr, nc)) continue;
                    // 새로운 좌표가 이전 좌표와 다른 그룹이라면 사다리 설치 비용과 두 그룹의 번호를 간선 리스트에 저장합니다.
                    if(land_group[r][c] != land_group[nr][nc]) {
                        // 사다리를 설치하는 비용은 두 격자 칸의 높이차입니다.
                        int cost = Math.abs(land[r][c] - land[nr][nc]);
                        edge_list.add(new Edge(land_group[r][c], land_group[nr][nc], cost));
                    }
                }
            }
        }  
    }
    
    // 주어진 좌표가 격자를 벗어나는지 확인하는 함수입니다.
    public boolean inRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
    
    // 사다리를 설치할 그룹 번호 a, b와 비용 cost를 저장할 간선 객체입니다.
    class Edge {
        int a;
        int b;
        int cost;
        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
}

/*
bfs로 상하좌우를 비교하며 방문
visited에 기록
height 범위 내의 이동이 가능한 영역을 모두 방문한다.
height 범위를 벗어난 이동이 필요한 경우 필요한 비용 중 최소 비용을 저장함
height 범위 내의 이동을 모두 마친 후 저장한 최소 비용을 더함

land의 모든 좌표를 방문 > height범위 이동 가능한 영역을 나눔 > 영역별 최소 비용 사다리 설치가 가능한 곳에 설치 (사다리가 중복 설치 되지않도록함)
반례존재
------------------------------------------------
bfs를 재귀
bfs를 이용하여 상하좌우를 방문
height 범위 내의 이동이 가능한 영역을 모두 방문한다.
height 범위를 벗어난 이동이 필요한 경우 필요한 비용 중 최소 비용을 저장함
최소 비용으로 저장된 좌표를 다시 bfs
반례존재
------------------------------------------------
사다리 없이 이동가능한 영역을 묶어 정점으로 취급
정점과 정점 사이의 간선을 통해 최소신장트리(MST)를 구성

*/