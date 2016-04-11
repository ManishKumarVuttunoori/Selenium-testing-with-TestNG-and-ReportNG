package selenium_test;

import java.util.List;

public class TestHelper {
	public static Boolean isStringPresent(List<String> stringList, String string)
	{
		for(String str: stringList)
		{
			if(string.contains(str))
				return true;
		}
		return false;
	}

}
