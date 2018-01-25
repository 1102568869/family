package tech.washmore.family.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Washmore
 * @version V1.0
 * @summary java8 stream辅助类
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/25
 */
public class StreamUtil {
    /**
     * @summary 根据对象某个字段去重
     * @version V1.0
     * @author Washmore
     * @since 2018/1/25
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * @summary 指定分组的时候如果使用map分组出现重名key时的异常处理方案
     * @version V1.0
     * @author Washmore
     * @since 2018/1/25
     */
    public static <T> BinaryOperator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }
}
