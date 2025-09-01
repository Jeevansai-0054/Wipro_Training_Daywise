import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { CartCountService } from '../services/cart-count.service';
import { User, MenuItem } from '../models/user.model';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <nav class="navbar navbar-expand-lg navbar-light">
      <div class="container">
        <a class="navbar-brand" routerLink="/">Instamart</a>
        
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <a class="nav-link" routerLink="/products" routerLinkActive="active">Products</a>
            </li>
            
            <ng-container *ngIf="currentUser$ | async as user">
              <li class="nav-item" *ngIf="user.roles.includes('CUSTOMER')">
                <a class="nav-link position-relative" routerLink="/cart" routerLinkActive="active">
                  Cart
                  <span *ngIf="(cartCount$ | async) && (cartCount$ | async)! > 0" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                    {{ cartCount$ | async }}
                  </span>
                </a>
              </li>
              <li class="nav-item" *ngIf="user.roles.includes('CUSTOMER')">
                <a class="nav-link" routerLink="/orders" routerLinkActive="active">My Orders</a>
              </li>
              
              <li class="nav-item dropdown" *ngIf="user.roles.includes('ADMIN')">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                  Admin
                </a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" routerLink="/admin/dashboard">Dashboard</a></li>
                  <li><a class="dropdown-item" routerLink="/admin/products">Manage Products</a></li>
                  <li><a class="dropdown-item" routerLink="/admin/users">Manage Users</a></li>
                </ul>
              </li>
            </ng-container>
          </ul>
          
          <ul class="navbar-nav">
            <ng-container *ngIf="currentUser$ | async as user; else authButtons">
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                  {{ user.fullName }}
                </a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" routerLink="/profile">Profile</a></li>
                  <li><hr class="dropdown-divider"></li>
                  <li><a class="dropdown-item" href="#" (click)="logout()">Logout</a></li>
                </ul>
              </li>
            </ng-container>
            
            <ng-template #authButtons>
              <li class="nav-item">
                <a class="nav-link" routerLink="/login">Login</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" routerLink="/register">Register</a>
              </li>
            </ng-template>
          </ul>
        </div>
      </div>
    </nav>
  `,
  styles: [`
    .navbar {
      background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%) !important;
      border-bottom: 1px solid #dee2e6;
    }
    
    .navbar-brand {
      font-weight: bold;
      color: #495057 !important;
      font-size: 1.5rem;
    }
    
    .nav-link {
      color: #6c757d !important;
      font-weight: 500;
    }
    
    .nav-link:hover {
      color: #495057 !important;
    }
    
    .nav-link.active {
      color: #007bff !important;
      background-color: rgba(0, 123, 255, 0.1);
      border-radius: 4px;
    }
    
    .dropdown-menu {
      min-width: 200px;
      background-color: #f8f9fa;
      border: 1px solid #dee2e6;
    }
    
    .dropdown-item {
      color: #6c757d;
    }
    
    .dropdown-item:hover {
      background-color: #e9ecef;
      color: #495057;
    }
    
    .badge {
      background-color: #dc3545 !important;
    }
  `]
})
export class NavbarComponent implements OnInit {
  currentUser$ = this.authService.currentUser$;
  cartCount$ = this.cartCountService.cartCount$;

  constructor(
    private authService: AuthService,
    private cartCountService: CartCountService
  ) {}

  ngOnInit(): void {}

  logout(): void {
    this.authService.logout();
  }
}
