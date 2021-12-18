// 가장 큰 수
// https://programmers.co.kr/learn/courses/30/lessons/42746?language=java#

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        String[] strNumbers =  Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);
        
        Arrays.sort(strNumbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 배열에 담긴 문자열숫자를 정순으로 붙인 것과 역순으로 붙인 것을 비교합니다.
                String a = o1+o2;
                String b = o2+o1;
                
                // compareTo는 문자열 비교시 같은 위치의 문자를 비교하며
                // 같을 경우 0, 다를 경우 아스키코드의 차이값이 나옵니다.
                // 따라서 정순으로 붙인 것과 역순으로 붙인 것을 같은 위치의 숫자를 차례로 비교하기때문에
                // 큰 값이 나오는 순서로 정렬이 됩니다.
                return b.compareTo(a);
            }
        });
        
        // 정렬된 배열을 하나의 문자열로 만듭니다.
        answer = String.join("", strNumbers);
        
        // 만약 첫 글자가 0일 경우 0을 return합니다.
        // 0으로 이루어진 배열을 0으로 만들어주는 과정입니다.
        if(answer.charAt(0) == '0') {
            return "0";
        }
        
        return answer;
    }
}