Challenge Mule flow has been designed using XML editor. Mule CE is embedded in a spring boot application using mule-spring-boot-starter

Solution outline
----------------
1) CRM mock service provided earlier has been used to mimic queue integration
2) Custom Policy service has been implemented to mimic rest service integration (https://github.com/harikrishnanmurthy/topdanmark-policy-mock)
3) Scatter-gather to achieve parallel processing
4) Reference Exception Strategy to handle exceptions
5) Java based custom Aggregation strategy to aggregate response from policy and queue services
6) Java based transformer to prepare the payload for SOAP service
7) Distribution service has been mocked using the WSDL provided in SOAP-UI (https://github.com/harikrishnanmurthy/topdanmark-distribution-mock)
8) ws-consumer to integrate WSDL based web service using XSLT transformer