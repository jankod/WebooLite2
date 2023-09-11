package hr.ja.weboo;

import java.util.*;

public class TemplateWidgetUtil {

    public String qute(String template, String key, String value) {
        return WebooUtil.qute(template, Map.of(key, value));
    }

    public String qute(String template, Object... kv) {
        if ((kv.length & 1) != 0) { // implicit nullcheck of input
            throw new RuntimeException("KeyValue length is odd: " + Arrays.toString(kv));
        }
        int size = kv.length >> 1;

        HashMap<String, Object> map = new HashMap<>(size);
        for (int i = 0; i < kv.length; i += 2) {
            Object k = Objects.requireNonNull(kv[i]);
            Object v = Objects.requireNonNull(kv[i + 1]);
            map.put(k.toString(), v);
        }

        return WebooUtil.qute(template, map);
    }
}
