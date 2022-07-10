// 설탕 배달
// https://www.acmicpc.net/problem/2839

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int remain = n % 5;
        if(n == 4 || n == 7) {
            System.out.println(-1);
        }
        else if(remain == 0) {
            System.out.println(n / 5);
        }
        else if(remain == 1 || remain == 3) {
            System.out.println(n / 5 + 1);
        }
        else if(remain == 2 || remain == 4) {
            System.out.println(n / 5 + 2);
        }
    }

    /*
    n   5   3   result
    3   0   1   1
    4   0   0   -1
    5   1   0   1
    6   0   2   2
    7   0   0   -1
    8   1   1   2
    9   0   3   3
    10  2   0   2
    11  1   2   3
    12  0   4   4
    13  2   1   3

    n = 8 부터 n = 13까지 5kg이 느는 동안 필요한 봉지의 수에 규칙이 발견됩니다.
    n을 5로 나눈 나머지가 1 또는 3 인 경우 n을 5로 나눈 몫+1개가 필요하며
    n을 5로 나눈 나머지가 2 또는 4 인 경우 n을 5로 나눈 몫+2개가 필요합니다.
     */
}
