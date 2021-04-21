package cn.bookmanager.controller;

import cn.bookmanager.entity.Payment;
import cn.bookmanager.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Payment
 * @author lengqie
 */

@RestController()
@RequestMapping("/")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PutMapping("/pay")
    public void addPayment(long id, String userId, String recordId, Double amount, HttpServletResponse response){
        Date date =new Date();

        if (paymentService.pay(userId, recordId, amount,date)) {
            response.setStatus(HttpStatus.OK.value());
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
    }

    @GetMapping("/pay")
    public List<Payment> getAllPayment(HttpServletResponse response){
        final List<Payment> allPayment = paymentService.getAllPayment();
        if (allPayment == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
        return allPayment;
    }

    @GetMapping("/pay/{id}")
    public Payment getPaymentById(@PathVariable long id, HttpServletResponse response){
        final Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
        return payment;
    }
}
