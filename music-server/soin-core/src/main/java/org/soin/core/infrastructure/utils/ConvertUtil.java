package org.soin.core.infrastructure.utils;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 类型转换工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-12-13 11:27
 **/
public class ConvertUtil {

    public ConvertUtil() {}

    /**
     * {@link R}
     *
     * @param t       转换数据源
     * @param classes 转换类型
     * @return 转换之后的类型
     */
    public static <T, R> R convert(T t, Class<R> classes) {
        Objects.requireNonNull(t, "数据源为空!");
        Objects.requireNonNull(classes, "转换类型为空!");
        R instance = ReflectUtil.newInstanceIfPossible(classes);
        return BeanCopier.create(t, instance, CopyOptions.create()).copy();
    }

    /**
     * {@link List<R>}
     *
     * @param list    转换数据源
     * @param classes 转换类型
     * @return 批量转换数据类型
     */
    public static <T, R> List<R> converts(Collection<T> list, Class<R> classes) {
        if (CollUtil.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().map(convert -> convert(convert, classes)).collect(Collectors.toList());
    }


}
