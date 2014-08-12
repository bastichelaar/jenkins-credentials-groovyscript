import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import hudson.plugins.sshslaves.*;
import static java.util.UUID.randomUUID  

username = "username"
password = "password"
description = "Test user"
id = randomUUID() as String

global_domain = Domain.global()
credentials_store =
	Jenkins.instance.getExtensionList(
		'com.cloudbees.plugins.credentials.SystemCredentialsProvider'
		)[0].getStore()

username_matcher = CredentialsMatchers.withUsername(username)
available_credentials =
	CredentialsProvider.lookupCredentials(
		StandardUsernameCredentials.class,
		Jenkins.getInstance(),
		hudson.security.ACL.SYSTEM,
		new SchemeRequirement("ssh")
		)

existing_credentials =
	CredentialsMatchers.firstOrNull(
		available_credentials,
		username_matcher
		)

credentials = new UsernamePasswordCredentialsImpl(
	CredentialsScope.GLOBAL,
		id,
		description,
		username,
		password
		)


if(existing_credentials != null) {
	credentials_store.updateCredentials(
		global_domain,
		existing_credentials,
		credentials
		)
	} else {
		credentials_store.addCredentials(global_domain, credentials)
	}
