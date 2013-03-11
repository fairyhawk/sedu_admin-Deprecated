package com.shangde.edu.sys.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;

public class DoMainAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(DoMainAction.class);
	// url地址
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// folder地址
	private String folderUrl;

	public String getFolderUrl() {
		return folderUrl;
	}

	public void setFolderUrl(String folderUrl) {
		this.folderUrl = folderUrl;
	}

	/**
	 * 转到复制文件 时间：2012-01-30 10:35 wd
	 */
	public String toCopyFile() {
		return "copyfile";
	}

	/**
	 * 替换文件 时间：2012-01-30 09:55 wd
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("finally")
	public String CopyFile() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {

			if (url != null && !url.equals("")) {
				String mytitle = ServletActionContext.getServletContext()
						.getRealPath("/"+url);
				File titleFile = new File(mytitle);
				if (titleFile.exists()) {
					String title = url;
					// System.out.println(title);
					title = title.replace("\\", "\\\\");
					// 模板路径
					String filePath = ServletActionContext.getServletContext()
							.getRealPath(
									"//back//jsp//sys//doMain//Template.jsp");
					String templateContent = "";
					FileInputStream fileinputstream = new FileInputStream(
							filePath);// 读取模板文件
					int lenght = fileinputstream.available();
					byte bytes[] = new byte[lenght];
					fileinputstream.read(bytes);
					fileinputstream.close();
					templateContent = new String(bytes, "utf-8");
					// System.out.print(templateContent);
					templateContent = templateContent.replaceAll("###title###",
							title);
					// System.out.print(templateContent);
					folderUrl = "//rewrite//" + folderUrl;
					String fileame = folderUrl.substring(
							folderUrl.lastIndexOf("/") + 1, folderUrl.length())
							+ ".jsp";
					fileame = ServletActionContext.getServletContext()
							.getRealPath(folderUrl + "//" + fileame);// 生成的html文件保存路径。
					File destFile = new File(fileame);
					if (!destFile.exists()) {
						destFile.getParentFile().mkdirs();
					}
					FileOutputStream fileoutputstream = new FileOutputStream(
							fileame);// 建立文件输出流
					// System.out.print("文件输出路径:");
					// System.out.print(fileame);
					byte tag_bytes[] = templateContent.getBytes();
					fileoutputstream.write(tag_bytes);
					fileoutputstream.close();
				}else{
					out.print("error01");
				}
			}
		} catch (FileNotFoundException e) {
			out.print("error01");
			logger.error("未发现文件" + e.toString());
		} catch (IOException e) {
			out.print("error02");
			logger.info("文件流错误" + e.toString());
		} finally {
			out.flush();
			out.close();
			return "copyfile";
		}
	}
}
