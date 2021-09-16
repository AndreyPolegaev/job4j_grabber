package ru.job4j.solid.isp;

/**
 * Example 1
 * Java developer doesnt need to write another code
 */

interface Responsibilities {
    void writeJavaCode();
    void writeScalaCode();
    void writeSPythonCode();
}

class JavaDeveloper implements Responsibilities {
    @Override
    public void writeJavaCode() {
    }

    @Override
    public void writeScalaCode() {
    }

    @Override
    public void writeSPythonCode() {
    }
}


/**
 * Example 2
 * SomeStore class needs to contain only add() and delete()
 * load() we need to remove from Actions
 */

interface Actions {
    void load();
    boolean add();
    boolean delete();
}

class SomeStore implements Actions {
    @Override
    public void load() {
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}

/**
 * Example 3
 * We need to separate it into 2 different interfaces
 */

interface SomeActions {
    void sqlSave();
    void memSave();
}
public class Base implements SomeActions {
    @Override
    public void sqlSave() {

    }

    @Override
    public void memSave() {

    }
}
