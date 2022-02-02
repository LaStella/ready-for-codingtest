// 최댓값과 최솟값
// https://programmers.co.kr/learn/courses/30/lessons/12939?language=java

class Solution {
    public String solution(String s) {
        String answer = "";
        
        String[] sArray = s.split(" ");
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for(int i = 0 ; i < sArray.length ; i++) {
            int temp = Integer.parseInt(sArray[i]);
            
            if(temp > max) max = temp;
            if(temp < min) min = temp;
        }
        
        answer = Integer.toString(min)+" "+Integer.toString(max);
        
        return answer;
    }
}