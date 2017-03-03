import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Compile
 */

@WebServlet("/Compile")
public class Compile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletContext().getRealPath("/");
		PrintWriter out = response.getWriter();
		String filename = request.getParameter("className")+".java";
		File fn = new File(path+"//Files//"+filename);
		FileOutputStream fos = new FileOutputStream(fn);
		System.out.println(request.getParameter("code"));
		byte[] sourcecode = request.getParameter("code").getBytes();
		fos.write(sourcecode);
		String compilecmd ="javac -d " + path + "\\Files\\Classes\\ " + path + "\\Files\\" + filename;
		Process error = Runtime.getRuntime().exec(compilecmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(error.getErrorStream()));
		String res="";
		while(true){
			String str = br.readLine();
			if(str!=null){
				res+=str;
				res+="\n";
			}
			else{
				break;
			}
		}
		if(res.equals("")){
			res="Compiled Successfully";
		}
		out.println(res);
		br.close();
		fos.close();
		/*
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		pw.println("<h1>hello world !! </h1>");
		pw.println("</body></html>");
		*/
	}

}
