// 2개 이하로 다른 비트
// https://programmers.co.kr/learn/courses/30/lessons/77885?language=java

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        // 주어진 정수가 짝수라면 비트의 끝자리가 0이므로 f(x) = x+1입니다.
        // 홀수라면 비트의 끝에서 가장 가까운 0을 1로 바꾸고, 오른쪽 비트를 0으로 바꾸면 됩니다.
        for(int i = 0 ; i < numbers.length ; i++) {
            // 짝수일 경우
            if(numbers[i] % 2 == 0) {
                answer[i] = numbers[i]+1;    
            }
            else {
                answer[i] = getMin(numbers[i]); 
            }
        }
        
        return answer;
    }
    
    // 주어진 정수가 홀수일 때 f(x)를 구하는 함수입니다.
    public long getMin(long l) {
        // 주어진 정수를 비트 문자열로 바꿉니다.
        String s = Long.toBinaryString(l);
        // 비트 문자열의 끝에서 부터 0을 탐색해야 하므로 1로 이루어진 비트일 경우 0을 찾을 수 없게됩니다.
        // 이를 방지하기 위해 비트 문자열의 앞에 0을 추가해줍니다.
        s = "0"+s;
        
        for(int i = s.length()-1 ; i >= 0 ; i--) {
            if(s.charAt(i) == '0') {
                // 0을 찾았다면 이를 1로 바꾸고 오른쪽 자리를 0으로 바꿔줍니다.
                // ex) l = 7, s = 00111  ->  s = 01011
                s = s.substring(0, i)+"10"+s.substring(i+2, s.length());
                break;
            }
        }
        
        // 문자열을 Long값으로 return합니다.
        return Long.valueOf(s, 2);
    }
}



/* 비트연산 XOR을 이용하여 다른 비트의 개수를 찾고 1씩 증가시키며 정답을 찾는 방법입니다. (시간초과)
class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i = 0 ; i < numbers.length ; i++) {
            answer[i] = getMin(numbers[i]);
        }
        
        return answer;
    }
    
    public long getMin(long l) {
        long result = l+1;
        
        while(!isCountLessTwo(l, result)) {
            result += 1;
        }
        
        return result;
    }
    
    public boolean isCountLessTwo(long a, long b) {
        String s = Long.toBinaryString(a^b);
        int count = 0;
        
        for(int i = 0 ; i < s.length() ; i++) {
            if(s.charAt(i) == '1') {
                count++;
                if(count > 2) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
*/