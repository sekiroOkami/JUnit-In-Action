package com.chapter14;

import com.chapter14.jdbc.ConnectionManager;
import com.chapter14.jdbc.TablesManager;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.Savepoint;

public class DatabaseOperationsExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback,
        AfterEachCallback {

    // connect DB
    private Connection connection;
    // track the state of db
    private Savepoint savepoint;

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        connection = ConnectionManager.openConnection();
        TablesManager.dropTable(connection);
        TablesManager.createTable(connection);
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint("savepoint");
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        connection.rollback(savepoint);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        ConnectionManager.closeConnection();
    }


}
