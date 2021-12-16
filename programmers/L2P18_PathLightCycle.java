import java.util.*;


class Solution {
    public int[] solution(String[] grid) {
        int[] answer = {};
        String[][] gridMatrix = new String[grid.length][];
        boolean[][][] visited = new boolean[grid.length][grid[0].length()][4];
        ArrayList<Integer> answerList = new ArrayList<>();
        
        for(int i = 0 ; i < grid.length ; i++) {
            gridMatrix[i] = grid[i].split("");
        }
        
        for(int i = 0 ; i < grid.length ; i++) {
            for(int j = 0 ; j < grid[0].length() ; j++) {
                for(int d = 0 ; d < 4 ; d++) {
                    if (!visited[i][j][d]) {
                        answerList.add(makeCycle(visited, gridMatrix, i, j, d, 0));
                    }
                }
            }
        }
        
        answer = answerList.stream().sorted().mapToInt(i -> i).toArray();

        return answer;
    }
    
    public int makeCycle(boolean[][][] visited, String[][] grid, int row, int column, int direction, int count) {
        if(visited[row][column][direction]) {
            return count;
        }
        
        visited[row][column][direction] = true;
        count++;
        // System.out.print("["+grid[row][column]+"]");
        
        int[][] nextVal = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        row = (row+nextVal[direction][0]+grid.length) % grid.length;
        column = (column+nextVal[direction][1]+grid[0].length) % grid[0].length;
        
        // System.out.print(grid[row][column]+" ");
        if(grid[row][column].equals("L")) {
            return makeCycle(visited, grid, row, column, (direction+3)%4, count);
        }
        else if(grid[row][column].equals("R")) {
            return makeCycle(visited, grid, row, column, (direction+1)%4, count);
        }
        else {
            return makeCycle(visited, grid, row, column, direction, count);
        }
    }
}