package vdll.net.android;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * 带着 session
 */
public class HttpClientUtil
{
	//public static final String urlString = "http://....?passport=...&password=...";  //先登录保存cookie
	//public static final String urlString2 = "http://......";
	public String sessionId = "";

	public void doGet(String urlStr) throws IOException
	{
		String key = "";
		String cookieVal = "";
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		/** 
		 * 设置cookie
		 */
		if (!sessionId.equals(""))
		{
			connection.setRequestProperty("Cookie", sessionId);
		}
		connection.connect(); //到此步只是建立与服务器的tcp连接，并未发送http请求

		//直到getInputStream()方法调用请求才真正发送出去
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null)
		{
			sb.append(line);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();

		for (int i=1;(key = connection.getHeaderField(i)) != null;i++)
		{
			cookieVal = connection.getHeaderField(i);
			cookieVal = cookieVal.substring(0, cookieVal.indexOf(";") > -1 ?cookieVal.indexOf(";"): cookieVal.length() - 1);
			sessionId = sessionId + cookieVal + ";";
		}

		connection.disconnect();
	}

	public void doPost(String urlStr, String parm) throws IOException
	{
		String key = "";
		String cookieVal = "";
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true); //设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
		connection.setDoInput(true); // 设置连接输入流为true
		connection.setRequestMethod("POST"); // 设置请求方式为post
		connection.setUseCaches(false); // post请求缓存设为false
		connection.setInstanceFollowRedirects(true); //// 设置该HttpURLConnection实例是否自动执行重定向
		// 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
        // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		/** 
		 * 设置cookie
		 */
		if (!sessionId.equals(""))
		{
			//System.out.println("--------"+sessionId);
			connection.setRequestProperty("Cookie", sessionId);
		}

		connection.connect();

		// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
		DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
        //String parm = "storeId=" + URLEncoder.encode("32", "utf-8"); //URLEncoder.encode()方法  为字符串进行编码           
        dataout.writeBytes(parm); 
        dataout.flush();
        dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)           
        // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder(); // 用来存储响应数据           
        // 循环读取流,若不到结尾处
        while ((line = bf.readLine()) != null)
		{
            sb.append(bf.readLine());
        }
        bf.close();    // 重要且易忽略步骤 (关闭流,切记!)

		for (int i=1;(key = connection.getHeaderField(i)) != null;i++)
		{
			cookieVal = connection.getHeaderField(i);
			cookieVal = cookieVal.substring(0, cookieVal.indexOf(";") > -1 ?cookieVal.indexOf(";"): cookieVal.length() - 1);
			sessionId = sessionId + cookieVal + ";";
		}

        connection.disconnect(); // 销毁连接
        System.out.println(sb.toString()); 
	}

	public static void main(String[] args) throws IOException
	{
		HttpClientUtil hcu = new HttpClientUtil();
		//hcu.doGet(urlString);
		//hcu.doGet(urlString2);

		String url = "http://www.btravelplus.com/tbi-erp/";   
		//访问主页
		hcu.doGet(url);
		//登陆账号
		hcu.doPost(url + "login", "username=huhaiyang&password=1234");
		//获取内容
		hcu.doGet("http://www.btravelplus.com/tbi-erp/tbi/jobLog/selectJobLog?pagenum=0");
		hcu.doGet("http://www.btravelplus.com/tbi-erp/tbi/jobLog/selectJobLog?pagenum=1");

		//hcu.doGet("http://www.btravelplus.com/tbi-erp/tbi/jobLog/addJobLog");

		//添加日志
		hcu.doPost("http://www.btravelplus.com/tbi-erp/tbi/jobLog/addJobLog"
				   , "confirmPerson=" + URLEncoder.encode("鲍学慧", "utf-8")
				   + "&department=" + URLEncoder.encode("研发部", "utf-8")
				   + "&endTime=" + URLEncoder.encode("12:00", "utf-8")
		 		   + "&job=" + URLEncoder.encode("整理安卓框架", "utf-8") //工作内容
				   + "&jobDate=" + URLEncoder.encode("2016-09-09", "utf-8")
				   + "&jobNo=" + URLEncoder.encode("123", "utf-8")
				   + "&jobResult=" + URLEncoder.encode("进行中", "utf-8")
				   + "&jobStyle=" + URLEncoder.encode("写资料", "utf-8")
				   + "&jobType=" + URLEncoder.encode("研发", "utf-8")
				   + "&oneTwo=" + URLEncoder.encode("独立完成", "utf-8")
				   + "&remark=" + URLEncoder.encode("", "utf-8") //备注
				   + "&requiredTime=" + URLEncoder.encode("3.5", "utf-8")
				   + "&startTime=" + URLEncoder.encode("08:30", "utf-8")
				   + "&userName=" + URLEncoder.encode("胡海洋", "utf-8")
				   + "&logStatus=" + URLEncoder.encode("1", "utf-8")
				   );

		hcu.doPost("http://www.btravelplus.com/tbi-erp/tbi/jobLog/addJobLog"
				   , "confirmPerson=" + URLEncoder.encode("鲍学慧", "utf-8")
				   + "&department=" + URLEncoder.encode("研发部", "utf-8")
				   + "&endTime=" + URLEncoder.encode("17:30", "utf-8")
		 		   + "&job=" + URLEncoder.encode("整理安卓框架", "utf-8") //工作内容
				   + "&jobDate=" + URLEncoder.encode("2016-09-09", "utf-8")
				   + "&jobNo=" + URLEncoder.encode("123", "utf-8")
				   + "&jobResult=" + URLEncoder.encode("进行中", "utf-8")
				   + "&jobStyle=" + URLEncoder.encode("写资料", "utf-8")
				   + "&jobType=" + URLEncoder.encode("研发", "utf-8")
				   + "&oneTwo=" + URLEncoder.encode("独立完成", "utf-8")
				   + "&remark=" + URLEncoder.encode("", "utf-8") //备注
				   + "&requiredTime=" + URLEncoder.encode("4.5", "utf-8")
				   + "&startTime=" + URLEncoder.encode("13:00", "utf-8")
				   + "&userName=" + URLEncoder.encode("胡海洋", "utf-8")
				   + "&logStatus=" + URLEncoder.encode("1", "utf-8")
				   );

				   
				   

		//修改日志
		/*hcu.doPost(
			"http://www.btravelplus.com/tbi-erp/tbi/jobLog/editJobLog"
			,  "id=" + URLEncoder.encode("22761", "utf-8")
			+ "&confirmPerson=" + URLEncoder.encode("鲍学慧", "utf-8")
			+ "&department=" + URLEncoder.encode("研发部", "utf-8")
			+ "&endTime=" + URLEncoder.encode("12:00", "utf-8")
			+ "&job=" + URLEncoder.encode("整理安卓框架", "utf-8") //工作内容
			+ "&jobDate=" + URLEncoder.encode("2016-09-08", "utf-8")
			+ "&jobNo=" + URLEncoder.encode("123", "utf-8") //类型
			+ "&jobResult=" + URLEncoder.encode("进行中", "utf-8")
			+ "&jobStyle=" + URLEncoder.encode("写资料", "utf-8")
			+ "&jobType=" + URLEncoder.encode("研发", "utf-8")
			+ "&oneTwo=" + URLEncoder.encode("独立完成", "utf-8")
			+ "&remark=" + URLEncoder.encode("", "utf-8") //备注
			+ "&requiredTime=" + URLEncoder.encode("3.5", "utf-8") //时间差
			+ "&startTime=" + URLEncoder.encode("08:30", "utf-8")
			+ "&userName=" + URLEncoder.encode("胡海洋", "utf-8")
		
			
		
		);*/

		//修改日志状态
		/*
		hcu.doPost(
			"http://www.btravelplus.com/tbi-erp/tbi/jobLog/editJobLog"
			,  "id=" + URLEncoder.encode("22761", "utf-8")
			+ "&logStatus=" + URLEncoder.encode("1", "utf-8")
		);*/
		
	}


	//根绝uri和cookie 下载整个页面
	public static void getPage(String uri, String cookie)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		try
		{
			URL url = new URL(uri);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("Post"); // 以Post方式提交表单，默认get方式
			con.setRequestProperty("Cookie", cookie);
			String result="";
	        StringBuffer buffer = new StringBuffer();
			BufferedReader reader = null;
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
											con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				buffer.append(line);
			}
			result = buffer.toString();
			System.out.println(result);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}




}

