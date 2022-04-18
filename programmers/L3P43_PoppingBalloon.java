// 풍선 터트리기
// https://programmers.co.kr/learn/courses/30/lessons/68646?language=java

// 주요 풀이 : 임의의 풍선을 기준으로 좌, 우로 나누어 한쪽이라도 작은 값을 가진 풍선이 없다면 해당 풍선은 최후까지 남을 수 있습니다.
// 값이 큰 풍선은 얼마든지 제거 가능하지만, 작은 풍선은 한번만 제거할 수 있으므로 기준이 되는 풍선의 좌, 우에 기준보다 작은 풍선이 존재하면 한쪽만 제거할 수 있기때문에 최후까지 남을 수 없습니다.

// <----------최종 풀이---------->
import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        // 중복제거에 용이한 set에 최후까지 남을 수 있는 풍선을 저장합니다.
        HashSet<Integer> set = new HashSet<>();
        int left_min = Integer.MAX_VALUE;
        int right_min = Integer.MAX_VALUE;
        
        // 풍선의 양 끝에서부터 각각 최소값을 갱신합니다.
        // 최소값을 갱신한다면 이전까지(왼쪽끝 또는 오른쪽 끝 ~ 현재풍선 전) 현재 풍선보다 작은 값을 가진 풍선이 없습니다.
        // 따라서, 각각 최소값을 갱신하는 풍선은 set에 넣습니다.
        for(int i = 0 ; i < a.length ; i++) {
            if(a[i] < left_min) {
                left_min = a[i];
                set.add(a[i]);
            }
            
            if(a[a.length-1-i] < right_min) {
                right_min = a[a.length-1-i];
                set.add(a[a.length-1-i]);
            }
        }
        
        answer = set.size();
        
        return answer;
    }
}


// <----------처음 풀이---------->
import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        int left_min = Integer.MAX_VALUE;
        int right_min = Integer.MAX_VALUE;
        
        // 풍선을 오른쪽 끝에서부터 스택에 넣습니다.
        // 스택에 넣을때 스택의 최소값보다 작은 값이라면 최소값을 갱신합니다.
        // 스택에서 뺼때 또한 최소값을 갱신해야하므로, 최소값을 갱신할때 이전 최소값을 스택에 넣습니다.
        // 따라서 스택에서 최소값을 뺀다면 다음 스택에서 빼는 값이 최소값이 됩니다.
        for(int i = a.length-1 ; i >= 0 ; i--) {
            if(a[i] <= right_min) {
                stack.push(right_min);
                right_min = a[i];
            }
            
            stack.push(a[i]);
        }
        
        // 스택에서 풍선을 꺼냅니다.
        // 해당 풍선을 기준으로 왼쪽 또는 오른쪽에 해당 풍선보다 작은 값을 가진 풍선이 없다면 해당 풍선은 최후까지 살아남을 수 있습니다.
        while(!stack.isEmpty()) {
            int pop_balloon = stack.pop();
            
            // 오른쪽 최소값의 풍선을 뽑은 경우 최소값을 갱신합니다.
            if(pop_balloon == right_min) {
                right_min = stack.pop();
            }
            
            if(pop_balloon < left_min || pop_balloon < right_min) {
                answer++;
            }
            
            // 뽑은 풍선이 현재까지 뽑은 풍선들의 최소값보다 작으면 최소값을 갱신합니다.
            if(pop_balloon < left_min) {
                left_min = pop_balloon;
            }
        }
        
        return answer;
    }
}

/*
임의의 풍선을 선택하였을때
해당 풍선의 좌, 우에 해당 풍선보다 값이 작은 풍선이 존재한다면 해당 풍선은 최후까지 남을 수 없다.

스택에 모든 풍선을 넣는다.
스택에 넣을때 최소값을 최신화하며, 스택에서 뺄때 또한 최신화해야한다.
*/


