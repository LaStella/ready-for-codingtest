// 사칙연산
// https://programmers.co.kr/learn/courses/30/lessons/1843?language=java

import java.util.*;

class Solution {
    int answer;
    public int solution(String arr[]) {
        answer = Integer.MIN_VALUE;
        
        List<Integer> operand = new ArrayList<>();
        List<String> operator = new ArrayList<>();
        
        for(int i = 0 ; i < arr.length ; i++) {
            if(i % 2 == 0) {
                operand.add(Integer.parseInt(arr[i]));
            }
            else {
                operator.add(arr[i]);
            }
        }
        
        dfs(operator, operand);
        
        return answer;
    }
    
    public void dfs(List<String> operator, List<Integer> operand) {
        if(operator.isEmpty()) {
            answer = Math.max(answer, operand.get(0));
        }
        else {
            for(int i = 0 ; i < operator.size() ; i++) {
                List<String> copied_operator = new ArrayList<>(operator);
                List<Integer> copied_operand = new ArrayList<>(operand);
                
                int result;
                
                if(copied_operator.remove(i).equals("+")) {
                    result = copied_operand.remove(i) + copied_operand.remove(i);
                    copied_operand.add(i, result);
                }
                else {
                    result = copied_operand.remove(i) - copied_operand.remove(i);
                    copied_operand.add(i, result);
                }
                
                dfs(copied_operator, copied_operand);
            }
        }
    }
}

/*
숫자 연산자가 번갈아 존재
0번 연산자는 0,1번 숫자
1번 연산자는 1,2번 숫자
2번 연산자는 2,3번 숫자
n번 연산자는 n,n+1번 숫자

연산결과는 n번자리에 넣는다.
숫자와 연산자들을 각각 리스트에 넣고 연산자를 기준으로 dfs
시간초과
-------------------------------------------------


*/