package br.com.api_order.domain.use_case.order;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.application.dtos.order.response.OrderResponse;
import br.com.api_order.application.dtos.payment.PaymentDTO;
import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.application.dtos.store.StoreDTO;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentStatus;
import br.com.api_order.domain.entity.payment.enums.PaymentType;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.domain.use_case.customer.FindCustomerByCPF;
import br.com.api_order.domain.use_case.order.item.CreateNewOrderItem;
import br.com.api_order.domain.use_case.payment.MakeANewPayment;
import br.com.api_order.domain.use_case.product.FindProductById;
import br.com.api_order.domain.use_case.product.FindProductByIdAndIdStore;
import br.com.api_order.domain.use_case.store.FindStoreById;
import br.com.api_order.use_case.order.CreateNewOrderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CreateNewOrderTest {

    @Mock
    private OrderPersistence orderPersistence;

    @Mock
    private CreateNewOrderItem createNewOrderItem;

    @Mock
    private FindStoreById findStoreById;

    @Mock
    private FindProductById findProductById;

    @Mock
    private FindProductByIdAndIdStore findProductByIdAndIdStore;

    @Mock
    private MakeANewPayment makeANewPayment;

    @Mock
    private FindCustomerByCPF findCustomerByCPF;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CreateNewOrderImpl createNewOrder;

    @Test
    void execute_shouldCreateOrderSuccessfully() {
        // Arrange
        OrderItemDomain orderItemDomain = new OrderItemDomain();
        orderItemDomain.setIdOrder(UUID.randomUUID());
        orderItemDomain.setQuantity(2);

        OrderDomain orderDomain = Mockito.mock(OrderDomain.class);
        List<OrderItemDomain> mockItems = List.of(orderItemDomain);

        String cpf = "12345678900";
        PaymentType paymentType = PaymentType.MERCADO_PAGO;

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(UUID.randomUUID());

        OrderDomain savedOrderDomain = new OrderDomain();
        savedOrderDomain.setId(UUID.randomUUID());

        orderItemDomain.setId(UUID.randomUUID());

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(UUID.randomUUID());
        paymentDTO.setQrCode("sampleQrCode");
        paymentDTO.setType(paymentType);
        paymentDTO.setStatus(PaymentStatus.PENDING);

        OrderResponse expectedResponse = new OrderResponse();
        expectedResponse.setQrCode(paymentDTO.getQrCode());
        expectedResponse.setPaymentType(paymentType);
        expectedResponse.setPaymentStatus(paymentDTO.getStatus());
        expectedResponse.setIdCustomer(customerDTO.getId());

        // Mockando os métodos
        when(orderDomain.getItems()).thenReturn(mockItems);
        doNothing().when(orderDomain).calculateTotal(findProductById);
        doNothing().when(orderDomain).validatedStore(findStoreById);
        doNothing().when(orderDomain).validatedQuantityItems(mockItems);
        doNothing().when(orderDomain).validatedItemOrException(findProductByIdAndIdStore);

        when(findCustomerByCPF.execute(cpf)).thenReturn(customerDTO);

        when(orderPersistence.save(any(OrderDomain.class))).thenReturn(savedOrderDomain);
        when(createNewOrderItem.execute(any(OrderItemDomain.class))).thenReturn(orderItemDomain);

        ProductDTO productDto = new ProductDTO();
        productDto.setId(UUID.randomUUID());
        productDto.setName("sample product");
        productDto.setPrice(BigDecimal.valueOf(10.0));

        StoreDTO storeDto = new StoreDTO();
        storeDto.setId(UUID.randomUUID());
        storeDto.setActive(true);

        when(findStoreById.execute(any(UUID.class))).thenReturn(storeDto);

        when(findProductById.execute(any(UUID.class))).thenReturn(productDto);

        when(makeANewPayment.execute(any(OrderDomain.class), eq(paymentType))).thenReturn(paymentDTO);

        // Corrigindo o mock do ModelMapper
        when(modelMapper.map(any(OrderDomain.class), eq(OrderResponse.class)))
                .thenReturn(expectedResponse);

        // Act
        OrderResponse response = createNewOrder.execute(orderDomain, cpf, paymentType);

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals("sampleQrCode", response.getQrCode());
        Assertions.assertEquals(paymentType, response.getPaymentType());
        Assertions.assertEquals(PaymentStatus.PENDING, response.getPaymentStatus());

        // Verify interactions
        Mockito.verify(orderPersistence, Mockito.times(2)).save(any(OrderDomain.class));
        Mockito.verify(createNewOrderItem, Mockito.times(1)).execute(any(OrderItemDomain.class));
        Mockito.verify(makeANewPayment, Mockito.times(1)).execute(any(OrderDomain.class), eq(paymentType));
        Mockito.verify(modelMapper, Mockito.times(1)).map(any(OrderDomain.class), eq(OrderResponse.class));
    }

}