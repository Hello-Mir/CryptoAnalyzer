package exceptions;

public class CryptoAnalyzerException extends RuntimeException {

    public CryptoAnalyzerException(String message) {
        super(message);
    }

    public CryptoAnalyzerException(Throwable cause) {
        super(cause);
    }

    public CryptoAnalyzerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptoAnalyzerException(ErrorCodes errorCode, String message) {
        super(message);
    }
}
