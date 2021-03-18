/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.entities.Item;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();


        //Crear el mapper y usarlo:
        //ClienteMapper cm=sqlss.getMapper(ClienteMapper.class)
        //cm...
        ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        imprimirEspacios();
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        System.out.println("--------------Consultar Clientes--------");
        System.out.println(cm.consultarClientes());
        imprimirEspacios();
        System.out.println("--------------Consultar Cliente---------");
        System.out.println(cm.consultarCliente(90));
        imprimirEspacios();
        cm.agregarItemRentadoACliente(4,5,convertirFecha("2019-10-04"),convertirFecha("2019-10-11"));
        System.out.println("--------------Consultar Cliente id--------");
        System.out.println(cm.consultarCliente(4));
        TipoItem holaMundoItem = new TipoItem(12,"hola mundo");
        Item aItem;
        aItem = new Item(holaMundoItem,366,"La patada del mocho 4",
                "mas mocho que nunca",
                convertirFecha("2007-08-07"), 30,
                "Semanal",
                "Ciencia ficcion"
        );
        im.insertarItem(aItem);
        imprimirEspacios();
        System.out.println("--------------Consultar Items--------");
        System.out.println(im.consultarItems());
        imprimirEspacios();
        System.out.println("--------------Consultar Item id--------");
        System.out.println(im.consultarItem(345));
        sqlss.commit();
        sqlss.close();
    }

    public static Date convertirFecha(String fecha){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static void imprimirEspacios(){
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
