package br.com.api_order.use_case.payment;

import br.com.api_order.application.dtos.payment.PaymentDTO;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentType;
import br.com.api_order.domain.use_case.payment.MakeANewPayment;
import br.com.api_order.infra.gateways.internal.payments.ApiPayments;
import br.com.api_order.infra.gateways.internal.payments.dto.NewPaymentDTO;
import br.com.api_order.infra.gateways.internal.payments.dto.order.OrderDTO;
import br.com.api_order.infra.gateways.internal.payments.dto.order.OrderItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MakeANewPaymentImpl implements MakeANewPayment {

    final ApiPayments apiPayments;

    @Override
    public PaymentDTO execute(OrderDomain orderDomain, PaymentType provider) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderDomain.getId());
        orderDTO.setTotal(orderDomain.getTotal());
        orderDTO.setIdStore(orderDomain.getIdStore());

        List<OrderItemDTO> orderItemDTOS = orderDomain.getItems().stream()
                .map(orderItemDomain -> new OrderItemDTO(
                        orderItemDomain.getIdProduct(),
                        orderItemDomain.getProduct().getName(),
                        orderItemDomain.getProduct().getPrice(),
                        orderItemDomain.getIdOrder(),
                        orderItemDomain.getQuantity()
                ))
                .collect(Collectors.toList());

        orderDTO.setItems(orderItemDTOS);

        NewPaymentDTO newPaymentDTO = new NewPaymentDTO();
        newPaymentDTO.setOrderDTO(orderDTO);
        newPaymentDTO.setProvider(provider);
        return apiPayments.createPayment(newPaymentDTO);
    }
}
