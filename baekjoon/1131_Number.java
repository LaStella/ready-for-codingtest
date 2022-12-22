// 숫자
// https://www.acmicpc.net/problem/1131

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long answer = 0;

        for (long i = a ; i <= b ; i++) {
            answer += getMinS(i, k);
        }

        System.out.println(answer);
    }

    // 수열의 최소값을 구하는 함수입니다.
    private static long getMinS(long a, int k) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(a);

        // a부터 수열을 구합니다. a, S(a), S(S(a)), ...
        while (true) {
            long s = getS(a, k);
            // 이미 구한적 있는 숫자면 최소값을 리턴합니다.
            if(priorityQueue.contains(s)) {
                return priorityQueue.poll();
            }
            priorityQueue.add(s);
            a = s;
        }
    }

    // 각 자리수를 K제곱 한 후 합을 구하는 함수 S입니다.
    private static long getS(long a, int k) {
        long answer = 0;

        while (a > 0) {
            answer += Math.pow(a % 10, k);
            a /= 10;
        }

        return answer;
    }
}


/*
수열은 N부터 시작하여 이전 수에 대해서 S함수 값을 구합니다.
S값을 계산하였을 때 이미 이전에 구한 값이라면 반복되므로 종료합니다.
    ex) 4, 16, 37, 58, 89, 145, 42, 20, 4 종료
수열에서 나온 값 중 최소값을 찾아 합을 구합니다.
 */