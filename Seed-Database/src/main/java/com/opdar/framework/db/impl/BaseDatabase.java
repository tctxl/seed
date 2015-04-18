package com.opdar.framework.db.impl;


import com.opdar.framework.db.convert.*;
import com.opdar.framework.db.interfaces.IDao;
import com.opdar.framework.db.interfaces.IDatabase;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Jeffrey on 2014/9/3
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
public class BaseDatabase implements IDatabase {
    private DataSource dataSource;
    private HashMap<Class<?>, Convert<?>> converts = new LinkedHashMap<Class<?>, Convert<?>>();

    public BaseDatabase(DataSource dataSource) {
        this.dataSource = dataSource;
        converts.put(Date.class, new DateConvert());
        converts.put(Timestamp.class, new TimestampConvert());
        converts.put(Integer.class, new IntegerConvert());
        converts.put(Byte.class, new ByteConvert());
        converts.put(Long.class, new LongConvert());
        converts.put(Character.class, new CharacterConvert());
        converts.put(Enum.class, new EnumConvert());
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> IDao<T> getDao(Class<T> cls) {
        return new BaseDaoImpl<T>(dataSource, cls, this);
    }

    @Override
    public void addConvert(Class<?> clz, Convert convert) {
        converts.put(clz, convert);
    }

    public HashMap<Class<?>, Convert<?>> getConverts() {
        return converts;
    }

    public void setConverts(HashMap<Class<?>, Convert<?>> converts) {
        this.converts = converts;
    }
}
