import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-image',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="product-image-wrapper">
      <img 
        [src]="imageUrl" 
        [alt]="altText" 
        class="product-image"
        (error)="onImageError($event)"
        (load)="onImageLoad($event)">
      <div *ngIf="isLoading" class="image-loading">
        <div class="spinner-border spinner-border-sm text-primary" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .product-image-wrapper {
      position: relative;
      width: 100%;
      height: 200px;
      overflow: hidden;
      background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
    }
    
    .product-image {
      width: 100%;
      height: 100%;
      object-fit: cover;
      transition: transform 0.3s;
    }
    
    .product-image:not([src]), .product-image[src=""] {
      background: linear-gradient(135deg, #e9ecef 0%, #f8f9fa 100%);
    }
    
    .image-loading {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
    
    .spinner-border-sm {
      width: 1.5rem;
      height: 1.5rem;
    }
  `]
})
export class ProductImageComponent {
  @Input() imageUrl: string = '';
  @Input() altText: string = 'Product Image';
  @Input() category: string = '';
  
  isLoading = true;
  currentFallbackIndex = 0;

  // Category-specific fallback images
  private getFallbackImages(): string[] {
    const categoryLower = this.category.toLowerCase();
    
    if (categoryLower.includes('electronics') || categoryLower.includes('laptop') || categoryLower.includes('smartphone') || categoryLower.includes('headphones')) {
      return [
        'https://via.placeholder.com/400x300/007bff/ffffff?text=Electronics',
        'https://via.placeholder.com/400x300/28a745/ffffff?text=Electronics',
        'https://via.placeholder.com/400x300/dc3545/ffffff?text=Electronics',
        'https://via.placeholder.com/400x300/fd7e14/ffffff?text=Electronics'
      ];
    } else if (categoryLower.includes('clothing') || categoryLower.includes('fashion') || categoryLower.includes('shirt') || categoryLower.includes('jeans')) {
      return [
        'https://via.placeholder.com/400x300/ffc107/000000?text=Clothing',
        'https://via.placeholder.com/400x300/20c997/ffffff?text=Clothing',
        'https://via.placeholder.com/400x300/17a2b8/ffffff?text=Clothing',
        'https://via.placeholder.com/400x300/6f42c1/ffffff?text=Clothing'
      ];
    } else if (categoryLower.includes('home') || categoryLower.includes('kitchen') || categoryLower.includes('mug')) {
      return [
        'https://via.placeholder.com/400x300/6f42c1/ffffff?text=Home+%26+Kitchen',
        'https://via.placeholder.com/400x300/17a2b8/ffffff?text=Home+%26+Kitchen',
        'https://via.placeholder.com/400x300/20c997/ffffff?text=Home+%26+Kitchen'
      ];
    } else {
      // Generic fallbacks
      return [
        'https://via.placeholder.com/400x300/007bff/ffffff?text=Product',
        'https://via.placeholder.com/400x300/28a745/ffffff?text=Product',
        'https://via.placeholder.com/400x300/dc3545/ffffff?text=Product',
        'https://via.placeholder.com/400x300/f8f9fa/6c757d?text=Product+Image',
        'https://via.placeholder.com/400x300/e9ecef/495057?text=Product+Image'
      ];
    }
  }

  onImageError(event: any): void {
    this.isLoading = true;
    const fallbackImages = this.getFallbackImages();
    if (this.currentFallbackIndex < fallbackImages.length) {
      event.target.src = fallbackImages[this.currentFallbackIndex];
      this.currentFallbackIndex++;
    } else {
             // If all fallbacks fail, show a placeholder
       event.target.style.display = 'none';
       event.target.parentElement.innerHTML = `
         <div class="placeholder-image d-flex flex-column align-items-center justify-content-center h-100">
           <div class="placeholder-icon mb-2">
             <svg width="48" height="48" viewBox="0 0 24 24" fill="currentColor" class="text-muted">
               <path d="M21 19V5c0-1.1-.9-2-2-2H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2zM8.5 13.5l2.5 3.01L14.5 12l4.5 6H5l3.5-4.5z"/>
             </svg>
           </div>
           <p class="text-muted mb-0 small">Image not available</p>
         </div>
       `;
    }
  }

  onImageLoad(event: any): void {
    this.isLoading = false;
  }
}
