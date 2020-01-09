package life.majiang.community.exception;

public enum CustomizeErrorCode implements  ICustomizeErrorCode{


    QUESTION_NOT_FOUND("GGMM 你找的问题不在了，换个试试呗");
    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
