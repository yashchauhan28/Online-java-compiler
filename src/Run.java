import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Run")
public class Run extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String filename = request.getParameter("classname").trim();
		String path = request.getServletContext().getRealPath("/");
		
		String runcmd = "java -cp " + path + "\\Files\\Classes\\ " + filename;
		Process exe = Runtime.getRuntime().exec(runcmd);
		try{
			exe.waitFor();
			BufferedReader bin = new BufferedReader(new InputStreamReader(exe.getInputStream()));
			BufferedReader berr = new BufferedReader(new InputStreamReader(exe.getErrorStream()));
			String res="";
			while(true){
				String temp=bin.readLine();
				if(temp==null){
					break;
				}
				else{
					res+=temp;
				}
			}
			if(res.equals("")){
				while(true){
					String temp = berr.readLine();
					if(temp==null){
						break;
					}
					else{
						res+=temp;
					}
				}
			}
			out.println(res);
			bin.close();
			berr.close();
			out.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
