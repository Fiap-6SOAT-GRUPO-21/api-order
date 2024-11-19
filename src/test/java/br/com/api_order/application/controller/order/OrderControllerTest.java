package br.com.api_order.application.controller.order;

import br.com.api_order.application.dtos.order.request.OrderRequest;
import br.com.api_order.application.dtos.order.response.OrderResponse;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.order.enums.StatusOrder;
import br.com.api_order.domain.use_case.order.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private FindOrderById findOrderById;

    @Mock
    private FindOrdersByCPF findOrdersByCPF;

    @Mock
    private FindAllOrders findAllOrders;

    @Mock
    private FindAllOrdersOrdered findAllOrdersOrdered;

    @Mock
    private CreateNewOrder createNewOrder;

    @Mock
    private UpdateOrder updateOrder;

    @Mock
    private UpdateOrderStatus updateOrderStatus;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderController orderController;

    private OrderDomain orderDomain;

    private OrderResponse orderResponse;

    private OrderRequest orderRequest;

    @BeforeEach
    void setUp() {
        orderDomain = new OrderDomain();
        orderDomain.setItems(Collections.emptyList());
        orderDomain.setStatus(StatusOrder.RECEIVED);
        orderDomain.setTotal(BigDecimal.valueOf(100.00));
        orderDomain.setIdCustomer(UUID.randomUUID());
        orderDomain.setIdStore(UUID.randomUUID());
        orderDomain.setIdPayment(UUID.randomUUID());
        orderDomain.setCreatedAt(LocalDateTime.now());
        orderDomain.setUpdatedAt(LocalDateTime.now());

        orderRequest = new OrderRequest();
        orderRequest.setIdStore(UUID.randomUUID());
        orderRequest.setItems(Collections.emptyList());

        orderDomain = new OrderDomain();
        orderDomain.setIdStore(orderRequest.getIdStore());
        orderDomain.setItems(Collections.emptyList());

        orderResponse = new OrderResponse();
    }

    @Test
    void testGetById_shouldReturnOKWithOrder_whenGivenValidOrderId() {
        final UUID orderId = UUID.fromString("cb5d3d3c-248d-455e-86f0-3887a5cb0120");

        when(findOrderById.execute(orderId)).thenReturn(orderDomain);
        when(modelMapper.map(orderDomain, OrderResponse.class)).thenReturn(orderResponse);

        ResponseEntity<OrderResponse> response = orderController.getById(orderId);

        assertEquals(ResponseEntity.ok(orderResponse), response);
    }

    @Test
    void testGetByCpf_shouldReturnOKWithOrder_whenGivenValidCpf() {
        final String cpf = "50264030850";
        when(findOrdersByCPF.execute(cpf)).thenReturn(List.of(orderDomain));
        when(modelMapper.map(orderDomain, OrderResponse.class)).thenReturn(orderResponse);

        ResponseEntity<List<OrderResponse>> response = orderController.getByCpf(cpf);

        assertEquals(response.getBody(), List.of(orderResponse));
    }

    @Test
    void testGetAll_shouldReturnAllOrders_whenThereAreOrders() {
        when(findAllOrders.execute()).thenReturn(List.of(orderDomain));
        when(modelMapper.map(orderDomain, OrderResponse.class)).thenReturn(orderResponse);

        ResponseEntity<List<OrderResponse>> response = orderController.getAll();

        assertEquals(response.getBody(), List.of(orderResponse));
    }

    @Test
    void testGetAll_shouldReturnEmpty_whenThereAreNotOrder() {
        when(findAllOrders.execute()).thenReturn(Collections.emptyList());

        ResponseEntity<List<OrderResponse>> response = orderController.getAll();

        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    void testGetByCpf_shouldReturnEmptyResponse_whenCpfDoesNotHaveOrders() {
        final String cpf = "50264030850";
        when(findOrdersByCPF.execute(cpf)).thenReturn(Collections.emptyList());

        ResponseEntity<List<OrderResponse>> response = orderController.getByCpf(cpf);

        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    void testGetAllOrdered_shouldReturnAllOrdersOrdered_whenOrderExists() {
        when(findAllOrdersOrdered.execute()).thenReturn(List.of(orderDomain));
        when(modelMapper.map(orderDomain, OrderResponse.class)).thenReturn(orderResponse);

        ResponseEntity<List<OrderResponse>> response = orderController.getAllOrdered();

        assertEquals(ResponseEntity.ok(List.of(orderResponse)), response);
    }

    @Test
    void testGetAllOrdered_shouldReturnEmpty_whenThereAreNotOrders() {
        when(findAllOrdersOrdered.execute()).thenReturn(Collections.emptyList());

        ResponseEntity<List<OrderResponse>> response = orderController.getAllOrdered();

        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    void testSave_shouldReturnCreated_whenOrderIsCreated() {
        when(createNewOrder.execute(orderDomain, null, null)).thenReturn(orderResponse);

        ResponseEntity<OrderResponse> response = orderController.save(orderRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderResponse, response.getBody());
    }

    @Test
    void testUpdate_shouldReturnOKWithUpdatedOrder_whenGivenValidOrderId() {
        final UUID orderId = UUID.fromString("0fc44065-be29-4610-bc36-b8cab14f9fc8");

        when(updateOrder.execute(orderId, null, orderDomain.getItems(), null)).thenReturn(orderDomain);
        when(modelMapper.map(orderDomain, OrderResponse.class)).thenReturn(orderResponse);

        ResponseEntity<OrderResponse> response = orderController.update(orderId, orderRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderResponse, response.getBody());
    }

    @Test
    void testUpdateStatus_shouldReturnOK_whenStatusIsUpdated() {
        final UUID orderId = UUID.fromString("9094f752-5481-4ba0-95fe-81162aef067f");
        StatusOrder status = StatusOrder.RECEIVED;

        ResponseEntity<Void> response = orderController.updateStatus(orderId, status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}