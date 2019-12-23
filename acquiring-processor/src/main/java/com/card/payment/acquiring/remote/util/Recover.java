package com.card.payment.acquiring.remote.util;

/**
 * @author s0n00e1
 */
public interface Recover {

    default String recover(Throwable t) {
        return "Max retry failed with error message  :: " + t.getMessage();
    }

}
