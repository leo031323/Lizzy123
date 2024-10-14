package notebook_job;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentMonth {
    public static void main(String[] args) {
        String currentMonth = new SimpleDateFormat("MMM").format(new Date());
        System.out.println(currentMonth);
    }
}
