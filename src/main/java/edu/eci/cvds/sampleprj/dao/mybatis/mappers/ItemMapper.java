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


    public List<Item> consultarItems();

    public Item consultarItem(@Param("idt") int id);

    public void insertarItem(@Param("item") Item it);

    public List<Item> consultarItemsDisponibles();

    public void actualizarTarifaItem(@Param("id") int id, @Param("tarifa") long tarifa);


}