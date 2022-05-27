# WindSurfer API Rest

This project we are consuming **Weatherbit Forecast API** .
In this app we are using Spring boot, RestTemplate, Junit and Mockito.     

If running on local machine then endpoint is available on:
## [http://localhost:8081](http://localhost:8081)



## Prerequisites 
- Java
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org/)
- [Lombok](https://objectcomputing.com/resources/publications/sett/january-2010-reducing-boilerplate-code-with-project-lombok)
- [*Weatherbit Forecast API](https://www.weatherbit.io/api/weather-forecast-16-day)
- [Open API Swagger Documentation]


## Tools
- Eclipse or IntelliJ IDEA (or any preferred IDE) with embedded Gradle
- Gradle (version = 6.5.1)
- Postman (or any RESTful API testing tool)
- Any browser


## API Endpoints

- #### WindSurfer suitable location for given date
    > **GET Mapping** http://localhost:8081/2022-06-07  - Get Windsurfer suitable locations for given date 2022-06-07 
                                       
                                      
    Output:: 
    ```
			"cityName": "Le Gros-Morne",
			"countryCode": "MQ",
			"longitude": "-61",
			"latitude": "14.7",
			"stateCode": "MQ",
			"timeZone": "America/Martinique",
			"averageTemp": "25.8",
			"windSpeed": "5.3"
    ```