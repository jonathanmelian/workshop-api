# API workshop I

Second API workshop, project done to learn about headers

## How to?

Hit /oauth/token with POST using the following credentials to get a token

	USERNAME = "user";
	PASSWORD = "password";
	CLIENT_ID = "workshop";
	CLIENT_SECRET = "secret";
	GRANT_TYPE = "password";

Hit /closed/hello using the token adquired from last call to get access to private endpoint

Hit /hello using any token or none to acces public endpoint


## Built With

* [SpringBoot](https://spring.io/docs) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management



