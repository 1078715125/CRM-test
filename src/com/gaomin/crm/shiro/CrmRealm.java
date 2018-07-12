package com.gaomin.crm.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.gaomin.crm.entity.Authority;
import com.gaomin.crm.entity.User;
import com.gaomin.crm.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrmRealm extends AuthorizingRealm {

	@Autowired
	private UserMapper userMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		User user = (User) principals.getPrimaryPrincipal();
		Set<String> roles = new HashSet<String>();
		List<Authority> authorities = user.getRole().getAuthorities();
		for (Authority auth : authorities) {
			roles.add(auth.getName());
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		String username = upToken.getUsername();

		User user = userMapper.getByName(username);

		if (user == null) {
			throw new UnknownAccountException("用户名【" + username + "】不存在！");
		}
		if (user.getEnabled() != 1) {
			throw new LockedAccountException("用户名【" + username + "】被锁定！");
		}

		Object principal = user;
		Object hashedCredentials = user.getPassword();
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,
				hashedCredentials, credentialsSalt, getName());

		return info;
	}

	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(
				"MD5");
		credentialsMatcher.setHashIterations(1024);

		setCredentialsMatcher(credentialsMatcher);
	}
	
	public static void main(String[] args) {
		
		ByteSource salt = ByteSource.Util.bytes("ceadfd47cdaa814c");
		SimpleHash simpleHash = new SimpleHash("MD5", "123456", salt, 1024);
		System.out.println(simpleHash);
	}

}
