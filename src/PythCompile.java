

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
 * Servlet implementation class PythCompile
 */
@WebServlet("/PythCompile")
public class PythCompile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletContext().getRealPath("/");
		PrintWriter out = response.getWriter();
		String filename = request.getParameter("className")+".py";
		File fn = new File(path+"//Files//"+filename);
		FileOutputStream fos = new FileOutputStream(fn);
		System.out.println(request.getParameter("code"));
		byte[] sourcecode = request.getParameter("code").getBytes();
		fos.write(sourcecode);
		/*Process setpath = Runtime.getRuntime().exec(pathset);
		try{
			setpath.waitFor();
		}
		catch(Exception e){
			System.out.println(e);
		}*/
		/*try{
			Thread.sleep(3000);
		}
		catch(Exception e){
			System.out.println(e);
		}*/
		String gotopath = "cd " + path + "\\Files";
		System.out.println(gotopath);
		Process pathset = Runtime.getRuntime().exec(gotopath);
		try{
			pathset.waitFor();
		}
		catch(Exception e){
			System.out.println(e);
		}
		String compilecmd = "python " + filename;
		System.out.println(compilecmd);
		Process exe = Runtime.getRuntime().exec(compilecmd);
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
			out.println(request.getParameter("code"));
			out.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		fos.close();
		
	}

}
