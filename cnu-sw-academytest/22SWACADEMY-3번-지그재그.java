import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr1 = br.readLine().split(" ");
        String[] arr2 = br.readLine().split(" ");

        Queue<String> queue1 = new LinkedList<>(Arrays.asList(arr1));
        Queue<String> queue2 = new LinkedList<>(Arrays.asList(arr2));

        StringBuilder sb = new StringBuilder();
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            if (queue1.isEmpty()) {
                while(!queue2.isEmpty()) {
                    sb.append(queue2.poll()+" ");
                }
                continue;
            }

            if (queue2.isEmpty()) {
                while(!queue1.isEmpty()) {
                    sb.append(queue1.poll()+" ");
                }
                continue;
            }

            sb.append(queue1.poll()+" ");
            sb.append(queue2.poll()+" ");
        }

        System.out.println(sb);
    }
}