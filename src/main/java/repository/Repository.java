package main.java.repository;

import java.sql.Statement;

public abstract class Repository {

    protected Statement statement;

    public Repository(Statement statement) {
        this.statement = statement;
    }

}
