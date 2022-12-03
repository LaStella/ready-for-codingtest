package codesquadtest.level2;
// https://github.com/code-squad/test-item-pool/blob/master/level2-common/level2.md

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] number = getNumber();

        while(true) {
            int[] answer = getAnswer();
            int strikeCount = getStrikeCount(answer, number);
            if (strikeCount == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                break;
            }
            int ballCount = getBallCount(answer, number) - strikeCount;
            StringBuilder sb = new StringBuilder();
            if (strikeCount != 0) sb.append(strikeCount + "스트라이크 ");
            if (ballCount != 0) sb.append(ballCount + "볼");
            System.out.println(sb);
        }
    }

    // 컴퓨터가 난수를 3개 생성하는 함수입니다.
    public static int[] getNumber() {
        Set<Integer> set = new HashSet<>();
        while (set.size() != 3) {
            set.add(new Random().nextInt(10)+1);
        }
        return set.stream().mapToInt(x -> x).toArray();
    }

    // 유저 입력을 확인하는 함수입니다.
    public static int[] getAnswer() throws IOException {
        LinkedHashSet<String> set;
        while(true) {
            System.out.print("숫자를 입력해주세요 ex)123 : ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] answer = br.readLine().split("");
            set = new LinkedHashSet<>(Arrays.asList(answer));
            if (set.size() == 3) break;
        }
        return set.stream().mapToInt(x -> Integer.parseInt(x)).toArray();
    }

    // 볼의 개수를 확인하는 함수입니다.
    public static int getBallCount(int[] answer, int[] number) {
        int ballCount = 0;
        Set<Integer> set = Arrays.stream(number).boxed().collect(Collectors.toSet());
        for (int i = 0 ; i < 3 ; i++) {
            if (set.contains(answer[i])) ballCount++;
        }
        return ballCount;
    }

    // 스트라이크의 개수를 확인하는 함수입니다.
    public static int getStrikeCount(int[] answer, int[] number) {
        int strikeCount = 0;
        for (int i = 0 ; i < 3 ; i++) {
            if (answer[i] == number[i]) strikeCount++;
        }
        return strikeCount;
    }
}
