package com.shangde.edu.feed.utils.template;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

/**
 * Created by IntelliJ IDEA.
 * User: chensong
 * Date: 11-10-27
 * Time: 下午1:15
 * To change this template use File | Settings | File Templates.
 */

/**
 * 修改加载模板工具类
 */
public class TemplateRourceLoader extends ResourceLoader {

	public void init(ExtendedProperties configuration) {
	}

	public InputStream getResourceStream(String source)
			throws ResourceNotFoundException {
		InputStream result = null;

		if (source == null || source.length() == 0) {
			throw new ResourceNotFoundException("模板没有被定义~！");
		}
		result = new ByteArrayInputStream(source.getBytes());
		return result;
	}

	public boolean isSourceModified(Resource resource) {
		return false;
	}

	public long getLastModified(Resource resource) {
		return 0;
	}

}
