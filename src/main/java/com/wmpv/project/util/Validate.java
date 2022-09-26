package com.wmpv.project.util;

import java.util.Objects;

import com.wmpv.project.exception.WsServiceException;

public class Validate {

	/**
	 * Valida nulos
	 * @param obj
	 * @throws WsServiceException
	 */
	public static void notFound(Object object) throws WsServiceException {
		if(Objects.isNull(object))
			throw new WsServiceException("no encontrado.");
	}
}
