import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Cart, CartItem } from '../models/order.model';

export interface AddToCartRequest {
  userId: number;
  productId: number;
  productName: string;
  quantity: number;
  unitPrice: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  addToCart(request: AddToCartRequest): Observable<void> {
    return this.http.post<void>(`${environment.apiBaseUrl}/orders/cart/addProd`, request);
  }

  getUserCart(userId: number): Observable<Cart> {
    return this.http.get<Cart>(`${environment.apiBaseUrl}/orders/cart/${userId}`);
  }

  updateCartItem(itemId: number, quantity: number): Observable<void> {
    return this.http.put<void>(`${environment.apiBaseUrl}/orders/cart/update`, {
      cartItemId: itemId,
      quantity: quantity
    });
  }

  removeFromCart(itemId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiBaseUrl}/orders/cart/deleteProd/${itemId}`);
  }
}
