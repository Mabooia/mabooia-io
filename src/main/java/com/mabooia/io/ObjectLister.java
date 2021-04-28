package com.mabooia.io;

public interface ObjectLister<O> {

    ResourceStream<O> listObjects();

    ResourceStream<String> listObjectNames();
}
