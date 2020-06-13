设计模式： 策略模式 IRule 接口 + AbstractLoadBanlance抽象类  
使用总结： 直接使用ribbon组件中的负载均衡算法，直接在restTemplate配置类注解上加上@LoadBalance注解  
          自定义的负载均衡规则的话  @LoadBalance注释掉
负载均衡算法总结：rest接口第几次请求数 % 服务器集群数量 = 实际调用服务器位置下标，每次服务重启之后rest的计数器下标置为1  