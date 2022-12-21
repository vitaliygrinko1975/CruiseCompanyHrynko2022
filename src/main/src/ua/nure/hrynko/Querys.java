package ua.nure.hrynko;



public class Querys {

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////


    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

    public static final String SQL_FIND_CRUISE_BY_ID = "SELECT * FROM cruises WHERE id=?";

    public static final String SQL_FIND_ALL_TARIFF_BY_SERVICES_ID = "SELECT * FROM tariffs WHERE services_id=?";

    public static final String SQL_FIND_ITEM_ON_USER_HAS_TARIFFS_BY_USER_ID = "SELECT * FROM users_has_tariffs " +
            "WHERE users_id=?";

    public static final String SQL_UPDATE_USERS_BY_ID = "UPDATE users SET login=?, password=?, first_name=?, last_name=?," +
            "email=?,phone=?,roles_id=?, accounts_id=? WHERE id=?";

    public static final String SQL_UPDATE_CRUISE_BY_ID = "UPDATE cruises SET name=?, description=?, price=?, ships_id=?," +
            "capacity=?,start_of_cruise=?,end_of_cruise=? WHERE id=?";

    public static final String SQL_UPDATE_USERS_HAS_TARIFFS_BY_USERS_ID_AND_TARIFFS_ID = "UPDATE users_has_tariffs" +
            " SET users_id=?, tariffs_id=?, end_of_contract=? WHERE users_id=? AND tariffs_id=?";

    public static final String SQL_UPDATE_TARIFF_BY_ID = "UPDATE tariffs SET name=?, description=?, price=?," +
            " services_id=? WHERE id=?";

    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

    public static final String SQL_DELETE_TARIFF_BY_ID = "DELETE FROM tariffs WHERE id = ?";

    public static final String SQL_DELETE_ACCOUNT_BY_ID = "DELETE FROM accounts WHERE id = ?";

    public static final String SQL_DELETE_USER_BY_USER_ID_AND_TARIFF_ID = "DELETE FROM users_has_tariffs" +
            " WHERE users_id=? AND tariffs_id=?";

    public static final String SQL_INSERT_USER = "INSERT INTO users (login, password,first_name,last_name,email,phone,blocked," +
            "roles_id, accounts_id) VALUE (?,?,?,?,?,?,?,?,?)";

    public static final String SQL_INSERT_CRUISE = "INSERT INTO cruises (name,description, price,ships_id,capacity," +
            "start_of_cruise,end_of_cruise) VALUE (?,?,?,?,?,?,?)";


    public static final String SQL_FIND_ALL_USER = "SELECT * FROM users ";

    public static final String SQL_FIND_ALL_ITEM_ON_USERS_HAS_TARIFFS = "SELECT * FROM users_has_tariffs";

    public static final String SQL_FIND_ALL_ROLE = "SELECT * FROM roles";

    public static final String SQL_FIND_ALL_ACCOUNT = "SELECT * FROM accounts";

    public static final String SQL_FIND_ALL_CRUISES = "SELECT * FROM cruises";

    public static final String SQL_FIND_ALL_TARIFFS_BY_SERVICE_ID = "SELECT * FROM tariffs WHERE services_id=?";

    public static final String SQL_FIND_ALL_SERVICES = "SELECT * FROM  services";

    public static final String SQL_FIND_ALL_TARIFFS_BY_SERVICE_ID_ORDER_BY_NAME_DESC = "SELECT * FROM tariffs " +
            " WHERE services_id=? ORDER BY name DESC";

    public static final String SQL_FIND_ALL_TARIFFS_BY_SERVICE_ID_ORDER_BY_NAME_ASC = "SELECT * FROM tariffs " +
            "WHERE services_id=? ORDER BY name ASC";

    public static final String SQL_FIND_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE id=?";

    public static final String SQL_UPDATE_ACCOUNT_BY_NUMBER_AND_BALANCE = "UPDATE accounts SET number=?, balance=? WHERE id=? ";

    public static final String SQL_UPDATE_USERS_HAS_TARIFFS_BY_USER_ID_AND_TARIFF_ID = "UPDATE accounts SET number=?, balance=? WHERE id=? ";

    public static final String SQL_INSERT_USERS_HAS_CRUISES = "INSERT INTO users_has_cruises (users_id, cruises_id,status)  VALUE (?,?,?)";

    public static final String SQL_INSERT_ACCOUNT = "INSERT INTO accounts (number, balance) VALUE (?,?)";

    public static final String SQL_SELECT_LAST_INSERT_ID = "SELECT max(id) FROM accounts";
}