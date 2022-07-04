// 자동 완성
// https://programmers.co.kr/learn/courses/30/lessons/17685?language=java

import java.util.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        
        // Trie구조 트리를 구성하는 루트노드입니다.
        Node rootNode = new Node();
        
        // 각 단어를 한글자씩 Trie구조 트리에 넣습니다.
        for(String word : words) {
            Node node = rootNode;
            for(int i = 0 ; i < word.length(); i++) {
                node = node.childMap.computeIfAbsent(word.charAt(i), key -> new Node());
                // 한글자씩 넣을때 자식의 수를 증가시킵니다.
                node.childCount++;
            }
        }
        
        // 각 단어에 대해 최소 입력 문자의 개수를 구합니다.
        for(String word : words) {
            Node node = rootNode;
            // 입력한 문자의 개수입니다.
            int count = 0;
            // 한글자씩 문자를 입력하며 자식의 수를 확인합니다.
            for(int i = 0 ; i < word.length(); i++) {
                node = node.childMap.get(word.charAt(i));
                count++;
                // 자식의 수가 1이라면 현재까지 입력한 문자로 해당 단어가 자동 완성이 가능하므로 중단합니다.
                if(node.childCount == 1) {
                    break;
                }
            }
            // 입력한 문자의 개수를 더합니다.
            answer += count;
        }
        
        return answer;
    }
    
    // Trie구조에 사용되는 객체입니다.
    class Node {
        int childCount = 0;
        HashMap<Character, Node> childMap = new HashMap<>();
        public Node() {
            
        }
    }
}
/* 
지난 가사검색에서 사용한 Trie구조를 사용해야 하는것으로 보입니다.

가사검색때와 마찬가지로 자식의 수를 childCount로 저장하여 자식 부분트리를 확인합니다.

1. 각 단어를 Trie구조 트리에 저장합니다.
    1-1. 저장시 자식의 개수를 늘려줍니다. (입력한 문자를 통해 자식의 수가 1개일때 자동 완성이 가능하기 때문입니다.)
2. 각 단어를 Trie구조 트리에서 자식의 수가 1이 될때까지 찾습니다.
    2-1. 한 문자씩 탐색할때마다 입력한 문자의 개수(count)를 늘려줍니다.
    2-2. 자식의 수가 1이라면 자동완성이 가능하므로 중단합니다.
    2-3. 1이 될때까지 찾지 못한 경우 단어의 모든 문자를 입력해야합니다. (자동 완성이 불가능한 단어입니다.)
3. 2에서 구한 입력한 문자의 개수를 answer에 더합니다.
*/