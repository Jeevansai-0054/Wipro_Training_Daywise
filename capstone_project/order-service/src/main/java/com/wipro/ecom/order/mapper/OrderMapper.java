package com.wipro.ecom.order.mapper;

import com.wipro.ecom.order.dto.CartView;
import com.wipro.ecom.order.dto.OrderView;
import com.wipro.ecom.order.entity.Cart;
import com.wipro.ecom.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    OrderView toOrderView(Order order);
    
    CartView toCartView(Cart cart);
}


