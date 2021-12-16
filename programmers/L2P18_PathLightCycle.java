// 빛의 경로 사이클
// https://programmers.co.kr/learn/courses/30/lessons/86052?language=java

import java.util.*;

class Solution {
    public int[] solution(String[] grid) {
        int[] answer = {};
        // 주어진 문자열 배열 grid를 2차원 string 배열로 변환하였습니다.
        // 변환하지 않고 문자열을 그대로 쓸 수 있지만 직관적이지 못하여 하지않았습니다.
        String[][] gridMatrix = new String[grid.length][];

        // 각 칸에서 하, 좌, 상, 우 방향으로 사이클을 생성하였는지 판별하는 boolean 배열입니다.
        boolean[][][] visited = new boolean[grid.length][grid[0].length()][4];
        ArrayList<Integer> answerList = new ArrayList<>();
        
        // grid를 2차원 배열로 변환합니다.
        for(int i = 0 ; i < grid.length ; i++) {
            gridMatrix[i] = grid[i].split("");
        }
        
        // 각 칸마다 하, 좌, 상, 우 방향으로 사이클을 생성합니다.
        for(int i = 0 ; i < grid.length ; i++) {
            for(int j = 0 ; j < grid[0].length() ; j++) {
                for(int d = 0 ; d < 4 ; d++) {
                    if (!visited[i][j][d]) {
                        answerList.add(makeCycle(visited, gridMatrix, i, j, d));
                    }
                }
            }
        }
        
        answer = answerList.stream().sorted().mapToInt(i -> i).toArray();

        return answer;
    }
    
    public int makeCycle(boolean[][][] visited, String[][] grid, int r, int c, int d) {
        // 현재의 row, column을 변경할때 쓰는 가중치입니다.
        // 순서대로 하, 좌, 상, 우 방향을 뜻합니다.
        int[][] nextVal = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        int count = 0;
        
        while(true) {
            if(visited[r][c][d]) {
                break;
            }
            
            visited[r][c][d] = true;
            count++;
            
            r = (r+nextVal[d][0]+grid.length) % grid.length;
            c = (c+nextVal[d][1]+grid[0].length) % grid[0].length;
            // 칸에 써져 있는 글자를 보고 방향을 변환합니다.
            if(grid[r][c].equals("L")) {
                // 좌회전일 경우 현재의 방향에서 3을 더하고 4(방향 수)만큼 나눈 나머지가 됩니다.
                d = (d+3) % 4;
            }
            else if(grid[r][c].equals("R")) {
                // 우회전은 1을 더합니다. 마찬가지로 방향 수 만큼 나눈 나머지가 됩니다.
                d = (d+1) % 4;
            }
        }
        
        return count;
    }
}