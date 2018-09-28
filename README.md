# fastweixin扩展内容
- <font size=8>ApiConfig扩展：将原ApiConfig配置类改为接口，并提供了默认的实现；可以通过继承默认实现或者直接实现ApiConfig接口的方式定制自己的ApiConfig。</font>
- <font size=8>Token获取扩展：在ApiConfig默认实现中，将token获取的逻辑抽离出来，抽象成接口TokenService，并提供默认实现；如果只想修改token获取方式（例如：通过统一的服务来管理token），只需要继承ApiConfig默认实现，指定自己实现的TokenService即可。</font>
- <font size=8>企业API配置扩展：与微信公众号ApiConfig扩展类似，QYAPIConfig直接继承ApiConfig，同样提供了默认的实现和TokenService实现。</font>
- <font size=8>增加了支付相关API，包含付款和红包API。</font>
- <font size=8>增加了全局的配置文件fastweixin.properties，用来配置超时和代理等参数。</font>
- <font size=8>扩展了NetWorkCenter工具类，增加了对代理的支持，同时原来的超时时间从配置文件中读取。</font>
