import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            String s = br.readLine();

            if (isCorrectBracket(s)) {
                System.out.println("YES");
                continue;
            }

            System.out.println("NO");
        }

    }

    // 올바른 괄호인지 확인하는 함수입니다.
    private static boolean isCorrectBracket(String s) {
        Stack<String> stack = new Stack<>();

        for (String bracket : s.split("")) {
            if (!stack.isEmpty() && isSameBracket(stack.peek(), bracket)) {
                stack.pop();
                continue;
            }

            stack.push(bracket);
        }

        if (stack.isEmpty()) {
            return true;
        }

        return false;
    }

    // 같은 종류의 괄호인지 확인하는 함수입니다.
    private static boolean isSameBracket(String open, String close) {
        if (open.equals("(") && close.equals(")"))
            return true;

        if (open.equals("{") && close.equals("}"))
            return true;

        if (open.equals("[") && close.equals("]"))
            return true;

        return false;
    }
}