import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxWidth = 0;
        int maxHeight = 0;
        
        for(int[] temp : sizes) {
            maxWidth = Math.max(maxWidth, Math.max(temp[0], temp[1]));
            maxHeight = Math.max(maxHeight, Math.min(temp[0], temp[1]));
        }
        answer = maxWidth * maxHeight;
        return answer;
    }
}