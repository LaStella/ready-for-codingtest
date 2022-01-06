// 삼각 달팽이
// https://programmers.co.kr/learn/courses/30/lessons/68645?language=java

class Solution {
    public int[] solution(int n) {
        int[] answer = {};
        // n*n크기의 배열에 삼각형을 저장합니다.
        int[][] a = new int[n][n];
        
        // 초기값 설정입니다. row와 column은 0, count는 1부터 넣습니다.
        // 삼각형의 첫 정수로 1을 넣어줍니다.
        int count = 1, r = 0, c = 0;
        int lastNumber = getLastNumber(n);
        a[r][c] = count++;

        // count를 1씩 증가시키며 a에 저장합니다.
        // 삼각형은 위>아래, 왼>오른, 왼위대각선 방향으로 정수를 채웁니다.
        while(count <= lastNumber) {
            // 위>아래 방향으로 정수를 채웁니다.
            // row가 배열 범위를 벗어나지 않으며, 다음 row에 저장된 값이 없을 때까지 반복합니다.
            while(r+1 < n && a[r+1][c] == 0) {
                a[++r][c] = count++;
            }
            // 왼>오른 방향으로 정수를 채웁니다.
            while(c+1 < n && a[r][c+1] == 0) {
                a[r][++c] = count++;
            }
            // 왼위대각선 방향으로 정수를 채웁니다.
            // 대각선 방향으로 채우게 될 경우 반드시 값이 채워진 공간을 만납니다.
            while(a[r-1][c-1] == 0) {
                a[--r][--c] = count++;
            }
        }
        
        // 만들어진 삼각형을 answer배열에 저장합니다.
        count = 0;
        answer = new int[lastNumber];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j <= i ; j++) {
                answer[count++] = a[i][j];
            }
        }

        return answer;
    }
    
    // 삼각형의 마지막 정수를 구하는 함수입니다.
    public int getLastNumber(int n) {
        int result = n;
        
        while(n-- > 0) {
            result += n;
        }
        
        return result;
    }
}