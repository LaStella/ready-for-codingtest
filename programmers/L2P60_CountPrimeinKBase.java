// k진수에서 소수 개수 구하기
// https://programmers.co.kr/learn/courses/30/lessons/92335?language=java

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        // 주어진 양의 정수 n을 k진수 String으로 만듭니다.
        String ns = Integer.toString(n, k);
        // 0을 기준으로 쪼갭니다.
        String[] nsarray = ns.split("0");
        
        for(String s : nsarray) {
            // 비어있는 배열은 무시합니다.
            if(s.equals("")) continue;
            else {
                // 배열안의 String s를 정수형으로 바꾸고 이를 소수인지 확인합니다.
                // String s를 정수형으로 바꿀때 int타입의 범위를 초과할 수 있으므로 long타입으로 바꿉니다.
                if(isPrime(Long.parseLong(s))) answer++;
            }
        }
        
        return answer;
    }
    
    // 주어진 정수 n이 소수인지 확인하는 함수입니다.
    public boolean isPrime(long n) {
        if(n == 1) {
            return false;
        }
        
        for(int i = 2 ; i <= Math.sqrt(n) ; i++) {
            if(n % i == 0) return false;
        }
        
        return true;
    }
}