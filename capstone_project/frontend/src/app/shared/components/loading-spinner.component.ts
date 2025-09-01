import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-loading-spinner',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="loading-container" [class.overlay]="overlay">
      <div class="spinner-container">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
        <p *ngIf="message" class="mt-3 text-muted">{{ message }}</p>
      </div>
    </div>
  `,
  styles: [`
    .loading-container {
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 2rem;
      background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
      border-radius: 8px;
      margin: 1rem 0;
    }
    
    .loading-container.overlay {
      position: fixed;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background-color: rgba(248, 249, 250, 0.9);
      z-index: 9999;
      padding: 0;
    }
    
    .spinner-container {
      text-align: center;
    }
    
    .spinner-border {
      width: 3rem;
      height: 3rem;
      color: #007bff;
    }
    
    p {
      color: #6c757d;
      font-weight: 500;
    }
  `]
})
export class LoadingSpinnerComponent {
  @Input() message: string = 'Loading...';
  @Input() overlay: boolean = false;
}
