import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        
        String s = Long.toString(n);
        
        String[] strArry = s.split("");
        
        Arrays.sort(strArry, Collections.reverseOrder());
        
        s = String.join("", strArry);
        
        answer = Long.parseLong(s); 
        
        return answer;
    }
}