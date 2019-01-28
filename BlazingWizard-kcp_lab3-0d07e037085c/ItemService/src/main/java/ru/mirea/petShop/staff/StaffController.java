package ru.mirea.petShop.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.petShop.Staff;

import java.util.List;

@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "staff/", method = RequestMethod.GET)
    @ResponseBody
    public List<Staff> findStaffs() {
        return staffService.findALl();
    }

    @RequestMapping(value = "staff/{itemID}", method = RequestMethod.GET)
    @ResponseBody
    public List<Staff> findStaff(@PathVariable int itemID){
        return staffService.findStaffByID(itemID);
    }

}
