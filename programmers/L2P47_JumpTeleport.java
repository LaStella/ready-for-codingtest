// 점프와 순간 이동
// https://programmers.co.kr/learn/courses/30/lessons/12980?language=java

import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        while(n != 0) {
            if(n % 2 == 0) {
                n /= 2;
            }
            else {
                ans++;
                n--;
                n /= 2;
            }
        }

        return ans;
    }
}