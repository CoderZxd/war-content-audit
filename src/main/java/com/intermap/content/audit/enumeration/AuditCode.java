package com.intermap.content.audit.enumeration;

/**
 * @Project war-content-audit
 * @Package com.intermap.content.audit.enumeration
 * @Author：zouxiaodong
 * @Description:
 * @Date:Created in 16:35 2019/3/25.
 */
public enum AuditCode {

    Y("Y"),//通过
    P("P"),//通过,审核下一条
    N("N"),//不通过
    F("F");//不通过,审核下一条

    private String code;

    AuditCode(String code) {
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }
}
