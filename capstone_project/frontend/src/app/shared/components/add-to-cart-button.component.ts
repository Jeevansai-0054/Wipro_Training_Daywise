import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-to-cart-button',
  standalone: true,
  imports: [CommonModule],
  template: `
    <button 
      class="btn btn-primary w-100 me-2" 
      [disabled]="isLoading || disabled"
      (click)="onAddToCart()"
      [class.btn-loading]="isLoading">
      <span *ngIf="!isLoading">
        <i class="bi bi-cart-plus"></i> Add to Cart
      </span>
      <span *ngIf="isLoading">
        <div class="spinner-border spinner-border-sm me-2" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
        Adding...
      </span>
    </button>
  `,
  styles: [`
    .btn {
      background: linear-gradient(135deg, #007bff 0%, #0056b3 100%);
      border: none;
      transition: all 0.2s;
    }
    
    .btn:hover:not(:disabled) {
      background: linear-gradient(135deg, #0056b3 0%, #004085 100%);
      transform: translateY(-1px);
    }
    
    .btn-loading {
      opacity: 0.7;
      cursor: not-allowed;
    }
    
    .spinner-border-sm {
      width: 1rem;
      height: 1rem;
    }
  `]
})
export class AddToCartButtonComponent {
  @Input() isLoading: boolean = false;
  @Input() disabled: boolean = false;
  @Output() addToCart = new EventEmitter<void>();

  onAddToCart(): void {
    if (!this.isLoading && !this.disabled) {
      this.addToCart.emit();
    }
  }
}
