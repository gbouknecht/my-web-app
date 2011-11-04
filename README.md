This web application is for me a means to learn Maven 3, Spring 3, JSF 2, etc.

As the name of the web application may already suggests, it has no practical
uses. For me it is only a playground to try things out. I am hosting it on
GitHub so I have access to it from different locations and have something to
talk about with colleagues. I may remove it from GitHub after play time is
over.

The environment I am developing in:

* Mac OS X Lion
* Java 1.6
* Tomcat 7.0
* Maven 3.0
* Eclipse 3.7 (with Maven integration in Eclipse (m2e) plug-in, version 1.0)
* Safari 5.1
* Firefox 7.0

To see the web application in action, you may deploy it on a Tomcat 7. Tomcat
should be listing on localhost port 8080. And there must be a Tomcat user named
"deployer" with password "deployer" that has the "manager-script" role.

1. Clone this repository.
2. Go to <code>my-web-app</code> and run <code>mvn tomcat:deploy</code>.
3. Browse to http://localhost:8080/my-web-app/

For redeployments:

1. Run <code>mvn clean tomcat:undeploy tomcat:deploy</code>.

