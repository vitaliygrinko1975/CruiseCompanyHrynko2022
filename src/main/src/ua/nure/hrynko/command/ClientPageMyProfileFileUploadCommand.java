package ua.nure.hrynko.command;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

public class ClientPageMyProfileFileUploadCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ClientPageMyProfileFileUploadCommand.class);
	private final String UPLOAD_DIRECTORY = "D:/РАБОТА/EPAM 2.0/FinalModule/CruiseCompanyHrynko2022/src/main/webapp/img";

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("ClientPageMyProfileFileUploadCommand starts");
		//process only if its multipart content
		if(ServletFileUpload.isMultipartContent(request)){
			try {
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);
				for(FileItem item : multiparts){
					if(!item.isFormField()){
						String name = new File(item.getName()).getName();
						item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
					}
				}
				//File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}
		}else{
			request.setAttribute("message",
					"Sorry this Servlet only handles file upload request");
		}
		LOG.debug("ClientPageMyProfileFileUploadCommand finished");
		return Path.PAGE_ERROR_PAGE;
	}

}