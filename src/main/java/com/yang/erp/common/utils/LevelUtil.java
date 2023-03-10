package com.yang.erp.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 层级计算工具类
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07 14:42:38
 */
public class LevelUtil {

    /**
     * 分割符
     */
    public final static String SEPARATOR = ".";

    /**
     * 根节点
     */
    public final static String ROOT = "0";


    /**
     * 通过父节点计算当前层级
     * @param parentLevel 上级层级
     * @param parentId 上级ID
     * @return 当前层级
     */
    public static String calculateLevel(String parentLevel, long parentId){
        if(StringUtils.isBlank(parentLevel)){
            return ROOT;
        }else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
