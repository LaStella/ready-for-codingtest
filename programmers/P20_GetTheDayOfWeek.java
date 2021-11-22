import java.time.*;
import java.time.format.*;
import java.util.Locale;

class Solution {
    public String solution(int a, int b) {
        String answer = "";
        
        // LocalDate예제를 보고 따라한것.
        // LocalDate date = LocalDate.of(2016, a, b);
        // DayOfWeek dayOfWeek = date.getDayOfWeek();
        // answer = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase();
        
        // toString메소드가 있어서 앞에서 3자리를 자른것.
        answer = LocalDate.of(2016, a, b).getDayOfWeek().toString().substring(0, 3);
        
        
        return answer;
    }
}