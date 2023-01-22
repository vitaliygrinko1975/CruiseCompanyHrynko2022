package ua.nure.hrynko.services;
import ua.nure.hrynko.dao.MySqlUserDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.User;
import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

    public  class SignUpValidator {
        private static final List<String> errors = new ArrayList<>();
        private static final MySqlUserDAO usersDAO = new MySqlUserDAO();

        private Map<String, String> phrases;
        /* метод запускает проеверку ополученных з Http request данных, и возвращает лист з ошибками если они есть */
        public List<String> registerValidate(HttpServletRequest req) throws DBException {
            phrases = (Map<String, String>) req.getAttribute("phrases");
            errors.clear();
            errors.add(validateName(req.getParameter("name")));
            errors.add(validateSurname(req.getParameter("surname")));
            errors.add(validateLogin(req.getParameter("login")));
            errors.add(validatePassword(req.getParameter("password"), req.getParameter("password")));
            errors.removeIf(Objects::isNull);
            return errors;
        }


    public boolean checkForUniquenessEmail(List<User> userList, String email) {
        return userList.stream().anyMatch(number -> number.getEmail().equalsIgnoreCase(email));
    }

    public boolean checkForUniquenessLogin(List<User> userList, String login) {
        return userList.stream().anyMatch(number -> number.getLogin().equalsIgnoreCase(login));
    }
        /* проверка имени */
        private String validateName(String name) {
            return name != null && name.length() >= 2 && name.length() < 32 ? null : phrases.get("langNameIsWrong");
        }

        /* перевірка фамилии */
        private String validateSurname(String surname) {
            return surname != null && surname.length() >= 2 && surname.length() < 32 ? null : phrases.get("langSurnameIsWrong");
        }

        /* проверка логинау */
        private String validateLogin(String login) throws DBException {
            if (usersDAO.findUserByLogin(login) != null) return phrases.get("langTheLoginIsAlreadyInUse");
            return login != null && login.length() >= 2 && login.length() < 32 ? null : phrases.get("langLoginIsWrong");
        }

        /* проверка паролей*/
        private String validatePassword(String password, String rePassword) {
            if (!password.equals(rePassword)) return phrases.get("langPasswordsAreNotTheSame");
            return password.length() >= 4 && password.length() < 32 ? null : phrases.get("langWrongPassword");
        }

    }

