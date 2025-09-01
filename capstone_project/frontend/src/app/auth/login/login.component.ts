import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../shared/services/auth.service';
import { AuthRequest } from '../../shared/models/user.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  template: `
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-4">
        <div class="card shadow">
          <div class="card-header bg-primary text-white text-center">
            <h4 class="mb-0">Login</h4>
          </div>
          <div class="card-body">
            <form [formGroup]="loginForm" (ngSubmit)="onSubmit()">
              <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input
                  type="text"
                  class="form-control"
                  id="username"
                  formControlName="username"
                  [class.is-invalid]="isFieldInvalid('username')"
                >
                <div class="invalid-feedback" *ngIf="isFieldInvalid('username')">
                  Username is required.
                </div>
              </div>
              
              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  formControlName="password"
                  [class.is-invalid]="isFieldInvalid('password')"
                >
                <div class="invalid-feedback" *ngIf="isFieldInvalid('password')">
                  Password is required.
                </div>
              </div>
              
              <div class="d-grid">
                <button type="submit" class="btn btn-primary" [disabled]="loginForm.invalid || isLoading">
                  <span *ngIf="isLoading" class="spinner-border spinner-border-sm me-2"></span>
                  {{ isLoading ? 'Logging in...' : 'Login' }}
                </button>
              </div>
            </form>
            
            <div class="text-center mt-3">
              <p class="mb-0">Don't have an account? <a routerLink="/register">Register here</a></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .card {
      border: none;
      border-radius: 15px;
    }
    
    .card-header {
      border-radius: 15px 15px 0 0 !important;
    }
    
    .btn-primary {
      border-radius: 25px;
      padding: 10px 20px;
    }
  `]
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      this.isLoading = true;
      const request: AuthRequest = this.loginForm.value;
      
      this.authService.login(request).subscribe({
        next: () => {
          this.toastr.success('Login successful!', 'Welcome back!');
          this.router.navigate(['/products']);
        },
        error: (error) => {
          this.toastr.error('Login failed. Please check your credentials.', 'Error');
          console.error('Login error:', error);
        },
        complete: () => {
          this.isLoading = false;
        }
      });
    }
  }

  isFieldInvalid(fieldName: string): boolean {
    const field = this.loginForm.get(fieldName);
    return field ? field.invalid && (field.dirty || field.touched) : false;
  }
}
