package com.chenjh.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.chenjh.util.crypto.AESCryptoUtil;
import com.chenjh.util.crypto.Base64Encoding;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.springframework.util.DefaultPropertiesPersister;


public class DataSourcePropertiesPersister extends DefaultPropertiesPersister
{
    /**
     * 操作日志对象
     */
    private static final Logger LOGGER = Logger.getLogger(DataSourcePropertiesPersister.class);
    
    /**
     * Load properties from the given InputStream into the given
     * Properties object.
     * @param props the Properties object to load into
     * @param is the InputStream to load from
     * @throws IOException in case of I/O errors
     * @see java.util.Properties#load
     */
    @Override
    public void load(Properties props, InputStream is) throws IOException
    {
        props.load(is);
        String pwd = new String(Base64Encoding.decode((String)props.get("jdbc.password")));
        props.put("jdbc.password", pwd);
    }



}
