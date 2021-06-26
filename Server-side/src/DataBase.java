import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase {
    private static DataBase DATA_BASE;
    private final Map<String, User> data;
    //               username,user

    private DataBase() {
        data = new ConcurrentHashMap<>();
    }

    public static DataBase getInstance() {
        if (DATA_BASE == null)
            DATA_BASE = new DataBase();
        return DATA_BASE;
    }

    public Map<String, User> getData() {
        return data;
    }
}
