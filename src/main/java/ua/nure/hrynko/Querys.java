package ua.nure.hrynko;



public class Querys {

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////

    //find
    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

    public static final String SQL_FIND_CRUISE_BY_ID = "SELECT * FROM cruises WHERE id=?";

    public static final String SQL_FIND_SHIP_BY_ID = "SELECT * FROM ships WHERE id=?";



    public static final String SQL_FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE id=?";

    public static final String SQL_FIND_CRUISE_BY_START_OF_CRUISE = "SELECT * FROM cruises WHERE DATE (start_of_cruise)=?";

    public static final String SQL_FIND_CRUISE_BY_DURATION = "SELECT * FROM cruises WHERE duration=?";

    public static final String SQL_FIND_ALL_USER = "SELECT * FROM users";

    public static final String SQL_FIND_ALL_FREE_SHIP_FROM_SHIPS_VIEW =
            "SELECT * FROM ships_view WHERE DATE(start_of_contract) NOT BETWEEN ? AND ?" +
                    " AND DATE(end_of_contract) NOT BETWEEN ? AND ?";


    public static final String SQL_FIND_ALL_ITEM_ON_USER_WITH_LIMIT = "SELECT * FROM users LIMIT ?,?";

    public static final String SQL_FIND_ALL_ITEM_ON_ORDERS = "SELECT * FROM orders";

    public static final String SQL_FIND_ALL_ITEM_OF_CRUISE_HAS_SHIPS = "SELECT * FROM cruises_has_ships";

    public static final String SQL_FIND_ALL_ITEM_ON_ORDERS_VIEW = "SELECT * FROM orders_view";

    public static final String SQL_FIND_ALL_ITEM_ON_ORDERS_WITH_LIMIT = "SELECT * FROM orders LIMIT ?,?";

    public static final String SQL_FIND_ALL_ITEM_ON_ORDERS_VIEW_WITH_LIMIT = "SELECT * FROM orders_view LIMIT ?,?";

    public static final String SQL_FIND_ALL_ITEM_ON_ORDERS_VIEW_BY_USER_ID_WITH_LIMIT =
            "SELECT * FROM orders_view WHERE users_id=? LIMIT ?,?";

    public static final String SQL_FIND_ALL_ITEM_ON_CRUISES_WITH_LIMIT = "SELECT * FROM cruises LIMIT ?,?";

    public static final String SQL_FIND_ALL_ITEM_ON_ORDERS_VIEW_BY_USER_ID =
            "SELECT * FROM orders_view WHERE users_id=?";

    public static final String SQL_COUNT_ITEMS_IN_ORDERS_VIEW = "SELECT COUNT(*) FROM orders_view";

    public static final String SQL_COUNT_ITEMS_IN_USER = "SELECT COUNT(*) FROM users";

    public static final String SQL_COUNT_ITEMS_IN_CRUISES = "SELECT COUNT(*) FROM cruises";

    public static final String SQL_COUNT_ITEMS_IN_CRUISE_HAS_SHIPS_BY_START_OF_CONTRACT = "SELECT COUNT(*) FROM cruises_has_ships" +
            " WHERE start_of_contract=?";

    public static final String SQL_FIND_ALL_ROLE = "SELECT * FROM roles";

    public static final String SQL_FIND_ALL_ACCOUNT = "SELECT * FROM accounts";

    public static final String SQL_FIND_ALL_CRUISES = "SELECT * FROM cruises";

    public static final String SQL_FIND_ALL_SHIPS = "SELECT * FROM  ships";

    public static final String SQL_FIND_ALL_SHIPS_FROM_SHIPS_VIEW = "SELECT * FROM ships_view";

    public static final String SQL_FIND_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE id=?";

    public static final String SQL_FIND_LAST_INSERT_ID = "SELECT max(id) FROM accounts";

     // update
    public static final String SQL_UPDATE_USERS_BY_ID = "UPDATE users SET login=?, password=?, first_name=?, last_name=?," +
            "email=?,phone=?,roles_id=?, accounts_id=? WHERE id=?";
    public static final String SQL_UPDATE_FOUR_PARAMETERS_OF_USERS_BY_ID = "UPDATE users SET first_name=?, last_name=?," +
            "email=?,phone=? WHERE id=?";
    public static final String SQL_UPDATE_CRUISE_BY_ID = "UPDATE cruises SET name=?, description=?, price=?," +
            "capacity=?,start_of_cruise=?,duration=?,status=? WHERE id=?";

    public static final String SQL_UPDATE_CRUISE_HAS_SHIP_BY_ID = "UPDATE cruises_has_ships SET cruises_id=?, ships_id=?," +
            " start_of_contract=?,end_of_contract=?,status=? WHERE id=?";

    public static final String SQL_UPDATE_SHIP_BY_ID = "UPDATE ships SET name=?, description=?,capacity=? WHERE id=?";
    public static final String SQL_UPDATE_ORDERS_BY_OBJECT_OF_ORDERS = "UPDATE orders" +
            " SET users_id=?,cruises_id=?,status=? WHERE id=?";
    public static final String SQL_UPDATE_ORDERS_BY_ID = "UPDATE orders" +
            " SET status=? WHERE id=?";
    public static final String SQL_UPDATE_ACCOUNT_BY_ID = "UPDATE accounts SET balance=?,file_name=? WHERE id=?";
    public static final String SQL_UPDATE_ACCOUNT_TWO_BY_ID = "UPDATE accounts SET balance=? WHERE id=?";


    // delete
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

    public static final String SQL_DELETE_ACCOUNT_BY_ID = "DELETE FROM accounts WHERE id = ?";

    public static final String SQL_DELETE_CRUISE_BY_ID = "DELETE FROM cruises WHERE id = ?";

    public static final String SQL_DELETE_CRUISES_HAS_SHIPS_BY_ID = "DELETE FROM cruises_has_ships WHERE id = ?";

    public static final String SQL_DELETE_SHIP_BY_ID = "DELETE FROM ships WHERE id = ?";

    //insert
    public static final String SQL_INSERT_USER = "INSERT INTO users (login, password, first_name, last_name, email,phone," +
            "roles_id, accounts_id) VALUE (?,?,?,?,?,?,?,?)";
    public static final String SQL_INSERT_CRUISE = "INSERT INTO cruises (name,description, price,capacity," +
            "start_of_cruise,duration,status) VALUE (?,?,?,?,?,?,?)";

    public static final String SQL_INSERT_SHIP = "INSERT INTO ships (name,description,capacity) VALUE (?,?,?)";

       public static final String SQL_INSERT_TO_ORDERS = "INSERT INTO orders (users_id, cruises_id,status)  VALUE (?,?,?)";
    public static final String SQL_INSERT_TO_CRUISES_HAS_SHIPS =
            "INSERT INTO cruises_has_ships (cruises_id,ships_id,start_of_contract,end_of_contract,status)  VALUE (?,?,?,?,?)";
    public static final String SQL_INSERT_ACCOUNT = "INSERT INTO accounts (balance,file_name) VALUE (?,?)";
}

//SELECT LAST_INSERT_ID();