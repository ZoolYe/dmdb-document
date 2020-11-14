package com.zool.dmdbdocument.service.utils;

import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : xxc
 * @description : 拷贝bean工具类
 * @date : 2020/3/6 09:06:46
 */
public class BeanCopierUtils {
    public static final Logger logger = LogManager.getLogger(BeanCopierUtils.class);

    /**
     * 连接源与目标类名分隔符
     */
    private static final String SEPARATE = "_";

    /**
     * 缓存拷贝Bean的BeanCopier
     */
    private static Map<String, BeanCopier> copierMap = new ConcurrentHashMap<>();

    /**
     * 获取拷贝源bean到目标bean的拷贝器对象
     *
     * @param source 源bean
     * @param target 目标bean
     * @return 拷贝源bean到目标bean的拷贝器对象
     * @author xxc
     */
    private static BeanCopier getCopier(Class<?> source, Class<?> target) {
        return getCopier(source, target, false);
    }

    /**
     * 获取拷贝源bean到目标bean的拷贝器对象
     *
     * @param source       源bean
     * @param target       目标bean
     * @param useConverter 是否使用转换器
     * @return 拷贝源bean到目标bean的拷贝器对象
     * @author xxc
     */
    private static BeanCopier getCopier(Class<?> source, Class<?> target, boolean useConverter) {
        String key = source.getName() + SEPARATE + target.getName() + SEPARATE + useConverter;
        BeanCopier copier = copierMap.get(key);
        if (copier == null) {
            copier = BeanCopier.create(source, target, useConverter);
            copierMap.put(key, copier);
        }

        return copier;
    }

    /**
     * 拷贝源bean属性到目标bean
     *
     * @param source 源bean
     * @param target 目标bean
     * @author xxc
     */
    public static void copy(Object source, Object target) {
        BeanCopier copier = getCopier(source.getClass(), target.getClass());
        copier.copy(source, target, null);
    }

    /**
     * 从源bean复制属性并生成新bean
     *
     * @param source      源bean
     * @param targetClass 目标类
     * @return 从源bean生成的目标bean
     * @author xxc
     */
    @SuppressWarnings("unchecked")
    public static <T, R> R copyBean(T source, Class<R> targetClass) {
        if (source != null && targetClass != null) {
            Object target;
            try {
                target = targetClass.getDeclaredConstructor().newInstance();

                BeanCopier copier = getCopier(source.getClass(), targetClass);
                copier.copy(source, target, null);
            } catch (Throwable t) {
                logger.warn("复制bean失败。源类别={}，目标类别={}。ex=", source.getClass(), targetClass, t.toString());
                return copyBean(source, targetClass);
            }

            return (R) target;
        } else {
            return null;
        }
    }

    /**
     * 从源bean列表复制属性并生成新bean列表
     *
     * @param sources     源bean列表
     * @param targetClass 目标类
     * @return 从源bean生成的目标bean
     * @author xxc
     */
    public static <T, R> List<R> copyList(List<T> sources, Class<R> targetClass) {
        if (!CollectionUtils.isEmpty(sources) && targetClass != null) {
            List<R> targetList = Lists.newArrayListWithCapacity(sources.size());
            for (T source : sources) {
                targetList.add(copyBean(source, targetClass));
            }
            return targetList;
        } else {
            return null;
        }
    }
}
