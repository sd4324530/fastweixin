# fastweixin扩展内容
- <font size=8>ApiConfig扩展：将原ApiConfig配置类改为接口，并提供了默认的实现；可以通过继承默认实现或者直接实现ApiConfig接口的方式定制自己的ApiConfig。</font>
- <font size=8>在ApiConfig默认实现中，将token获取的逻辑抽离出来，抽象成接口TokenService，并提供默认实现；如果只想修改token获取方式（例如：通过统一的服务来管理token），只需要继承ApiConfig默认实现，指定自己实现的TokenService即可。</font>
