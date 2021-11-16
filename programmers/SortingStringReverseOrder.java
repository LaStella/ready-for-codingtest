import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] strArry = s.split("");
        
        Arrays.sort(strArry, Collections.reverseOrder());
        answer = String.join("", strArry);

        return answer;
    }
}