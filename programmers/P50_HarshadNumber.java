import java.util.*;

class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int sum = 0;

        // stream 이용
        // sum = Integer.toString(x).chars().map(i -> i - '0').sum();
        
        String[] sArray = Integer.toString(x).split("");
        
        for(String s : sArray) {
            sum += Integer.parseInt(s);    
        }
        
        if(x % sum != 0) {
            answer = false;
        }

        return answer;
    }
}