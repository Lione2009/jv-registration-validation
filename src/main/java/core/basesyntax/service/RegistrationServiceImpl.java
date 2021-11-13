package core.basesyntax.service;

import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private StorageDaoImpl dao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        checkUserDataForTrueValues(user);
        if (user.getAge() < 18) {
            throw new RuntimeException("User is not adult!");
        }
        if (dao.get(user.getLogin()) != null) {
            throw new RuntimeException("Login is exist!");
        }
        return dao.add(user);
    }

    private void checkUserDataForTrueValues (User user) {
        if (user == null) {
            throw new RuntimeException("Incorrect entry about user");
        }
        if (user.getLogin() == null || user.getPassword() == null) {
            throw new RuntimeException("User's login or password is absent");
        }
        if ( user.getPassword().length() < 6) {
            throw new RuntimeException("User's password is very short");
        }
        Integer age = user.getAge();
        if (age == null) {
            throw new RuntimeException("User's age is empty");
        }
        if (age < 0 || age > 130) {
            throw new RuntimeException("Incorrect user's age. Age =" + user.getAge());
        }
    }
}
