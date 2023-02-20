package ua.nure.hrynko.command.client;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.interfaces.AccountDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ClientPageMyProfileFileUploadCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;
    private final transient AccountDAO accountDAO;
    private static final Logger LOG = Logger.getLogger(ClientPageMyProfileFileUploadCommand.class);
    private final String UPLOAD_DIRECTORY = "D:/РАБОТА/EPAM 2.0/FinalModule/CruiseCompanyHrynko2022/src/main/webapp/img";

    public ClientPageMyProfileFileUploadCommand(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("ClientPageMyProfileFileUploadCommand starts");
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(request.getParameter("userId"));
        LOG.trace("Request parameter: userId--> " + userId);
        Account account = accountDAO.findAccountById(userId);

        //process only if its multipart content
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multipart = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multipart) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                        account.setFileName("./img" + File.separator + name);
                        LOG.trace("set file name: --> " + name);
                        accountDAO.updateAccountToDb(account);
                    }
                }
                //File uploaded successfully
                session.setAttribute("message", "File Uploaded Successfully");

            } catch (Exception ex) {
                session.setAttribute("message", "File Upload Failed due to " + ex);
            }
        } else {
            session.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }
        LOG.debug("ClientPageMyProfileFileUploadCommand finished");
        return Path.PAGE_ERROR_PAGE;
    }

}