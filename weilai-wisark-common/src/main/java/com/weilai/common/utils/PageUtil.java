package com.weilai.common.utils;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlInjectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weilai.common.exception.SystemException;
import com.weilai.common.request.PageRequest;
import com.weilai.common.response.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页工具类
 */
@Slf4j
public class PageUtil {


    /**
     * 转换为查询参数
     */
    public static <T> Page<T> toPageQuery(PageRequest pageRequest) {
        Page<T> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());

        List<PageRequest.SortItem> sortItemList = pageRequest.getSortItemList();
        if (CollectionUtils.isEmpty(sortItemList)) {
            return page;
        }

        // 设置排序字段并检测是否含有sql注入
        List<OrderItem> orderItemList = new ArrayList<>();
        for (PageRequest.SortItem sortItem : sortItemList) {

            if (StrUtil.isEmpty(sortItem.getColumn())) {
                continue;
            }

            if (SqlInjectionUtils.check(sortItem.getColumn())) {
                log.error("存在SQL注入： : {}", sortItem.getColumn());
                throw new SystemException("存在SQL注入风险");
            }
            orderItemList.add(sortItem.getIsAsc() ? OrderItem.asc(sortItem.getColumn()) : OrderItem.desc(sortItem.getColumn()));
        }
        page.setOrders(orderItemList);
        return page;
    }







    /**
     * 将MyBatis-Plus分页结果转换为通用分页结果
     * @param page MyBatis-Plus分页对象
     * @param <T> 数据类型
     * @return 通用分页结果
     */
    public static <T> PageResult<T> toPageResult(IPage<T> page) {
        return new PageResult<>(
                page.getRecords(),
                page.getCurrent(),
                page.getSize(),
                page.getTotal(),
                page.getPages()
        );
    }

    /**
     * 将MyBatis-Plus分页结果转换为通用分页结果，并支持数据转换
     * @param page MyBatis-Plus分页对象
     * @param converter 数据转换函数
     * @param <T> 源数据类型
     * @param <R> 目标数据类型
     * @return 通用分页结果
     */
    public static <T, R> PageResult<R> toPageResult(IPage<T> page, Function<T, R> converter) {
        return new PageResult<>(
                page.getRecords().stream().map(converter).collect(Collectors.toList()),
                page.getCurrent(),
                page.getSize(),
                page.getTotal(),
                page.getPages()
        );
    }

    /**
     * 创建空的分页结果
     * @param current 当前页
     * @param size 每页大小
     * @param <T> 数据类型
     * @return 空的分页结果
     */
    public static <T> PageResult<T> emptyPage(long current, long size) {
        return new PageResult<>(current, size);
    }
}
