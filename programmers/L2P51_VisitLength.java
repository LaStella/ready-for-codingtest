import java.util.*;
// 방문길이
// https://programmers.co.kr/learn/courses/30/lessons/49994?language=java

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        // 지나간 길을 저장할 set입니다.
        HashSet<String> set = new HashSet<>();
        int x = 0, y = 0;
        
        for(int i = 0 ; i < dirs.length() ; i++) {
            // 상, 하, 좌, 우에 따라 x,y좌표를 변경합니다.
            // 변경시 "변경 전 좌표+변경 후 좌표"를 지나간 길로 set에 저장합니다.
            // 격자를 넘어가는 경우 위 과정은 진행되지 않습니다.
            // U과 D, R과 L은 같은 길을 지날 수 있으므로 set에 저장될 길을 일치시키기 위해 D과 L의 경우 "변경 후 좌표+변경 전 좌표"가 저장됩니다.
            if(dirs.charAt(i) == 'U') {
                if(y+1>5) continue;
                set.add(""+x+y+x+(y+1));
                y++;
            }
            else if(dirs.charAt(i) == 'D') {
                if(y-1<-5) continue;
                set.add(""+x+(y-1)+x+y);
                y--;
            }
            else if(dirs.charAt(i) == 'R') {
                if(x+1>5) continue;
                set.add(""+x+y+(x+1)+y);
                x++;
            }
            else {
                if(x-1<-5) continue;
                set.add(""+(x-1)+y+x+y);
                x--;
            }
        }

        answer = set.size();
        
        return answer;
    }
}