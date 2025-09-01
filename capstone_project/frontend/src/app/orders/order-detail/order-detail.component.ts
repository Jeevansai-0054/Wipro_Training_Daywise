import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Order, OrderStatus } from '../../shared/models/order.model';
import { AuthService } from '../../shared/services/auth.service';
import { CurrencyService } from '../../shared/services/currency.service';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-order-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="container">
      <div class="row mb-4">
        <div class="col">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a routerLink="/orders">Orders</a></li>
              <li class="breadcrumb-item active" aria-current="page">Order #{{ order?.id }}</li>
            </ol>
          </nav>
          <h2 class="mb-0">Order Details</h2>
          <p class="text-muted">Track your order status and details</p>
        </div>
        <div class="col-auto">
          <span class="badge bg-primary fs-6">₹ Indian Rupees</span>
        </div>
      </div>
      
      <div *ngIf="isLoading" class="text-center py-5">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
        <p class="mt-3 text-muted">Loading order details...</p>
      </div>

      <div *ngIf="!isLoading && !order" class="text-center py-5">
        <i class="bi bi-exclamation-triangle display-1 text-warning"></i>
        <h4 class="mt-3">Order Not Found</h4>
        <p class="text-muted">The order you're looking for doesn't exist.</p>
        <button class="btn btn-primary" routerLink="/orders">
          <i class="bi bi-arrow-left"></i> Back to Orders
        </button>
      </div>

      <div *ngIf="!isLoading && order" class="row">
        <div class="col-md-8">
          <div class="card mb-4">
            <div class="card-header">
              <div class="d-flex justify-content-between align-items-center">
                <h5 class="mb-0">Order #{{ order.id }}</h5>
                <span class="badge" [ngClass]="getStatusBadgeClass(order.status)">
                  {{ order.status }}
                </span>
              </div>
            </div>
            <div class="card-body">
              <div class="row mb-3">
                <div class="col-md-6">
                  <strong>Order Date:</strong>
                  <p class="text-muted">{{ order.createdAt | date:'medium' }}</p>
                </div>
                <div class="col-md-6">
                  <strong>Order Status:</strong>
                  <p class="text-muted">{{ order.status }}</p>
                </div>
              </div>
              
              <div class="order-items">
                <h6>Order Items</h6>
                <div class="item p-3 border rounded mb-2" *ngFor="let item of order.items">
                  <div class="row align-items-center">
                    <div class="col-md-6">
                      <h6 class="mb-1">{{ item.productName }}</h6>
                      <p class="text-muted mb-0">Quantity: {{ item.quantity }}</p>
                    </div>
                    <div class="col-md-3 text-center">
                      <span class="text-muted">Unit Price:</span>
                      <p class="mb-0">{{ formatPrice(item.productPrice) }}</p>
                    </div>
                    <div class="col-md-3 text-end">
                      <span class="text-muted">Total:</span>
                      <h6 class="mb-0 text-primary">{{ formatPrice(item.totalPrice) }}</h6>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          
          <div class="card">
            <div class="card-header">
              <h5 class="mb-0">Order Timeline</h5>
            </div>
            <div class="card-body">
              <div class="timeline">
                <div class="timeline-item" *ngFor="let status of getOrderTimeline()">
                  <div class="timeline-marker" [ngClass]="status.active ? 'active' : 'inactive'"></div>
                  <div class="timeline-content">
                    <h6 class="mb-1">{{ status.title }}</h6>
                    <p class="text-muted mb-0">{{ status.description }}</p>
                    <small class="text-muted">{{ status.date }}</small>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <div class="col-md-4">
          <div class="card sticky-top" style="top: 20px;">
            <div class="card-header">
              <h5 class="mb-0">Order Summary</h5>
            </div>
            <div class="card-body">
              <div class="d-flex justify-content-between mb-2">
                <span>Items:</span>
                <span>{{ order.items.length }}</span>
              </div>
              <div class="d-flex justify-content-between mb-2">
                <span>Subtotal:</span>
                <span>{{ formatPrice(order.totalAmount) }}</span>
              </div>
              <div class="d-flex justify-content-between mb-2">
                <span>Shipping:</span>
                <span class="text-success">Free</span>
              </div>
              <hr>
              <div class="d-flex justify-content-between mb-3">
                <strong>Total:</strong>
                <strong class="text-primary h5 mb-0">{{ formatPrice(order.totalAmount) }}</strong>
              </div>
              <button class="btn btn-primary w-100 mb-2" routerLink="/products">
                <i class="bi bi-shop"></i> Continue Shopping
              </button>
              <button class="btn btn-outline-secondary w-100" routerLink="/orders">
                <i class="bi bi-arrow-left"></i> Back to Orders
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .breadcrumb {
      background: transparent;
      padding: 0;
      margin-bottom: 1rem;
    }
    
    .breadcrumb-item a {
      color: #6c757d;
      text-decoration: none;
    }
    
    .breadcrumb-item a:hover {
      color: #0d6efd;
    }
    
    .order-items .item {
      background-color: #f8f9fa;
      transition: all 0.2s;
    }
    
    .order-items .item:hover {
      background-color: #e9ecef;
    }
    
    .timeline {
      position: relative;
      padding-left: 30px;
    }
    
    .timeline-item {
      position: relative;
      margin-bottom: 2rem;
    }
    
    .timeline-marker {
      position: absolute;
      left: -35px;
      top: 5px;
      width: 12px;
      height: 12px;
      border-radius: 50%;
      border: 2px solid #dee2e6;
    }
    
    .timeline-marker.active {
      background-color: #28a745;
      border-color: #28a745;
    }
    
    .timeline-marker.inactive {
      background-color: #dee2e6;
    }
    
    .timeline-content {
      padding-left: 20px;
    }
    
    .sticky-top {
      z-index: 1020;
    }
    
    .badge {
      font-size: 0.9rem;
      padding: 0.5em 1em;
    }
    
    .badge-pending {
      background-color: #ffc107;
      color: #000;
    }
    
    .badge-processing {
      background-color: #17a2b8;
      color: #fff;
    }
    
    .badge-shipped {
      background-color: #6f42c1;
      color: #fff;
    }
    
    .badge-delivered {
      background-color: #28a745;
      color: #fff;
    }
    
    .badge-cancelled {
      background-color: #dc3545;
      color: #fff;
    }
  `]
})
export class OrderDetailComponent implements OnInit {
  order: Order | null = null;
  isLoading = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private authService: AuthService,
    private currencyService: CurrencyService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const orderId = params['id'];
      if (orderId) {
        this.loadOrder(orderId);
      }
    });
  }

  loadOrder(orderId: number): void {
    this.isLoading = true;
    this.http.get<Order>(`${environment.apiBaseUrl}/orders/${orderId}`).subscribe({
      next: (order) => {
        this.order = order;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading order:', error);
        this.toastr.error('Failed to load order details', 'Error');
        this.isLoading = false;
      }
    });
  }

  getStatusBadgeClass(status: string): string {
    const statusClasses: { [key: string]: string } = {
      'PENDING': 'badge-pending',
      'PROCESSING': 'badge-processing',
      'SHIPPED': 'badge-shipped',
      'DELIVERED': 'badge-delivered',
      'CANCELLED': 'badge-cancelled'
    };
    
    return statusClasses[status] || 'bg-secondary';
  }

  getOrderTimeline(): any[] {
    if (!this.order) return [];
    
    const timeline = [
      {
        title: 'Order Placed',
        description: 'Your order has been placed successfully',
        date: this.order.createdAt,
        active: true
      }
    ];
    
    if (this.order.status !== OrderStatus.PENDING) {
      timeline.push({
        title: 'Processing',
        description: 'Your order is being processed',
        date: new Date(new Date(this.order.createdAt).getTime() + 24 * 60 * 60 * 1000).toISOString(),
        active: true
      });
    }
    
    if (this.order.status === OrderStatus.SHIPPED || this.order.status === OrderStatus.DELIVERED) {
      timeline.push({
        title: 'Shipped',
        description: 'Your order has been shipped',
        date: new Date(new Date(this.order.createdAt).getTime() + 48 * 60 * 60 * 1000).toISOString(),
        active: true
      });
    }
    
    if (this.order.status === OrderStatus.DELIVERED) {
      timeline.push({
        title: 'Delivered',
        description: 'Your order has been delivered',
        date: new Date(new Date(this.order.createdAt).getTime() + 72 * 60 * 60 * 1000).toISOString(),
        active: true
      });
    }
    
    return timeline;
  }

  formatPrice(price: number): string {
    return this.currencyService.formatPrice(price);
  }
}
