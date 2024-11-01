// SpringReact/src/main/java/spring/conf/SpringConfiguration.java
package spring.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


// @Configuration: 이 클래스가 Spring 설정 파일임을 나타냄
// @EnableTransactionManagement: 트랜잭션 관리를 활성화 <tx:annotation-driven>과 동일한 역할
// @PropertySource("classpath:spring/db.properties"): 외부 프로퍼티 파일인 db.properties에서 데이터베이스 연결 정보를 읽어옴

@Configuration
@EnableTransactionManagement // root-context.xml line 22 과 동일
@PropertySource("classpath:spring/db.properties")
@MapperScan("member.dao board.dao") // root-context.xml line 20 과 동일
public class SpringConfiguration { // Connection Pool & DataSource
	
	@Value("${jdbc.driver}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Autowired
	private ApplicationContext context;

	/** Chapter05_XML/src/spring/applicationContext.xml line 14 - 20 */	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		
		return basicDataSource;
	}
//	BasicDataSource 객체를 생성하고, 데이터베이스 연결에 필요한 정보를 설정
//	BasicDataSource : Apache DBCP(데이터베이스 커넥션 풀) 라이브러리에서 제공하는 커넥션 풀을 관리하는 객체
	
	
	
	/** Chapter05_XML/src/spring/applicationContext.xml line 22 - 27 */	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		//sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));
		/*sqlSessionFactoryBean.setMapperLocations(
				new ClassPathResource[] {
						new ClassPathResource("mapper/userMapper.xml"),
						new ClassPathResource("mapper/userUploadMapper.xml")
				}
		);*/
		// 위 sqlSessionFactoryBean.setMapperLocations() 와 동일
		sqlSessionFactoryBean.setMapperLocations(
				context.getResources("classpath:mapper/*Mapper.xml")
		);
		
		
//		sqlSessionFactoryBean.setTypeAliasesPackage("member.bean");
		sqlSessionFactoryBean.setTypeAliasesPackage("*.bean");
		
		
		return sqlSessionFactoryBean.getObject(); // SqlSessionFactory 로 변환하여 넘겨줌
	}
//	SqlSessionFactory : MyBatis와 데이터베이스 간의 연결을 관리하는 역할
//	SqlSessionFactoryBean을 사용하여 SqlSessionFactory를 생성
//	setDataSource : 데이터 소스는 위에서 정의한 BasicDataSource를 사용
//	setConfigLocation : MyBatis 설정 파일인 mybatis-config.xml의 위치 설정
//	setMapperLocations : MyBatis에서 사용할 매퍼 파일(userMapper.xml)의 위치 설정
//	이 SqlSessionFactory는 MyBatis의 SqlSession을 생성할 때 사용
	
	
	
	/** Chapter05_XML/src/spring/applicationContext.xml line 29 - 32 */	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		
		return sqlSessionTemplate;
	}
//	SqlSessionTemplate : MyBatis의 SqlSession 인터페이스를 구현한 클래스, 
// 		Spring과 MyBatis 간의 연동을 담당
//	SqlSessionFactory에서 세션을 얻어와 데이터베이스에 접근하거나 트랜잭션을 관리
//	이 템플릿은 DAO에서 사용됨
	
	
	/** Chapter05_XML/src/spring/applicationContext.xml line 34 - 37 */	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = 
				new DataSourceTransactionManager(dataSource());
		
		return dataSourceTransactionManager;
	}
//	DataSourceTransactionManager : 데이터베이스 트랜잭션을 관리하는 매니저 클래스
//								   트랜잭션의 시작, 커밋, 롤백 관리
//	Spring 이 트랜잭션을 관리할 수 있도록 트랜잭션 매니저를 정의하며, 이 트랜잭션 매니저는 위에서 정의한 BasicDataSource를 사용하여 트랜잭션을 관리
//	이 설정을 통해 서비스 계층에서 @Transactional을 사용하여 트랜잭션 범위 지정
	
	
}
