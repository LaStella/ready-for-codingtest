// 튜브의 소개팅
// https://programmers.co.kr/learn/courses/30/lessons/1839?language=java

import java.util.*;

class Solution {
    public int[] solution(int m, int n, int s, int[][] time_map) {
        int moveDistance = 0;
        int sumOfTalkTime = 0;
      
        int[] answer = new int[2];
        answer[0] = moveDistance;
        answer[1] = sumOfTalkTime;
        
        int[][][] board = new int[m][n][2];
        for(int r = 0 ; r < m ; r++) {
            for(int c = 0 ; c < n ; c++) {
                // Arrays.fill(board[i][j], 2500)
                board[r][c][0] = 2500;
            }
        }
        // Arrays.fill(board[0][0], 2500);
        // System.out.println(board[0][0][1]);
        
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 0));
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while(!q.isEmpty()) {
            Node p_node = q.poll();
            int r = p_node.r;
            int c = p_node.c;
            // 현재 좌표가 소개팅녀의 위치라면 종료합니다.
            if(r == n-1 && c == m-1) {
                answer[0] = p_node.moveDistance;
                answer[1] = p_node.sumOfTalkTime;
                break;
            }
            
            // 현재 좌표에 도착했을 때의 이동 횟수가 기존것보다 많으면 넘어갑니다.
            if(board[r][c][0] < p_node.moveDistance) {
                continue;
            }
            
            // 이동 횟수가 더 적으면 현재의 이동 횟수와 대화 시간으로 저장합니다.
            if(board[r][c][0] > p_node.moveDistance) {
                board[r][c][0] = p_node.moveDistance;
                board[r][c][1] = p_node.sumOfTalkTime;
            }
            // 이동 횟수가 같으면 대화 시간이 더 적은 것으로 저장합니다.
            else {
                board[r][c][1] = Math.min(board[r][c][1], p_node.sumOfTalkTime);
            }
            
            // 현재 좌표를 기준으로 상, 하, 좌, 우로 움직인 새로운 좌표를 만들어 탐색합니다.
            for(int i = 0 ; i < 4 ; i++) {
                int nr = r+dr[i];
                int nc = c+dc[i];
                // 좌표가 파티장을 벗어나면 넘어갑니다.
                if(!inRange(nr, nc, m, n)) continue;
                
                // 새로운 좌표가 테이블이 위치하거나 튜브가 미친오리로 변하는 좌표라면 탐색할 수 없습니다.
                if(time_map[nr][nc] != -1 && p_node.sumOfTalkTime + time_map[nr][nc] <= s) {
                    q.add(new Node(nr, nc, p_node.moveDistance+1, p_node.sumOfTalkTime+time_map[nr][nc]))
                }
            }
        }
        
        // for(int r = 0 ; r < m ; r++) {
        //     for(int c = 0 ; c < n ; c++) {
        //         System.out.printf("%1d", board[r][c][0]);
        //     }
        //     System.out.println();
        // }
        
        
        
      
        return answer;
    }
    
    public boolean inRange(int r, int c, int m, int n) {
        return 0 <= r && r < m && 0 <= c && c < n;
    }
}

class Node {
    int r;
    int c;
    int moveDistance;
    int sumOfTalkTime;
    Node(int r, int c, int m, int s) {
        this.r = r;
        this.c = c;
        this.moveDistance = m;
        this.sumOfTalkTime = s;
    }
}

/*
노드에 좌표, 이동 횟수, 대화시간을 저장하여 이를 bfs를 이용하여 탐색합니다.
큐에서 뽑은 노드의 좌표가 도착지점이면 종료합니다.
큐에서 뽑은 노드에 해당하는 이동 횟수와 대화 시간이 기존보다 더 작은지 확인합니다.
작다면 보드를 수정하고, 상,하,좌,우로 움직인 4개의 좌표를 큐에 넣습니다.
작지 않으면 넘어갑니다.

*/