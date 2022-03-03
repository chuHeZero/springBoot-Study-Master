package com.example.manage.exception;

import com.example.manage.domain.IResultCode;
import com.example.manage.enums.common.BizErrorCodeEnum;
import lombok.Data;

/**
 * 业务异常类信息
 */
@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected final IResultCode resultCode;

    /**
     * 无参默认构造 UNSPECIFIED
     */
    public BizException() {
        super(BizErrorCodeEnum.SYSTEM_ERROR.getMsg());
        this.resultCode = BizErrorCodeEnum.SYSTEM_ERROR;
    }

    /**
     * 指定错误码构造通用异常
     *
     * @param resultCode 错误码
     */
    public BizException(final IResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    /**
     * 指定详细描述构造通用异常
     *
     * @param detailedMessage 详细描述
     */
    public BizException(final String detailedMessage) {
        super(detailedMessage);
        this.resultCode = BizErrorCodeEnum.SYSTEM_ERROR;
    }

    /**
     * 指定异常构造通用异常
     *
     * @param t 异常
     */
    public BizException(final Throwable t) {
        super(t);
        this.resultCode = BizErrorCodeEnum.SYSTEM_ERROR;
    }

    /**
     * 构造通用异常
     *
     * @param resultCode      错误码
     * @param detailedMessage 详细描述
     */
    public BizException(final IResultCode resultCode, final String detailedMessage) {
        super(detailedMessage);
        this.resultCode = resultCode;
    }

    /**
     * 构造通用异常
     *
     * @param resultCode 错误码
     * @param t          异常
     */
    public BizException(final IResultCode resultCode, final Throwable t) {
        super(resultCode.getMsg(), t);
        this.resultCode = resultCode;
    }

    /**
     * 构造通用异常
     *
     * @param detailedMessage 详细描述
     * @param t               异常
     */
    public BizException(final String detailedMessage, final Throwable t) {
        super(detailedMessage, t);
        this.resultCode = BizErrorCodeEnum.SYSTEM_ERROR;
    }

    /**
     * 构造通用异常
     *
     * @param resultCode      错误码
     * @param detailedMessage 详细描述
     * @param t               异常
     */
    public BizException(final IResultCode resultCode, final String detailedMessage,
                        final Throwable t) {
        super(detailedMessage, t);
        this.resultCode = resultCode;
    }

    /**
     * errorCode
     *
     * @return
     */
    public IResultCode getErrorCode() {
        return resultCode;
    }

}