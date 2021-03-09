# Eda-Project
# project modules

1. Search-Service 

This micro-service is responsible for the whole search and filtering related services. All the search related cache (LRU cache for the user-wise, location-wise cache implemented here. This service must be highly scalable according to the peak time such as festive seasons assuming that more users will be looking for holiday stays. 

2. Offer-Service 

This micro-service is responsible for the offers related search, caches.  As an enhancement, this module should be enhanced to manage offer related rule engine. Such as seasonal offers, religious offers, origin country-wise offers, user-wise offers and discount coupon handling, etc. This service also be highly scalable service. 

3. Order-Service 

This micro-service is responsible for the user selection and booking order creations. But order creation happens when user acknowledge the order after selection. Based on order creation, booking process will be started. 

4. Booking-Service 

This service is responsible for the booking operation related activities, such as listinig to booking life cycle events and update booking accordingly. 

5. Payment-Service 

This service is responsible for the payment related activities such as calling to payment gateway and act according to the return status of the payment. 

6. Config-Service 

This service is internal service which used to manage all the micro-services' application configurations. 

7. Auth-Service 

This service is internal service which manage authentication and authorization of the login and other service call between apps and web application api calls. 

8. Discovery-service (Eureka) 

This is service discovery server which helps to communicate between api calls easily and dynamically. 

9. Boking-Gateway (Zuul Gateway) 

This service manages and perform routing between services and external api call routings. 

# How to run this services
This project consists of micro-services. Below steps needs to execute run this project flow.
1. Install mongodb server in your local computer. If you are using custome port instead of default port(27017). you need to update configuration files in "Config-server" module.
2. Install Kafka stream in your local computer. If you are using custome port instead of default port(9092). you need to update configuration files in "Config-server" module.
3. Start config-service module.
4. Start discovery-service module.
5. Start Booking-Gateway module.
6. Start Auth-Server Module.
7. Start rest of the service now.
8. You can access Eureka server via http://localhost:8761/ and make sure service registry is registered properly. if not, please check your service logs to find out any issue with starting services.
9. Open web-module and install all dependencies "npm install".
10. Start the web module with "npm start".
11. Now you can access the front end via http://localhost:3000
