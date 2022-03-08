// 등굣길
// https://programmers.co.kr/learn/courses/30/lessons/42898?language=java

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        // 길을 나타낼 배열입니다.
        int[][] road = new int[n][m];
        // 물에 잠긴 지역의 존재 여부를 나타낼 배열입니다.
        boolean[][] puddles_exist = new boolean[n][m];
        
        // 물에 잠긴 지역의 좌표를 true로 저장합니다.
        for(int[] puddle : puddles) {
            puddles_exist[puddle[1]-1][puddle[0]-1] = true;
        }
        
        // 집에서 아래쪽으로 이동하는 최단 경로를 저장합니다.
        for(int i = 1 ; i < n ; i++) {
            // 물에 잠긴 지역이 존재한다면 더 이상 아래로 이동할 수 없으므로 중단합니다.
            if(puddles_exist[i][0]) {
                break;
            }
            else {
                road[i][0] = 1;    
            }
        }
        
        // 집에서 오른쪽으로 이동하는 최단 경로를 저장합니다.
        for(int i = 1 ; i < m ; i++) {
            // 물에 잠긴 지역이 존재한다면 더 이상 오른쪽으로 이동할 수 없으므로 중단합니다.
            if(puddles_exist[0][i]) {
                break;
            }
            else {
                road[0][i] = 1;    
            }
        }
        
        // 집의 좌표를 (0,0) 이라고 할때 (1,1)부터 경로의 개수를 계산하게 됩니다.
        // (a, b)지점으로의 최단 거리 경로의 개수 = (a-1, b)지점으로의 최단 거리 경로의 개수 + (a, b-1)지점으로의 최단 거리 경로의 개수 입니다.
        for(int r = 1 ; r < n ; r++) {
            for(int c = 1 ; c < m ; c++) {
                // 물에 잠긴 지역은 갈 수 없으므로 배열의 초기값 0이 유지됩니다.
                if(!puddles_exist[r][c]) {
                    road[r][c] = (road[r-1][c]+road[r][c-1]) % 1000000007;
                }
            }
        }
        
        answer = road[n-1][m-1];
        
        return answer;
    }
}