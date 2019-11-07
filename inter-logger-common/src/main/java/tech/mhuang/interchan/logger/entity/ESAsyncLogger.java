package tech.mhuang.interchan.logger.entity;

import lombok.Data;

/**
 * 异步日志
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
public class ESAsyncLogger {

    /**
     * es操作类型（新增 or 修改）
     */
    private ESAsyncLoggerOpType opType;

    /**
     * 操作的ES名称,对应比如张三
     */
    private String name;
    /**
     * es记录的索引
     */
    private String index;

    /**
     * es记录的索引类型
     */
    private String type;

    /**
     * es记录的日志
     */
    private ESOperatorLogger esOperatorLogger;

}
