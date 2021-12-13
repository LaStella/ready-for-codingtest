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
        // System.out.println("e"+expressionDeque);
        
        for(String s: operatorsPermutation) {
            Deque<String> deque1 = new ArrayDeque<>(expressionDeque);
            for(int i = 0 ; i < s.length() ; i++) {
                Deque<String> deque2 = new ArrayDeque<>();
                
                String operator = s.substring(i, i+1);
                
                while(!deque1.isEmpty()) {
                    if(deque1.peek().equals(operator)) {
                        deque2.add(getOperationValue(deque2.pollLast(), deque1.poll(), deque1.poll()));
                    }
                    else {
                        deque2.add(deque1.poll());
                    }
                }
                
                deque1 = deque2;
            }
            long result = Math.abs(Long.valueOf(deque1.peek()).longValue());
            if(result > answer) {
                answer = result;
            }
            // System.out.println(deque1.peek());
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
        Deque<String> list = new ArrayDeque<>();
        int index = 0;
        
        for(int i = 0 ; i < expression.length() ; i++) {
            if(!Character.isDigit(expression.charAt(i))) {
                String temp = expression.substring(index,i);
                list.add(temp);
                list.add(expression.substring(i,i+1));
                index = i+1;
            }
        }
        list.add(expression.substring(index, expression.length()));
        
        return list;
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