package ru.mirea.petShop.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.mirea.petShop.Staff;

import java.util.List;

@Service
public class StaffService
{
    private StaffDAO staffDAO;

    @Autowired
    public StaffService(StaffDAO staffDAO){
        this.staffDAO = staffDAO;
    }

    @Nullable
    public  List<Staff> findALl() {
        return  staffDAO.findAll();
    }

    @Nullable
    public List<Staff> findStaffByID(int id) {
        return  staffDAO.findStaffByID(id);
    }
}
