package com.oa;

import java.io.InputStream;
import java.util.List;

public interface HqParse {

	public List<Hq> parse(InputStream is) throws Exception;
	
}
