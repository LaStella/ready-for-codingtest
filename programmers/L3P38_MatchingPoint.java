// 매칭 점수
// https://programmers.co.kr/learn/courses/30/lessons/42893?language=java

import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String word, String[] pages) {
        int answer = 0;
        HashMap<String, Info> map = new HashMap<>();
        // 모든 알파벳을 소문자로 통일합니다.
        word = word.toLowerCase();
        int index = 0;
        for(String page : pages) {
            // 페이지의 정보를 담을 객체를 만듭니다.
            Info info = new Info(index);
            String page_name;
            
            // 모든 알파벳을 소문자로 통일합니다.
            page = page.toLowerCase();
            // head안의 url을 page의 이름으로 저장합니다.
            Pattern head_url_pattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
            Matcher head_url_matcher = head_url_pattern.matcher(page);
            head_url_matcher.find();
            System.out.println("<--page name-->");
            System.out.println(head_url_matcher.group().replace("<meta property=\"og:url\" content=",""));
            page_name = head_url_matcher.group().replace("<meta property=\"og:url\" content=","");
            // map.put(m.group().replace("<meta property=\"og:url\" content=",""), new Score(index));
            
            // 페이지의 body에서 word를 찾습니다.
            // <body> 이후의 모든 문자를 body로 취급하며, word는 하나의 영단어 이므로 알파벳소문자를 제외한 모든 문자를 공백으로 바꿔줍니다.
            String body = page.split("<body>")[1].replaceAll("[^a-z]", " ");
            // 
            Pattern body_word_pattern = Pattern.compile("\\b"+word+"\\b");
            Matcher body_word_matcher = body_word_pattern.matcher(body);
            System.out.println("<--Find Word-->");
            // System.out.println(body);
            // word를 찾을 때마다 기본 점수를 증가시킵니다.
            double basic_score = 0;
            while(body_word_matcher.find()) {
                basic_score++;
                System.out.println(body_word_matcher.group());
            }
            info.basic_score = basic_score;
            
            // 외부링크를 찾습니다.
            Pattern external_url_pattern = Pattern.compile("<a href=\"(\\S*)\"");
            Matcher external_url_matcher = external_url_pattern.matcher(page);
            System.out.println("<--link list-->");
            List<String> link_list = new ArrayList<>();
            // 외부링크를 찾을 때마다 url을 리스트에 저장합니다.
            while(external_url_matcher.find()) {
                System.out.println(external_url_matcher.group().replace("<a href=", ""));
                link_list.add(external_url_matcher.group().replace("<a href=", ""));
            }
            info.link_list = link_list;
            
            map.put(page_name, info);
            // System.out.println();
            
            

            
            // System.out.println(m.group());
            
            System.out.println("-----------page end------------");
            index++;
        }
        
        
        return answer;
    }
}

class Info {
    int index;
    double basic_score;
    double link_score;
    List<String> link_list;
    
    public Info(int index) {
        this.index = index;
        this.basic_score = 0;
        this.link_score = 0;
        this.link_list = new ArrayList<>();
    }
}

/*
head_url을 id로, Info객체를 value로 하는 해쉬멥 생성
Info객체는 인덱스, 기본점수, 링크점수, 링크리스트로 이루어짐

기본점수 = body내에 등장한 word 수
링크점수 = 다른 페이지에서의 기본점수 / 다른 페이지에서의 외부 링크 수

*/