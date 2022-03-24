package exceptions;

public enum ErrorCodes {
    TEN(10),
    TWENTY(20);

    private int code;

    ErrorCodes(int code) {
    }

    public int getErrorCode() {
        return code;
    }
}
