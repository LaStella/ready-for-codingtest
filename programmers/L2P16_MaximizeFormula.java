// 수식 최대화
// https://programmers.co.kr/learn/courses/30/lessons/67257?language=java

import java.util.*;

class Solution {
    public long solution(String expression) {
        long answer = 0;
        String[] operators = {"+", "-", "*"};
        boolean[] visited = new boolean[operators.length];
        
        ArrayList<String> operatorsPermutation = getPermutation(operators, "", visited);
        Deque<String> expressionDeque = getExpressionDeque(expression);
        
        for(String s: operatorsPermutation) {
            Deque<String> expDeque = new ArrayDeque<>(expressionDeque);

            for(int i = 0 ; i < s.length() ; i++) {
                Deque<String> expDeque2 = new ArrayDeque<>();
                String operator = s.substring(i, i+1);
                
                while(!expDeque.isEmpty()) {
                    if(expDeque.peek().equals(operator)) {
                        expDeque2.add(getOperationValue(expDeque2.pollLast(), expDeque.poll(), expDeque.poll()));
                    }
                    else {
                        expDeque2.add(expDeque2.poll());
                    }
                }

                expDeque = expDeque2;
            }
            
            long result = Math.abs(Long.valueOf(expDeque.peek()).longValue());
            if(result > answer) {
                answer = result;
            }
        }
        
        return answer;
    }
    
    public ArrayList getPermutation(String[] operators, String permutation, boolean[] visited) {
        ArrayList<String> list = new ArrayList<>();
        
        if(permutation.length() == operators.length) {
            list.add(permutation);
            return list;
        }
        
        String temp = permutation;
        for(int i = 0 ; i < operators.length ; i++) {
            if(!visited[i]) {
                visited[i] = true;
                permutation += operators[i];
                list.addAll(getPermutation(operators, permutation, visited));
                permutation = temp;
                visited[i] = false;    
            }
        }
        
        return list;
    }
    
    public Deque getExpressionDeque(String expression) {
        Deque<String> deque = new ArrayDeque<>();
        int index = 0;
        
        for(int i = 0 ; i < expression.length() ; i++) {
            if(!Character.isDigit(expression.charAt(i))) {
                String temp = expression.substring(index,i);
                deque.add(temp);
                deque.add(expression.substring(i,i+1));
                index = i+1;
            }
        }
        deque.add(expression.substring(index, expression.length()));
        
        return deque;
    }
    
    public String getOperationValue(String a, String operator, String b) {
        String result = "";
        
        switch (operator) {
            case "+":
                result = Long.toString(Long.parseLong(a)+Long.parseLong(b));
                break;
            case "-":
                result = Long.toString(Long.parseLong(a)-Long.parseLong(b));
                break;
            case "*":
                result = Long.toString(Long.parseLong(a)*Long.parseLong(b));
        }
        
        return result;
    }
}