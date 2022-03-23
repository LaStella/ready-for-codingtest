// 길 찾기 게임
// https://programmers.co.kr/learn/courses/30/lessons/42892?language=java

import java.util.*;

class Solution {
    int postorderIndex = 0;
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        
        List<Node> list = new ArrayList<>();
        
        // nodeinfo를 가지고 Node객체를 만들어 저장합니다.
        for(int i = 0 ; i < nodeinfo.length ; i++) {
            list.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        
        // y값에 대해 정렬하여 자식 노드의 y값은 항상 부모 노드보다 작게합니다.
        list.sort((n1, n2) -> {
            return n2.y - n1.y;
        });
        
        // y값이 가장 큰 노드가 머리 노드가 됩니다.
        Node head = new Node(list.get(0).index, list.get(0).x, list.get(0).y);
        
        // 노드들을 이진 트리에 넣습니다.
        for(int i = 1 ; i < list.size() ; i++) {
            Node i_node = list.get(i);
            Queue<Node> q = new LinkedList<Node>();
            q.add(head);
            
            while(!q.isEmpty()) {
                // 큐에서 뽑힌 노드
                Node p_node = q.poll();
                // 모든 노드는 서로 다른 x값을 가지므로 x값이 크거나, 작거나 둘 중 하나입니다.
                if(p_node.x > i_node.x) {
                    // x값이 작기때문에 왼쪽 자식이 됩니다.
                    if(p_node.left == null) {
                        p_node.left = i_node;
                        break;
                    }
                    else {
                        q.add(p_node.left);
                    }
                }
                else {
                    // x값이 크기때문에 오른쪽 자식이 됩니다.
                    if(p_node.right == null) {
                        p_node.right = i_node;
                        break;
                    }
                    else {
                        q.add(p_node.right);
                    }
                }
            }
        }
        
        // 전위 순회, 후위 순회를 합니다.
        preorder(head, answer[0]);
        postorder(head, answer[1]);
        
        return answer;
    }
    
    // 전위 순회를 하는 함수입니다.
    public void preorder(Node head, int[] answer) {
        Stack<Node> s = new Stack<Node>();
        s.push(head);
        int index = 0;
        
        while(!s.isEmpty()) {
            Node node = s.pop();
            
            answer[index++] = node.index;
            
            if(node.right != null) {
                s.push(node.right);
            }
            
            if(node.left != null) {
                s.push(node.left);
            }
        }
    }
    
    // 후위 순회를 하는 함수입니다.
    // 전역변수 postorderIndex를 이용하여 배열에 저장합니다.
    public void postorder(Node head, int[] answer) {
        if(head.left == null && head.right == null) {
            answer[postorderIndex++] = head.index;
        }
        else {
            if(head.left != null) {
                postorder(head.left, answer);
            }
            if(head.right != null) {
                postorder(head.right, answer);
            }
            answer[postorderIndex++] = head.index;
        }
    }
}

class Node {
    int index;
    int x;
    int y;
    Node left;
    Node right;
    
    public Node(int index, int x, int y) {
        this.index = index;
        this.x = x;
        this.y = y;
        left = null;
        right = null;
    }
}