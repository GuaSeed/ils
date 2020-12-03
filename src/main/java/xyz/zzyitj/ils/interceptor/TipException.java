package xyz.zzyitj.ils.interceptor;


import xyz.zzyitj.ils.enums.ResultEnum;

/**
 * @author intent
 */
public class TipException extends Exception {
    private static final long serialVersionUID = 5061399106905500926L;
    private ResultEnum resultEnum;

    public TipException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.resultEnum = resultEnum;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }

    @Override
    public String toString() {
        return resultEnum.toString();
    }
}
