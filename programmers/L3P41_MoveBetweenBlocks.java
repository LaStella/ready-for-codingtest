// 블록 이동하기
// https://programmers.co.kr/learn/courses/30/lessons/60063?language=java

import java.util.*;

class Solution {
    int n;
    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        
        int[] end_point = {n-1, n-1};
        int[][] next_value = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        Queue<Robot> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        
        // 시작지점 (0, 0), (0, 1)로부터 시작합니다.
        q.add(new Robot(0, new int[][] {{0, 0}, {0, 1}}));
        visited[0][0] = true;
        visited[0][1] = true;
        // int[][] a = new int[][] {{4, 5}, {5, 5}};
        // int[][] b = new int[][] {{4, 5}, {5, 5}};
        // int[] t = {4, 5};
        // System.out.println(Arrays.equals(a[0], new int[] {4, 5}));
        
        while(!q.isEmpty()) {
            Robot p_robot = q.poll();
            
            if(Arrays.equals(p_robot.l[0], end_point) || Arrays.equals(p_robot.l[1], end_point)) {
                System.out.println(p_robot.t);
                break;
            }
            else {
                for(int i = 0 ; i < 4 ; i++) {
                    int[][] nl = new int[2][2];
                    nl[0][0] = p_robot.l[0][0]+next_value[i][0];
                    nl[0][1] = p_robot.l[0][1]+next_value[i][1];
                    nl[1][0] = p_robot.l[1][0]+next_value[i][0];
                    nl[1][1] = p_robot.l[1][1]+next_value[i][1];
                    if(isOutOfBoard(nl[0][0]) || isOutOfBoard(nl[0][1]) || isOutOfBoard(nl[1][0]) || isOutOfBoard(nl[1][1])) {
                        continue;
                    }
                    q.add(new Robot(p_robot.t+1, nl));
                }
            }
        }
        
        
        
        
        return answer;
    }
    
    public boolean isOutOfBoard(int l) {
        if(l < 0 || l >= n) return true;
        else return false;
    }
}

class Robot {
    int t;
    int[][] l;
    Robot(int t, int[][] l) {
        this.t = t;
        this.l = l;
    }
}

/*
bfs를 이용 - 상하좌우 이동, 회전
*/