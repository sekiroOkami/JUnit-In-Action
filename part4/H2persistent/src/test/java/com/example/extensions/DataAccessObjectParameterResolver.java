package com.example.extensions;

import com.example.jdbc.ConnectionManager;
import com.example.jdbc.PassengerDao;
import com.example.jdbc.PassengerDaoImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class DataAccessObjectParameterResolver implements ParameterResolver {

    // return true if the parameter is of type PassengerDao
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter()
                .getType()
                .equals(PassengerDao.class);
    }

    // returns a newly initialized PassengerDaoImpl that receives as a constructor parameter
    // the connection provided by the ConnectionManager This parameter will be injected into the test constructor at
    // runtime
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new PassengerDaoImpl(ConnectionManager.openConnection());
    }
}
