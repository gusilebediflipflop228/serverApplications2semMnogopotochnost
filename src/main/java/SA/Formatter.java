package SA;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
    private static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // отдельный экзепляр

    public String format(Date date) { // копия
        return dateFormat.get().format(date);
    }
}
