package com.ws1001.services;

import com.ws1001.models.TakenKey;
import com.ws1001.repositories.TakenKeyRepository;
import com.ws1001.services.exceptions.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ramic on 22.05.2017..
 */
@Service
public class TakenKeyService extends BaseService<TakenKey, TakenKeyRepository> {

    public TakenKey save(TakenKey model) throws ServiceException {
        try {
            return super.save(model);
        } catch (ServiceException e) {
            throw new ServiceException("Bad takenKey request");
        }
    }

    public List<TakenKey> takenKeys() throws ServiceException {
        return repository.getAllTaken();
    }

}
