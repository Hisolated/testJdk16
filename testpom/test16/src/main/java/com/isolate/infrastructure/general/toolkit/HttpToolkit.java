package com.isolate.infrastructure.general.toolkit;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP工具箱 模拟执行get和post请求
 */
@Slf4j
public final class HttpToolkit {

	public static int TIME_OUT = 1000*6;// 6秒
	
	private static CloseableHttpClient httpclient = HttpClients.createDefault();
	
	
	public static String doGet(String url, Map<String, String> params,Charset charset) throws Exception {
		return doGet(url, params, charset,0);
	}

	  /**
     * 执行一个HTTP GET请求，返回请求响应的HTML
     * 
     * @param url
     *            请求的URL地址
     * @param params
     *            请求的查询参数,可以为null
     * @param charset
     *            字符集 Consts.UTF_8
     * @return 返回请求响应的HTML
     */

	public static String doGet(String url, Map<String, String> params,Charset charset,int timeout) throws Exception {
		CloseableHttpResponse response = null;
		try {
			timeout = timeout>0?timeout:TIME_OUT;
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
			httpclient = HttpClients.createDefault();
			List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				valuePairs.add(nameValuePair);
			}
			RequestBuilder requestBuilder = RequestBuilder.get().setConfig(requestConfig).setUri(url)
					.addParameters(valuePairs.toArray(new NameValuePair[valuePairs.size()]));
			response = httpclient.execute(requestBuilder.build());
			HttpEntity entity = response.getEntity();
			String respContent = EntityUtils.toString(entity, charset).trim();
			return respContent;

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if(response!=null){
				response.close();
			}
			if(log.isDebugEnabled()){
				log.info("地址："+url+" 参数："+params,log);
			}
		}
	}
	public static String doGet(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
		CloseableHttpResponse response = null;
		try {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT).build();
			httpclient = HttpClients.createDefault();
			List<NameValuePair> valuePairs = new ArrayList<>(params.size());
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				valuePairs.add(nameValuePair);
			}
			RequestBuilder requestBuilder = RequestBuilder.get().setConfig(requestConfig).setUri(url)
					.addParameters(valuePairs.toArray(new NameValuePair[valuePairs.size()]));
			if (headers != null && headers.size() > 0) {
				for (String key : headers.keySet()) {
					requestBuilder.addHeader(key, headers.get(key));
				}
			}
			response = httpclient.execute(requestBuilder.build());
			HttpEntity entity = response.getEntity();
			String respContent = EntityUtils.toString(entity, Consts.UTF_8).trim();
			return respContent;

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if(response!=null){
				response.close();
			}
			if(log.isDebugEnabled()){
				log.info("地址："+url+" 参数："+params,log);
			}
		}
	}

    /**
     * 执行一个HTTP POST请求，返回请求响应的HTML,form表单提交
     * 
     * @param url
     *            请求的URL地址
     * @param params
     *            请求的查询参数,可以为null
     * @param charset
     *            字符集 Consts.UTF_8
     * @return 返回请求响应的HTML
     */

	public static String doPost(String url, Map<String, String> params, Charset charset,int timeout) throws Exception {
		HttpPost post = null;
		CloseableHttpResponse response = null;
		try {
			timeout = timeout>0?timeout:TIME_OUT;
			post = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
			post.setConfig(requestConfig);
			httpclient = HttpClients.createDefault();
			List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				valuePairs.add(nameValuePair);
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs, charset);
			RequestBuilder requestBuilder = RequestBuilder.post().setUri(new URI(url));
			requestBuilder.setEntity(formEntity);
			post.setEntity(formEntity);
			// HttpResponse resp = httpclient.execute(httpPost);
			response = httpclient.execute(requestBuilder.build());
			HttpEntity entity = response.getEntity();
			String respContent = EntityUtils.toString(entity, charset).trim();
			return respContent;

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if(post!=null){
				post.abort();
			}
			if(response!=null){
				response.close();
			}
			if(log.isDebugEnabled()){
				log.info("地址："+url+" 参数："+params,log);
			}
		}
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML,json表单提交
	 *
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集 Consts.UTF_8
	 * @return 返回请求响应的HTML
	 */

	public static String doJSONPost(String url, Map<String, String> params, Charset charset,int timeout) throws Exception {
		HttpPost post = null;
		CloseableHttpResponse response = null;
		try {
			timeout = timeout>0?timeout:TIME_OUT;
			post = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
			post.setConfig(requestConfig);
			httpclient = HttpClients.createDefault();
			StringEntity formEntity = new StringEntity(new JSONObject().set("JSON", params).toString());
			formEntity.setContentType("application/json;charset=utf-8");
			RequestBuilder requestBuilder = RequestBuilder.post().setUri(new URI(url));
			requestBuilder.setEntity(formEntity);
			post.setEntity(formEntity);

			response = httpclient.execute(requestBuilder.build());
			HttpEntity entity = response.getEntity();
			String respContent = EntityUtils.toString(entity, charset).trim();
			return respContent;

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if(post!=null){
				post.abort();
			}
			if(response!=null){
				response.close();
			}
			if(log.isDebugEnabled()){
				log.info("地址："+url+" 参数："+params,log);
			}
		}
	}



	/**
     * 
     
     * Description:  通过HTTP图片路径下载到本地磁盘<BR> 
     
     * @author zhu.qiyuan
     
     * @date 2016年11月24日 下午4:49:45
     
     * @param _sImgHttpUrl 图片路径
     * @param _sImgName 下载后的图片文件名
     * @param _sDestPath 下载后图片存放的文件夹路径
     * @throws Exception
     */
    public static void downLoadImg(String _sImgHttpUrl,String _sImgName,String _sDestPath) throws Exception{
    	FileOutputStream output = null;
    	try {
			File storeFile = new File(_sDestPath+"/"+_sImgName);  
			output = new FileOutputStream(storeFile);  
			downLoadImg(_sImgHttpUrl, output);
			output.flush();
		} catch (Exception e) {
			log.error("下载图片异常", e);
			e.printStackTrace();
			throw new Exception("下载图片异常",e);
		}finally{
			if(output!=null){
				output.close();
			}									
		}
    }
    
    /**
     * 
     * Description: 通过HTTP图片路径下载到本地磁盘 <BR>  
     * @author yang.wei
     * @date 2017年11月29日 上午9:29:49
     * @param _sImgHttpUrl 图片地址
     * @param out 输出流
     * @throws Exception
     */
    public static void downLoadImg(String _sImgHttpUrl,OutputStream out) throws IOException{
    	CloseableHttpResponse response = null;
    	HttpGet get = null;
    	InputStream in = null;
		try {
			httpclient = HttpClients.createDefault();
			get = new HttpGet(_sImgHttpUrl);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT).build();
			get.setConfig(requestConfig);
			response = httpclient.execute(get);
			in = response.getEntity().getContent();
			IOUtils.copy(in, out);
		} catch (IOException e) {
			throw e;
		} finally{
			if(get!=null){
				get.abort();
			}
			if(in!=null){
				in.close();
			}
			if(response!=null){
				response.close();
			}
		}
    }
    
}