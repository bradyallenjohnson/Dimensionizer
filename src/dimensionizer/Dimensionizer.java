package dimensionizer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;

public class Dimensionizer 
{

	public static void main(String[] args)
	{
		ApplicationContext context = new GenericApplicationContext(  );
		Resource beanFileResource = 
			context.getResource( "classpath:dimensionizer/dimensionBeans.xml" );
		BeanFactory factory = new XmlBeanFactory( beanFileResource );
		
		Dimension rootDimension = (Dimension) factory.getBean( "root" );
		System.out.println( rootDimension.toString( 0.3 ) );
	}

}
