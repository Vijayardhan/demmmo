package com.lms;

public interface SerializationService {

    void serializeLibrary(Library library, String fileName);

    Library deserializeLibrary(String fileName);
}