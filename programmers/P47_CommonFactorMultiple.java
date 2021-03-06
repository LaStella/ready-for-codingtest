import java.util.*;

class Solution {
    public int[] solution(int n, int m) {
        int[] answer = {};
        List<Integer> list1 = createDivArry(n);
        List<Integer> list2 = createDivArry(m);
        
        // 두 리스트를 비교해 공약수를 구함
        list1.retainAll(list2);
        
        // 공약수를 내림차순으로 정렬하면 첫번째 값이 최대공약수가 된다.
        Collections.sort(list1, Collections.reverseOrder());
       
        answer = new int[2];
        answer[0] = list1.get(0);
        answer[1] = n*m/answer[0];
        
        return answer;
    }
    
    // 약수 리스트를 만드는 함수
    public List createDivArry(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1 ; i < Math.sqrt(n) ; i++) {
            if(n % i == 0) {
                list.add(i);
                list.add(n/i);
            }
        }
        if(n % Math.sqrt(n) == 0) {
            list.add((int)Math.sqrt(n));
        }
        return list;
    }
}

// 호제법으로 풀이
class Solution {
    public int[] solution(int n, int m) {
        int[] answer = {};

        answer = new int[2];
        answer[0] = Gcd(n, m);
        answer[1] = n*m/answer[0];
        
        return answer;
    }
    // a,b값의 크기를 비교에 제수 피제수를 구분할 필요가 없는 이유는 
    // a<b 일경우 a%b==a이기에 재귀과정에서 큰 값이 a로 작은 값이 b로 들어간다.
    public int Gcd(int a, int b) {
        int r = a % b;
        
        if(r == 0) {
            return b;
        }
        else {
            return Gcd(b, r);
        }
    }
}