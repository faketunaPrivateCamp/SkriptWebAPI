package dev.f2a.addon.skriptwebapi.internal.http;

public enum SkweapiServerStatus {
    OPERATION_SUCCESS,
    SERVER_IS_RUNNING,
    SERVER_IS_STOPPED,
    CONTEXT_PATH_NOT_START_WITH_SLASH,
    CONTEXT_PATH_NOT_END_WITH_SLASH,
    EXCEPTION_OCCURRED
}

