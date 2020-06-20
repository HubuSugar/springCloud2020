package edu.hubu.springcloud.service;

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
    public String paymentInfo_TIMEOUT(Long id) {

        int timeNum = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK id :" + id + "\t" + "耗时" + timeNum+ "秒钟";
    }
}
