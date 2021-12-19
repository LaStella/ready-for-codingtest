// 소수 찾기
// https://programmers.co.kr/learn/courses/30/lessons/42839?language=java#

import java.util.*;

class Solution {
    public int solution(String numbers) {
        int answer = 0;
        // 소수가 저장되는 HashSet 입니다.
        HashSet<Integer> set = new HashSet<>();
        
        getPermutation(numbers, "", set);
        
        answer = set.size();
        
        return answer;
    }
    
    // 순열을 구하는 함수입니다.
    public void getPermutation(String numbers, String result, HashSet set) {
        // 숫자를 한개 이상 뽑은 순열을 저장합니다.
        if(!result.equals("")) {
            int resultNumb = Integer.parseInt(result);
            // 이미 저장되어있는 순열은 제외합니다.
            if(!set.contains(resultNumb)) {
                // 해당 순열이 소수가 맞는지 확인하고 저장합니다.
                if(isPrimeNumber(resultNumb)) {
                    set.add(resultNumb);
                }
            }
        }
        
        // numbers의 각 숫자로부터 하나씩 뽑아 result에 붙여줍니다.
        for(int i = 0 ; i < numbers.length() ; i++) {
            // temp는 numbers에서 i번째를 뺀 나머지 문자열을 나타냅니다.
            String temp = numbers.substring(0, i) + numbers.substring(i+1, numbers.length());
            // i번째를 붙여준 reuslt를 다시 재귀호출합니다.
            getPermutation(temp, result+numbers.substring(i, i+1), set);
        }
    }
    
    // 소수인지 확인하는 함수입니다.
    public boolean isPrimeNumber(int number) {
        // 1과 0은 소수가 아니므로 false를 return합니다.
        if(number < 2) {
            return false;
        }
        
        // number의 절반까지 나머지연산을 확인합니다.
        for(int i = 2; i <= Math.sqrt(number) ; i++) {
            if(number % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}

// bool배열 visitied를 이용하여 순열을 구하는 함수
/* 
public void getPermutation(String numbers, String result, boolean[] visited, HashSet answerSet) {
        if(!result.equals("")) {
            int resultNumb = Integer.parseInt(result);
            if(!answerSet.contains(resultNumb)) {
                if(isPrimeNumber(resultNumb)) {
                    answerSet.add(resultNumb);
                }
            }
        }
        
        String temp = result;
        for(int i = 0 ; i < numbers.length() ; i++) {
            if(!visited[i]) {
                result += numbers.substring(i, i+1);
                visited[i] = true;
                getPermutation(numbers, result, visited, answerSet);
                result = temp;
                visited[i] = false;    
            }
        }
    }
*/