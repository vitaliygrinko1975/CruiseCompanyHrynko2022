package ua.nure.hrynko.services;

import ua.nure.hrynko.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class SignUpValidator {
    private static final List<String> errors = new ArrayList<>();

    /* метод запускает проверку полученных з Http request данных, и возвращает лист з ошибками если они есть */
    public List<String> registerValidate(HttpServletRequest request) {
        errors.clear();
        errors.add(validateFirstName(request.getParameter("addFirstNameUser")));
        errors.add(validateLastName(request.getParameter("addLastNameUser")));
        errors.add(validatePhone(request.getParameter("addPhoneUser")));
        errors.removeIf(Objects::isNull);
        return errors;
    }

    public boolean checkForUniquenessLogin(List<User> userList, String login) {
        return userList.stream().anyMatch(number -> number.getLogin().equalsIgnoreCase(login));
    }

    /* проверка имени */
    private String validateFirstName(String name) {
        return name != null && name.length() >= 2 && name.length() < 32 ? null : "Имя невалидно";
    }

    /* преоверка фамилии */
    private String validateLastName(String surname) {
        return surname != null && surname.length() >= 2 && surname.length() < 32 ? null : "Фамилия невалидна";
    }

    public boolean checkForUniquenessEmail(List<User> userList, String email) {
        return userList.stream().anyMatch(number -> number.getEmail().equalsIgnoreCase(email));
    }

    private String validatePhone(String number) {
        return number != null && number.length() > 0
                && Pattern.matches("^[+3]\\d{12,12}$", number) ? null : "Номер невалидный";
    }
}

