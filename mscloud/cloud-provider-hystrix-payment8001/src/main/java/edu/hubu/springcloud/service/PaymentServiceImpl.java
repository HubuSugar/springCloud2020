package edu.hubu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import edu.hubu.springcloud.dao.PaymentDao;
import edu.hubu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * created by Sugar  2020/5/30 22:07
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentByid(Long id) {
        return paymentDao.getPaymentByid(id);
    }

    /**
     * hystrix正常访问的方法
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_Ok(Long id){

        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK id :" + id + "\t" + "O(∩_∩)O哈哈~";
    }


    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String paymentInfo_TIMEOUT(Long id)
    {
//        System.out.println("从客户端接收到的参数id为：" + id);
//        long result = id * 5;
//        int age = 10 / 0;
        return "线程池:  "+Thread.currentThread().getName()+" id:  "+id+"\t"+"O(∩_∩)O哈哈~"+"  耗时(秒): ";
    }
    public String paymentInfo_TimeOutHandler(Long id)
    {
        return "线程池:  "+Thread.currentThread().getName()+"  8001系统繁忙或者运行报错，请稍后再试,id:  "+id+"\t"+"o(╥﹏╥)o";
    }

}
