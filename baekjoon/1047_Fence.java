// 울타리
// https://www.acmicpc.net/problem/1047

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Tree> treeList = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            
            Tree tree = new Tree(x, y, length);
            treeList.add(tree);
        }

        List<Integer> sortedTreeListByX = treeList.stream()
                .map(Tree::getX)
                .sorted(((o1, o2) -> o1 - o2))
                .toList();
        List<Integer> sortedTreeListByY = treeList.stream()
                .map(Tree::getY)
                .sorted((o1, o2) -> o1 - o2)
                .toList();

        for (int x1 = 0 ; x1 < n ; x1++) {
            for (int x2 = n-1 ; x2 >= x1 ; x2--) {
                for (int y1 = 0 ; y1 < n ; y1++) {
                    for (int y2 = n-1 ; y2 >= y1 ; y2--) {
                        Fence fence = new Fence(sortedTreeListByX.get(x1), sortedTreeListByX.get(x2), sortedTreeListByY.get(y1), sortedTreeListByY.get(y2));

                        PriorityQueue<Tree> outRangeTreeQueue = new PriorityQueue<>(((o1, o2) -> o2.getLength() - o1.getLength()));

                        for (Tree tree : treeList) {
                            if (!fence.isInRange(tree.getX(), tree.getY())) {
                                outRangeTreeQueue.add(tree);
                            }
                        }


                        int needCount = fence.getNeedCount();
                        int cutCount = 0;
                        while (needCount > 0 && !outRangeTreeQueue.isEmpty()) {
                            needCount -= outRangeTreeQueue.poll().getLength();
                            cutCount++;
                        }

                        if (needCount <= 0) System.out.println(cutCount);

                    }
                }
            }
        }


        System.out.println("!"); //breakpoint


    }
}

class Tree {
    int x, y, length;

    public Tree(int x, int y, int length) {
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return length;
    }
}

class Fence {
    int x1, x2, y1, y2;

    public Fence(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int getNeedCount() {
        return (2 * (x2 - x1)) + (2 * (y2 - y1));
    }

    public boolean isInRange(int x, int y) {
        return x1 <= x && x <= x2 && y1 <= y && y <= y2;
    }
}



/*
처음 문제에서 울타리 개수에 대한 언급이 없어 예제 1, 2번의 입출력만 보고 울타리의 개수가 여러개인줄 알았습니다.

직사각형 울타리를 가능한 최대 크기에서부터 크기를 줄이며 나무를 최소로 베는 경우를 찾습니다.
가능한 최대 크기의 울타리는 x값의 최댓값과 최솟값, y값의 최댓값과 최솟값으로 이루어집니다.

각 4 꼭지점에 대해 for문을 사용하여 크기를 점차 줄입니다.
우선 각각 x값과 y값의 크기순으로 정렬된 리스트 2개를 만들어 각 꼭지점을 저장합니다.
울타리를 이루는 4 꼭지점은 각각 (x1,y1) (x1, y2) (x2, y1) (x2, y2) 라고 한다면
x1은 x 최솟값부터 시작, x2는 x최댓값부터 시작하여 1씩 줄이며 리스트에서 다음값을 (x1은 최솟값, x2는 최댓값)을 가져옵니다.
y값도 마찬가지입니다.




 */