package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户虚拟货币表
 * @TableName user_virtual_currency
 */
@Data
@TableName("user_virtual_currency")
@Schema(description = "用户虚拟货币表")
public class UserVirtualCurrency implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 用户ID
     */
    @NotNull(message = "[用户ID]不能为空")
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 余额
     */
    @NotNull(message = "[余额]不能为空")
    @Schema(description = "余额")
    private BigDecimal balance;

    /**
     * 冻结金额
     */
    @NotNull(message = "[冻结金额]不能为空")
    @Schema(description = "冻结金额")
    private BigDecimal freezeBalance;

    /**
     * 更新时间
     */
    @NotNull(message = "[更新时间]不能为空")
    @Schema(description = "更新时间")
    private DateTime updatedAt;
}