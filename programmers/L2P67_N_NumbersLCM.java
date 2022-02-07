// N개의 최소공배수
// https://programmers.co.kr/learn/courses/30/lessons/12953?language=java

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        
        // 최대공약수와 최소공배수를 나타내는 변수입니다.
        int gcf = arr[0];
        int lcm = arr[0];

        for(int i : arr) {
            // 배열의 원소들을 순회하며 최소공배수를 계산합니다.
            // 두 수 (A, B)의 최소공배수는 A*B/최대공약수 입니다.
            // 이전에 구한 최소공배수와 다음 원소를 통해 새로운 최소공배수 반복해서 구합니다.
            gcf = gcd(lcm, i);
            lcm = lcm*i/gcf;
        }
        
        // 최종적으로 구하게 되는 최소공배수가 곧 n개의 숫자들의 최소공배수가 됩니다.
        answer = lcm;
        
        return answer;
    }
    
    // 유클리드 호제법으로 최대공약수를 구하는 함수입니다.
    public int gcd(int a, int b) {
        int r = a % b;
        if(r == 0) {
            return b;
        }
        else {
            return gcd(b, r);
        }
    }
}