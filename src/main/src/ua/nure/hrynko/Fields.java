package ua.nure.hrynko;

/**
 * Holder for fields names of DB tables and beans.
 */
public final class Fields {

    // entities
    public static final String ENTITY_ID = "id";
    //users
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE = "phone";
    public static final String USER_ROLES_ID = "roles_id";
    public static final String ACCOUNTS_ID = "accounts_id";
    //roles
    public static final String ROLE_NAME = "name";
    //ships
    public static final String SHIPS_NAME = "name";
    public static final String SHIPS_DESCRIPTION = "description";

    //cruises
    public static final String CRUISE_NAME = "name";
    public static final String CRUISE_DESCRIPTION = "description";
    public static final String CRUISE_PRICE = "price";
    public static final String CRUISE_SHIPS_ID = "ships_id";
    public static final String CRUISE_CAPACITY = "capacity";
    public static final String CRUISE_START_OF_CRUISE = "start_of_cruise";
    public static final String CRUISE_DURATION = "duration";

    //accounts
    public static final String ACCOUNTS_BALANCE = "balance";

    // orders
    public static final String USERS_ID = "users_id";
    public static final String CRUISES_ID = "cruises_id";
    public static final String STATUS = "status";

}