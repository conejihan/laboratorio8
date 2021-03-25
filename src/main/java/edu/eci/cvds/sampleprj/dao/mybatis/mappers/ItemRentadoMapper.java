package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoMapper {

    public List<ItemRentado> consultarItemsRentados();

    public ItemRentado consultarItemRentado(@Param("idr") int id);
}
