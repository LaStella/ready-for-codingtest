// 다음에는 HashSet을 사용해보자.

import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        
        ArrayList<Integer> resultList = new ArrayList<>();
        
        for(int i = 0 ; i < numbers.length ; i++) {
            for(int j = i+1 ; j < numbers.length ; j++) {
                //중복값 거르기
                if(!resultList.contains(numbers[i] + numbers[j])) {
                    resultList.add(numbers[i] + numbers[j]);
                }
            }
        }
        
        //정렬
        resultList.sort(Comparator.naturalOrder());
        
        //배열로 바꿔줌
        answer = new int[resultList.size()];
        for(int i = 0 ; i < answer.length ; i++) {
            answer[i] = resultList.get(i);
        }
        
        return answer;
    }
}