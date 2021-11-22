import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxWidth = 0;
        int maxHeight = 0;
        
        for(int[] temp : sizes) {
            maxWidth = Math.max(maxWidth, Math.max(temp[0], temp[1]));  //sizes에 들어있는 각 행의 가로, 세로의 값 중 큰 값을 가로로
            maxHeight = Math.max(maxHeight, Math.min(temp[0], temp[1]));    //작은 값은 세로로 각각 최대치(maxWidht, maxHeight)를 구한다.
        }
        answer = maxWidth * maxHeight;
        return answer;
    }
}