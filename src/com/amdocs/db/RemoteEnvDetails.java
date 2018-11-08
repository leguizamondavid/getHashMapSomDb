package com.amdocs.db;
import java.util.Properties;
import javax.naming.Context;

import com.amdocs.util.AutomationUtils;


public class RemoteEnvDetails {

    private static volatile RemoteEnvDetails instance;
    private static final Object lock = new Object();

    private String initialContextFactory;
    private String securityPrincipal;
    private String securityCredentials;
    private String providerUrl;

    private String jndiName;

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    
    private String targetDbUrl;
    private String targetDbUser;
    private String targetDbPassword;
    
    private String envHost;
    private String envPort;

    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    
    private static final String TARGET_DB_URL = "target.db.url";
    private static final String TARGET_DB_USER = "target.db.user";
    private static final String TARGET_DB_PASSWORD = "target.db.password";

    private static final String ENV_HOST = "env.host";
    private static final String ENV_PORT = "env.port";
    private static final String SO_ID = "so_id";
	
	private static final String REMOTE_ENV_CONFIG = "/src/main/resources/remote.env.properties";

    private RemoteEnvDetails() {

	Properties prop = AutomationUtils.loadProperty(REMOTE_ENV_CONFIG);

	this.setInitialContextFactory(prop.getProperty(Context.INITIAL_CONTEXT_FACTORY));
	this.setSecurityPrincipal(prop.getProperty(Context.SECURITY_PRINCIPAL));
	this.setSecurityCredentials(prop.getProperty(Context.SECURITY_CREDENTIALS));
	this.setProviderUrl(prop.getProperty(Context.PROVIDER_URL));

	// jndi name
	this.setJndiName(prop.getProperty(DB_URL));

	// DB details
	this.setDbUrl(prop.getProperty(DB_URL));
	this.setDbUser(prop.getProperty(DB_USER));
	this.setDbPassword(prop.getProperty(DB_PASSWORD));
	
	this.setTargetDbUrl(prop.getProperty(TARGET_DB_URL));
	this.setTargetDbUser(prop.getProperty(TARGET_DB_USER));
	this.setTargetDbPassword(prop.getProperty(TARGET_DB_PASSWORD));

	// ENV details
	this.setEnvHost(prop.getProperty(ENV_HOST));
	this.setEnvPort(prop.getProperty(ENV_PORT));
    }

    public static RemoteEnvDetails getInstance() {
	RemoteEnvDetails r = instance;
	if (r == null) {
	    synchronized (lock) {
		r = instance;
		if (r == null) {
		    r = new RemoteEnvDetails();
		    instance = r;
		}
	    }
	}
	return r;
    }

    public String getInitialContextFactory() {
	return initialContextFactory;
    }

    private void setInitialContextFactory(String initialContextFactory) {
	this.initialContextFactory = initialContextFactory;
    }

    public String getSecurityPrincipal() {
	return securityPrincipal;
    }

    private void setSecurityPrincipal(String securityPrincipal) {
	this.securityPrincipal = securityPrincipal;
    }

    public String getSecurityCredentials() {
	return securityCredentials;
    }

    private void setSecurityCredentials(String securityCredentials) {
	this.securityCredentials = securityCredentials;
    }

    public String getProviderUrl() {
	return providerUrl;
    }

    private void setProviderUrl(String providerUrl) {
	this.providerUrl = providerUrl;
    }

    public String getDbUrl() {
	return dbUrl;
    }

    private void setDbUrl(String dbUrl) {
	this.dbUrl = dbUrl;
    }

    public String getDbUser() {
	return dbUser;
    }

    private void setDbUser(String dbUser) {
	this.dbUser = dbUser;
    }

    public String getDbPassword() {
	return dbPassword;
    }

    private void setDbPassword(String dbPassword) {
	this.dbPassword = dbPassword;
    }

    private void setJndiName(String jndiName) {
	this.jndiName = jndiName;
    }

    public String getJndiName() {
	return jndiName;
    }

    public String getEnvHost() {
	return envHost;
    }

    private void setEnvHost(String envHost) {
	this.envHost = envHost;
    }

    public String getEnvPort() {
	return envPort;
    }

    private void setEnvPort(String envPort) {
	this.envPort = envPort;
    }

	public String getTargetDbUrl() {
		return targetDbUrl;
	}

	public void setTargetDbUrl(String targetDbUrl) {
		this.targetDbUrl = targetDbUrl;
	}

	public String getTargetDbUser() {
		return targetDbUser;
	}

	public void setTargetDbUser(String targetDbUser) {
		this.targetDbUser = targetDbUser;
	}

	public String getTargetDbPassword() {
		return targetDbPassword;
	}

	public void setTargetDbPassword(String targetDbPassword) {
		this.targetDbPassword = targetDbPassword;
	}
	
	public static String getSoId() {
		return SO_ID;
	}
}
