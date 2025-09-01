import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../shared/services/auth.service';
import { UserCreateDto } from '../../shared/models/user.model';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  template: `
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-4">
        <div class="card shadow">
          <div class="card-header bg-success text-white text-center">
            <h4 class="mb-0">Register</h4>
          </div>
          <div class="card-body">
            <form [formGroup]="registerForm" (ngSubmit)="onSubmit()">
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
                  Username must be at least 3 characters.
                </div>
              </div>
              
              <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  formControlName="email"
                  [class.is-invalid]="isFieldInvalid('email')"
                >
                <div class="invalid-feedback" *ngIf="isFieldInvalid('email')">
                  Please enter a valid email address.
                </div>
              </div>
              
              <div class="mb-3">
                <label for="fullName" class="form-label">Full Name</label>
                <input
                  type="text"
                  class="form-control"
                  id="fullName"
                  formControlName="fullName"
                  [class.is-invalid]="isFieldInvalid('fullName')"
                >
                <div class="invalid-feedback" *ngIf="isFieldInvalid('fullName')">
                  Full name is required.
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
                  Password must be at least 6 characters.
                </div>
              </div>
              
              <div class="mb-3">
                <label for="address" class="form-label">Address (Optional)</label>
                <textarea
                  class="form-control"
                  id="address"
                  rows="3"
                  formControlName="address"
                ></textarea>
              </div>
              
              <div class="mb-3">
                <label for="phone" class="form-label">Phone (Optional)</label>
                <input
                  type="tel"
                  class="form-control"
                  id="phone"
                  formControlName="phone"
                >
              </div>
              
              <div class="d-grid">
                <button type="submit" class="btn btn-success" [disabled]="registerForm.invalid || isLoading">
                  <span *ngIf="isLoading" class="spinner-border spinner-border-sm me-2"></span>
                  {{ isLoading ? 'Creating Account...' : 'Register' }}
                </button>
              </div>
            </form>
            
            <div class="text-center mt-3">
              <p class="mb-0">Already have an account? <a routerLink="/login">Login here</a></p>
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
    
    .btn-success {
      border-radius: 25px;
      padding: 10px 20px;
    }
  `]
})
export class RegisterComponent {
  registerForm: FormGroup;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      fullName: ['', [Validators.required]],
      address: [''],
      phone: ['']
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.isLoading = true;
      const userData: UserCreateDto = this.registerForm.value;
      
      this.authService.register(userData).subscribe({
        next: () => {
          this.toastr.success('Registration successful! Please login.', 'Account Created');
          this.router.navigate(['/login']);
        },
        error: (error) => {
          this.toastr.error('Registration failed. Please try again.', 'Error');
          console.error('Registration error:', error);
        },
        complete: () => {
          this.isLoading = false;
        }
      });
    }
  }

  isFieldInvalid(fieldName: string): boolean {
    const field = this.registerForm.get(fieldName);
    return field ? field.invalid && (field.dirty || field.touched) : false;
  }
}
