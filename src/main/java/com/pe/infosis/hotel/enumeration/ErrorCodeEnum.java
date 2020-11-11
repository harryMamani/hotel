
package com.pe.infosis.hotel.enumeration;

import com.pe.infosis.hotel.bean.Status;

public enum ErrorCodeEnum
{
    OK(ErrorContext.LOGIC, 0, "Ejecuci\u00f3n exitosa."), 
    UNAUTHORIZED(ErrorContext.LOGIC, 1, "No se encuentra autenticado."),
    ACCESS_DENIED(ErrorContext.LOGIC, 2, "No tiene acceso para este recurso."),
    MIMETYPE_INVALID(ErrorContext.LOGIC, 3, "El tipo de dato es inválido."),
    STORE_PROCEDURE(ErrorContext.LOGIC, 4, "Error inesperado."),
    FAIL(ErrorContext.LOGIC, 1000, "Error inesperado."),
    RESOURCE_NOT_EXIST(ErrorContext.STORE_PROCEDURE, 1, "Recurso no existe."),
    
    HABITACION_YA_EXISTE(ErrorContext.LOGIC, 5, "habitaci\u00f3n ya existe."), 
    HABITACION_NO_EXISTE(ErrorContext.LOGIC, 6, "habitaci\u00f3n No existe."), 
    TIPO_HABITACION_NO_EXISTE(ErrorContext.LOGIC, 7, "Tipo de habitaci\u00f3n no existe."), 
    ESTADO_HABITACION_NO_EXISTE(ErrorContext.LOGIC, 8, "Estado de habitaci\u00f3n no existe."),
    TRANSICION_NO_PERMITIDA(ErrorContext.LOGIC, 9, "Cambio de estado no permitido."),
    HABITACIONES_NO_DISPONIBLES(ErrorContext.LOGIC, 10, "No hay habitaciones disponibles."),
    CAPACIDAD_EXCEDIDA(ErrorContext.LOGIC, 11, "El n\u00famero de persona excede la capacidad de la habitaci\u00f3n."),
    VALIDACION(ErrorContext.LOGIC, 12, ""),
    ;

    private ErrorContext context;
    private Status status;

    ErrorCodeEnum(ErrorContext context, Integer index, String message)
    {
        this.status = new Status(context.getCode(index), message);
        this.context = context;
    }

    public ErrorContext getContext()
    {
        return context;
    }

    public String getCode()
    {
        return this.status.getCode();
    }

    public String getMessage()
    {
        return this.status.getMessage();
    }

    public boolean isCode(String code)
    {
        return this.getCode().equals(code);
    }

    public Status status()
    {
        return this.status;
    }

}
