package com.epam.ism.utils;

import com.epam.ism.dao.exception.DAOConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class immediately loads the DAO properties file '***.properties' once in memory and provides
 * a constructor which takes the specific key which is to be used as property key prefix of the DAO
 * properties file. There is a property getter which only returns the property prefixed with
 * 'specificKey.' and provides the option to indicate whether the property is mandatory or not.
 *
 * @author IDS.
 */

public class PropertyManager {
    //Constants
    private static final Properties PROPERTIES = new Properties();

    private String propertyFileName;

    public PropertyManager(String propertyFileName) {
        this.propertyFileName = propertyFileName;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(propertyFileName);

        if (propertiesFile == null) {
            throw new PropertyException("Properties file '" + propertyFileName + "' is missing in classpath.");
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new PropertyException("Loading properties failed.",e);
        }

    }

    /**
     * Returns the DAOProperties instance specific property value associated with the given key with
     * the option to indicate whether the property is mandatory or not.
     * @param key The key to be associated with a DAOProperties instance specific value.
     * @return The DAOProperties instance specific property value associated with the given key.
     * @throws DAOConfigurationException If the returned property value is null or empty while
     * it is mandatory.
     */
    public String getProperty(String key) throws DAOConfigurationException {
           String property = PROPERTIES.getProperty(key);

        if (property == null || property.trim().length() == 0) {
            throw new PropertyException("Required property '" + key + "'"
                    + " is missing in properties file '" + propertyFileName + "'.");

        }

        return property;
    }

}
