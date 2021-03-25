package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;

import java.util.List;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.google.inject.Inject;


public class MyBATISTipoItemDAO implements TipoItemDAO {
    @Inject
    private TipoItemMapper tipoItemMapper;

    @Override
    public List<TipoItem> loadItems() throws PersistenceException {
        try{
            return tipoItemMapper.getTiposItems();
        } catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los tipo items",e);
        }

    }

    @Override
    public TipoItem load(int id) throws PersistenceException {
        try {
            return tipoItemMapper.getTipoItem(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item por el id"+id,e);
        }
    }

    @Override
    public void save(TipoItem tipoItem) throws PersistenceException {
        try {
            tipoItemMapper.addTipoItem(tipoItem);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al agregar el nuevo tipo item",e);
        }
    }

}

