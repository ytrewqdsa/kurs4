package ru.mirea.petShop.IdentityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.petShop.Token;
import ru.mirea.petShop.User;
import org.apache.commons.codec.digest.DigestUtils;

@Service
public class IdentityService {

    private final String secretKey = "LOL";

    private IdentityDAO identityDAO;

    @Autowired
    public IdentityService(IdentityDAO identityDAO){
        this.identityDAO = identityDAO;
    }

    public Token getToken(User auth)
    {
        User user = identityDAO.getUserByLogin(auth.getLogin());

        if (user.getPassword().equals(auth.getPassword())){

            Token token = new Token(user.getId(), user.getRole());
            String signature = DigestUtils.sha256Hex(token.toString() + secretKey);
            token.setSignature(signature);

            return token;
        }
        else {
            return null;
        }

    }


}
