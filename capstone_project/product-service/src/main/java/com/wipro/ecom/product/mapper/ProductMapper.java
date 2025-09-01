package com.wipro.ecom.product.mapper;

import com.wipro.ecom.product.dto.ProductCreateDto;
import com.wipro.ecom.product.dto.ProductUpdateDto;
import com.wipro.ecom.product.dto.ProductView;
import com.wipro.ecom.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    
    ProductView toProductView(Product product);
    
    Product toProduct(ProductCreateDto productCreateDto);
    
    void updateProductFromDto(ProductUpdateDto productUpdateDto, @MappingTarget Product product);
}


