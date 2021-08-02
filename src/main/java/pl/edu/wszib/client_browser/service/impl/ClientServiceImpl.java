package pl.edu.wszib.client_browser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.client_browser.dao.IClientDAO;
import pl.edu.wszib.client_browser.model.Client;
import pl.edu.wszib.client_browser.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {
    private final IClientDAO clientDAO;

    @Autowired
    public ClientServiceImpl(final IClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client getClientById(final Long id) {
        return this.clientDAO.getClientById(id);
    }

    @Override
    public void deleteClient(final Client client) {
        this.clientDAO.deleteClient(client);
    }

    //TODO add client validator
    @Override
    public boolean addNewClient(final Client client) {

        if (client.equals(this.clientDAO.getClientById(client.getId()))) {
            return false;
        }
        addNewClient(client);
        return true;

    }
}
