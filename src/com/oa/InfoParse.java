package com.oa;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public interface InfoParse {
	
	public ArrayList<String> parse(InputStream is) throws Exception;
	
}
