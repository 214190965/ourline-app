package com.ourline.ourlinecommon.code;

public enum StatusCode {
    /**
     * @Fields STATUS_CUCCESS 请求成功，retStatus='1'
     */
    STATUS_SUCCESS("1"),
    /**
     * @Fields STATUS_FAILD
     *         请求失败retStatus='0'(业务服务不会用到此状态。如果要表示业务失败状态，请用<code>CODE_FAILD</code>实例)
     */
    STATUS_FAILD("0"),
    /**
     * @Fields CODE_SUCCESS 业务请求成功，retCode='200'(通常情况下，此时的retMsg可以不用赋值)
     */
    CODE_SUCCESS("200"),
    /**
     * @Fields CODE_FAILD
     *         业务请求失败，retCode='0'(retCode可按需要自定义代码。此时应赋值retMsg响应给客户端，提示失败原因)
     */
    CODE_FAILD("0");

    private String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
