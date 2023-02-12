package ua.nure.hrynko.command;

import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Main interface for the Command pattern implementation.
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 8879403039606311780L;


    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, ServletException,
            AppException, SQLException, ParseException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}