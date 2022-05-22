package io.qingyu.shop.utils.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 返回的结果数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String codeMsg;

    /**
     *  返回的数据
     */
    private T data;
}
