package im.turms.turms.util;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.function.Function3;

import java.util.*;
import java.util.function.Function;

public class MapUtil {
    private MapUtil() {
    }

    public static <K, V> Map<K, V> deepMerge(Map<K, V> map1, Map<? extends K, ? extends V> map2) {
        for (K key : map2.keySet()) {
            V value1 = map1.get(key);
            V value2 = map2.get(key);
            if (value1 instanceof Collection && value2 instanceof Collection) {
                if (!((Collection) value1).containsAll((Collection) value2)) {
                    ((Collection) value1).addAll((Collection) value2);
                }
            } else if (value1 instanceof Map && value2 instanceof Map) {
                deepMerge((Map) value1, (Map) value2);
            } else {
                V existingValue = map1.get(key);
                if (existingValue == null || !existingValue.equals(value2)) {
                    map1.put(key, value2);
                }
            }
        }
        return map1;
    }

    public static Map addValueKeyToAllLeaves(Object properties) {
        if (properties instanceof Map) {
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) properties).entrySet()) {
                Object value = entry.getValue();
                entry.setValue(addValueKeyToAllLeaves(value));
            }
            return (Map) properties;
        } else {
            HashMap<Object, Object> map = new HashMap<>(1);
            map.put("value", properties);
            return map;
        }
    }

    public static Mono<Boolean> fluxMerge(Function<Multimap<Long, Long>, Void> mapFiller, Function3<List<Mono<Boolean>>, Long, Set<Long>, Void> monosFiller) {
        HashMultimap<Long, Long> multimap = HashMultimap.create();
        mapFiller.apply(multimap);
        List<Mono<Boolean>> monos = new ArrayList<>(multimap.keySet().size());
        for (Long key : multimap.keySet()) {
            Set<Long> values = multimap.get(key);
            monosFiller.apply(monos, key, values);
        }
        return Flux.merge(monos).all(value -> value);
    }
}
