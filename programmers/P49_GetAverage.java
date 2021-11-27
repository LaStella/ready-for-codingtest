import java.util.*;

class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        
        // stream 이용
        // answer = Arrays.stream(arr).average().getAsDouble();
        for(int i : arr) {
            answer += i;
        }
        answer /= arr.length;
        
        
        return answer;
    }
}