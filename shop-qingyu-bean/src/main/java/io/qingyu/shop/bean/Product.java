package io.qingyu.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.qingyu.shop.utils.id.SnowFlakeFactory;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 商品类
 */
@Data
@TableName("t_product")
public class Product implements Serializable {
    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * 商品名称
     */
    @TableField("t_pro_name")
    private String proName;

    /**
     * 商品价格
     */
    @TableField("t_pro_price")
    private BigDecimal proPrice;

    /**
     * 商品库存
     */
    @TableField("t_pro_stock")
    private Integer proStock;

    public Product(){
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
    }
}
