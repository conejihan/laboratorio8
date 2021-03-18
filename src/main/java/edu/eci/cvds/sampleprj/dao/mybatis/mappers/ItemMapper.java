package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    List<Item> consultarItems();
    Item consultarItem(@Param("idit")int id);
    void insertarItem(@Param("item")Item it);
}
