import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  constructor(
    private toastr: ToastrService,
    private router: Router,
    private authService: AuthService
  ) { }

  handleError(error: any, context: string = 'Operation'): void {
    console.error(`Error in ${context}:`, error);
    
    let errorMessage = 'An unexpected error occurred';
    let errorTitle = 'Error';

    if (error.status === 401) {
      errorMessage = 'Your session has expired. Please login again.';
      errorTitle = 'Authentication Required';
      this.authService.logout();
      this.router.navigate(['/login']);
    } else if (error.status === 403) {
      errorMessage = 'You do not have permission to perform this action.';
      errorTitle = 'Access Denied';
    } else if (error.status === 400) {
      errorMessage = error.error?.message || 'Invalid request data';
      errorTitle = 'Bad Request';
    } else if (error.status === 404) {
      errorMessage = 'The requested resource was not found.';
      errorTitle = 'Not Found';
    } else if (error.status === 409) {
      errorMessage = error.error?.message || 'Resource already exists.';
      errorTitle = 'Conflict';
    } else if (error.status === 422) {
      errorMessage = error.error?.message || 'Validation failed.';
      errorTitle = 'Validation Error';
    } else if (error.status === 500) {
      errorMessage = 'Server error. Please try again later.';
      errorTitle = 'Server Error';
    } else if (error.status === 0) {
      errorMessage = 'Network error. Please check your connection.';
      errorTitle = 'Connection Error';
    } else if (error.error?.message) {
      errorMessage = error.error.message;
    }

    this.toastr.error(errorMessage, errorTitle);
  }

  handleSuccess(message: string, title: string = 'Success'): void {
    this.toastr.success(message, title);
  }

  handleWarning(message: string, title: string = 'Warning'): void {
    this.toastr.warning(message, title);
  }

  handleInfo(message: string, title: string = 'Info'): void {
    this.toastr.info(message, title);
  }
}






