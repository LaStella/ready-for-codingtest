// 중앙값
// https://www.acmicpc.net/problem/1572

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());



    }
}


/*
Indexed Tree(인덱스트리)를 사용합니다.
k-1개의 입력을 트리에 넣을 경우 k개 미만이라면 아무것도 표시하지 않으므로 결과에 영향을 주지않습니다.
k개 부터 디스플레이에 중앙값이 표시됩니다.
k번째부터 n번째 값까지 차례로 트리에 넣으며 중앙값을 탐색합니다.
매번 중앙값을 탐색하며 이전에 넣은 값들은 트리에서 제거합니다. 
 */