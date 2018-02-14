Challenge Mule flow has been designed using XML editor. The Mule CE used to execute this flow is an embedded Mule ESB in a spring boot application (microservice architecture) using mule-spring-boot-starter

Solution outline
----------------
1) CRM mock service provided earlier has been used to mimic queue integration.
2) Custom Policy service (microservice) has been implemented to mimic rest service integration (https://github.com/harikrishnanmurthy/topdanmark-policy-mock).
3) Scatter-gather to achieve parallel processing.
4) Reference Exception Strategy to handle exceptions.
5) Java based custom Aggregation strategy to aggregate response from policy and queue services.
6) Java based transformer to prepare the payload for SOAP service.
7) Distribution service has been mocked using the WSDL provided in SOAP-UI (https://github.com/harikrishnanmurthy/topdanmark-distribution-mock).
8) ws-consumer to integrate WSDL based web service using XSLT transformer.

Challenges faced
----------------
1) Difficulty in downloading few artifacts(for eg mule-plugin-weave-xx) from maven central/mule repo since this application was developed within a restricted network. Had to manually download and place it in the classpath.
2) Designing the flow using an XML editor since i wasn't able to download and install any point studio for the aforementioned reason.
3) Using ws-consumer connector to consume web services without using DataWeaver and DataMapper components. Could have used cxf connector but since significant time was invested in making ws consumer work, i carried on with it

Starting the Application
------------------------
Download as maven project into your IDE(pref eclipse).
Download activemq-all-5.8.0.jar and place it in the classpath of the eclipse project.
Download mule-plugin-weave-4.0 from mule repository and place it in the classpath of the eclipse project.
Configure maven build and set goal as mvn clean install.
Upon successful building, run SCBMuleApplication.java as a java application(to boot up the embedded mule).
