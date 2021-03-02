package com.ourline.framework;

import java.io.Serializable;

public class BaseResult implements Serializable {
    private static final long serialVersionUID = -3948389268046368059L;
    /**
     * @Fields retStatus 请求状态
     * */
    private String Status;
    /**
     * @Fields retCode 返回代码
     * */
    private String retCode;
    /**
     * @Fields retMsg 返回消息
     * */
    private String retMsg;
    /**
     * @Fields retData 返回数据
     * */
    private Object retData;

    /**
     * 默认无参构造
     * */
    public BaseResult(){
        super();
        //默认请求成功
        this.Status = StatusCode.STATUS_SUCCESS.getCode();
        //默认失败返回状态
        this.retCode = StatusCode.CODE_FAILD.getCode();
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }
}
