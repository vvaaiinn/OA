package com.oa;

import java.io.InputStream;
import java.util.List;

public interface EmParse {

	public List<Em> parse(InputStream is) throws Exception;
	
}
