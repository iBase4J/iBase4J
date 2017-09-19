package org.ibase4j.core.base;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;

/**
 * @author ShenHuaJie
 * @since 2017年2月14日 下午3:27:17
 */
@SuppressWarnings("serial")
public class Parameter implements Serializable {
    public Parameter() {
    }

    public Parameter(String service, String method) {
        this.service = service;
        this.method = method;
    }

    public Parameter(String service, String method, Object... param) {
        this.service = service;
        this.method = method;
        this.param = param;
    }

    public Parameter(Object result) {
        this.result = result;
    }

    private String service;
    private String method;

    private Object[] param;
    private Object result;

    private final String no = "[" + IdWorker.getId() + "]";

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public Parameter setMethod(String method) {
        this.method = method;
        return this;
    }

    public Object[] getParam() {
        return param;
    }

    public Parameter setParam(Object... param) {
        this.param = param;
        return this;
    }

    public String getNo() {
        return no;
    }

    public Parameter setResult(Object result) {
        this.result = result;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public List<?> getResultList() {
        if (result instanceof List<?>) {
            return (List<?>)result;
        }
        return null;
    }

    public Page<?> getResultPage() {
        if (result instanceof Page<?>) {
            return (Page<?>)result;
        }
        return null;
    }

    public Long getResultLong() {
        if (result instanceof Long) {
            return (Long)result;
        }
        return null;
    }
}
