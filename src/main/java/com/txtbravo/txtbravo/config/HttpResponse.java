package com.txtbravo.txtbravo.config;

public enum HttpResponse
{
    OK(200), CREATED(201), ACCEPTED(202), PARTIAL_INFO(203), NO_RESPONSE(204), MOVED(301), FOUND(302),
    METHOD(303), NOT_MODIFIED(304), BAD_REQUEST(400), UNAUTHORIZED(401), PAYMENT_REQUIERED(402), FORBIDDEN(403),
    NOT_FOUND(404), UNPROCESSABLE_ENTITY(422), INTERNAL_ERROR(500), NOT_IMPLEMENTED(501), OVERLOADED(502), GATEWAY_TIMEOUT(503),

    KEY_CODE("code"), KEY_STATUS("status"), KEY_MESSAGE("message"), KEY_ERROR("error"), KEY_ERRORS("errors"), KEY_SUCCESS("success"),
    KEY_NOT_FOUND("not_found");

    private int code;
    private String key;

    HttpResponse(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    HttpResponse(String key)
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }
}