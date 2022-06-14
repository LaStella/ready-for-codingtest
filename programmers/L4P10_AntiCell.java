// 안티 세포
// https://programmers.co.kr/learn/courses/30/lessons/86054?language=java

import java.util.*;

class Solution {
    int c_count;
    public int[] solution(int[] a, int[] s) {
        int[] answer = new int[s.length];
        
        for(int i = 0 ; i < s.length ; i++) {
            // 배열a에서 배열b를 잘라냅니다.
            int[] b = Arrays.copyOfRange(a, 0, s[i]);
            a = Arrays.copyOfRange(a, s[i], a.length);
            
            // 결과로 나오는 c배열의 개수입니다.
            c_count = 0;
            
            Deque<Integer> y = new ArrayDeque<>();
            dfs(0, b, y);
            
            answer[i] = c_count;
        }
        
        return answer;
    }
    
    public void dfs(int depth, int[] b, Deque<Integer> y) {
        // 깊이(i)가 b의 길이(n)라면 종료합니다.
        if(depth == b.length) {
            c_count++;
            return;
        }
        Deque<Integer> n_y = new ArrayDeque<>(y);
        // 합성하지 않고 다음 세포로 넘어갑니다.
        n_y.add(b[depth]);
        dfs(depth+1, b, n_y);
        
        // Y가 현재 세포와 같은 크기의 숫자라면 합성할 수 있습니다.
        if(!y.isEmpty() && y.peekLast() == b[depth]) {
            n_y = new ArrayDeque<>(y);
            int[] n_b = b.clone();
            n_y.pollLast();
            n_b[depth] *= 2;
            dfs(depth, n_b, n_y);
        }
    }
}

/*
세포의 합성 과정은 스택을 이용하여,
c의 개수는 dfs를 이용하여 계산이 가능해 보입니다.

배열b에서 dfs의 깊이(i)에 해당하는 원소를 확인합니다.
스택이 비어있다면(즉, Y가 존재하지 않는다면) 스택에 세포를 넣습니다.
스택에 세포가 존재한다면 맨 위의 세포를 꺼내 현재 세포와 비교합니다.
같을 경우 합쳐서 스택에 넣을 수 있으며, 합치지 않고 그냥 넣을 수도 있습니다.
합칠 경우 깊이는 그대로하여 dfs를 다시 실행합니다. 이때, 깊이(i)를 c에 저장합니다.
합치지 않을 경우 깊이(i)를 늘립니다.

깊이가 배열b의 크기(n)이 되면 c를 set에 넣고 종료합니다.
set에 저장된 c들은 중복이 제거되므로 set의 크기가 곧 답이됩니다.

문제발생> Y세포를 합치는 경우와 합치지 않는 경우 두 경우 모두 dfs로 계산하는데 빈 스택에 접근하는 오류가 발생합니다.
Stack을 dfs에서 사용하게 될 경우 같은 주소값을 사용하여서 빈 스택에서 값을 뽑는 오류가 발생한다고 생각됩니다.
위와같은 문제가 배열b에서도 동일하게 발생하여 dfs에 사용하는 각 자료형을 새로 생성하여 해결하였습니다.
시간초과
-------------------------------------------------------
시간이 너무 오래 걸리므로 dp을 활용해야 하는 것으로 보입니다.
우선 각 세포들은 모두 양수이며 배열c는 중복되는 배열이 나올 수 없습니다.
점화식이 도저히 떠오르지 않아 포기하였습니다.

*/