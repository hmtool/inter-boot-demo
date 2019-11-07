package tech.mhuang.interchan.logger.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * ES操作日志
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
public class ESOperatorLogger {

    /**
     * Id
     */
    @JSONField(serialize = false)
    private String id;

    /**
     * URL
     */
    private String url;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 请求得类名
     */
    private String clazz;

    /**
     * 请求得方法名
     */
    private String method;

    /**
     * 操作用户id
     */
    private String userId;

    /**
     * 操作ip
     */
    private String ip;

    /**
     * 操作头
     */
    private String headerData;

    /**
     * 查询对象
     */
    private String queryData;

    /**
     * 发送得数据
     */
    private String sendData;

    /**
     * 返回得参数
     */
    private String restData;

    /**
     * 开始操作时间
     */
    private String startDate;

    /**
     * 结束操作时间
     */
    private String endDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 异常信息
     */
    private String eMsg;

    /**
     * 异常详细信息
     */
    private String eDetailMsg;

    /**
     * 系统类型
     */
    private String systemType;

    /**
     * 状态(0代表执行中，1代表成功，2代表异常）
     */
    private Integer status = 0;

    /**
     * 开始时间的Unit时间戳
     */
    private Date startDateUnit;

    /**
     * 结束时间的Unit时间戳
     */
    private Date endDateUnit;
}
