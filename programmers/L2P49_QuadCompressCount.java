// 쿼드압축 후 개수 세기
// https://programmers.co.kr/learn/courses/30/lessons/68936?language=java

class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        
        quad(arr, answer);

        return answer;
    }
    
    // 쿼드 압축을 하는 함수입니다.
    public void quad(int[][] arr, int[] answer) {
        // 주어진 2차 배열의 모든 요소가 같다면 배열의 첫번째 값으로 압축합니다.(첫 번째 값의 개수를 올려줍니다.)
        if(isAllSame(arr)) {
            // answer[0] = 0의 개수, answer[1] = 1의 개수입니다.
            answer[arr[0][0]]++;
        }
        // 요소가 같지 않다면 4개의 균일한 정사각형(2차 배열)으로 쪼갠 뒤, 쿼드 압축을 합니다.
        else {
            // 첫 번째 영역의 범위는 row : 0~length/2, column : 0~length/2
            // 두 번째 영역의 범위는 row : 0~length/2, column : length/2~length
            // 세 번째 영역의 범위는 row : length/2~length, column : 0~length/2
            // 네 번째 영역의 범위는 row : length/2~length, column : length/2~length
            int length = arr.length;
            quad(makeArr(0, 0, arr), answer);
            quad(makeArr(0, length/2, arr), answer);
            quad(makeArr(length/2, 0, arr), answer);
            quad(makeArr(length/2, length/2, arr), answer);
        }
    }
    
    // 주어진 2차배열의 모든 요소가 같은지 확인하는 함수입니다.
    public boolean isAllSame(int[][] arr) {
        int length = arr.length;
        int first = arr[0][0];
        
        for(int r = 0 ; r < length ; r++) {
            for(int c = 0 ; c < length ; c++) {
                if(first != arr[r][c]) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // row와 column의 시작지점을 받아 새로운 2차 배열을 만드는 함수입니다.
    public int[][] makeArr(int rStart, int cStart, int[][] arr) {
        int length = arr.length;
        int[][] result = new int[length/2][length/2];
        
        for(int r = rStart ; r < rStart+length/2 ; r++) {
            for(int c = cStart ; c < cStart+length/2 ; c++) {
                result[r-rStart][c-cStart] = arr[r][c];
            }
        }
        
        return result;
    }
}