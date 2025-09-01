import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Product } from '../../shared/models/product.model';
import { AuthService } from '../../shared/services/auth.service';
import { CartService } from '../../shared/services/cart.service';
import { CurrencyService } from '../../shared/services/currency.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="container mt-4">
      <div *ngIf="isLoading" class="text-center py-5">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
        <p class="mt-3 text-muted">Loading product details...</p>
      </div>

      <div *ngIf="!isLoading && !product" class="text-center py-5">
        <i class="bi bi-exclamation-triangle display-1 text-warning"></i>
        <h4 class="mt-3">Product Not Found</h4>
        <p class="text-muted">The product you're looking for doesn't exist.</p>
        <button class="btn btn-primary" routerLink="/products">
          <i class="bi bi-arrow-left"></i> Back to Products
        </button>
      </div>

      <div *ngIf="!isLoading && product" class="row">
        <div class="col-md-6">
          <div class="product-image-container">
            <img [src]="getProductImage(product)" 
                 [alt]="product.name" 
                 class="product-image"
                 (error)="onImageError($event, product)"
                 loading="lazy">
            <div class="image-overlay">
              <button class="btn btn-primary" (click)="addToCart(product)">
                <i class="bi bi-cart-plus"></i> Add to Cart
              </button>
            </div>
          </div>
        </div>
        
        <div class="col-md-6">
          <div class="product-info">
            <nav aria-label="breadcrumb">
              <ol class="breadcrumb">
                <li class="breadcrumb-item"><a routerLink="/products">Products</a></li>
                <li class="breadcrumb-item"><a routerLink="/products">{{ product.category }}</a></li>
                <li class="breadcrumb-item active" aria-current="page">{{ product.name }}</li>
              </ol>
            </nav>
            
            <h1 class="product-title">{{ product.name }}</h1>
            <p class="product-description">{{ product.description }}</p>
            
            <div class="product-meta mb-4">
              <span class="badge bg-primary me-2">{{ product.category }}</span>
              <span class="badge bg-success" *ngIf="product.active">In Stock</span>
              <span class="badge bg-danger" *ngIf="!product.active">Out of Stock</span>
            </div>
            
            <div class="price-section mb-4">
              <div class="currency-toggle mb-3">
                <div class="btn-group" role="group">
                  <button type="button" class="btn btn-outline-primary" [class.active]="currency === 'INR'" (click)="setCurrency('INR')">₹ INR</button>
                  <button type="button" class="btn btn-outline-primary" [class.active]="currency === 'USD'" (click)="setCurrency('USD')">$ USD</button>
                </div>
              </div>
              
              <div class="main-price">
                                 <span class="h2 text-primary" *ngIf="currency === 'USD'">{{ formatPrice(product.price) }}</span>
                <span class="h2 text-primary" *ngIf="currency === 'INR'">{{ formatInrPrice(product.price) }}</span>
              </div>
              
              <div class="secondary-price">
                                 <small class="text-muted" *ngIf="currency === 'INR'">{{ formatPrice(product.price) }}</small>
                                 <small class="text-muted" *ngIf="currency === 'USD'">{{ formatPrice(product.price) }}</small>
              </div>
            </div>
            
            <div class="action-buttons">
              <div class="row g-3">
                <div class="col-md-4">
                  <div class="input-group">
                    <button class="btn btn-outline-secondary" (click)="decreaseQuantity()" [disabled]="quantity <= 1">
                      <i class="bi bi-dash"></i>
                    </button>
                    <input type="number" class="form-control text-center" [(ngModel)]="quantity" min="1" max="99">
                    <button class="btn btn-outline-secondary" (click)="increaseQuantity()">
                      <i class="bi bi-plus"></i>
                    </button>
                  </div>
                </div>
                <div class="col-md-8">
                  <button class="btn btn-primary w-100" (click)="addToCart(product)" [disabled]="!product.active">
                    <i class="bi bi-cart-plus"></i> Add to Cart
                  </button>
                </div>
              </div>
            </div>
            

          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .product-image-container {
      position: relative;
      border-radius: 12px;
      overflow: hidden;
      box-shadow: 0 4px 20px rgba(0,0,0,0.1);
    }
    
    .product-image {
      width: 100%;
      height: 400px;
      object-fit: cover;
      transition: transform 0.3s;
    }
    
    .product-image-container:hover .product-image {
      transform: scale(1.05);
    }
    
    .image-overlay {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: rgba(0,0,0,0.7);
      display: flex;
      align-items: center;
      justify-content: center;
      opacity: 0;
      transition: opacity 0.3s;
    }
    
    .product-image-container:hover .image-overlay {
      opacity: 1;
    }
    
    .product-info {
      padding: 20px;
    }
    
    .product-title {
      font-size: 2.5rem;
      font-weight: 700;
      margin-bottom: 1rem;
      color: #2c3e50;
    }
    
    .product-description {
      font-size: 1.1rem;
      line-height: 1.6;
      color: #6c757d;
      margin-bottom: 1.5rem;
    }
    
    .product-meta .badge {
      font-size: 0.9rem;
      padding: 0.5em 1em;
    }
    
    .price-section {
      background: #f8f9fa;
      padding: 1.5rem;
      border-radius: 8px;
    }
    
    .main-price {
      font-weight: 700;
      margin-bottom: 0.5rem;
    }
    
    .secondary-price {
      font-size: 0.9rem;
    }
    
    .action-buttons {
      background: #f8f9fa;
      padding: 1.5rem;
      border-radius: 8px;
    }
    
    .input-group {
      max-width: 150px;
    }
    
    .breadcrumb {
      background: transparent;
      padding: 0;
      margin-bottom: 1.5rem;
    }
    
    .breadcrumb-item a {
      color: #6c757d;
      text-decoration: none;
    }
    
    .breadcrumb-item a:hover {
      color: #0d6efd;
    }
    
    .btn-group .btn.active {
      background-color: #0d6efd;
      border-color: #0d6efd;
      color: white;
    }
    

  `]
})
export class ProductDetailComponent implements OnInit {
  product: Product | null = null;
  isLoading = true;
  quantity = 1;
  currency: 'USD' | 'INR' = 'INR';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private authService: AuthService,
    private cartService: CartService,
    private currencyService: CurrencyService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const productId = params['id'];
      if (productId) {
        this.loadProduct(productId);
      }
    });
  }

  loadProduct(productId: number): void {
    this.isLoading = true;
    this.http.get<Product>(`${environment.apiBaseUrl}/products/${productId}`).subscribe({
      next: (product) => {
        this.product = product;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading product:', error);
        this.toastr.error('Failed to load product details', 'Error');
        this.isLoading = false;
      }
    });
  }

  setCurrency(currency: 'USD' | 'INR'): void {
    this.currency = currency;
  }

  increaseQuantity(): void {
    if (this.quantity < 99) {
      this.quantity++;
    }
  }

  decreaseQuantity(): void {
    if (this.quantity > 1) {
      this.quantity--;
    }
  }

  addToCart(product: Product): void {
    const user = this.authService.getCurrentUser();
    if (!user) {
      this.toastr.error('Please login to add items to cart', 'Authentication Required');
      return;
    }

    if (!product.active) {
      this.toastr.error('This product is currently out of stock', 'Error');
      return;
    }

    const request = {
      userId: user.id,
      productId: product.id,
      productName: product.name,
      quantity: this.quantity,
      unitPrice: product.price
    };

    this.cartService.addToCart(request).subscribe({
      next: () => {
        this.toastr.success(`${this.quantity}x ${product.name} added to cart!`, 'Success');
        this.quantity = 1;
      },
      error: (error) => {
        console.error('Error adding to cart:', error);
        this.toastr.error('Failed to add item to cart', 'Error');
      }
    });
  }

  getProductImage(product: Product): string {
    if (product.imageUrl && product.imageUrl.trim() !== '') {
      return product.imageUrl;
    }
    return this.getPlaceholderImage(product.category);
  }

  getPlaceholderImage(category: string | undefined): string {
    if (!category) {
      return 'https://images.unsplash.com/photo-1441986300917-64674bd600d8?w=600&h=400&fit=crop';
    }
    
    const categoryImages: { [key: string]: string } = {
      'Electronics': 'https://images.unsplash.com/photo-1498049794561-7780e7231661?w=600&h=400&fit=crop',
      'Clothing': 'https://images.unsplash.com/photo-1441986300917-64674bd600d8?w=600&h=400&fit=crop',
      'Books': 'https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=600&h=400&fit=crop',
      'Home': 'https://images.unsplash.com/photo-1484101403633-562f891dc89a?w=600&h=400&fit=crop',
      'Sports': 'https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=600&h=400&fit=crop'
    };
    
    return categoryImages[category] || 'https://images.unsplash.com/photo-1441986300917-64674bd600d8?w=600&h=400&fit=crop';
  }

  onImageError(event: any, product: Product): void {
    event.target.src = this.getPlaceholderImage(product.category);
  }

  formatPrice(price: number): string {
    return this.currencyService.formatPrice(price);
  }

  formatInrPrice(price: number): string {
    return this.currencyService.formatInr(this.currencyService.usdToInr(price));
  }
}
