package com.hit.dao;

public interface IDao<ID extends java.io.Serializable, T> {

    T find(ID id) throws IllegalArgumentException;
    boolean save(T object) throws IllegalArgumentException;
}
