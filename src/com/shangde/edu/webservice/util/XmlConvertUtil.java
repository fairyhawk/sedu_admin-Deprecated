package com.shangde.edu.webservice.util;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.commons.betwixt.strategy.DefaultObjectStringConverter;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * XML转换工具类,可将XML与josn,Map,List对象相互转换
 * @author Yangning
 * @date 2011-11-11
 */
public class XmlConvertUtil {
	
	private static final Logger logger = Logger.getLogger(XmlConvertUtil.class);
	
	/**
	 * @param map
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-1
	 */
	@SuppressWarnings("rawtypes")
	public static String maptoXml(Map map,String tableName) {
		Document document = DocumentHelper.createDocument();
		Element nodeElement = document.addElement(tableName);
		for (Object obj : map.keySet()) {
			nodeElement.addAttribute(String.valueOf(obj),String.valueOf(map.get(obj)));
		}
		doc2String(document);
		return doc2String(document);
	}

	/**
	 * @param list
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-1
	 */
	@SuppressWarnings("rawtypes")
	public static String listtoXml(List list,String tableName) {
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() > 0){
			for(int i=0; i<list.size(); i++){
				sb.append(maptoXml((Map)list.get(i),tableName));
			}
		}
		String str = sb.toString();
		str = str.replaceAll("(<\\?xml.*(\\?>))+", "").replaceAll("\n", "");
		return str;
	}
	/*
	 * @param args
	 * Author:Yangning
	 * CreateDate:2011-11-1
	 */
	public static void main(String [] args){
		List<Map> list = new ArrayList<Map>();
		for(int i=0; i<10; i++){
			Map map = new HashMap<String,String>();
			map.put("yang", "yang");
			map.put("fiyang", "fiyang");
			list.add(map);
		}
		String str = listtoXml(list,"finace").replaceAll("(<\\?xml.*(\\?>))+", "");
		String finalString = listtoXml(list,"finace");
		System.out.println(finalString);
	}
	

	/**
	 * @param json
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-1
	 */
	public static String jsontoXml(String json) {
		try {
			XMLSerializer serializer = new XMLSerializer();
			JSON jsonObject = JSONSerializer.toJSON(json);
			return serializer.write(jsonObject);
		} catch (Exception e) {
			logger.error("XmlConvertUtil.jsontoXml" + e.getMessage());
		}
		return null;
	}

	/**
	 * @param xml
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-1
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map xmltoMap(String xml) {
		try {
			Map map = new HashMap();
			Document document = DocumentHelper.parseText(xml);
			Element nodeElement = document.getRootElement();
			List node = nodeElement.elements();
			for (Iterator it = node.iterator(); it.hasNext();) {
				Element elm = (Element) it.next();
				map.put(elm.attributeValue("label"), elm.getText());
				elm = null;
			}
			node = null;
			nodeElement = null;
			document = null;
			return map;
		} catch (Exception e) {
			logger.error("XmlConvertUtil.xmltoMap" + e.getMessage());
		}
		return null;
	}

	/**
	 * @param xml
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-1
	 */
	@SuppressWarnings("rawtypes")
	public static List xmltoList(String xml) {
		try {
			List<Map> list = new ArrayList<Map>();
			Document document = DocumentHelper.parseText(xml);
			Element nodesElement = document.getRootElement();
			List nodes = nodesElement.elements();
			for (Iterator its = nodes.iterator(); its.hasNext();) {
				Element nodeElement = (Element) its.next();
				Map map = xmltoMap(nodeElement.asXML());
				list.add(map);
				map = null;
			}
			nodes = null;
			nodesElement = null;
			document = null;
			return list;
		} catch (Exception e) {
			logger.error("XmlConvertUtil.xmltoList" + e.getMessage());
		}
		return null;
	}

	/**
	 * @param xml
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-1
	 */
	public static String xmltoJson(String xml) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		return xmlSerializer.read(xml).toString();
	}

	/**
	 * @param document
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-1
	 */
	public static String doc2String(Document document) {
		String s = "";
		XMLWriter writer = null;
		ByteArrayOutputStream out = null;
		try {
			// 使用输出流来进行转化
			out = new ByteArrayOutputStream();
			// 使用UTF-8编码
			OutputFormat format = new OutputFormat("", true, "UTF-8");
			writer = new XMLWriter(out, format);
			writer.write(document);
			s = out.toString("UTF-8");
		} catch (Exception e) {
			logger.error("XmlConvertUtil.doc2String" + e.getMessage());
		}finally{
			try{
				if(writer != null){
					writer.close();
					writer = null;
				}
				if(out != null){
					out.close();
					out = null;
				}
			}catch(Exception e){
				logger.error("XmlConvertUtil.doc2String" + e.getMessage());
			}
		}
		return s;
	}
	
	/**
	 * 将单个对象转为XML数据
	 * @param object
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-1
	 */
	public static String Object2XmlString(Object object) {
		   String xmlString = null;
		   // 创建一个输出流，将用来输出Java转换的XML文件
		   StringWriter outputWriter = new StringWriter();
		   // 创建一个BeanWriter实例，并将BeanWriter的输出重定向到指定的输出流
		   BeanWriter beanWriter = new BeanWriter(outputWriter);
		   // 配置BeanWriter对象
		   beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(true);
		   DefaultObjectStringConverter converter = new DefaultObjectStringConverter();
		   beanWriter.getBindingConfiguration().setObjectStringConverter(converter);
		   beanWriter.setEndTagForEmptyElement(true);
		   beanWriter.getBindingConfiguration().setMapIDs(false);
		   beanWriter.setWriteEmptyElements(true);
		   try {
			   
		    beanWriter.write(object);
		    xmlString = outputWriter.toString();
		    
		   } catch (Exception e) {
			   e.printStackTrace();
			  logger.error("XmlConvertUtil.Object2XmlString" + e.getMessage());
		   }finally{
		   // 关闭输出流
			   try {
				   if(beanWriter != null){
						  beanWriter.close();
						  beanWriter = null;
				   }
				   if(outputWriter != null){
					   outputWriter.close();
					   outputWriter = null;
				   }
				   
			   } catch (Exception e) {
				   logger.error("XmlConvertUtil.Object2XmlString" + e.getMessage());
			   }
		   }
		   return xmlString;
		}

}
