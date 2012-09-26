<a href="https://github.com/socialsignin/socialsignin-provider">SocialSignin Provider Module</a> for <a href="https://github.com/michaellavelle/spring-social-soundcloud">Spring-Social-SoundCloud</a>
======================================================

See <a href="https://github.com/socialsignin/socialsignin-showcase">socialsignin-showcase</a> for an example of this module in use.

Add this module to your classpath and component scan to auto-register and configure spring-social-soundcloud for your
application.
```
  <repository>
        <id>opensourceagility-snapshots</id>
        <url>http://repo.opensourceagility.com/snapshots </url>
    </repository>
...
    <dependencies>
        ...
        <dependency>
        <groupId>org.socialsignin</groupId>
            <artifactId>socialsignin-soundcloud</artifactId>
            <version>1.0.2-SNAPSHOT</version>
        </dependency>
        ...
```
```
    <context:component-scan
                base-package="org.socialsignin.provider" />
```

As well as configuring the SoundCloudConnectionFactory and registering with the ConnectionFactoryRegistry,
this component scan also makes a <a href="https://github.com/socialsignin/socialsignin-provider/blob/master/src/main/java/org/socialsignin/provider/ProviderService.java">ProviderService&lt;SoundCloud&gt;</a> implementation available as a bean in your application. This service
can be used by your application to obtain SoundCloud API Clients for common use cases without needing to work with
the Connection api of Spring Social directly:

```
public class YourService
{
  @Autowired
  private SoundCloudProviderService soundCloudProviderService;
  ...

}
```

Prerequesites/Setup
-------------------

Requires Spring Social's ConnectionFactoryRegistry, a UsersConnectionRepository and a ConnectionRepository (associated with the authenticated user) to exist as beans in your application context.

When using the default component scan to register this SoundCloud module, the following properties must be set in your application:
```
soundcloud.consumerKey=
soundcloud.consumerSecret=
soundcloud.redirectUri=http://localhost:8080/signinOrConnect/soundcloud
```
The <a href="https://github.com/socialsignin/spring-social-security/blob/master/src/main/java/org/socialsignin/springsocial/security/web/ProviderSignInOrConnectController.java"ProviderSignInOrConnectController</a> from 
<a href="https://github.com/socialsignin/spring-social-security">Spring-Social-Security<a/> must be registered as an MVC controller in your
application if you wish users be able to both "login" and "connect" with soundcloud, and the soundcloud.redirectUri must be specified
in your properties file as above.  This is to allow for the single redirect url required by SoundCloud to be specified
as http://localhost:8080/signinOrConnect/soundcloud, and for both use cases of "login" and "connect" to be supported.

If only one of the "Logging In" and "Connecting" use cases is required (if you have ProviderSignInController *or* ConnectController in your application, but not both), then
this controller and property do not need to be present in your application.  The return url of SoundCloud can then
be set as either of the /signin or /connect callback urls


Module Features
---------------

- Auto-configuration and registration of SoundCloudConnectionFactory based on properties in your environment
- Registers a SoundCloudProviderService bean - this component can be injected into your application's services and provides
access to SoundCloud API Client directly for common use cases, reducing need for developers to interact with low-level connections.
- If Spring-Social-Security is used, the SoundCloudConnectInterceptor required by Spring-Social-Security will 
automatically be registered with the ConnectInterceptorList for easy injection into ConnectController.
- Quickstart implementations of UsersConnectionRepostory/ConnectionRepository to help developers get up and running
using Spring-Social without needing to set up databases in the first instance.

