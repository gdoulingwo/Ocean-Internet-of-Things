package org.linkworld.ocean.system.common;

import lombok.Data;

/**
 * @author HALOXIAO
 * @since 2021-11-06 17:26
 **/
@Data
public class ResultBean<T> {

    private T data;
    private String msg;
    private int code;


    public static ResultBean OK() {
        ResultBean bean = new ResultBean();
        bean.setCode(200);
        return bean;
    }

}
