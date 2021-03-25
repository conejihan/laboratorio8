package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {

    @Inject
    private ItemRentadoMapper itemRentadoMapper;

    @Override
    public List<ItemRentado> loadItems() throws PersistenceException {
        try{
            return itemRentadoMapper.consultarItemsRentados();
        } catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los itemsRentados",e);
        }
    }

    @Override
    public ItemRentado load(int id) throws PersistenceException {
        try{
            return itemRentadoMapper.consultarItemRentado(id);
        } catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el itemRentado "+id,e);
        }
    }
}