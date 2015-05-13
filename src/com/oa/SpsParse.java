package com.oa;

import java.io.InputStream;
import java.util.List;

public interface SpsParse {

	public List<Sps> parse(InputStream is) throws Exception;
	
}