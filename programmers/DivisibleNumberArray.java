import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};
        ArrayList<Integer> resultList = new ArrayList<>();
        
        for(int i : arr) {
            if(i % divisor == 0) {
                resultList.add(i);
            }
        }
        
        resultList.sort(Comparator.naturalOrder());
        
        if(resultList.size() != 0) {
            answer = new int[resultList.size()];
            for(int i = 0 ; i < answer.length ; i++) {
                answer[i] = resultList.get(i);
            }
        }
        else {
            answer = new int[] {-1};
        }
        
        return answer;
    }
}