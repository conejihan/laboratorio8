package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.TipoItem;

import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemMapper {
    public List<TipoItem> getTiposItems() throws PersistenceException;

    public TipoItem getTipoItem(@Param("idti") int id) throws PersistenceException;

    public void addTipoItem(@Param("ti") TipoItem tipoItem) throws PersistenceException;
}
