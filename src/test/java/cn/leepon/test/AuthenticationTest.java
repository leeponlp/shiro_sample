package cn.leepon.test;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * 
 * <p>
 * Title: AuthenticationTest
 * </p>
 * <p>
 * Description: 认证测试
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 传智.燕青
 * @date 2015-3-23下午4:10:37
 * @version 1.0
 */
public class AuthenticationTest {

	// 用户登陆和退出
	@Test
	public void testLoginAndLogout() {

		Subject subject = getSubject("classpath:shiro-first.ini");

		// 在认证提交前准备token（令牌）
		// 这里的账号和密码 将来是由用户输入进去
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","111111");

		try {
			// 执行认证提交
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过：" + isAuthenticated);

		// 退出操作
		subject.logout();

		// 是否认证通过
		isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过：" + isAuthenticated);

	}

	

	// 自定义realm
	@Test
	public void testCustomRealm() {
		
		Subject subject = getSubject("classpath:shiro-realm.ini");

		// 在认证提交前准备token（令牌）
		// 这里的账号和密码 将来是由用户输入进去
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","111111");

		try {
			// 执行认证提交
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过：" + isAuthenticated);

	}

	// 自定义realm实现散列值匹配
	@Test
	public void testCustomRealmMd5() {
		
		Subject subject = getSubject("classpath:shiro-realm-md5.ini");

		// 在认证提交前准备token（令牌）
		// 这里的账号和密码 将来是由用户输入进去
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","222222");

		try {
			// 执行认证提交
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过：" + isAuthenticated);

	}
	
	
	private Subject getSubject(String file) {
		
		// 创建securityManager工厂，通过ini配置文件创建securityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(file);
		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// 将securityManager设置当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);

		// 从SecurityUtils里边创建一个subject
		Subject subject = SecurityUtils.getSubject();
		return subject;
	}

}
