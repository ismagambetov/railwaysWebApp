package com.epam.ism.utils;

import com.epam.ism.entity.Restriction;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.InputStream;

public final class XmlManager {
    private static XmlManager INSTANCE = null;
    private InputStream input = null;
    private Object object = null;

    public static XmlManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XmlManager();
        }

        return INSTANCE;
    }

    public Object getParsedObject(String pathToXml, Class<?> clazz) throws JAXBException {

        if (this.input == null) {
            this.input = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathToXml);
        }

        if (this.object == null) {
            this.object = JAXBContext.newInstance(clazz).createUnmarshaller().unmarshal(input);
        }

        return this.object;

    }
}
