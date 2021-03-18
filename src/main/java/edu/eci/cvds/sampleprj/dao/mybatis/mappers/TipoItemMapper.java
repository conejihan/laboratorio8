package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemMapper {
    List<TipoItem> getTiposItems();
    TipoItem getTipoItem(int id);
    void addTipoItem(String des);
}
