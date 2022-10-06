package com.bh.store.service.ex;

public class OrderItemEmptyException extends ServiceException{
    public OrderItemEmptyException() {
        super();
    }

    public OrderItemEmptyException(String message) {
        super(message);
    }

    public OrderItemEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderItemEmptyException(Throwable cause) {
        super(cause);
    }

    protected OrderItemEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
