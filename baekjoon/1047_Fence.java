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

        // 나무의 x와 y좌표를 기준으로 오름차순 정렬한 리스트를 만듭니다.
        List<Integer> sortedTreeListByX = new ArrayList<>();
        List<Integer> sortedTreeListByY = new ArrayList<>();

        // 모든 나무를 리스트에 저장합니다.
        StringTokenizer st;
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            Tree tree = new Tree(x, y, length);

            sortedTreeListByX.add(x);
            sortedTreeListByY.add(y);

            treeList.add(tree);
        }

        // 백준 stream 컴파일 에러
//        // 나무의 x좌표를 기준으로 오름차순 정렬한 리스트를 만듭니다.
//        List<Integer> sortedTreeListByX = treeList.stream()
//                .map(Tree::getX)
//                .sorted((o1, o2) -> o1 - o2)
//                .toList();
//
//        // 나무의 y좌표를 기준으로 오름차순 정렬한 리스트를 만듭니다.
//        List<Integer> sortedTreeListByY = treeList.stream()
//                .map(Tree::getY)
//                .sorted((o1, o2) -> o1 - o2)
//                .toList();
        
        Collections.sort(sortedTreeListByX);
        Collections.sort(sortedTreeListByY);

        int answer = Integer.MAX_VALUE;

        // x와 y좌표에 대해서 모든 경우의 수를 고려하여 울타리를 만듭니다.
        // x와 y로 정렬한 리스트에서 순서대로 뽑아 울타리를 만들게됩니다.
        // x1이 x좌표 리스트에서 0번째라면 x2는 가장 큰 n-1번째부터 0번째까지 될 수 있습니다.
        // x1 <= x2, y1 <= y2
        for (int x1 = 0 ; x1 < n ; x1++) {
            for (int x2 = n-1 ; x2 >= x1 ; x2--) {
                for (int y1 = 0 ; y1 < n ; y1++) {
                    for (int y2 = n-1 ; y2 >= y1 ; y2--) {
                        // (x1, y1), (x2, y2)를 꼭지점으로 가지는 직사각형 울타리를 만듭니다.
                        Fence fence = new Fence(sortedTreeListByX.get(x1), sortedTreeListByX.get(x2), sortedTreeListByY.get(y1), sortedTreeListByY.get(y2));

                        // 나무의 길이 내림차순으로 저장되는 우선순위큐입니다.
                        PriorityQueue<Tree> sortedByLengthTreeList = new PriorityQueue<>(((o1, o2) -> o2.getLength() - o1.getLength()));

                        // 벤 나무의 개수
                        int cutCount = 0;
                        // 벤 나무의 총 길이
                        int cutLength = 0;

                        for (Tree tree : treeList) {
                            // 울타리안에 포함되지 않는 나무는 벱니다.
                            if (!fence.isInRange(tree.getX(), tree.getY())) {
                                cutCount++;
                                cutLength += tree.getLength();
                                continue;
                            }
                            // 울타리안에 포함되는 나무는 우선순위큐에 넣습니다.
                            sortedByLengthTreeList.add(tree);
                        }

                        // 울타리를 만드는데 필요한 나무의 길이
                        int needLength = fence.getNeedLength();

                        // 부족한 나무 개수를 채우기 위해 울타리 안의 나무를 길이순으로 벱니다.
                        while (needLength > cutLength && !sortedByLengthTreeList.isEmpty()) {
                            cutLength += sortedByLengthTreeList.poll().getLength();
                            cutCount++;
                        }

                        // 울타리를 만들 수 있는 경우만 최소 벤 나무 개수를 저장합니다.
                        if (needLength <= cutLength) answer = Math.min(answer, cutCount);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}


// 나무 객체입니다.
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

// 울타리 객체입니다.
class Fence {
    int x1, x2, y1, y2;

    public Fence(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    // 울타리를 만드는데 필요한 나무의 개수를 리턴하는 함수입니다.
    public int getNeedLength() {
        // x1과 x2 또는 y1과 y2가 같은 경우 사각형의 한 변의 길이만 필요합니다.
        if (x1 == x2) return y2-y1;
        if (y1 == y2) return x2-x1;
        return (2 * (x2 - x1)) + (2 * (y2 - y1));
    }

    // 주어진 좌표 (x, y)가 울타리 안의 좌표인지 확인하는 함수입니다.
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