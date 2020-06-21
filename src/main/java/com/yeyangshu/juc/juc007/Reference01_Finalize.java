/**
 * Copyright (C), 2018-2020
 * FileName: Reference01_Finalize
 * Author:   11077
 * Date:     2020/6/20 10:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc007;

/**
 * 重写finalize，便于观察（只是为了观察，其余情况不应该重写）
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/20 10:18
 */
public class Reference01_Finalize {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
