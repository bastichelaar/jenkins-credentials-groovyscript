import hudson.security.SecurityRealm;


realm = new hudson.plugins.active_directory.ActiveDirectorySecurityRealm(
  "Domain",
  null,
  null,
  "Password",
  "Server"
)
strategy = new hudson.security.FullControlOnceLoggedInAuthorizationStrategy()

jenkins.model.Jenkins.instance.securityRealm = realm
jenkins.model.Jenkins.instance.setAuthorizationStrategy(strategy)
