package com.example.extensions;

import com.example.jdbc.ConnectionManager;
import com.example.jdbc.TablesManager;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.Savepoint;

/**
 * 1. Before the execution of the entire test suite, reinitialize the db and open a connection to it.
 * 2. At the end of the execution of the suite, close the connection to the database.
 * 3. Before executing a test, make sure the database is in a known state so the developer can be sure it content is
 * tested correctly.
 */
public class DatabaseOperationsExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback,
        AfterEachCallback {
    private Connection connection;

    // Track the state of the database before the execution of a test and restore it after the test.
    private Savepoint savepoint;

    // beforeAll, inherited from the BeforeAllCallback interface,
    // is executed before the whole suite.
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        connection = ConnectionManager.openConnection();
        TablesManager.dropTable(connection);
        TablesManager.createTable(connection);
    }

    // afterAll, inherited from the AfterALlCallback interface, is executed after the whole suite
    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        ConnectionManager.closeConnection();
    }

    // beforeEach, inherited from the BeforeEachCallback interface, is executed before each test,
    // it disables autocommit mode, so the db resulting from the execution of the test should not be committed
    // then the method saves the state of the da before the execution of the test so that , after the test, the
    // developer can roll back to it.
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint("savepoint");
    }

    // afterEach, inherited from the AfterEachCallback, is executed after each test.
    // it rolls back to the state of the database that was saved before the execution of the test.
    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        connection.rollback(savepoint);
    }




}
