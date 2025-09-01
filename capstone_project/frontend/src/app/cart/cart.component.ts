import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Cart, CartItem } from '../shared/models/order.model';
import { AuthService } from '../shared/services/auth.service';
import { CartService } from '../shared/services/cart.service';
import { CartCountService } from '../shared/services/cart-count.service';
import { CurrencyService } from '../shared/services/currency.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './cart.component.html',
  styles: [`
    .cart-item {
      background: linear-gradient(135deg, #f8f9fa 0%, #ffffff 100%);
      border: 1px solid #e9ecef;
      border-radius: 8px;
      transition: all 0.2s;
    }
    
    .cart-item:hover {
      background: linear-gradient(135deg, #e9ecef 0%, #f8f9fa 100%);
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(108, 117, 125, 0.1);
      border-color: #dee2e6;
    }
    
    .input-group {
      max-width: 150px;
    }
    
    .price-info {
      font-size: 0.9rem;
      color: #6c757d;
    }
    
    .total-price {
      font-size: 1.1rem;
      color: #495057;
    }
    
    .sticky-top {
      z-index: 1020;
    }
    
    .card {
      border: 1px solid #e9ecef;
      background: linear-gradient(135deg, #f8f9fa 0%, #ffffff 100%);
    }
    
    .card-header {
      background: linear-gradient(135deg, #e9ecef 0%, #f8f9fa 100%);
      border-bottom: 1px solid #dee2e6;
    }
  `]
})
export class CartComponent implements OnInit {
  cart: Cart | null = null;
  isLoading = true;

  constructor(
    private cartService: CartService,
    private cartCountService: CartCountService,
    private authService: AuthService,
    private currencyService: CurrencyService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    const user = this.authService.getCurrentUser();
    if (!user) {
      this.router.navigate(['/login']);
      return;
    }

    this.isLoading = true;
    this.cartService.getUserCart(user.id).subscribe({
      next: (cart) => {
        this.cart = cart;
        this.isLoading = false;
        // Update cart count
        this.cartCountService.updateCartCount(cart.items?.length || 0);
      },
      error: (error) => {
        console.error('Error loading cart:', error);
        this.toastr.error('Failed to load cart', 'Error');
        this.isLoading = false;
        this.cart = null;
      }
    });
  }

  updateQuantity(itemId: number, newQuantity: number): void {
    if (newQuantity < 1) return;

    this.cartService.updateCartItem(itemId, newQuantity).subscribe({
      next: () => {
        this.loadCart();
        this.toastr.success('Cart updated successfully', 'Success');
      },
      error: (error) => {
        console.error('Error updating cart:', error);
        this.toastr.error('Failed to update cart', 'Error');
      }
    });
  }

  removeItem(itemId: number): void {
    this.cartService.removeFromCart(itemId).subscribe({
      next: () => {
        this.loadCart();
        this.toastr.success('Item removed from cart', 'Success');
        // Decrement cart count
        this.cartCountService.decrementCartCount();
      },
      error: (error) => {
        console.error('Error removing item:', error);
        this.toastr.error('Failed to remove item', 'Error');
      }
    });
  }

  checkout(): void {
    const user = this.authService.getCurrentUser();
    if (!user) return;

    // For now, we'll just redirect to orders page
    // TODO: Implement proper checkout flow
    this.toastr.success('Order placed successfully!', 'Order Confirmed');
    this.router.navigate(['/orders']);
  }

  formatPrice(price: number): string {
    return this.currencyService.formatPrice(price);
  }
}
