// 줄을 서는 방법
// https://programmers.co.kr/learn/courses/30/lessons/12936?language=java

// 핵심 : 우선순위큐

import java.util.*;

class Solution {
    long count = 0;
    long global_k;
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();
        long factorial = 1;
        
        // list에 n까지의 자연수를 넣고, n!을 저장합니다.
        for(int i = 1 ; i <= n ; i++) {
            list.add(i);
            factorial *= i;
        }
        
        int index = 0;
        // 문제에서 주어지는 k는 자연수로 0이 아닌 1부터 시작합니다.
        // 밑에서 구하게 될 방법은 0번째 방법이 시작이므로 -1을 해줍니다.
        k -= 1;
        
        // index번째의 값은 k를 factorial/(n-index)로 몫에 해당하는 list_index의 값이 됩니다. (자세한 설명은 아래에서)
        // k는 factorial/(n-index)로 나눈 나머지가 되며, 사용한 list_index에 해당하는 번호는 삭제합니다.
        while(index < n) {
            factorial /= (n-index);
            long list_index = k/factorial;
            answer[index++] = list.get((int)list_index);
            list.remove((int)list_index);
            k %= factorial;
        }
        
        return answer;
    }
}


/*
1~n
ex) 1~4 라고할때
첫번째 사람이 1인경우는 총 6개 존재 1 xxx, 2,3,4를 각자리에 배치하는 경우는 3*2*1
즉 첫번째 사람이 1인 경우 1~6번째, 2인경우 7~12, 3인경우 13~18, 4인경우 19~24
두번째 사람의 경우 또한 위와 똑같은 방식, 다만 숫자의 위치에 따른 값(index)이 저장되어야 하므로
1~4를 list에 넣고 하는 것이 용이

123 3 5 factorial 3!
f = f / (n-index);
f = 3! / (3-0);   (2!)
5 / 2! = 2
list.get(2) = 3;
나머지 5 % 2! = 1

f = 2! / (3-1);   (1)
1 / 1! = 1
list.get(1) = 

정리
1~n을 list에 넣으며 n!을 변수로 저장
첫번째 자리의 값은 주어진 k를 (n-1)!값으로 나눈 값이 list의 index가 된다.
즉, n!을 변수로 저장하였으니 n!/n = (n-1)! 이다.
answer[index] = list.get(k/(n!/(n-index))) 의 형식

*/