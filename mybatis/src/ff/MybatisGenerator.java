package ff;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-23 上午11:09
 */
public class MybatisGenerator {
	public void generatot(){
		List<String> warnings = new ArrayList<String>();
		boolean overWrite = true;
		File confFile = new File("generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		try {
			Configuration conf = cp.parseConfiguration(confFile);
			DefaultShellCallback callback = new DefaultShellCallback(overWrite);
			
			try {
				MyBatisGenerator myBatisGenerator = new MyBatisGenerator(conf,callback, warnings);
				try {
					myBatisGenerator.generate(null);
					System.out.println("end");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		MybatisGenerator generator = new MybatisGenerator();
		generator.generatot();
	}
}
