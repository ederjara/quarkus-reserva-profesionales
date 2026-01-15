package com.ejara.platform.infraestructure.exceptions.handlers;

import com.ejara.platform.interfaces.resources.response.ExceptionResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
    @Override
    public Response toResponse(BusinessException exception) {
        log.error("Error de negocio", exception);
        return Response
                .status(HttpResponseStatus.PRECONDITION_FAILED.code())
                .entity(ExceptionResponse.builder().message(exception.getMessage()).build())
                .build();
    }
}
