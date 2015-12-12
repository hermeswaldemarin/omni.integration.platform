package br.com.omniplusoft.gateway.infrastructure.exception;

/**
 * Created by hermeswaldemarin on 10/12/15.
 */
public class LoginException extends RuntimeException {

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

}
