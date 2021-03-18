package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemDAO {
    
    public List<TipoItem> loadItems() throws PersistenceException;

    public TipoItem load(int id) throws PersistenceException;

    public void save(TipoItem tipoItem) throws PersistenceException;
}
