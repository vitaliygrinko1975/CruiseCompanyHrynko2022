package ua.nure.hrynko;



public class Querys {

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////

    //find
    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

    public static final String SQL_FIND_CRUISE_BY_ID = "SELECT * FROM cruises WHERE id=?";

    public static final String SQL_FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE id=?";

    public static final String SQL_FIND_CRUISE_BY_START_OF_CRUISE = "SELECT * FROM cruises WHERE DATE (start_of_cruise)=?";

    public static final String SQL_FIND_CRUISE_BY_DURATION = "SELECT * FROM cruises WHERE duration=?";

    public static final String SQL_FIND_ALL_USER = "SELECT * FROM users";

    public static final String SQL_FIND_ALL_ITEM_ON_ORDERS = "SELECT * FROM orders";

    public static final String SQL_FIND_ALL_ITEM_ON_ORDERS_VIEW = "SELECT * FROM orders_view";

    public static final String SQL_FIND_ALL_ITEM_ON_ORDERS_WITH_LIMIT = "SELECT * FROM orders LIMIT ?,?";

    public static final String SQL_FIND_ALL_ITEM_ON_ORDERS_VIEW_WITH_LIMIT = "SELECT * FROM orders_view LIMIT ?,?";

    public static final String SQL_FIND_ALL_ROLE = "SELECT * FROM roles";

    public static final String SQL_FIND_ALL_ACCOUNT = "SELECT * FROM accounts";

    public static final String SQL_FIND_ALL_CRUISES = "SELECT * FROM cruises";

    public static final String SQL_FIND_ALL_SERVICES = "SELECT * FROM  services";

    public static final String SQL_FIND_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE id=?";

    public static final String SQL_FIND_LAST_INSERT_ID = "SELECT max(id) FROM accounts";

     // update
    public static final String SQL_UPDATE_USERS_BY_ID = "UPDATE users SET login=?, password=?, first_name=?, last_name=?," +
            "email=?,phone=?,roles_id=?, accounts_id=? WHERE id=?";
    public static final String SQL_UPDATE_FOUR_PARAMETERS_OF_USERS_BY_ID = "UPDATE users SET first_name=?, last_name=?," +
            "email=?,phone=? WHERE id=?";
    public static final String SQL_UPDATE_CRUISE_BY_ID_EIGHT = "UPDATE cruises SET name=?, description=?, price=?, ships_id=?," +
            "capacity=?,start_of_cruise=?,duration=? WHERE id=?";
    public static final String SQL_UPDATE_CRUISE_BY_ID_NINE = "UPDATE cruises SET name=?, description=?, price=?, ships_id=?," +
            "capacity=?,start_of_cruise=?,duration=?,status=? WHERE id=?";
    public static final String SQL_UPDATE_ORDERS_BY_OBJECT_OF_ORDERS = "UPDATE orders" +
            " SET users_id=?,cruises_id=?,status=? WHERE id=?";
    public static final String SQL_UPDATE_ORDERS_BY_ID = "UPDATE orders" +
            " SET status=? WHERE id=?";
    public static final String SQL_UPDATE_ACCOUNT_BY_ID = "UPDATE accounts SET balance=? WHERE id=?";

    // delete
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

    public static final String SQL_DELETE_ACCOUNT_BY_ID = "DELETE FROM accounts WHERE id = ?";

    public static final String SQL_DELETE_CRUISE_BY_ID = "DELETE FROM cruises WHERE id = ?";

    //insert
    public static final String SQL_INSERT_USER = "INSERT INTO users (login, password, first_name, last_name, email,phone," +
            "roles_id, accounts_id) VALUE (?,?,?,?,?,?,?,?)";
    public static final String SQL_INSERT_CRUISE = "INSERT INTO cruises (name,description, price,ships_id,capacity," +
            "start_of_cruise,duration,status) VALUE (?,?,?,?,?,?,?,?)";
       public static final String SQL_INSERT_TO_ORDERS = "INSERT INTO orders (users_id, cruises_id,status)  VALUE (?,?,?)";

    public static final String SQL_INSERT_ACCOUNT = "INSERT INTO accounts (balance) VALUE (?)";

}