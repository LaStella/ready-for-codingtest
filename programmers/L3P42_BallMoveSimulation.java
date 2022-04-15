// 공 이동 시뮬레이션
// https://programmers.co.kr/learn/courses/30/lessons/87391?language=java

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = 0;
        // 0~3까지의 쿼리에 따라 더하는 값이 다릅니다. 
        int[][] next_value = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        
        for(int r = 0 ; r < n ; r++) {
            for(int c = 0 ; c < m ; c++) {
                // (r, c)에서 시작합니다.
                int[] location = {r, c};
                // 쿼리들을 순서대로 시뮬레이션합니다.
                for(int i = 0 ; i < queries.length ; i++) {
                    int command = queries[i][0];
                    int dx = queries[i][1];
                    while(dx > 0) {
                        location[0] += next_value[command][0];
                        location[1] += next_value[command][1];
                        if(location[0] < 0 || location[0] >= n || location[1] < 0 || location[1] >= m) {
                            location[0] -= next_value[command][0];
                            location[1] -= next_value[command][1];
                            break;
                        }
                        dx--;
                    }
                }
                // 쿼리들을 시뮬레이션한 결과 공의 위치가 (x, y)인지 확인합니다.
                if(location[0] == x && location[1] == y) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}