class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        boolean[][] visited = new boolean[m][n];
        
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(picture[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++;
                    int areaCount = DFS(i, j, visited, picture);
                    if(areaCount > maxSizeOfOneArea) {
                        maxSizeOfOneArea = areaCount;
                    }
                }
            }
        }
        
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int DFS(int y, int x, boolean[][] visited, int[][] picture) {
        int areaCount = 0;
        
        areaCount++;
        visited[y][x] = true;
        if(y-1 >= 0) {
            if(picture[y][x] == picture[y-1][x] && !visited[y-1][x]) {
                areaCount += DFS(y-1, x, visited, picture);
            }
        }
        
        if(y+1 < picture.length) {
            if(picture[y][x] == picture[y+1][x] && !visited[y+1][x]) {
                areaCount += DFS(y+1, x, visited, picture);
            }
        }
        
        if(x-1 >= 0) {
            if(picture[y][x] == picture[y][x-1] && !visited[y][x-1]) {
                areaCount += DFS(y, x-1, visited, picture);
            }
        }
        
        if(x+1 < picture[0].length) {
            if(picture[y][x] == picture[y][x+1] && !visited[y][x+1]) {
                areaCount += DFS(y, x+1, visited, picture);
            }
        }
        
        return areaCount;
    }
}