package selenium_test;

import java.util.List;

public class TestHelper {
	//contains helper methods
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
