import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Order, OrderStatus } from '../../shared/models/order.model';
import { AuthService } from '../../shared/services/auth.service';
import { CurrencyService } from '../../shared/services/currency.service';

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './order-list.component.html',
  styles: [`
    .order-item {
      background: linear-gradient(135deg, #f8f9fa 0%, #ffffff 100%);
      border: 1px solid #e9ecef;
      border-radius: 8px;
      transition: all 0.2s;
    }
    
    .order-item:hover {
      background: linear-gradient(135deg, #e9ecef 0%, #f8f9fa 100%);
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(108, 117, 125, 0.1);
      border-color: #dee2e6;
    }
    
    .order-items {
      font-size: 0.9rem;
      color: #6c757d;
    }
    
    .order-total {
      text-align: right;
    }
    
    .sticky-top {
      z-index: 1020;
    }
    
    .badge {
      font-size: 0.75rem;
      padding: 0.5em 0.75em;
    }
    
    .badge-pending {
      background-color: #fff3cd;
      color: #856404;
      border: 1px solid #ffeaa7;
    }
    
    .badge-processing {
      background-color: #d1ecf1;
      color: #0c5460;
      border: 1px solid #bee5eb;
    }
    
    .badge-shipped {
      background-color: #e2d9f3;
      color: #6f42c1;
      border: 1px solid #d4c4e7;
    }
    
    .badge-delivered {
      background-color: #d4edda;
      color: #155724;
      border: 1px solid #c3e6cb;
    }
  `]
})
export class OrderListComponent implements OnInit {
  orders: Order[] = [];
  isLoading = true;

  constructor(
    private authService: AuthService,
    private currencyService: CurrencyService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {
    const user = this.authService.getCurrentUser();
    if (!user) {
      this.router.navigate(['/login']);
      return;
    }

    this.isLoading = true;
    // TODO: Implement order service to fetch user orders
    // For now, we'll use mock data
    setTimeout(() => {
      this.orders = [
        {
          id: 1,
          userId: user.id,
          items: [
            { id: 1, productId: 1, productName: 'Laptop', quantity: 1, productPrice: 50000, totalPrice: 50000 }
          ],
          totalAmount: 50000,
          status: OrderStatus.DELIVERED,
          createdAt: new Date('2024-01-15').toISOString(),
          updatedAt: new Date('2024-01-20').toISOString()
        },
        {
          id: 2,
          userId: user.id,
          items: [
            { id: 2, productId: 2, productName: 'Smartphone', quantity: 1, productPrice: 25000, totalPrice: 25000 }
          ],
          totalAmount: 25000,
          status: OrderStatus.PROCESSING,
          createdAt: new Date('2024-01-25').toISOString(),
          updatedAt: new Date('2024-01-25').toISOString()
        }
      ];
        this.isLoading = false;
    }, 1000);
  }

  viewOrderDetails(orderId: number): void {
    this.router.navigate(['/orders', orderId]);
  }

  getStatusBadgeClass(status: string): string {
    switch (status) {
      case 'PENDING':
        return 'badge-pending';
      case 'PROCESSING':
        return 'badge-processing';
      case 'SHIPPED':
        return 'badge-shipped';
      case 'DELIVERED':
        return 'badge-delivered';
      default:
        return 'bg-secondary';
    }
  }

  getTotalSpent(): number {
    return this.orders.reduce((total, order) => total + order.totalAmount, 0);
  }

  getOrdersByStatus(status: string): Order[] {
    return this.orders.filter(order => order.status === status);
  }

  formatPrice(price: number): string {
    return this.currencyService.formatPrice(price);
  }

  getProductNames(items: any[]): string {
    return items.map(item => item.productName).join(', ');
  }
}
