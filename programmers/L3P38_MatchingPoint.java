// 매칭 점수
// https://programmers.co.kr/learn/courses/30/lessons/42893?language=java

import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String word, String[] pages) {
        int answer = 0;

        for(String page : pages) {
            // System.out.println(page);
            Pattern head_url_pattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
            Matcher m = head_url_pattern.matcher(page);
            m.find();
            System.out.println("<--page name-->");
            System.out.println(m.group().replace("<meta property=\"og:url\" content=",""));
            
            Pattern body_url_pattern = Pattern.compile("<a href=\"(\\S*)\"");
            m = body_url_pattern.matcher(page);
            System.out.println("<--link list-->");
            while(m.find()) {
                System.out.println(m.group().replace("<a href=", ""));    
            }
            // System.out.println();
            System.out.println("-----------page end------------");
        }
        
        return answer;
    }
}