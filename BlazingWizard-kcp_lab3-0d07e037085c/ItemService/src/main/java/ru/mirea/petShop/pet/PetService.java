
package ru.mirea.petShop.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.mirea.petShop.Pet;

import java.util.List;

@Service
public class PetService {

    PetDAO petDAO;

    @Autowired
    public PetService(PetDAO petDataAcsesObject) {
        this.petDAO = petDataAcsesObject;
    }

    @Nullable
    public List<Pet> findAll() {
        return petDAO.findAll();
    }

    @Nullable
    public List<Pet> findPetByID(int id) {
        return petDAO.findPetByID(id);
    }

}



