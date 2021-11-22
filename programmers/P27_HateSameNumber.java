import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        int prevInt = 0;
        ArrayList<Integer> resultList = new ArrayList<>();
        
        for(int i = 0 ; i < arr.length ; i++) {
            if(i == 0 || arr[i] != prevInt) {
                prevInt = arr[i];
                resultList.add(arr[i]);
            }
        }
        
        answer = new int[resultList.size()];
        
        for(int i = 0 ; i < answer.length ; i++) {
            answer[i] = resultList.get(i);
        }

        return answer;
    }
}