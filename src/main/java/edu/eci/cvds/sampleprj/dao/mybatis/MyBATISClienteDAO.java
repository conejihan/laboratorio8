package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBATISClienteDAO implements ClienteDAO {

    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public Cliente load(long id) throws PersistenceException {
        try {
            return clienteMapper.consultarCliente(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el Clinete con el id: " + id, e);
        }
    }

    @Override
    public void saveItemRentadoCliente(long idcli, int idit, Date fechainicio, Date fechafin) throws PersistenceException {
        try {
            clienteMapper.agregarItemRentadoACliente(idcli, idit, fechainicio, fechafin);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al añadir el nuevo cliente", e);
        }

    }

    @Override
    public List<Cliente> loadClientes() throws PersistenceException {
        try {
            return clienteMapper.consultarClientes();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar todos los clientes", e);
        }
    }

    @Override
    public List<ItemRentado> loadItemsCliente(long idCliente) throws PersistenceException {
        try {
            return clienteMapper.consultarItems(idCliente);
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar los items de los clientes", e);
        }
    }

    @Override
    public void addCliente(Cliente c) throws PersistenceException {
        try {
            clienteMapper.registrarCliente(c);
        } catch (Exception e) {
            throw new PersistenceException(e.toString(), e);
        }

    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws PersistenceException {
        try {
            clienteMapper.vetarCliente(docu, estado);
        } catch (Exception e) {
            throw new PersistenceException("Error al vetar al cliente con documento "+docu, e);
        }

    }
}

