import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { CartService } from './cart.service';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CartCountService {
  private cartCountSubject = new BehaviorSubject<number>(0);
  public cartCount$ = this.cartCountSubject.asObservable();

  constructor(
    private cartService: CartService,
    private authService: AuthService
  ) {
    this.loadCartCount();
  }

  loadCartCount(): void {
    const user = this.authService.getCurrentUser();
    if (user) {
      this.cartService.getUserCart(user.id).subscribe({
        next: (cart) => {
          this.updateCartCount(cart.items?.length || 0);
        },
        error: (error) => {
          console.error('Error loading cart count:', error);
          this.updateCartCount(0);
        }
      });
    } else {
      this.updateCartCount(0);
    }
  }

  updateCartCount(count: number): void {
    this.cartCountSubject.next(count);
  }

  incrementCartCount(): void {
    const currentCount = this.cartCountSubject.value;
    this.updateCartCount(currentCount + 1);
  }

  decrementCartCount(): void {
    const currentCount = this.cartCountSubject.value;
    this.updateCartCount(Math.max(0, currentCount - 1));
  }

  resetCartCount(): void {
    this.updateCartCount(0);
  }

  getCurrentCount(): number {
    return this.cartCountSubject.value;
  }
}






