package ajax;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/jquery/uploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	System.out.println("init call()");
    }
    
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) 
//    		throws ServletException, IOException {
//    	System.out.println("service call()");
//    	Enumeration<String> enumer = req.getHeaderNames();
//    	while(enumer.hasMoreElements()) {
//    		String key = enumer.nextElement();
//    		String val =req.getHeader(key);
//    		System.out.println(key + " : " + val);
//    	}
//    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doGet call()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doPost call()");
		String path = "c:tmp"; //지정 위치 path로 변경 
		
		ServletContext sc = this.getServletContext(); 
		path = sc.getRealPath("upload");//위치 지정 
		
		MultipartRequest multi = //
		new MultipartRequest(request, path, 5 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
		Enumeration en = multi.getFileNames();
		while(en.hasMoreElements()) {
		String name = (String) en.nextElement();
		String fileName = multi.getFilesystemName(name);
		System.out.println("name: " + name + ", fileName: " + fileName);
		}
	}

}
