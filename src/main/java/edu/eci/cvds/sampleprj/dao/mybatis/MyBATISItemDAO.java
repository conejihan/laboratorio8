package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import java.sql.SQLException;
import java.util.List;

public class MyBATISItemDAO implements ItemDAO {

    @Inject
    private ItemMapper itemMapper;

    @Override
    public void save(Item it) throws PersistenceException {
        try {
            itemMapper.insertarItem(it);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el item " + it.toString(), e);
        }

    }

    @Override
    public Item load(int id) throws PersistenceException {
        try {
            return itemMapper.consultarItem(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            System.out.println("Entre al error");
            throw new PersistenceException("Error al consultar el item " + id, e);
        }

    }

    @Override
    public List<Item> loadItems() throws PersistenceException {
        try {
            return itemMapper.consultarItems();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar todos los items de la tabla", e);
        }
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException {
        try {
            itemMapper.actualizarTarifaItem(id, tarifa);
        } catch (Exception e) {
            throw new PersistenceException("Error al actualizar la tarifa con id: " + id+ " a la tarifa: "+ tarifa, e);
        }

    }

    @Override
    public List<Item> consultarItemsDisponibles() throws PersistenceException {
        try {
            return itemMapper.consultarItemsDisponibles();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar todos los items disponibless", e);
        }
    }

}