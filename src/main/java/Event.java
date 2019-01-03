import javafx.scene.input.DataFormat;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private DateFormat dateFormat;
    private int id = new Random().nextInt(100);
    private String msg;
    private Date date;

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + dateFormat.format(date) +
                "}\n";
    }
}
