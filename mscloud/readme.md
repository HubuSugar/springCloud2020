step01.搭建父工程pom  
step02.搭建支付微服务（基于spring boot2.0的项目搭建）  
step03.搭建订单微服务（通过restTemplate 进行服务间的调用）   
step04.将项目中的公共代码抽取到一个工程（通过maven clean、install发布到本地maven库）  


## 开始引入微服务的基本知识
## 第一部分 服务注册
step05.搭建Eureka注册中心(@EnableEurekaServer表明是Eureka服务端)  
## 到这里通过支付服务去调用订单，一切显得很自然，但是当微服务多起来之后量变引起质变，所以需要引入服务治理框架 
step06.将其他微服务注册进Eureka(@EnableEurekaClient表明Eureka客户端)  

step07.Eureka集群(高可用，单点故障)(互相注册，相互守望)  
step08.将支付和订单微服务注册到集群上  
step09.服务（支付微服务）搭建8001、8002、8003三个集群  

step10.微服务负载均衡(通过LoadBalanced注解)  
step11.修改微服务的主机名称和ip显示  

## 其他注册中心zookeeper、consul、nacos
step12.将支付微服务注册进zookeeper微服务(安装zookeeper服务,服务相当于zookeeper的临时节点)  
step13.将订单微服务注册进zookeeper  

## 使用consul作为注册中心
step14.将订单和支付微服务注册进consul注册中心

## Ribbon负载均衡+服务调用（客户端负载均衡工具，将用户的请求平摊到多个服务器，nginx/LVS/F5）
  Nginx负载均衡--客户端所有的请求都会交给nginx,由nginx统一转发  
  Ribbon负载均衡--本地负载均衡，在注册中心获取注册服务列表缓存到JVM本地,从而实现RPC调用  
  eureka-client依赖中已经引入了ribbon组件
  
step15.自定义负载均衡规则  
  官方：不能放在@ComponentScan能扫描的包下  
## Feign/OpenFeigh 声明式的web服务客户端（接口绑定器）
step16.引入OpenFeign, 客户端通过Feign调用微服务暴露的服务接口  
   （1）在主启动类上添加@EnableFeignClients
    (2) 引入微服务提供者的service接口，通过@FeignClient注解指定feign调用的那个微服务     
step17.OpenFeign设置超时控制（默认等待时间是1s） 
    在客户端的yml中通过ribbon配置ReadTimeout、ConnectTimeout

step18.OpenFeign配置日志级别
    在feign客户端yml通过logging配置设置（操作过程中debug前的空格忘记写了，坑）
## Hystrix服务降级fallback、服务熔断break、服务限流flowlimit   
step19.新建模拟服务降级的微服务  
step20.服务降级(jmeter压测8001，发现ok方法也变慢了),通过@hystrixCommand、@EnableCircuitBreaker注解对8001服务提供者异常（超时、运行时异常）进行兜底，服务端自我保护（一般降级在客户端） 
step21.客户端服务降级
       这个地方测试异常情况比较顺利，测试正常情况时调试了好久，最终注释掉了服务侧的业务代码才成功了。(坑)   
step22.服务降级的全局异常(运行时异常、超时、服务宕机)的兜底方法处理 @defaultPropertities   
       每一个方法都写一个兜底的方法会导致代码膨胀  
step23.服务降级的通用配置：使用一个通用类去实现带有FeignClient注解的服务端绑定接口  
       模拟8001服务宕机情况（停止8001服务）  
step24.服务熔断：应对雪崩效应的微服务服务链路保护机制   
       与服务降级相比有一个恢复机制。  
       当失败次数达到一定比例、或者在一定时间达到多少次，保险丝会跳闸，当请求正常时保险丝会尝试放过请求(有一个半开状态)  
step25.hystrix服务监控:搭建hystrix服务监控平台(EnableHystrixDashboard)            

## 本章节开始springCloud gateway网关(gateway包括路由route、断言predictate、过滤器filter)   
step26.网关的yml配置和代码配置  
       注：代码在配置路由转发时，代码的实现逻辑是将path后的路由转发到uri地址下(小坑)  
       1.请求在什么时间点后生效(-after)
       2.请求是否带有cookies
       3.请求是否带有请求头    
       
## spring cloud config/bus
step27.config服务端搭建，从github仓库读取配置，注意：目前github主分支变成main分支  
step28.config客户端搭建，从分布式配置中心configServer读取配置  
step29.遇到的问题：配置中心的配置更新之后，configServer可以自动获取到最新配置，但是configClient则无法从configServer获取最新配置，如何解决？  
       1. actuator插件，2.yml文件暴露监控点，3.业务类上加上@RefreshScope注解，4，手动发送POST请求触发（ip:port/actuator/refresh）  
       更优化的解决方案：bus --- 达到一处通知，处处生效或者定点通知的目的
       广播通知：curl -X POST "http://localhost:3344/actuator/bus-refresh"  
       定点通知：curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"   

## 本章要开始消息编程模型的学习（spring cloud stream）
在微服务中，我们可能会用到不同的消息中间件rabbitMQ、kafka、RocketMQ等，所以我们想有一个框架能像JDBC屏蔽Oracle、Mysql数据库  
的差异一样帮我们屏蔽不同消息中间件的差异  
step30.消息的发送端和消息的接受到，通过Binder(Source和Sink)与底层的消息中间件交互  
step31.消息的重复消费和消息的持久化（通过分组的方法）  
       重复消费：当有多个消费者时，一条消息会被多次消费
       消息丢失：当消费者宕机或者不存在时，消息消费会丢失  

## 本章开始学习微服务的链路监控sleuth + zipkin
zipkin下载地址：https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/，下载exec.jar文件，以java -jar的方式运行    
    

## 开始spring cloud alibaba的学习
#### 服务注册与发现
step32.基于nacos的服务注册功能
       nacos支持AP、CP两种模式：切换 curl -X PUT '$NACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP'  

#### 服务配置
step33.基于nacos的配置中心  
配置规则：${prefix}-${spring.profile.active}.${file-extension}, ${prefix}默认为${spring.application.name}
step34.nacos集群搭建  
* 配置mysql持久化  
 ![image](https://github.com/HubuSugar/springCloud2020/blob/master/mscloud/assets/mysql-settings.jpg)  
* 集群配置  
 ![image](https://github.com/HubuSugar/springCloud2020/blob/master/mscloud/assets/nacos-settings.png)  
* nginx配置  
 ![image](https://github.com/HubuSugar/springCloud2020/blob/master/mscloud/assets/nginx-settings.png)  
## 本章开始流控学习sentinel  
注意：流控解析说明见8401服务readme
step34.流控模式测试api

