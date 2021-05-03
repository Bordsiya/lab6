package utils;

/**
 * Argument State for request (what we need to add to request) or special requests, connected with {@link Console}
 * @author NastyaBordun
 * @version 1.1
 */

public enum ArgumentState {
    ADD_OBJECT,
    UPDATE_OBJECT,
    ERROR,
    OK,
    SCRIPT_MODE,
    EXIT;
}
