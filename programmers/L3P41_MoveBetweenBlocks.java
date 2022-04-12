// 블록 이동하기
// https://programmers.co.kr/learn/courses/30/lessons/60063?language=java

import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length-1;
        
        int[] end_point = {n, n};
        Queue<int[][]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][n+1];
        q.add(new int[][] {{0, 0}, {0, 1}});
        new Robot(0, new int[][] {{0, 0}, {0, 1}});
        
        // int[][] a = new int[][] {{4, 5}, {5, 5}};
        // int[][] b = new int[][] {{4, 5}, {5, 5}};
        // int[] t = {4, 5};
        // System.out.println(Arrays.equals(a[0], new int[] {4, 5}));
        
        while(!q.isEmpty()) {
            int[][] location = q.poll();
            
            if(Arrays.equals(location[0], end_point) || Arrays.equals(location[1], end_point)) {
                
            }
        }
        
        
        
        
        return answer;
    }
}

class Robot {
    int d;
    int[][] l;
    Robot(int d, int[][] l) {
        this.d = d;
        this.l = l;
    }
}

/*
bfs를 이용 - 상하좌우 이동, 회전
*/