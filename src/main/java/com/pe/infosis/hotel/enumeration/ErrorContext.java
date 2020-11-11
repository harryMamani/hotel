package com.pe.infosis.hotel.enumeration;

public enum ErrorContext
{
    LOGIC(0), STORE_PROCEDURE(1000), EXTERNAL_SERVICE(2000);

    private Integer base;

    ErrorContext(Integer base)
    {
        this.base = base;
    }

    public String getCode(Integer index)
    {
        return String.format("%04d", this.base + index);
    }
}
