package com.oa;

import java.io.InputStream;
import java.util.List;

public interface NewsParse {

	public List<News> parse(InputStream is) throws Exception;
	
}
