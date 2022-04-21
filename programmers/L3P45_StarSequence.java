// 스타 수열   
// https://programmers.co.kr/learn/courses/30/lessons/70130?language=java

import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // a의 원소별 개수를 저장합니다.
        for(int n : a) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        // key는 교집합 원소가 됩니다.
        // 교집합 원소 key에 대하여 (key의 개수)*2는 최대로 가능한 스타 수열의 길이가 됩니다.
        for(int key : map.keySet()){
            // 기존 스타수열보다 최대 길이가 짧은 스타 수열은 계산할 필요가 없습니다.
            if(answer/2 >= map.get(key)) {
                continue;
            }
            
            int count = 0;
            int i = 0;
            while(i < a.length-1) {
                // 두 원소가 모두 교집합 원소이거나 원소가 아니면 스타 수열에 들어갈 수 없습니다.
                // 즉 두 원소중 하나는 교집합 원소이며 다른 하나는 교집합 원소가 아니어야 합니다.
                if((a[i] != key && a[i+1] != key) || (a[i] == key && a[i+1] == key)) {
                    i++;
                    continue;
                }
                
                // 스타 수열에 들어갈 수 있는 두 원소이므로 인덱스를 2 늘려 다다음 원소부터 스타 수열에 들어갈 수 있는 원소를 찾습니다.
                count++;
                i += 2;
            }
            
            answer = Math.max(answer, count*2);
        }
        
        return answer;
    }
}

/*
교집합 원소의 개수가 최소 1개 이상이어야 하므로
a에서 가장 많이 나오는 숫자가 교집합 원소(i)가 되면 가장 긴 수열을 얻을 수 있다.

교집합이 될 원소(i)를 기준으로 a의 스타 수열을 만든다.
1. {i, x} 또는 {x, i}의 형태의 집합을 구한다. (x != i)
2. 스타 수열은 부분수열이므로 원래 순서를 유지해야한다.
*/