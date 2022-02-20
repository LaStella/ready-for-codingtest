// 정수 삼각형
// https://programmers.co.kr/learn/courses/30/lessons/43105?language=java

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int max = 0;
        
        // 삼각형의 꼭대기에서 아래로 갈 수 있는 최대값을 아래로 더하면서 내려갑니다.
        // triangle[r][c]는 triangle[r-1][c-1]의 right이며 triangle[r-1][c]의 left입니다.
        // 따라서 두 값중 큰 값을 선택하여 더하면 해당 값에서 얻을 수 있는 최대값이 됩니다.
        // r = 0 일때 삼각형의 꼭대기이므로 r = 1부터 시작합니다.
        for(int r = 1 ; r < triangle.length ; r++) {
            for(int c = 0 ; c < triangle[r].length ; c++) {
                // triangle[r-1][c-1] 가 존재하지 않으므로 선택할 수 있는 값은 하나뿐입니다.
                if(c == 0) {
                    triangle[r][c] += triangle[r-1][c];
                }
                // triangle[r-1][c] 가 존재하지 않으므로 선택할 수 있는 값은 하나뿐입니다.
                else if(c == triangle[r].length-1) {
                    triangle[r][c] += triangle[r-1][c-1];
                }
                else {
                    triangle[r][c] += Math.max(triangle[r-1][c-1], triangle[r-1][c]);    
                }
                // 삼각형의 바닥일 경우 최대값을 계산합니다.
                if(r == triangle.length-1) {
                    max = Math.max(max, triangle[r][c]);
                }
            }
        }
        
        answer = max;
        
        return answer;
    }
}

/*
0
01
012
0123
01234
triangle[depth][index]의 left = triangle[depth-1][index-1]
triangle[depth][index]의 right = triangle[depth-1][index]
*/
