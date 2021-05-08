package cn.bookmanager.controller;

import cn.bookmanager.entity.Payment;
import cn.bookmanager.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@Tag(name = "PaymentController",description = "支付一些操作")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    /**
     * roles[user]支付 记录
     * @param recordId  Record.Id
     * @param amount    Payment.Amount
     * @param response  response
     */
    @Tag(name = "PaymentController")
    @Operation(summary = "支付 记录",description = "添加支付记录")
    @RequiresRoles({"user"})
    @PutMapping("/pay")
    public void addPayment(
            @Parameter (description = "记录ID") String recordId,
            @Parameter (description = "金额") Double amount, HttpServletResponse response){

        Date date =new Date();

        if (paymentService.pay(recordId, amount,date)) {
            response.setStatus(HttpStatus.OK.value());
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * roles[admin] 获取全部支付记录
     * @param response response
     * @return Payment s
     */
    @Tag(name = "PaymentController")
    @Operation(summary = "获取全部支付记录",description = "获取全部支付记录")
    @RequiresRoles({"admin"})
    @GetMapping("/pay")
    public List<Payment> getAllPayment(HttpServletResponse response){
        final List<Payment> allPayment = paymentService.getAllPayment();
        if (allPayment == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
        return allPayment;
    }

    /**
     * roles[admin] 支付（...）
     * @param id Payment.Id
     * @param response response
     * @return Payment
     */
    @Tag(name = "PaymentController")
    @Operation(summary = "支付（...）",description = "支付（...）")
    @RequiresRoles({"admin"})
    @GetMapping("/pay/{id}")
    public Payment getPaymentById(
            @Parameter(description = "支付记录ID") @PathVariable long id, HttpServletResponse response){
        final Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
        return payment;
    }
}
