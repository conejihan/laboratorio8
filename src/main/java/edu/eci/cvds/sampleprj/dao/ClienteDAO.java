package edu.eci.cvds.sampleprj.dao;

import java.util.Date;
import java.util.List;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ClienteDAO {
    
    public void saveItemRentadoCliente(long id, int idit, Date fechainicio, Date fechafin) throws PersistenceException;

    public Cliente load(long id) throws PersistenceException;

    public List<Cliente> loadClientes() throws PersistenceException;

    public List<ItemRentado> loadItemsCliente(long idCliente) throws PersistenceException;

    public void addCliente(Cliente c) throws PersistenceException;

    public void vetarCliente(long docu, boolean estado) throws PersistenceException;
}
