step01.搭建父工程pom
step02.搭建支付微服务（基于spring boot2.0的项目搭建）
step03.搭建订单微服务（通过restTemplate 进行服务间的调用）
step04.将项目中的公共代码抽取到一个工程（通过maven clean、install发布到本地maven库）


## 开始引入微服务的基本知识
## 第一部分 服务注册
step05.搭建Eureka注册中心(@EnableEurekaServer表明是Eureka服务端)
# 到这里通过支付服务去调用订单，一切显得很自然，但是当微服务多起来之后量变引起质变，所以需要引入服务治理框架 
step06.将其他微服务注册进Eureka(@EnableEurekaClient表明Eureka客户端)

step07.Eureka集群(高可用，单点故障)(互相注册，相互守望)
step08.将支付和订单微服务注册到集群上
