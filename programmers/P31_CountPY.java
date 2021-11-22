class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int count = 0;
        
        s = s.toLowerCase().replaceAll("[^p, y]", "");

        for(int i = 0 ; i < s.length() ; i++) {
            if(s.charAt(i) == 'p') {
                count++;
            }
            else {
                count--;
            }
        }
        
        if(count != 0) {
            answer = false;
        }

        return answer;
    }
}