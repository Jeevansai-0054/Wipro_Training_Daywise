import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Product } from '../../shared/models/product.model';
import { AuthService } from '../../shared/services/auth.service';
import { CartService } from '../../shared/services/cart.service';
import { ProductService } from '../../shared/services/product.service';
import { CartCountService } from '../../shared/services/cart-count.service';
import { ErrorHandlerService } from '../../shared/services/error-handler.service';
import { LoadingSpinnerComponent } from '../../shared/components/loading-spinner.component';
import { AddToCartButtonComponent } from '../../shared/components/add-to-cart-button.component';
import { ProductImageComponent } from '../../shared/components/product-image.component';
import { CurrencyService } from '../../shared/services/currency.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, RouterModule, LoadingSpinnerComponent, AddToCartButtonComponent, ProductImageComponent],
  templateUrl: './product-list.component.html',
  styles: [`
    .product-card {
      transition: transform 0.2s, box-shadow 0.2s;
      border: 1px solid #e9ecef;
      border-radius: 12px;
      overflow: hidden;
      background: linear-gradient(135deg, #f8f9fa 0%, #ffffff 100%);
    }
    
    .product-card:hover {
      transform: translateY(-5px);
      box-shadow: 0 8px 25px rgba(108, 117, 125, 0.15) !important;
      border-color: #dee2e6;
    }
    
    .product-image-container {
      position: relative;
      overflow: hidden;
      height: 200px;
      background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
    }
    
    .product-card:hover app-product-image .product-image {
      transform: scale(1.05);
    }
    
    .product-overlay {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: rgba(108, 117, 125, 0.8);
      display: flex;
      align-items: center;
      justify-content: center;
      opacity: 0;
      transition: opacity 0.3s;
    }
    
    .product-card:hover .product-overlay {
      opacity: 1;
    }
    
    .product-title {
      font-size: 1.1rem;
      font-weight: 600;
      line-height: 1.3;
      margin-bottom: 0.5rem;
      color: #495057;
    }
    
    .product-description {
      font-size: 0.9rem;
      color: #6c757d;
      line-height: 1.4;
      margin-bottom: 1rem;
    }
    
    .price-container {
      text-align: left;
    }
    
    .badge {
      font-size: 0.75rem;
      padding: 0.5em 0.75em;
      background-color: #e9ecef !important;
      color: #6c757d !important;
    }
    
    .card-img-top {
      border-top-left-radius: 12px;
      border-top-right-radius: 12px;
    }
    
    .btn-primary {
      background: linear-gradient(135deg, #007bff 0%, #0056b3 100%);
      border: none;
    }
    
    .btn-primary:hover {
      background: linear-gradient(135deg, #0056b3 0%, #004085 100%);
    }
    
    .btn-outline-secondary {
      border-color: #dee2e6;
      color: #6c757d;
    }
    
    .btn-outline-secondary:hover {
      background-color: #e9ecef;
      border-color: #adb5bd;
      color: #495057;
    }
  `]
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  isLoading = true;
  addingToCart: { [productId: number]: boolean } = {};

  constructor(
    private authService: AuthService,
    private cartService: CartService,
    private productService: ProductService,
    private cartCountService: CartCountService,
    private errorHandler: ErrorHandlerService,
    private currencyService: CurrencyService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.isLoading = true;
    
    this.productService.getAllProducts().subscribe({
      next: (products) => {
        this.products = products;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorHandler.handleError(error, 'Loading Products');
        this.isLoading = false;
        
        // Fallback to mock data if API fails
        this.loadMockProducts();
      }
    });
  }

  private loadMockProducts(): void {
    this.products = [
      {
        id: 1,
        name: 'Laptop',
        description: 'High-performance laptop for work and gaming',
        price: 50000,
        quantity: 10,
        sku: 'LAP001',
        category: 'Electronics',
        imageUrl: 'https://via.placeholder.com/400x300/007bff/ffffff?text=Laptop',
        active: true,
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString()
      },
      {
        id: 2,
        name: 'Smartphone',
        description: 'Latest smartphone with advanced features',
        price: 25000,
        quantity: 15,
        sku: 'PHN001',
        category: 'Electronics',
        imageUrl: 'https://via.placeholder.com/400x300/28a745/ffffff?text=Smartphone',
        active: true,
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString()
      },
      {
        id: 3,
        name: 'Headphones',
        description: 'Wireless noise-cancelling headphones',
        price: 5000,
        quantity: 20,
        sku: 'HP001',
        category: 'Electronics',
        imageUrl: 'https://via.placeholder.com/400x300/dc3545/ffffff?text=Headphones',
        active: true,
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString()
      },
      {
        id: 4,
        name: 'T-Shirt',
        description: 'Comfortable cotton t-shirt',
        price: 500,
        quantity: 50,
        sku: 'TSH001',
        category: 'Clothing',
        imageUrl: 'https://via.placeholder.com/400x300/ffc107/000000?text=T-Shirt',
        active: true,
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString()
      },
      {
        id: 5,
        name: 'Coffee Mug',
        description: 'Ceramic coffee mug with elegant design',
        price: 300,
        quantity: 100,
        sku: 'MUG001',
        category: 'Home & Kitchen',
        imageUrl: 'https://via.placeholder.com/400x300/6f42c1/ffffff?text=Coffee+Mug',
        active: true,
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString()
      },
      {
        id: 6,
        name: 'Backpack',
        description: 'Durable backpack for daily use',
        price: 1200,
        quantity: 25,
        sku: 'BAG001',
        category: 'Fashion',
        imageUrl: 'https://via.placeholder.com/400x300/17a2b8/ffffff?text=Backpack',
        active: true,
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString()
      },
      {
        id: 7,
        name: 'Wireless Mouse',
        description: 'Ergonomic wireless mouse for productivity',
        price: 800,
        quantity: 30,
        sku: 'MOU001',
        category: 'Electronics',
        imageUrl: 'https://via.placeholder.com/400x300/fd7e14/ffffff?text=Wireless+Mouse',
        active: true,
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString()
      },
      {
        id: 8,
        name: 'Jeans',
        description: 'Comfortable denim jeans for everyday wear',
        price: 800,
        quantity: 40,
        sku: 'JEA001',
        category: 'Clothing',
        imageUrl: 'https://via.placeholder.com/400x300/20c997/ffffff?text=Jeans',
        active: true,
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString()
      }
    ];
  }

  addToCart(product: Product): void {
    const user = this.authService.getCurrentUser();
    if (!user) {
      this.errorHandler.handleWarning('Please login to add items to cart', 'Login Required');
      this.router.navigate(['/login']);
      return;
    }

    // Check if product is in stock
    if (product.quantity <= 0) {
      this.errorHandler.handleError({ status: 400, error: { message: 'Product is out of stock' } }, 'Add to Cart');
      return;
    }

    // Set loading state for this product
    this.addingToCart[product.id] = true;

    this.cartService.addToCart({
      userId: user.id,
      productId: product.id,
      productName: product.name,
      quantity: 1,
      unitPrice: product.price
    }).subscribe({
      next: () => {
        this.errorHandler.handleSuccess(`${product.name} added to cart successfully!`);
        // Update cart count
        this.cartCountService.incrementCartCount();
        this.addingToCart[product.id] = false;
      },
      error: (error) => {
        this.errorHandler.handleError(error, 'Add to Cart');
        this.addingToCart[product.id] = false;
      }
    });
  }

  isAddingToCart(productId: number): boolean {
    return this.addingToCart[productId] || false;
  }

  onImageError(event: any): void {
    // Set a fallback image if the original fails to load
    event.target.src = 'https://picsum.photos/400/300?random=999';
  }



  viewDetails(productId: number): void {
    this.router.navigate(['/products', productId]);
  }

  formatPrice(price: number): string {
    return this.currencyService.formatPrice(price);
  }
}
