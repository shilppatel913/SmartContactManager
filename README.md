**Dependencies**
1. Web
2. JPA
3. Mysql-connector
4. Thymeleaf
5. Spring Security
6. Bean Validation API
7. Hibernate-validator
8. DevTools


**Spring Security**
The moment you add this dependency into your project all of the endpoints would be automatically secured and you'd be requiring a username and password to access all of them. The password is generated at runtime and will be shown on your console and username is user. Now you can customise everything to perform the necessary auth and to secure only some of the endpoints. To do that you need to follow the below steps.

1. Create implementation class of UserDetails interface.
2. Create implementation of UserDetailsService interface.
3. Create a configuration file and extend WebSecurityConfigurerAdapter so that you can use it's methods.
    
    Following are the beans to be created in the above configuration file
      1. UserDetailsService
      2. BcryptPasswordEncoder to encode your password.
      3. DaoAuthenticationProvider
   
   After you have to use the methods of WebSecurityConfigurerAdapter to customise our paths
    1. configure(AuthenticationManagerBuilder auth)
    2. configure(HttpSecurity http)
    
 **Several methods that we can use to configure the behaviour of the formlogin() according to our needs**
 1.loginPage() - The custom login page
 2. loginProcessingUrl() - URL where your data (username and password) will be sent.
 3. defaultSuccessfulUrl() - The landing page after successful login
 4.failureUrl() - The landing page after unsuccessful login.
 
 
 **Rich Text Editor**
 You can use the **tiny MCE API** to integrate the rich text editor into your application. Just go into the documentation and add the script tag where you need the rich text editor. Also include id of the textarea to add the editor there.
