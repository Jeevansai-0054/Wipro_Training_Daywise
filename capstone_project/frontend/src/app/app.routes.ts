import { Routes } from '@angular/router';
import { authGuard } from './shared/guards/auth.guard';
import { adminGuard } from './shared/guards/admin.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/products', pathMatch: 'full' },
  { path: 'login', loadComponent: () => import('./auth/login/login.component').then(m => m.LoginComponent) },
  { path: 'register', loadComponent: () => import('./auth/register/register.component').then(m => m.RegisterComponent) },
  { path: 'products', loadComponent: () => import('./products/product-list/product-list.component').then(m => m.ProductListComponent) },
  { path: 'products/:id', loadComponent: () => import('./products/product-detail/product-detail.component').then(m => m.ProductDetailComponent) },
  { path: 'cart', loadComponent: () => import('./cart/cart.component').then(m => m.CartComponent), canActivate: [authGuard] },
  { path: 'orders', loadComponent: () => import('./orders/order-list/order-list.component').then(m => m.OrderListComponent), canActivate: [authGuard] },
  { path: 'orders/:id', loadComponent: () => import('./orders/order-detail/order-detail.component').then(m => m.OrderDetailComponent), canActivate: [authGuard] },
  { path: 'profile', loadComponent: () => import('./auth/profile/profile.component').then(m => m.ProfileComponent), canActivate: [authGuard] },
  { 
    path: 'admin', 
    canActivate: [authGuard, adminGuard],
    children: [
      { path: 'products', loadComponent: () => import('./admin/product-admin/product-admin.component').then(m => m.ProductAdminComponent) },
      { path: 'users', loadComponent: () => import('./admin/users-list/users-list.component').then(m => m.UsersListComponent) },
      { path: 'dashboard', loadComponent: () => import('./admin/dashboard/dashboard.component').then(m => m.DashboardComponent) }
    ]
  },
  { path: '**', redirectTo: '/products' }
];
