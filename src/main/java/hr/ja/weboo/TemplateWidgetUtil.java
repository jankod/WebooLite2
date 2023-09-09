package hr.ja.weboo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TemplateWidgetUtil {

    public String qute(String template, String key, String value) {
        return WebooUtil.qute(template, Map.of(key, value));
    }

    public String qute(String template, Object... kv) {
        if ((kv.length & 1) != 0) { // implicit nullcheck of input
            throw new RuntimeException("KeyValue length is odd");
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

//    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
//        return new ImmutableCollections.MapN<>(k1, v1, k2, v2);
//    }


//    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
//        return new ImmutableCollections.MapN<>(k1, v1, k2, v2, k3, v3);
//    }
//
//
//    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
//        return new ImmutableCollections.MapN<>(k1, v1, k2, v2, k3, v3, k4, v4);
//    }
//
//
//    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
//        return new ImmutableCollections.MapN<>(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
//    }
//
//
//
//    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
//                               K k6, V v6) {
//        return new ImmutableCollections.MapN<>(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5,
//                                               k6, v6);
//    }
//
//
//    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
//                               K k6, V v6, K k7, V v7) {
//        return new ImmutableCollections.MapN<>(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5,
//                                               k6, v6, k7, v7);
//    }
//
//
//    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
//                               K k6, V v6, K k7, V v7, K k8, V v8) {
//        return new ImmutableCollections.MapN<>(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5,
//                                               k6, v6, k7, v7, k8, v8);
//    }
//
//
//    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
//                               K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
//        return new ImmutableCollections.MapN<>(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5,
//                                               k6, v6, k7, v7, k8, v8, k9, v9);
//    }
//
//
//    static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
//                               K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
//        return new ImmutableCollections.MapN<>(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5,
//                                               k6, v6, k7, v7, k8, v8, k9, v9, k10, v10);
//    }
//

}
